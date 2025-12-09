package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class NewsPage {
    public static ViewInteraction sortingNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    //    public static ViewInteraction mainMenuElementMain = onView(allOf(withId(android.R.id.title), withText("Main")));
    public static ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));

    public void waitingPageToLoad() {
        step("Ожидание загрузки страницы");
//        Espresso.onView(ViewMatchers.isRoot()).perform(WaitForViewAction.waitForView(
//                ViewMatchers.withId(R.id.news_list_recycler_view), 10000));
        onView(isRoot()).perform(waitId(R.id.edit_news_material_button, 5000));
    }

    public void checkPageLoaded() {
        step("Проверка отображения страницы");
       // newsList.check(matches(isDisplayed()));
        editNewsButton.check(matches(isDisplayed()));
        sortingNewsButton.check(matches(isDisplayed()));
        filterNewsButton.check(matches(isDisplayed()));
    }

    public void editNews() {
        step("Кнопка редактировать новости");
        DataHelper.tapButton(editNewsButton);
    }

    public static void goToNewsControlPanelPage() {
        step("Переход на страницу управления новостями");
        NavBar navBar = new NavBar();
        NewsControlPanelPage newsControlPanelPage = new NewsControlPanelPage();
        NewsPage newsPage = new NewsPage();

        navBar.clickMainMenuButton();
        navBar.goToNewsPage();
        newsPage.waitingPageToLoad();
        newsPage.editNews();
        newsControlPanelPage.waitingPageToLoad();
    }

    public static void goToNewsEditPage() {
        step("Переход на страницу создания новости");
        goToNewsControlPanelPage();
        NewsControlPanelPage newsControlPanelPage = new NewsControlPanelPage();
        newsControlPanelPage.tapCreateButton();
    }

    public static void sortingNews() {
        tapButton(sortingNewsButton);
    }
}

