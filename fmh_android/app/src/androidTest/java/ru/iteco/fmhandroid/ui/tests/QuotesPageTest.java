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
import ru.iteco.fmhandroid.ui.page.NavBar;
import ru.iteco.fmhandroid.ui.page.QuotesPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class QuotesPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        logInCheck();
        waitFor(1000);
    }

//    @After
//    public void exitApp() {
//        logOutCheck();
//    }

    NavBar navBar = new NavBar();
    QuotesPage quotesPage = new QuotesPage();

    @Test
    @DisplayName("Проверка загрузки страницы Ццитаты")

    public void quotesLoadedTest() {
        navBar.goToQuotesPage();
        quotesPage.waitingPageToLoad();
        quotesPage.checkPageLoaded();
    }

    @Test
    @DisplayName("Проверка работы элементов страницы Цитаты")
    public void quotesElementsPageTest() {
        navBar.goToQuotesPage();
        quotesPage.waitingPageToLoad();
        quotesPage.quotesItemOpenClose(2);
        quotesPage.checkDescriptionItem();
    }
}
