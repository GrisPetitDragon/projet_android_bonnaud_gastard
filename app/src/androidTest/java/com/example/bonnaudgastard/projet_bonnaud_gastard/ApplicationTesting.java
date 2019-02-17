package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.findElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.getText;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.web.webdriver.Locator;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ApplicationTesting {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.bonnaudgastard.projet_bonnaud_gastard", appContext.getPackageName());
    }


    @Test
    public void URLisCorrect(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        onWebView().withElement(findElement(Locator.ID, "webview"))
                .check(webMatches(getText(), containsString("Bunny")));
    }

    /*@Test
    public void buttonsCreated(){

    }

    @Test
    public void videoCreated(){

    }

    @Test
    public void buttonsWorking(){

    }*/
}
