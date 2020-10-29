package com.client.modules;

import com.client.processor.WestpacTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingHomePage extends WestpacTest{

    public WebDriver driver;

    @FindAll(
            @FindBy(xpath = "//li[contains(@class,'sw-header-menu-item')]/a")
    )
    public List<WebElement> lstLstHeaderMenuItem;

    @FindAll(
            @FindBy(xpath = "//li[contains(@id,'ubermenu-section')]/a")
    )
    public List<WebElement> lstLstUberMenuItem;

    @FindAll(
            @FindBy(xpath = "//li[contains(@class,'sw-ubermenu-column-item')]/a")
    )
    public List<WebElement> lstSubMenuItem;

    public LandingHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement selectSubMenu(String subMenuName){
        return lstSubMenuItem
                .stream()
                .filter(items -> items.getText().toLowerCase().equals(subMenuName.toLowerCase()))
                .findFirst().get();
    }

    public WebElement selectHeaderMenu(String headerMenuName){
        return lstLstHeaderMenuItem
                .stream()
                .filter(items -> items.getText().toLowerCase().equals(headerMenuName.toLowerCase()))
                .findFirst().get();
    }

    public WebElement selectUberMenu(String uberMenuName){
        return lstLstUberMenuItem
                .stream()
                .filter(items -> items.getText().toLowerCase().equals(uberMenuName.toLowerCase()))
                .findFirst().get();
    }
}
