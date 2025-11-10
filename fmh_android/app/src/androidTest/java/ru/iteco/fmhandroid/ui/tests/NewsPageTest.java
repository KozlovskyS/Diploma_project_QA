package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.rightLogin;
import static ru.iteco.fmhandroid.ui.data.DataHelper.rightPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.LoginPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        LoginPage.logIn(rightLogin, rightPassword);
        Thread.sleep(1500);
        onView(isRoot()).perform(waitId(R.id.authorization_image_button, 5000));
        onView(withText("News")).check(matches(isDisplayed()));
        tapButton(MainPage.allNewsButton);
        onView(withId(R.id.edit_news_material_button)).check(matches(isDisplayed()));
    }

    @After
    public void closeApp() {
        LoginPage.logOut();
    }

    @Test
    public void gotoMainFromMenu() {
        tapButton(MainPage.mainMenuButton);
        tapButton(NewsPage.mainMenuElementMain);

        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void gotoAboutFromMenu() {
        tapButton(MainPage.mainMenuButton);
        tapButton(MainPage.mainMenuElementAbout);

        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

    @Test
    public void gotoQuotesPage() {
        tapButton(MainPage.ourMissionButton);
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void sortingNewsTest() { //в разработке - новости не отображаются
        tapButton(NewsPage.sortingNewsButton);
    }

    @Test
    public void filerNewsByCategoryTest() {
        tapButton(NewsPage.filterNewsButton);
        onView(withId(R.id.filter_news_title_text_view)).check(matches(isDisplayed()));

        Espresso.pressBack();
    }

}
