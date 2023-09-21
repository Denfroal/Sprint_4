package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageModel {
    private WebDriver driver;

    // Локатор для поля имя
    public By userNameField = By.xpath(".//input[@placeholder = '* Имя']");

    // Локатор для поля фамилии
    public By userSurnameField = By.xpath(".//input[@placeholder = '* Фамилия']");

    // Локатор для поля адрес
    public By addressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");

    // Локатор для поля станция метро
    public By metroStantionField = By.className("select-search");

    // Локатор для кнопки "Далее"
    public By buttonOrderNext = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор для поля "Когда привезти самокат"
    public By dateOrderField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");

    // Локатор для поля "Срок аренды"
    public By rentalPeriodField = By.className("Dropdown-placeholder");

    // Локатор для поля "Цвет самоката"
    public By samokatBlackColourField = By.xpath(".//input[@id = 'black']");

    // Локатор для комментария
    public By commentField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");

    // Локатор для поля номер телефона
    public By telephoneNumberField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");

    // Локатор для нижней кнопки "Заказать"
    public By buttonOrderMiddle = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text () = 'Заказать']");

    // Локатор для кнопки подтверждения заказа "Да"
    public By buttonYesOrder = By.xpath(".//div[@class = 'Order_Buttons__1xGrp' ]/button[text()='Да']");

    // Локатор для сообщения об успешном создании заказа
    public By madeOrder = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']");

    public OrderPageModel(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonOrderMiddle() {
        driver.findElement(buttonOrderMiddle).click();
    }

    public void clickButtonYesOrder() {
        driver.findElement(buttonYesOrder).click();
    }

    public void clickButtonOrderNext() {
        driver.findElement(buttonOrderNext).click();
    }

}
