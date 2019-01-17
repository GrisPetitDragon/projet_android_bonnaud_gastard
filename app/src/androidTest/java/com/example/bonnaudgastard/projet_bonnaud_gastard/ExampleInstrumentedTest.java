package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.web.webdriver.Locator;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.findElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.getText;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.bonnaudgastard.projet_bonnaud_gastard", appContext.getPackageName());
    }

    @Test
    public void URLisCorrect(){
        onWebView().withElement(findElement(Locator.ID, "webview"))
            .check(webMatches(getText(), containsString("Big Buck Bunny")));
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
