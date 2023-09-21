import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import page_object_model.MainPageModel;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class FaqSectionTest {
    protected WebDriver driver;

    // Номер строки в разделе Faq
    private final int stringFaqNumber;

    // Ожидаемый вопрос в Faq
    private final String question;

    //Ожидаемый ответ в Faq
    private final String answer;

    // Адрес страницы
    String url = "https://qa-scooter.praktikum-services.ru/";

    // Конструктор для теста
    public FaqSectionTest(int stringFaqNumber, String question, String answer) {
        this.stringFaqNumber = stringFaqNumber;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getFaqData() {
        return new Object[][] {
                // Ожидаемые результаты в секции "О Важном"
                { 0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { 1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { 2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                { 3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                { 4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                { 5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                { 6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                { 7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void testFaqSection () {

        // Для браузера FireFox
        /*System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\bin\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();*/

        // Для браузера Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        // Переход на страницу
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        MainPageModel mainPage = new MainPageModel(driver);
        // Скроллинг до нужного элемента на странице
        mainPage.scrollToFaqSection();
        //Ожидание загрузки раздела Faq
        mainPage.waitForLoadFaqSection();
        // Клик по строчке с вопросом в разделе Faq
        driver.findElement(By.id("accordion__heading-" + stringFaqNumber)).click();

        // Проверка появления элемента с ответом
        Assert.assertTrue(driver.findElement(By.id("accordion__panel-" + stringFaqNumber)).isDisplayed());
        // Проверка ожидаемого текста вопроса
        Assert.assertEquals(question, driver.findElement(By.id("accordion__heading-" + stringFaqNumber)).getText());
        // Проверка ожидаемого текста ответа
        Assert.assertEquals(answer, driver.findElement(By.id("accordion__panel-" + stringFaqNumber)).getText());
    }
    @AfterMethod
    // Метод завершения сеанса браузера
    public void tearDown() {
        driver.quit();
    }

}
