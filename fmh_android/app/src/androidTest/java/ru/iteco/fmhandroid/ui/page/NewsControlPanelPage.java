package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapOkButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitFor;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;

import java.util.Random;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class NewsControlPanelPage {
    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    public static ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));


    public void waitingPageToLoad() {
        step("Ожидание загрузки страницы панели управления");
        onView(isRoot()).perform(waitId(R.id.add_news_image_view, 5000));
    }

    public void checkPageLoaded() {
        step("Проверка загрузки страницы панели управления");
        newsList.check(matches(isDisplayed()));
    }


    public void scrollAndClickToNewsWithTittle(String tittle) {
        DataHelper.waitElement(R.id.news_list_recycler_view);
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(allOf(withText(tittle)))));
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()))
                .perform(actionOnItem(hasDescendant(withText(tittle)), click()));
    }

    public ViewInteraction findNewsByText(String text) {
        step("Поиск новости по тексту '" + text + "'");
        return onView(withText(text));
    }

    public void tapCreateButton() {
        step("Нажать кнопку создать новость");
        addNewsButton.check(matches(isDisplayed()));
        tapButton(addNewsButton);
    }

    public void tapEditingNewsButton(String titleOfNews) {
        step("Редактировать новость '" + titleOfNews + "'");
        ViewInteraction editNewsButton = onView(
                allOf(
                        withId(R.id.edit_news_item_image_view),
                        hasSibling(withText(titleOfNews))
         //               withContentDescription("News editing button")
                )
        );
        editNewsButton.check(matches(isDisplayed()));
        tapButton(editNewsButton);
    }
    public ViewInteraction deleteNewsButton(String title) {
        return onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(title))))))));
    }

    public void deleteNews(String title) { //новый метод
        scrollAndClickToNewsWithTittle(title);
        onView(CoreMatchers.allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(title))))
                .perform(DataHelper.clickChildViewWithId(R.id.delete_news_item_image_view));
        waitFor(500);
        tapOkButton();
    }
//    public void deleteNews(String titleOfNews) {
//        step("Удалить новость '" + titleOfNews + "'");
//        waitFor(1000);
//        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
//        onView(allOf(
//                withId(R.id.delete_news_item_image_view),
//                withContentDescription("News delete button"),
//                hasSibling(withText(titleOfNews))
//        ))
//                .perform(click());
//        DataHelper.tapOkButton();
//    }





}
