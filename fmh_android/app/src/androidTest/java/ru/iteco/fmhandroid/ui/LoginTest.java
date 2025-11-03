package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void loginTest() {
        //Thread.sleep(5000);
        onView(isRoot()).perform(waitId(R.id.enter_button, 5000));

        ViewInteraction signInButton = onView(withId(R.id.enter_button));
        signInButton.check(matches(isDisplayed()));

        ViewInteraction loginTextField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
        loginTextField.check(matches(isDisplayed()));
        loginTextField.perform(replaceText("login2"), closeSoftKeyboard());

        ViewInteraction passwordTextField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));
        passwordTextField.check(matches(isDisplayed()));
        passwordTextField.perform(replaceText("password2"), closeSoftKeyboard());

        signInButton.perform(click());

//        ViewInteraction linearLayout = onView(
//                allOf(withId(R.id.container_list_news_include_on_fragment_main),
//                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
//                        isDisplayed()));
//        linearLayout.check(matches(isDisplayed()));
//
//        ViewInteraction imageButton = onView(
//                allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization"),
//                        withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
//                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
//                        isDisplayed()));
//        imageButton.check(matches(isDisplayed()));
//
        onView(isRoot()).perform(waitId(R.id.authorization_image_button, 10000));

        ViewInteraction authImageButton = onView(withId(R.id.authorization_image_button));
        authImageButton.check(matches(isDisplayed()));
        authImageButton.perform(click());
//
//        ViewInteraction materialTextView = onView(withId(android.R.id.title));
//        materialTextView.check(matches(isDisplayed()));
//        materialTextView.perform(click());
    }

}
