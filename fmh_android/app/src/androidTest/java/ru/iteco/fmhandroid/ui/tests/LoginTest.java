package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.data.DataHelper.rightLogin;
import static ru.iteco.fmhandroid.ui.data.DataHelper.rightPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;
import static ru.iteco.fmhandroid.ui.data.DataHelper.wrongLogin;
import static ru.iteco.fmhandroid.ui.page.LoginPage.authImageButton;
import static ru.iteco.fmhandroid.ui.page.LoginPage.logOut;
import static ru.iteco.fmhandroid.ui.page.LoginPage.loginTextField;
import static ru.iteco.fmhandroid.ui.page.LoginPage.passwordTextField;
import static ru.iteco.fmhandroid.ui.page.LoginPage.signInButton;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.LoginPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private View decorView; //переменная для определения всплывающих сообщений

    @Before
    public void setUp() {

// инициализация decorView
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        /* onView(withText(R.string.toast_msg)).
                inRoot(RootMatchers.withDecorView(not(decorView)))
                .check(matches(isDisplayed())); */
    }

    @Test
    public void successLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(rightLogin, rightPassword);
        Thread.sleep(1500);
        onView(isRoot()).perform(waitId(R.id.authorization_image_button, 5000));
        authImageButton.check(matches(isDisplayed()));
        logOut();
    }

    @Test
    public void loginFieldIsEmptyLoginTest() throws InterruptedException {
        LoginPage.logIn("", rightPassword);
        //       Thread.sleep(500);

        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));

        onView(withText("Login and password cannot be empty")).inRoot(new DataHelper.ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void wrongLoginLoginTest() throws InterruptedException {
        LoginPage.logIn(wrongLogin, rightPassword);
//        Thread.sleep(500);

        onView(withText(R.string.wrong_login_or_password))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));

//        onView(withText("Something went wrong. Try again later.")).inRoot(new DataHelper.ToastMatcher())
//                .check(matches(isDisplayed()));
    }

}
