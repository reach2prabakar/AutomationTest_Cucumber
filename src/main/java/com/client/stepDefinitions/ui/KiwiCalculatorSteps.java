package com.client.stepDefinitions.ui;

import com.client.modules.*;
import com.client.processor.WestpacTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class KiwiCalculatorSteps extends WestpacTest {
    private static final Logger logger = LogManager.getLogger(KiwiCalculatorSteps.class);
    LandingPage landingPage = new LandingPage(driver);

    @Given("^user opens Westpac desktop application$")
    public void loadApplication(){
        landingPage.loadUrl();
    }

    @When("^user navigated to the (.*) and (.*) and then to (.*) menu option$")
    public void navigateToMenuOption(String headerName,String uberMenuName,String subMenuName){
        appHelpers.clickOnElement(landingPage.selectHeaderMenu(headerName));
        appHelpers.howerOnElement(landingPage.selectUberMenu(uberMenuName));
        appHelpers.clickOnElement(landingPage.selectSubMenu(subMenuName));
    }

    @And("^she finds the information icon besides all the calculator field$")
    public void calculatorFieldIconValidation(){
        KiwiCalculatorPage kiwiCalculatorPage = new KiwiCalculatorPage(driver);
        appHelpers.clickOnElement(kiwiCalculatorPage.btnkiwiCalculator);
        KiwiSaverCalculatorPage kiwiSaverCalculatorPage = new KiwiSaverCalculatorPage(driver);
        appHelpers.switchToIframe(kiwiSaverCalculatorPage.frmCalFields);
        int position = appHelpers.isElementPresentOnList(kiwiSaverCalculatorPage.icnCalculatorField);
        logger.info("Information Icon validation for the field"+appHelpers.getTextOnListPosition(kiwiSaverCalculatorPage.lblCalculatorFields,position));
        assertThat("Information Icon is not present for the field"+appHelpers.getTextOnListPosition(kiwiSaverCalculatorPage.lblCalculatorFields,position),
                position,
                equalTo(0));
        appHelpers.switchToDefault();
    }

    @Then("^she is able to get a clear information of what needs to be entered in the field$")
    public void calculatorFieldInfo(DataTable dataTable){
        List<Map<String, String>> calcFieldListMap  = dataTable.asMaps();
        KiwiSaverCalculatorPage kiwiSaverCalculatorPage = new KiwiSaverCalculatorPage(driver);
        appHelpers.switchToIframe(kiwiSaverCalculatorPage.frmCalFields);
        for (Map<String, String> dataMap : calcFieldListMap) {
            int elementPresentPosition = appHelpers.isTextPresentOnList(kiwiSaverCalculatorPage.lblCalculatorFields, dataMap.get("calField"));
            String iconMsg = kiwiSaverCalculatorPage.validateFieldInformation(elementPresentPosition);
            logger.info("The information icon for " + dataMap.get("calField") + " does not match");
            assertThat("The information icon for " + dataMap.get("calField") + " does not match",
                    iconMsg.trim(), equalTo(dataMap.get("fieldInformation").trim()));
        }
        appHelpers.switchToDefault();
    }

    @Then("^user is able to calculate his kiwi saver projected balance at retirement$")
    public void calculatorRetirementBalance(DataTable dataTable){
        List<Map<String, String>> calcFieldListMap  = dataTable.asMaps();
        KiwiCalculatorPage kiwiCalculatorPage = new KiwiCalculatorPage(driver);
        appHelpers.clickOnElement(kiwiCalculatorPage.btnkiwiCalculator);
        KiwiSaverCalculatorPage kiwiSaverCalculatorPage = new KiwiSaverCalculatorPage(driver);
        for (Map<String, String> dataMap : calcFieldListMap) {
           switch (dataMap.get("employment")){
               case "Employment":
                   kiwiSaverCalculatorPage.employedRetirementCalc(dataMap);
                   break;
               case "Self-employed":
               case "Not employed":
                   kiwiSaverCalculatorPage.noEmployRetirementCalc(dataMap);
                   break;
               default:
                   break;
           }
        }
    }
}