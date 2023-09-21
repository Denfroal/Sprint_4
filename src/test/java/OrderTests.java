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
import page_object_model.OrderPageModel;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderTests {
    protected WebDriver driver;

    // Имя
    private final String userName;

    //Фамилия
    private final String userSurName;

    // Адрес
    private final String address;

    // Станция метро
    private final int metroStantion;

    // номер телефона
    private final String telephoneNumber;

    // срок аренды
    private final String rentalPeriod;

    // комментарий
    private final String comment;

    // Адрес страницы
    String url = "https://qa-scooter.praktikum-services.ru/";

    public OrderTests(String userName, String userSurName, String address, int metroStantion, String telephoneNumber, String rentalPeriod, String comment) {
        this.userName = userName;
        this.userSurName = userSurName;
        this.address = address;
        this.metroStantion = metroStantion;
        this.telephoneNumber = telephoneNumber;
        this.rentalPeriod = rentalPeriod;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                // Тестовые данные для заказа
                { "Николай", "Сидоров", "г. Рязань, Касимовское шоссе, д.8", 1, "+79205218512", "сутки", "можно поскорее"},
                { "Фёдор", "Иванов", "г. Москва, ул. Ленина, д.15", 2, "+79105059163", "двое суток", "классный сервис"}
        };
    }

    // Тесты на позитивный сценарий по верхней кнопке заказать
    @Test
    public void testAboveButtonOrder () {

        // Для браузера FireFox
        /*System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\bin\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();*/

        // Для браузера Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        // Переход на страницу
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        MainPageModel mainPageModel = new MainPageModel(driver);
        // Клик по верхней кнопке заказать
        mainPageModel.clickOnButtonOrderAbove();

        // Заполнение полей "Для кого самокат"
        OrderPageModel orderPage = new OrderPageModel(driver);
        driver.findElement(orderPage.userNameField).sendKeys(userName);
        driver.findElement(orderPage.userSurnameField).sendKeys(userSurName);
        driver.findElement(orderPage.addressField).sendKeys(address);
        driver.findElement(orderPage.metroStantionField).click();
        driver.findElement(By.xpath(".//ul[@class='select-search__options']/li[@data-value = '" + metroStantion +"']")).click();
        driver.findElement(orderPage.telephoneNumberField).sendKeys(telephoneNumber);

        // Заполнение полей "Про аренду"
        orderPage.clickButtonOrderNext();
        driver.findElement(orderPage.dateOrderField).click();
        driver.findElement(By.xpath(".//div[@class = 'react-datepicker__day react-datepicker__day--029 react-datepicker__day--weekend']")).click();
        driver.findElement(orderPage.rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-option' and text() = '" + rentalPeriod + "']")).click();
        driver.findElement(orderPage.samokatBlackColourField).click();
        driver.findElement(orderPage.commentField).sendKeys(comment);

        // Подтверждение заказа
        orderPage.clickButtonOrderMiddle();
        orderPage.clickButtonYesOrder();
        String resultText = driver.findElement(orderPage.madeOrder).getText();
        Assert.assertTrue(resultText.contains("Заказ оформлен"));
    }

    // Тесты на позитивный сценарий по нижней кнопке заказать
    @Test
    public void testBelowButtonOrder () {

        System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\bin\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        // Переход на страницу
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        MainPageModel mainPageModel = new MainPageModel(driver);
        // Скролинг до нижней кнопки заказать
        mainPageModel.scrollToButtonOrderBelow();
        // Клик по нижней кнопке заказать
        mainPageModel.clickOnButtonOrderBelow();

        // Заполнение полей "Для кого самокат"
        OrderPageModel orderPage = new OrderPageModel(driver);
        driver.findElement(orderPage.userNameField).sendKeys(userName);
        driver.findElement(orderPage.userSurnameField).sendKeys(userSurName);
        driver.findElement(orderPage.addressField).sendKeys(address);
        driver.findElement(orderPage.metroStantionField).click();
        driver.findElement(By.xpath(".//ul[@class='select-search__options']/li[@data-value = '" + metroStantion + "']")).click();
        driver.findElement(orderPage.telephoneNumberField).sendKeys(telephoneNumber);

        // Заполнение полей "Про аренду"
        orderPage.clickButtonOrderNext();
        driver.findElement(orderPage.dateOrderField).click();
        driver.findElement(By.xpath(".//div[@class = 'react-datepicker__day react-datepicker__day--029 react-datepicker__day--weekend']")).click();
        driver.findElement(orderPage.rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-option' and text() = '" + rentalPeriod + "']")).click();
        driver.findElement(orderPage.samokatBlackColourField).click();
        driver.findElement(orderPage.commentField).sendKeys(comment);

        // Подтверждение заказа
        orderPage.clickButtonOrderMiddle();
        orderPage.clickButtonYesOrder();
        String resultText = driver.findElement(orderPage.madeOrder).getText();
        Assert.assertTrue(resultText.contains("Заказ оформлен"));
    }
    @AfterMethod
    // Метод завершения сеанса браузера
    public void tearDown() {
        driver.quit();
    }
}
