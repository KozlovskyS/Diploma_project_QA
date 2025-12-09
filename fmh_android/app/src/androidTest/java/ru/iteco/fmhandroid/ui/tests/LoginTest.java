package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.Data.rightLogin;
import static ru.iteco.fmhandroid.ui.data.Data.rightPassword;
import static ru.iteco.fmhandroid.ui.data.Data.sqlInject;
import static ru.iteco.fmhandroid.ui.data.Data.toastMsgCannotBeEmpty;
import static ru.iteco.fmhandroid.ui.data.Data.toastMsgSomethingWentWrong;
import static ru.iteco.fmhandroid.ui.data.Data.wrongLogin;
import static ru.iteco.fmhandroid.ui.data.Data.xssInject;
import static ru.iteco.fmhandroid.ui.data.DataHelper.logOutCheck;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitFor;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.LoginPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private View decorView; //переменная для определения всплывающих сообщений

    @Before
    public void setUp() {
// инициализация decorView
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
        logOutCheck();
    }

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Авторизация с валидными данными")
    public void successLoginTest() {
        loginPage.logIn(rightLogin, rightPassword);
        waitFor(1500);
        mainPage.waitingPageToLoad();
        mainPage.checkPageLoaded();
    }

    @Test
    @DisplayName("Авторизация с пустым полем логина")  // 1 способ обнаружения всплывающего сообщения
    public void loginFieldIsEmptyLoginTest() {
        LoginPage.logIn("", rightPassword);
        waitFor(1500);
        step("Проверка сообщения *" + toastMsgCannotBeEmpty);
        /**      обнаружение сообщения с использованием decorView */
        onView(withText(toastMsgCannotBeEmpty))
                .inRoot(withDecorView(not(decorView)))
                //.inRoot(RootMatchers.withDecorView(not(decorView))) //вариант 2
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Авторизация с пустым полем логина")  // 2 способ обнаружения всплывающего сообщения
    public void loginFieldIsEmptyLoginTest2() {
        LoginPage.logIn("", rightPassword);
        waitFor(1500);
        step("Проверка сообщения *" + toastMsgCannotBeEmpty);
        /**      обнаружение сообщения с использованием decorView */
        onView(withText(toastMsgCannotBeEmpty))
               // .inRoot(withDecorView(Matchers.not(decorView)))
                .inRoot(RootMatchers.withDecorView(not(decorView))) //вариант 2
                .check(matches(isDisplayed()));

    }

    @Test
    @DisplayName("Авторизация с пустым полем логина")  // 3 способ обнаружения всплывающего сообщения
    public void loginFieldIsEmptyLoginTest3() {
        LoginPage.logIn("", rightPassword);
        waitFor(1500);
        step("Проверка сообщения *" + toastMsgCannotBeEmpty);
/**       обнаружение сообщения с ToastMatcher  */
        onView(withText(toastMsgCannotBeEmpty)).inRoot(new DataHelper.ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Авторизация с невалидными данными")
    public void wrongLoginLoginTest() {
        LoginPage.logIn(wrongLogin, rightPassword);
        waitFor(500);
        step("Проверка сообщения *" + toastMsgSomethingWentWrong);
        onView(withText(toastMsgSomethingWentWrong))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Тест авторизации с SQL-иньекцией")
    public void injectionSQLLoginTest() {
        LoginPage.logIn(sqlInject, rightPassword);
        waitFor(500);
        step("Проверка сообщения *" + toastMsgSomethingWentWrong);
        onView(withText(toastMsgSomethingWentWrong))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Тест авторизации с XSS-иньекцией")
    public void injectionXSSLoginTest() {
        LoginPage.logIn(xssInject, rightPassword);
        waitFor(500);
        step("Проверка сообщения *" + toastMsgSomethingWentWrong);
        onView(withText(toastMsgSomethingWentWrong))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}
