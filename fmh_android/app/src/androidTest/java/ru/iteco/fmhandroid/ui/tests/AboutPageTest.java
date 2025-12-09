package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.DataHelper.logInCheck;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitFor;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.NavBar;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        logInCheck();
        waitFor(1000);
    }

    NavBar navBar = new NavBar();
    AboutPage aboutPage = new AboutPage();

    @Test
    @DisplayName("Проверка отображения страницы О приложении")
    public void aboutPageLoadedTest() {
        navBar.clickMainMenuButton();
        navBar.goToAboutPage();
        aboutPage.waitingPageToLoad();
        aboutPage.checkPageLoaded();
    }
}
