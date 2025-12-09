package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static io.qameta.allure.kotlin.Allure.step;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    public static ViewInteraction aboutPrivacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public static ViewInteraction aboutTermsOfUseLink = onView(withId(R.id.about_terms_of_use_value_text_view));
    public static ViewInteraction aboutDeveloper = onView(withId(R.id.about_company_info_label_text_view));

    public static void waitingPageToLoad() {
        step("Ожидание загрузки страницы О приложении");
        onView(isRoot()).perform(waitId(R.id.about_version_title_text_view, 5000));
    }
    public static void checkPageLoaded() {
        step("Проверка загрузки страницы О приложении");
        aboutPrivacyPolicyLink.check(matches(isDisplayed()));
        aboutTermsOfUseLink.check(matches(isDisplayed()));
        aboutDeveloper.check(matches(isDisplayed()));
    }

}
