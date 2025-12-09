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
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    NavBar navBar = new NavBar();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    int itemNumber = 1;

    @Before
    public void setUp() {
        logInCheck();
       waitFor(1000);
        mainPage.checkPageLoaded();
        tapButton(MainPage.allNewsButton);
        newsPage.waitingPageToLoad();
    }

    @Test
    @DisplayName("Проверка отображения страницы Новости")
    public void newsPageOpenedTest() {
        newsPage.checkPageLoaded();
    }

    @Test
    @DisplayName("Переход на главную страницу через меню")
    public void gotoMainFromMenu() {
        tapButton(NavBar.mainMenuButton);
        tapButton(NavBar.mainMenuElementMain);
        MainPage.waitingPageToLoad();
        MainPage.checkPageLoaded();
    }

    @Test
    @DisplayName("Переход на  страницу О программе через меню")
    public void gotoAboutFromMenu() {
        tapButton(NavBar.mainMenuButton);
        tapButton(NavBar.mainMenuElementAbout);
        AboutPage.waitingPageToLoad();
        AboutPage.checkPageLoaded();
        Espresso.pressBack();
    }

//    @Test
//    public void sortingNewsTest() { //в разработке - новости не отображаются
//        tapButton(NewsPage.sortingNewsButton);
//    }
//
//    @Test
//    public void filerNewsByCategoryTest() {
//        tapButton(NewsPage.filterNewsButton);
//        onView(withId(R.id.filter_news_title_text_view)).check(matches(isDisplayed()));
//
//        Espresso.pressBack();
//    }

}
