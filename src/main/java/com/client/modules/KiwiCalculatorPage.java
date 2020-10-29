package com.client.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class KiwiCalculatorPage extends LandingHomePage {

    @FindBy(xpath = "//section//a[@href='/kiwisaver/calculators/kiwisaver-calculator/']")
    public WebElement btnkiwiCalculator;

    public KiwiCalculatorPage(WebDriver driver){
        super(driver);
        waitForPage();
    }

    public void waitForPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(btnkiwiCalculator));
        assertThat("Kiwi Calculator page is not loaded",driver.getTitle(),
                equalTo("KiwiSaver calculator - Westpac NZ")
        );
    }
}
