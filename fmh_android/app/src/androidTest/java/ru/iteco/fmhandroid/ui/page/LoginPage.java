package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.data.DataHelper.enterData;
import static ru.iteco.fmhandroid.ui.data.DataHelper.rightLogin;
import static ru.iteco.fmhandroid.ui.data.DataHelper.rightPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class LoginPage {
    public static ViewInteraction loginTextField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
    public static ViewInteraction passwordTextField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));
    public static ViewInteraction signInButton = onView(allOf(withId(R.id.enter_button)));
    public static ViewInteraction authImageButton = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction logOutButton = onView(withId(android.R.id.title));

    public static void logIn(String login, String password) {
        onView(isRoot()).perform(waitId(R.id.enter_button, 5000));

        loginTextField.check(matches(isDisplayed()));
        enterData(loginTextField, login);

        passwordTextField.check(matches(isDisplayed()));
        enterData(passwordTextField, password);

        tapButton(signInButton);
    }

    public static void logOut() {
        onView(isRoot()).perform(waitId(R.id.authorization_image_button, 5000));
        tapButton(authImageButton);
        tapButton(logOutButton);
    }
}
