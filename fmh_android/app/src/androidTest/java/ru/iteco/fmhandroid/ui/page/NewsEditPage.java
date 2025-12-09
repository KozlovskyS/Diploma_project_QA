package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.Data.category1;
import static ru.iteco.fmhandroid.ui.data.Data.descriptionNewsCreated;
import static ru.iteco.fmhandroid.ui.data.Data.titleNewsCreated;
import static ru.iteco.fmhandroid.ui.data.DataHelper.okButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.tapButton;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsEditPage {
    ViewInteraction categoryTextField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    ViewInteraction titleTextField = onView(withId(R.id.news_item_title_text_input_edit_text));
    ViewInteraction dateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    ViewInteraction timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
    ViewInteraction switchActive = onView(withId(R.id.switcher));
    ViewInteraction saveButton = onView(withId(R.id.save_button));
    ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
//    ViewInteraction okButton = onView(
//            allOf(withId(android.R.id.button1), withText("OK")));

    public void waitingPageToLoad() {
        step("Ожидание загрузки страницы редактирования новости");
        onView(isRoot()).perform(waitId(R.id.news_item_category_text_auto_complete_text_view, 5000));
    }

    public void checkPageLoaded() {
        step("Проверка загрузки страницы редактирования новости");
        categoryTextField.check(matches(isDisplayed()));
    }

    public void editCategory(String text) {
        step("Ввод Категория: " + text);
        categoryTextField.check(matches(isDisplayed()));
        categoryTextField.perform(replaceText(text));
    }

    public void editTitle(String text) {
        step("Вводим  Заголовок: " + text);
        titleTextField.check(matches(isDisplayed()));
        titleTextField.perform(replaceText(text), closeSoftKeyboard());
    }

    public void editDate() {
        step("Ввод дата");
        dateField.check(matches(isDisplayed()));
        dateField.perform(click());
        okButton.perform(scrollTo(), click());
    }

    public void editTime() {
        step("Ввод время");
        timeField.check(matches(isDisplayed()));
        timeField.perform(click());
        okButton.perform(scrollTo(), click());
    }

    public void editDescription(String description) {
        step("Ввод описание: " + description);
        descriptionField.check(matches(isDisplayed()));
        descriptionField.perform(replaceText(description), closeSoftKeyboard());
    }

    public void switchOnOff() {
        step("Переключение активности новости");
        tapButton(switchActive);
    }

    public void tapToSaveButton() {
        step("Сохранить");
        tapButton(saveButton);
    }

    public void tapToCancelButton() {
        step("Отменить");
        tapButton(cancelButton);
    }

    public void creatingNews(String category, String title, String description) {
        step("Создание новости");
        editCategory(category);
        editTitle(title);
        editDate();
        editTime();
        editDescription(description);
        tapToSaveButton();
    }
}
