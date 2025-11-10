package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;

public class FilterNewsPage {
    public static ViewInteraction filterNewsTitle = onView(withId(R.id.filter_news_title_text_view));
    public static ViewInteraction categoryNewsListButton = onView(allOf(withId(com.google.android.material.R.id.text_input_end_icon),
            withContentDescription("Show dropdown menu")));
    public static ViewInteraction categoryNewsField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction dateStartPublish = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public static ViewInteraction dateEndPublish = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public static ViewInteraction filterButton = onView(withId(R.id.filter_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction isActiveCheckBox = onView(withId(R.id.filter_news_active_material_check_box));
    public static ViewInteraction isInactiveCheckBox = onView(withId(R.id.filter_news_inactive_material_check_box));
}
