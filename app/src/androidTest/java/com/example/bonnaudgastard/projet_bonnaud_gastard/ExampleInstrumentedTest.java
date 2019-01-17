package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.web.assertion.WebViewAssertions;
import androidx.test.espresso.web.sugar.Web;
import androidx.test.espresso.web.webdriver.DriverAtoms;
import androidx.test.espresso.web.webdriver.Locator;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.bonnaudgastard.projet_bonnaud_gastard", appContext.getPackageName());
    }

    @Test
    public void URLisCorrect(){
        Web.onWebView().withElement(DriverAtoms.findElement(Locator.ID, "webview"))
            .check(WebViewAssertions.webMatches(DriverAtoms.getText(), CoreMatchers.containsString("Big Buck Bunny")));
    }

    @Test
    public void buttonsCreated(){

    }

    @Test
    public void videoCreated(){

    }

    @Test
    public void buttonsWorking(){

    }
}
