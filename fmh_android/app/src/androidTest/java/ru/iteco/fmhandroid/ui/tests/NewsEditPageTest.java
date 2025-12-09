package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.Data.category1;
import static ru.iteco.fmhandroid.ui.data.Data.category2;
import static ru.iteco.fmhandroid.ui.data.Data.descriptionNewsCreated;
import static ru.iteco.fmhandroid.ui.data.Data.descriptionNewsUpdated;
import static ru.iteco.fmhandroid.ui.data.Data.emptyField;
import static ru.iteco.fmhandroid.ui.data.Data.specSymbolString;
import static ru.iteco.fmhandroid.ui.data.Data.sqlInject;
import static ru.iteco.fmhandroid.ui.data.Data.stringLenght120;
import static ru.iteco.fmhandroid.ui.data.Data.stringLenght520;
import static ru.iteco.fmhandroid.ui.data.Data.titleNewsCreated;
import static ru.iteco.fmhandroid.ui.data.Data.titleNewsUpdated;
import static ru.iteco.fmhandroid.ui.data.Data.toastMsgEmptyField;
import static ru.iteco.fmhandroid.ui.data.Data.toastMsgLongDescription;
import static ru.iteco.fmhandroid.ui.data.Data.toastMsgSpecialCharacters;
import static ru.iteco.fmhandroid.ui.data.Data.toastMsgTitleToLong;
import static ru.iteco.fmhandroid.ui.data.Data.xssInject;
import static ru.iteco.fmhandroid.ui.data.DataHelper.generateRandomFourDigitString;
import static ru.iteco.fmhandroid.ui.data.DataHelper.logInCheck;
import static ru.iteco.fmhandroid.ui.data.DataHelper.okButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapOkButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitFor;
import static ru.iteco.fmhandroid.ui.page.NewsPage.goToNewsControlPanelPage;
import static ru.iteco.fmhandroid.ui.page.NewsPage.goToNewsEditPage;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.NewsControlPanelPage;
import ru.iteco.fmhandroid.ui.page.NewsEditPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsEditPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private View decorView;

    @Before
    public void setUp() {
        activityRule.getScenario().onActivity(
                activity -> decorView = activity.getWindow().getDecorView()
        );
        logInCheck();
        waitFor(1200);
    }

    NewsEditPage newsEditPage = new NewsEditPage();
    NewsControlPanelPage newsControlPanelPage = new NewsControlPanelPage();


    @Test
    @DisplayName("Создать новость")
    public void createNewsTest() {
        String testTitle = titleNewsCreated + generateRandomFourDigitString();
        try {
            goToNewsEditPage();
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.creatingNews(category1, testTitle, descriptionNewsCreated);
            waitFor(1000);
            step("Проверка создания новости: " + testTitle + " создана и отображается в списке");
            newsControlPanelPage.scrollAndClickToNewsWithTittle(testTitle);
            ViewInteraction result = newsControlPanelPage.findNewsByText(testTitle);
            result.check(matches(isDisplayed()));
        } finally {
            newsControlPanelPage.deleteNews(testTitle);
        }
    }

    @Test
    @DisplayName("Редактирование существующей новости")
    public void editingNewsTest() {
        String testTitle = titleNewsUpdated + generateRandomFourDigitString();
        goToNewsEditPage();
        newsEditPage.creatingNews(category1, titleNewsCreated, descriptionNewsCreated);
        try {
//            goToNewsControlPanelPage();
            newsControlPanelPage.scrollAndClickToNewsWithTittle(titleNewsCreated);
            newsControlPanelPage.tapEditingNewsButton(titleNewsCreated);
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.editCategory(category2);
            newsEditPage.editTitle(testTitle);
            newsEditPage.editDate();
            newsEditPage.editTime();
            newsEditPage.editDescription(descriptionNewsUpdated);
            waitFor(200);
            newsEditPage.tapToSaveButton();
            waitFor(500);
            step("Новость с новым названием '" + testTitle + "' создана");
            newsControlPanelPage.scrollAndClickToNewsWithTittle(testTitle);
            ViewInteraction result = newsControlPanelPage.findNewsByText(testTitle);
            result.check(matches(isDisplayed()));
        } finally {
            newsControlPanelPage.deleteNews(testTitle);
        }

    }

    @Test
    @DisplayName("Удаление новости")
    public void deleteNewsTest() {
        goToNewsEditPage();
        newsEditPage.creatingNews(category1, titleNewsCreated, descriptionNewsCreated);
        newsControlPanelPage.scrollAndClickToNewsWithTittle(titleNewsCreated);
        newsControlPanelPage.deleteNews(titleNewsCreated);
        waitFor(1000);
        step("Новость '" + titleNewsCreated + "' удалена");
        onView(allOf(withText(titleNewsCreated), isDisplayed())).check(doesNotExist());

    }

    @Test
    @DisplayName("Создание новости с заголовком длиной более 500")
    public void wrongDataCreateNewsTest() {
        try {
            goToNewsEditPage();
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.editCategory(category1);
            newsEditPage.editTitle(stringLenght520);
            newsEditPage.editDate();
            newsEditPage.editTime();
            newsEditPage.editDescription(descriptionNewsCreated);
            newsEditPage.tapToSaveButton();

            step("Сообщение об ошибке: " + toastMsgTitleToLong);
            onView(withText(toastMsgTitleToLong))
                    .inRoot(withDecorView(Matchers.not(decorView)))
                    .check(matches(isDisplayed()));
        } catch (Exception e) {
            e.printStackTrace();
            waitFor(2000);
            try {
                newsControlPanelPage.scrollAndClickToNewsWithTittle(stringLenght520);
                ViewInteraction result = newsControlPanelPage.
                        findNewsByText(stringLenght520);
                result.check(doesNotExist());
                waitFor(2000);
                throw new AssertionError("News is visible when it should not be!");
            } catch (AssertionFailedError q) {
                q.printStackTrace();
                newsControlPanelPage.deleteNews(stringLenght520);
                throw new AssertionError("Toast with message '" + toastMsgTitleToLong + "' does not exist");
            }
        }
    }

    @DisplayName("Сохранение новости с пустым заголовком")
    @Test
    public void emptyTitleCreateNewsTest() {
        goToNewsEditPage();
        newsEditPage.waitingPageToLoad();
        newsEditPage.checkPageLoaded();
        newsEditPage.editCategory(category1);
        newsEditPage.editTitle(emptyField);
        newsEditPage.editDate();
        newsEditPage.editTime();
        newsEditPage.editDescription(descriptionNewsUpdated);
        newsEditPage.tapToSaveButton();

        step("Сообщение об ошибке: " + toastMsgEmptyField);
        onView(withText(toastMsgEmptyField))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @DisplayName("Сохранение новости с спецсимволами в заголовке")
    @Test
    public void specialSymbolInTitleCreateNewsTest() {
        try {
            goToNewsEditPage();
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.editCategory(category1);
            newsEditPage.editTitle(specSymbolString);
            newsEditPage.editDate();
            newsEditPage.editTime();
            newsEditPage.editDescription(descriptionNewsUpdated);
            newsEditPage.tapToSaveButton();

            step("Сообщение об ошибке: " + toastMsgSpecialCharacters);
            onView(withText(toastMsgSpecialCharacters))
                    .inRoot(withDecorView(Matchers.not(decorView)))
                    .check(matches(isDisplayed()));
        } catch (Exception e) {
            e.printStackTrace();
            waitFor(2000);
            try {
                ViewInteraction result = newsControlPanelPage.
                        findNewsByText(specSymbolString);
                result.check(doesNotExist());
                waitFor(2000);
                throw new AssertionError("News is visible when it should not be!");
            } catch (AssertionFailedError q) {
                q.printStackTrace();
                newsControlPanelPage.deleteNews(specSymbolString);
                throw new AssertionError("Toast with message '" + toastMsgSpecialCharacters + "' does not exist");
            }
        }
    }


    @DisplayName("Сохранение новости с описанием длиной более 500")
    @Test
    public void longDescriptionCreateNewsTest() {
        String testTitle = titleNewsCreated + generateRandomFourDigitString();
        try {
            goToNewsEditPage();
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.editCategory(category1);
            newsEditPage.editTitle(testTitle);
            newsEditPage.editDate();
            newsEditPage.editTime();
            newsEditPage.editDescription(stringLenght520);
            newsEditPage.tapToSaveButton();

            step("Сообщение об ошибке: " + toastMsgLongDescription);
            onView(withText(toastMsgLongDescription))
                    .inRoot(withDecorView(Matchers.not(decorView)))
                    .check(matches(isDisplayed()));
        } catch (Exception e) {
            e.printStackTrace();
            waitFor(2000);
            try {
                newsControlPanelPage.scrollAndClickToNewsWithTittle(testTitle);
                ViewInteraction result = newsControlPanelPage.
                        findNewsByText(testTitle);
                result.check(doesNotExist());
                waitFor(2000);
                throw new AssertionError("News is visible when it should not be!");
            } catch (AssertionFailedError q) {
                q.printStackTrace();
                newsControlPanelPage.deleteNews(testTitle);
                throw new AssertionError("Toast with message '" + toastMsgLongDescription + "' does not exist");
            }
        }
    }


    @DisplayName("Сохранение новости с SQL-инъекцией в заголовке ")
    @Test
    public void SqlInjectionInTitleCreateNewsTest() {
        try {
            goToNewsEditPage();
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.editCategory(category1);
            newsEditPage.editTitle(sqlInject);
            newsEditPage.editDate();
            newsEditPage.editTime();
            newsEditPage.editDescription(descriptionNewsUpdated);
            newsEditPage.tapToSaveButton();

            step("SQL-инъекция '" + sqlInject + "' не сработала");
            newsControlPanelPage.scrollAndClickToNewsWithTittle(sqlInject);
            ViewInteraction result = newsControlPanelPage.findNewsByText(sqlInject);
            result.check(matches(isDisplayed()));

        } finally {
            newsControlPanelPage.scrollAndClickToNewsWithTittle(sqlInject);
            newsControlPanelPage.deleteNews(sqlInject);
        }
    }

    @DisplayName("Сохранение новости с SQL-инъекцией в описании")
    @Test
    public void SqlInjectionInDescriptionCreateNewsTest() {
        String testTitle = titleNewsCreated + generateRandomFourDigitString();
        try {
            goToNewsEditPage();
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.editCategory(category1);
            newsEditPage.editTitle(testTitle);
            newsEditPage.editDate();
            newsEditPage.editTime();
            newsEditPage.editDescription(sqlInject);
            newsEditPage.tapToSaveButton();

            step("SQL-инъекция '" + sqlInject + "' не сработала");
            newsControlPanelPage.scrollAndClickToNewsWithTittle(testTitle);
            ViewInteraction result = newsControlPanelPage.findNewsByText(testTitle);
            result.check(matches(isDisplayed()));
        } finally {
            newsControlPanelPage.scrollAndClickToNewsWithTittle(testTitle);
            newsControlPanelPage.deleteNews(testTitle);
        }
    }


    @DisplayName("Сохранение новости с XSS-инъекцией в заголовке")
    @Test
    public void xssInjectionInTitleCreateNewsTest() {
        try {
            goToNewsEditPage();
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.editCategory(category1);
            newsEditPage.editTitle(xssInject);
            newsEditPage.editDate();
            newsEditPage.editTime();
            newsEditPage.editDescription(descriptionNewsUpdated);
            newsEditPage.tapToSaveButton();

            step("XSS-инъекция '" + xssInject + "' не сработала");
            newsControlPanelPage.scrollAndClickToNewsWithTittle(xssInject);
            ViewInteraction result = newsControlPanelPage.findNewsByText(xssInject);
            result.check(matches(isDisplayed()));
        } finally {
            newsControlPanelPage.scrollAndClickToNewsWithTittle(xssInject);
            newsControlPanelPage.deleteNews(xssInject);
        }
    }

    @Test
    @DisplayName("Сохранение новости с XSS-инъекцией в описании")
    public void xssInjectionInDescriptionCreateNewsTest() {
        String testTitle = titleNewsCreated + generateRandomFourDigitString();
        try {
            goToNewsEditPage();
            newsEditPage.waitingPageToLoad();
            newsEditPage.checkPageLoaded();
            newsEditPage.editCategory(category1);
            newsEditPage.editTitle(testTitle);
            newsEditPage.editDate();
            newsEditPage.editTime();
            newsEditPage.editDescription(xssInject);
            newsEditPage.tapToSaveButton();

            step("SQL-инъекция '" + xssInject + "' не срабатала");
            newsControlPanelPage.scrollAndClickToNewsWithTittle(testTitle);
            ViewInteraction result = newsControlPanelPage.findNewsByText(testTitle);
            result.check(matches(isDisplayed()));
        } finally {
            newsControlPanelPage.scrollAndClickToNewsWithTittle(testTitle);
            newsControlPanelPage.deleteNews(testTitle);
        }
    }
}
