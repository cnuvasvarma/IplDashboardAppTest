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
public class TeamPageTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\vas\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaipldashboard.ccbp.tech/");
    }

    @AfterMethod
    public void shutDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testTheTeamPage(){
        String expectedCskUrl = "https://qaipldashboard.ccbp.tech/team-matches/CSK";
        WebElement cskEl = driver.findElement(By.cssSelector("ul.teams-list>a:nth-of-type(4)"));
        cskEl.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedCskUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedCskUrl,currentUrl,"URLs do not match");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.responsive-container")));
        Boolean teamBanner = driver.findElement(By.cssSelector("img[alt='team banner']")).isDisplayed();

        Assert.assertEquals(teamBanner,"Team image is not displayed");
    }

    @Test(priority = 2)
    public void verifyLatestMatch() {

        WebElement cskEl = driver.findElement(By.cssSelector("ul.teams-list>a:nth-of-type(4)"));
        cskEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.responsive-container")));


        String expectedText = "Royal Challengers Bangalore";
        WebElement latestMatch = driver.findElement(By.cssSelector("p.latest-match-team-name"));
        Assert.assertEquals(expectedText,latestMatch.getText(),"Latest match team name does not match");
    }

    @Test(priority = 3)
    public void countOfRecentMatches(){
        WebElement cskEl = driver.findElement(By.cssSelector("ul.teams-list>a:nth-of-type(4)"));
        cskEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.responsive-container")));

        List<WebElement> matchesPlayed = driver.findElements(By.cssSelector("ul.recent-matches-list>li"));
        int count = matchesPlayed.size();
        //int count = Integer.parseInt(countOfMatches);
        Assert.assertEquals(count,13,"Count does not match");

    }



}
