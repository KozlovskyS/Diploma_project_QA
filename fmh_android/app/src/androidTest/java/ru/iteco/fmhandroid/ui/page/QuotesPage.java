package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class QuotesPage {
    public static ViewInteraction titleOurMission = onView(withId(R.id.our_mission_title_text_view));
    public static ViewInteraction descriptionOurMission = onView(withId(R.id.our_mission_item_description_text_view));
    public static ViewInteraction quotesList = onView(withId(R.id.our_mission_item_list_recycler_view));

    public void checkPageLoaded() {
        step("Проверка загрузки страницы Цитаты");
        titleOurMission.check(matches(isDisplayed()));
        quotesList.check(matches(isDisplayed()));
    }


    public void waitingPageToLoad() {
        step("Ожидание загрузки страницы Цитаты");
        onView(isRoot()).perform(waitId(R.id.our_mission_item_list_recycler_view, 5000));
    }

    @Step("Раскрытие цитаты")
    public void quotesItemOpenClose(int numberOfItem) {
        step("Раскрытие цитаты №" + numberOfItem);
        quotesList.check(matches(isDisplayed()));
        quotesList.perform(actionOnItemAtPosition(numberOfItem, click()));
    }

    public void checkDescriptionItem() {
        step("Проверка раскрытия цитаты");
        onView(
                allOf(withId(R.id.our_mission_item_description_text_view),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))
        ).check(matches(isDisplayed()));
    }
}
