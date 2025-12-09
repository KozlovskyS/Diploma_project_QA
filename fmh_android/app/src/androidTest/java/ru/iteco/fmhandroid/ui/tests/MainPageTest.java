package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.data.Data.rightLogin;
import static ru.iteco.fmhandroid.ui.data.Data.rightPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.logInCheck;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitFor;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.LoginPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NavBar;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class MainPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        logInCheck();
        waitFor(1000);
    }

    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверка загрузки главного экрана")
    public void mainPageLoadedTest() {
        mainPage.waitingPageToLoad();
        mainPage.checkPageLoaded();
    }

    @Test
    @DisplayName("Переход на страницу новостей через меню")
    public void gotoNewsPageFromMenu() {
        mainPage.waitingPageToLoad();
        tapButton(NavBar.mainMenuButton);
        tapButton(NavBar.mainMenuElementNews);
        onView(withId(R.id.edit_news_material_button)).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход на страницу О программе через меню")
    public void gotoAboutFromMenu() {
        mainPage.waitingPageToLoad();
        tapButton(NavBar.mainMenuButton);
        tapButton(NavBar.mainMenuElementAbout);
        AboutPage.waitingPageToLoad();
        AboutPage.checkPageLoaded();
        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

    @Test
    @DisplayName("Переход на страницу редактирования новостей по кнопке")
    public void gotoNewsPage() {
        mainPage.waitingPageToLoad();
        tapButton(MainPage.allNewsButton);
        onView(withId(R.id.edit_news_material_button)).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Переход на страницу цитат по кнопке")
    public void gotoQuotesPage() {
        mainPage.waitingPageToLoad();
        tapButton(NavBar.ourMissionButton);
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()));
    }

}