
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Listeners(FitnessAppReport.class)
public class HomePageTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\vas\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaipldashboard.ccbp.tech/");
    }

    @AfterMethod
    public void quite(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testTheHeading(){
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class ='teams-list-container']")));
        WebElement iplLogo = driver.findElement(By.xpath("//img[@class ='ipl-logo']"));
        Assert.assertEquals(true,iplLogo.isDisplayed(),"IPL logo is not displayed");

        String expectedText = "IPL Dashboard";
        WebElement currentHeading = driver.findElement(By.xpath("//h1[@class='ipl-dashboard-heading']"));
        String actualTest = currentHeading.getText();
        Assert.assertEquals(expectedText,actualTest,"Heading text does not match");
    }

    @Test(priority = 2)
    public void testListOfItems() {
        String[] listItems = {"Royal Challengers Bangalore", "Kolkata Knight Riders", "Kings XI Punjab", "Chennai Super Kings", "Rajasthan Royals", "Mumbai Indians", "Sunrisers Hyderabad", "Delhi Capitals"};
        List<WebElement> orderOfList = driver.findElements(By.xpath("//p[@class='team-name']"));
        int i =0;
        for (WebElement team: orderOfList){
            String teamName = team.getText();
            String listTeam = listItems[i];
            i++;
            Assert.assertEquals(teamName,listTeam,"Order and list of Teams do not match");

        }

    }


}
