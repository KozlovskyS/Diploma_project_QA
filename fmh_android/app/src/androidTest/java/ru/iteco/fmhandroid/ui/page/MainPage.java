package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;
import static ru.iteco.fmhandroid.ui.page.LoginPage.authImageButton;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class MainPage {
    public static ViewInteraction allNewsButton = onView(
            Matchers.allOf(withId(R.id.all_news_text_view), withText("All news")));
    public static ViewInteraction expandNewsButton = onView(
            allOf(withId(R.id.expand_material_button)));


    public static void waitingPageToLoad() {
        step("Ожидание загрузки главной страницы");
        onView(isRoot()).perform(waitId(R.id.authorization_image_button, 5000));
    }

    public static void checkPageLoaded() {
        step("Проверка загрузки главной страницы");
        expandNewsButton.check(matches(isDisplayed()));
    }

}
