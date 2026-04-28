package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebLoginTest {

    @Test
    public void validLoginTest() {

        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/login");

        // Valid login
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Validate success message
        String msg = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(msg.contains("You logged into a secure area!"));

        // Validate logout button
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

        driver.quit();
    }

    @Test
    public void invalidLoginTest() {

        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/login");

        // Invalid login
        driver.findElement(By.id("username")).sendKeys("wrong");
        driver.findElement(By.id("password")).sendKeys("wrong");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Validate error
        String msg = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(msg.contains("Your username is invalid!"));

        driver.quit();
    }
}
