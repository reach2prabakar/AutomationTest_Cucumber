package com.client.helpers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class AppHelpers {

    private static final Logger logger = LogManager.getLogger(AppHelpers.class);
    private final WebDriver driver;

    public AppHelpers(WebDriver driver){
        this.driver = driver;
    }

    public ExpectedCondition<Boolean> jsLoad(){
        return driver -> ((JavascriptExecutor) driver)
                   .executeScript("return document.readyState").toString().equals("complete");
    }

    private ExpectedCondition<Boolean> angularLoad() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return (Boolean) ((JavascriptExecutor) driver).executeScript(
                            "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
                } catch (JavascriptException e) {
                    return true;
                }
            }
        };
    }

    public void waitForJSandJQueryToLoad() {
        ExpectedCondition<Boolean> jsLoad = jsLoad();
        ExpectedCondition<Boolean> angularLoad = angularLoad();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.and(angularLoad, jsLoad));
    }

    public void clickOnElement(WebElement webElement){
        webElement.click();
        waitForJSandJQueryToLoad();
    }

    public void hoerOnElement(WebElement webElement){
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    public void enterTextOnElement(WebElement webElement,String text){
        logger.info("Text entered on "+webElement +" is"+text);
        webElement.clear();
        webElement.click();
        webElement.sendKeys(text);
        waitForJSandJQueryToLoad();
    }

    public int isElementPresentOnList(List<WebElement> webElementList){
        int position =0;
        if(webElementList.size()>0){
            for (WebElement element : webElementList){
                if(element.isDisplayed()){
                    position++;
                }else{
                    return  position;
                }
            }
        }else{
            logger.error("there is no element in the list");
            assertThat("Element list size should be greater than 0",false);
        }
        if(webElementList.size()==position){
            return 0;
        }
        return position;
    }

    public int isTextPresentOnList(List<WebElement> webElementList,String text){
        int position = 0;
        if(webElementList.size()>0){
            for (WebElement element : webElementList){
                if(element.getText().contains(text)){
                    return position;
                }
                position++;
            }
            return position;
        }else{
            logger.error("there is no element in the list");
        }
        return -1;
    }

    public String getTextOnListPosition(List<WebElement> webElementList,int var1){
        int var2=0;
        if(webElementList.size()<var1){
            return "List size is smaller to get the text";
        }
        if(webElementList.size()>0) {
            for (WebElement webElement : webElementList) {
                if (var1 == var2) {
                    return webElement.getText();
                }
                var2++;
            }
        }else{
            logger.error("there is no element in the list");
        }
        return "NA";
    }

    public void selectFromDDText(WebElement element,String text){
        boolean uiTag = false;
        try{
            Select select = new Select(element);
            select.selectByVisibleText(text);
        }catch (UnexpectedTagNameException e){
            uiTag = true;
        }
        if(uiTag){
            List<WebElement> dropdownList =  element.findElements(By.tagName("li"));
            dropdownList.stream()
                    .filter(items -> items.getText().toLowerCase().equals(text.toLowerCase())).findFirst().get().click();
        }
    }

    public void selectFromRadioList(List<WebElement> webElements,String text){
            webElements.stream()
                    .filter(items -> items.getText().toLowerCase().contains(text.toLowerCase())).findFirst().get().click();
    }

    public void switchToIframe(WebElement element){
        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void switchToDefault(){
        driver.switchTo().defaultContent();
    }

    public void pageRefresh(){
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
}
