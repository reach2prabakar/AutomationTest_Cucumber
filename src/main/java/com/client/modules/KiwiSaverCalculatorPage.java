package com.client.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KiwiSaverCalculatorPage extends LandingHomePage {

    @FindBy(xpath = "//h1[text()='KiwiSaver Retirement Calculator']")
    public WebElement txtKiwiSaverRetirementCal;

    @FindBy(xpath ="//div[@id='calculator-embed']/iframe")
    public WebElement frmCalFields;

    @FindAll(
        @FindBy(xpath = "//div[@class='field-group-set']/div[@class='field-group-set-frame']/div")
    )
    public List<WebElement> lblCalculatorFields;

    @FindAll(
         @FindBy(xpath = "//div[@class='field-group-set']/div[@class='field-group-set-frame']/div//i[@class='icon']")
    )
    public List<WebElement> icnCalculatorField;

    @FindBy(xpath = "//div[@class='field-message message-info ng-binding']")
    public WebElement txtFieldInformation;

    @FindBy(xpath = "//div[contains(@model,'CurrentAge')]//input[@ng-model='displayModel']")
    public WebElement txtCurrentAge;

    @FindBy(xpath ="//div[contains(@ng-model,'EmploymentStatus')]//i/parent::div")
    public WebElement ddEmpStatus;

    @FindBy(xpath = "//div[contains(@ng-model,'EmploymentStatus')]//ul")
    public WebElement ddOptionEmpStatus;

    @FindBy(xpath = "//div[contains(@model,'AnnualIncome')]//input[@ng-model='displayModel']")
    public WebElement txtSalary;

    @FindAll(
        @FindBy(xpath = "//div[contains(@ng-model,'KiwiSaverMemberContribution')]//div[contains(@class,'radio-control')]")
    )
    public List<WebElement> radKiwiSaver;

    @FindBy(xpath = "//div[contains(@model,'KiwiSaverBalance')]//input[@ng-model='displayModel']")
    public WebElement txtCurrentBalance;

    @FindBy(xpath = "//div[contains(@model,'VoluntaryContributions')]//input[@ng-model='displayModel']")
    public WebElement txtVolContribution;

    @FindBy(xpath ="//div[contains(@ng-model,'period')]//i/parent::div")
    public WebElement ddFrequency;

    @FindBy(xpath = "//div[contains(@ng-model,'period')]//ul")
    public WebElement ddOptionFrequency;

    @FindAll(
            @FindBy(xpath = "//div[contains(@ng-model,'RiskProfile')]//div[contains(@class,'radio-control')]")
    )
    public List<WebElement> radRiskProfile;

    @FindBy(xpath = "//div[contains(@model,'SavingsGoal')]//input[@ng-model='displayModel']")
    public WebElement txtSavingGoal;

    @FindBy(xpath = "//span[text()='View your KiwiSaver retirement projections']")
    public WebElement btnViewProjection;

    @FindBy(xpath = "//span[contains(@class,'result-currency')]")
    public WebElement lblProjectionValue;

    public KiwiSaverCalculatorPage(WebDriver driver){
        super(driver);
        waitForPage();
    }

    public void waitForPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(txtKiwiSaverRetirementCal));
        assertThat("KiwiSaver Retirement Calculator page is not loaded",driver.getTitle(),
                equalTo("KiwiSaver Retirement Calculator - Westpac NZ")
        );
    }

    public String validateFieldInformation(int var1){
        int var3 = 0;
        for (WebElement element :icnCalculatorField){
            if(var1==var3){
                element.click();
                return txtFieldInformation.getText();
            }
            var3++;
        }
        return "";
    }

    public void employedRetirementCalc(Map<String, String> dataMap){
        appHelpers.pageRefresh();
        appHelpers.switchToIframe(frmCalFields);
        appHelpers.enterTextOnElement(txtCurrentAge,dataMap.get("currentAge"));
        appHelpers.clickOnElement(ddEmpStatus);
        appHelpers.selectFromDDText(ddOptionEmpStatus,dataMap.get("employment"));
        appHelpers.enterTextOnElement(txtSalary,dataMap.get("salary"));
        appHelpers.selectFromRadioList(radKiwiSaver,dataMap.get("kiwiSaver"));
        appHelpers.selectFromRadioList(radRiskProfile,dataMap.get("riskProfile"));
        appHelpers.clickOnElement(btnViewProjection);
        assertThat("user is not able to view his projected retirement balance",lblProjectionValue.getText().replaceAll("\\n",""),
                equalTo(dataMap.get("projection")));
        appHelpers.switchToDefault();
    }

    public void noEmployRetirementCalc(Map<String, String> dataMap){
        appHelpers.pageRefresh();
        appHelpers.switchToIframe(frmCalFields);
        appHelpers.enterTextOnElement(txtCurrentAge,dataMap.get("currentAge"));
        appHelpers.clickOnElement(ddEmpStatus);
        appHelpers.selectFromDDText(ddOptionEmpStatus,dataMap.get("employment"));
        appHelpers.enterTextOnElement(txtCurrentBalance,dataMap.get("kiwiBalance"));
        appHelpers.enterTextOnElement(txtVolContribution,dataMap.get("volContribution"));
        appHelpers.clickOnElement(ddFrequency);
        appHelpers.selectFromDDText(ddOptionFrequency,dataMap.get("frequent"));
        appHelpers.selectFromRadioList(radRiskProfile,dataMap.get("riskProfile"));
        appHelpers.enterTextOnElement(txtSavingGoal,dataMap.get("savingGoal"));
        appHelpers.clickOnElement(btnViewProjection);
        assertThat("user is not able to view his projected retirement balance",lblProjectionValue.getText().replaceAll("\\n",""),
                equalTo(dataMap.get("projection")));
        appHelpers.switchToDefault();
    }


}
