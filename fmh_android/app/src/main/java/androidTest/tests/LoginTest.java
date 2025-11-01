package androidTest.tests;


import static androidTest.data.DataHelper.rightLogin;
import static androidTest.data.DataHelper.rightPassword;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
// import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidTest.page.MainPage;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import androidTest.page.LoginPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public class LoginTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    @Test
    public void successLoginTest() {
        loginPage.logIn(rightLogin, rightPassword);
        mainPage.logOutButtonIsVisible();
        mainPage.titleNewsIsVisible();
    }

}
