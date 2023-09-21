package page_object_model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class MainPageModel {

    private WebDriver driver;

    public MainPageModel(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для верхней кнопки Заказать
    public By buttonOrderAbove = By.className("Button_Button__ra12g");

    // Локатор для нижней кнопки Заказать
    public By buttonOrderBelow = By.xpath(".//div[@class = 'Home_RoadMap__2tal_']/div[@class = 'Home_FinishButton__1_cWm']/button");

    // Локатор для раздела FAQ
    public By FaqSection = By.cssSelector(".accordion");

    public void clickOnButtonOrderAbove() {
        driver.findElement(buttonOrderAbove).click();
    }

    public void clickOnButtonOrderBelow() {
        driver.findElement(buttonOrderBelow).click();
    }

    public void waitForLoadFaqSection() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(driver.findElement(FaqSection)));
    }

    public void scrollToFaqSection() {
        WebElement elementFaq = driver.findElement(FaqSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementFaq);
    }

    public void scrollToButtonOrderBelow() {
        WebElement elementButtonBelow = driver.findElement(buttonOrderBelow);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementButtonBelow);
    }

}

