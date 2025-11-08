package com.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;

import java.net.URL;
import java.time.Duration;

public class AppTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        // URL of the Selenium Grid Hub (container name from docker-compose.yml)
        URL gridUrl = new URL("http://selenium-hub:4444/wd/hub");

        // Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox");

        driver = new RemoteWebDriver(gridUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testOpenGoogle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        Assertions.assertTrue(title.contains("Google"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
