package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapButton;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;

public class NavBar {
    public static ViewInteraction mainMenuButton =
            onView(allOf(withId(R.id.main_menu_image_button), withContentDescription(R.string.main_menu)));
    public static ViewInteraction authImageButton = onView(withId(R.id.authorization_image_button));
   public static ViewInteraction ourMissionButton = onView(
            Matchers.allOf(withId(R.id.our_mission_image_button), withContentDescription("Our Mission")));
    public static ViewInteraction mainMenuElementNews = onView(allOf(withId(android.R.id.title), withText("News")));
    public static ViewInteraction mainMenuElementAbout = onView(allOf(withId(android.R.id.title), withText("About")));
    ViewInteraction logOutButton = onView(withText("Log out"));
    public static ViewInteraction mainMenuElementMain = onView(allOf(withId(android.R.id.title), withText("Main")));

    public void logOut() {
        step("Выход из учетной записи");
        authImageButton.check(matches(isDisplayed()));
        tapButton(authImageButton);
        logOutButton.check(matches(isDisplayed()));
        tapButton(logOutButton);
    }

    public void clickMainMenuButton() {
        step("Открыть меню");
        mainMenuButton.check(matches(isDisplayed()));
        tapButton(mainMenuButton);
    }

    public void goToQuotesPage() {
        step("Переход на страницу Цитаты");
        ourMissionButton.check(matches(isDisplayed()));
        ourMissionButton.perform(click());
    }

    public void goToNewsPage() {
        step("Переход на страницу Новости");
        mainMenuElementNews.check(matches(isDisplayed()));
        mainMenuElementNews.perform(click());
    }

    public void goToAboutPage() {
        step("Переход на страницу О приложении");
        mainMenuElementAbout.check(matches(isDisplayed()));
        mainMenuElementAbout.perform(click());
    }

    public void goToMainPage() {
        step("Переход на главный экран");
        mainMenuElementMain.check(matches(isDisplayed()));
        mainMenuElementMain.perform(click());
    }
}
