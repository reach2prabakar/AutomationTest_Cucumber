package com.client.modules;

import com.client.library.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class  LandingPage extends LandingHomePage{

    public LandingPage(WebDriver driver){super(driver);}

    public void loadUrl(){
        driver.get(new PropertyReader().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        assertThat("Westpac home page is not loaded",driver.getTitle(),
                equalTo("Bank | Westpac New Zealand - Helping Kiwis with their personal banking"));
    }

}
