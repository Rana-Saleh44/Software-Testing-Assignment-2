import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SeleniumTest {
    private WebDriver webDriver;

    @Before
    public void init(){
        System.setProperty("webdriver.edge.driver", "C:\\Users\\LENOVO\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        webDriver = new EdgeDriver();
        webDriver.navigate().to("https://www.imdb.com/");
        webDriver.manage().window().maximize();
    }

    @Test
    public void testSearch() throws Exception{
        webDriver.findElement(By.id("suggestion-search")).sendKeys("The Shawshank Redemption");
        webDriver.findElement(By.id("suggestion-search-button")).click();
        Thread.sleep(3000);

        WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/ul/li[1]/div[2]/div/a"));
        String actualValue = webElement.getText();

        String expectedValue = "The Shawshank Redemption";
        Assert.assertEquals(expectedValue, actualValue);

        Thread.sleep(3000);
        webDriver.close();
    }

    @Test
    public void testTop_250_Movies() throws  Exception{
        Thread.sleep(3000);
        WebElement menuButton = webDriver.findElement(By.xpath("//*[@id=\"imdbHeader-navDrawerOpen\"]/span"));
        menuButton.click();

        Thread.sleep(1000);
        WebElement top250Movies = webDriver.findElement(By.xpath("//*[@id=\"imdbHeader\"]/div[2]/aside[1]/div/div[2]/div/div[1]/span/div/div/ul/a[2]"));
        top250Movies.click();

        Thread.sleep(1000);
        WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div[3]/section/div/div[2]/div/ul/li[1]/div[2]/div/div/div[1]/a/h3"));
        String actualValue = webElement.getText();

        Thread.sleep(1000);
        String expectedValue = "The Shawshank Redemption";
        //String expectedValue = "1. The Shawshank Redemption";
        //Assert.assertEquals(expectedValue, actualValue);

        assertTrue(actualValue.contains(expectedValue));
        Thread.sleep(5000);
        webDriver.close();
    }

    @Test
    public void testAdvancedSearch() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        WebElement allButton = webDriver.findElement(By.xpath("//*[@id=\"nav-search-form\"]/div[1]/div/label"));
        allButton.click();
        WebElement advancedsearchButton = webDriver.findElement(By.xpath("//*[@id=\"navbar-search-category-select-contents\"]/ul/a/span[2]"));
        advancedsearchButton.click();

        WebElement titletypeButton = webDriver.findElement(By.xpath("//*[@id=\"titleTypeAccordion\"]/div[1]/label"));
        titletypeButton.click();

        WebElement movieButton = webDriver.findElement(By.xpath("//*[@id=\"accordion-item-titleTypeAccordion\"]/div/section/button[1]"));
        movieButton.click();

        WebElement releasedateButton = webDriver.findElement(By.xpath("//*[@id=\"releaseDateAccordion\"]/div[1]/label"));
        releasedateButton.click();
        WebElement fromDateActual = webDriver.findElement(By.xpath("//*[@id=\"accordion-item-releaseDateAccordion\"]/div/div/div[2]/div[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", fromDateActual);
        try {
            fromDateActual.sendKeys("2010");
        } catch (InvalidElementStateException e) {
            js.executeScript("arguments[0].value='2010';", fromDateActual);
        }
        WebElement toDateActual = webDriver.findElement(By.xpath("//*[@id=\"accordion-item-releaseDateAccordion\"]/div/div/div[2]/div[2]"));
        js.executeScript("arguments[0].scrollIntoView(true);", toDateActual);
        try {
            toDateActual.sendKeys("2020");
        } catch (InvalidElementStateException e) {
            js.executeScript("arguments[0].value='2020';", toDateActual);
        }
        Thread.sleep(1000);
        /*String fromDateExpected = "2010";
        String toDateExcepected = "2020";
        Assert.assertEquals(fromDateExpected, fromDateActual);
        Assert.assertEquals(toDateExcepected, toDateActual);*/

        WebElement genreButton = webDriver.findElement(By.xpath("//*[@id=\"genreAccordion\"]/div[1]/label"));
        genreButton.click();
        WebElement actionButton = webDriver.findElement(By.xpath("//*[@id=\"accordion-item-genreAccordion\"]/div/section/button[1]"));
        actionButton.click();

        WebElement seeresultsButton = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[1]/button"));
        seeresultsButton.click();

        /*Thread.sleep(2000);
        WebElement userratingButton = webDriver.findElement(By.xpath("//*[@id=\"adv-srch-sort-by\"]/option[4]"));
        userratingButton.click();
                                                                                                                    // it works
        Thread.sleep(2000);
        WebElement sortorderButton = webDriver.findElement(By.xpath("//*[@id=\"adv-srch-sort-order\"]"));
        sortorderButton.click();*/

        Thread.sleep(5000);
        webDriver.close();
    }
}
