package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;

public class MainPage {
    public static ViewInteraction mainMenuButton =
            onView(allOf(withId(R.id.main_menu_image_button), withContentDescription(R.string.main_menu)));
    public static ViewInteraction mainMenuElementNews = onView(allOf(withId(android.R.id.title), withText("News")));
    public static ViewInteraction mainMenuElementAbout = onView(allOf(withId(android.R.id.title), withText("About")));
    public static ViewInteraction ourMissionButton = onView(
            Matchers.allOf(withId(R.id.our_mission_image_button), withContentDescription("Our Mission")));
    public static ViewInteraction allNewsButton = onView(
            Matchers.allOf(withId(R.id.all_news_text_view), withText("All news")));
    public static ViewInteraction expandNewsButton = onView(
            allOf(withId(R.id.expand_material_button)));



}
