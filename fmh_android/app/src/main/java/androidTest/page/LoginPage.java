package androidTest.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class LoginPage {
    public static ViewInteraction loginField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
    public static ViewInteraction passwordField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));
    public static ViewInteraction signInButton = onView(withId(R.id.enter_button));

    public void logIn(String login, String password) {
        loginField.perform(replaceText(login));
        passwordField.perform(replaceText(password));
        signInButton.check((ViewAssertion) isDisplayed()).perform(click());

    }
}
