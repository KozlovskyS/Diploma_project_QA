package androidTest.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static java.util.EnumSet.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {
   public static ViewInteraction titleNews = onView(allOf(withText("News"), withParent(withParent(withId(R.id.container_list_news_include_on_fragment_main)))));
   public static ViewInteraction logOutButton = onView(withId(R.id.authorization_image_button));

    public void logOutButtonIsVisible() {
        logOutButton.check(matches(isDisplayed()));
    }

    public void titleNewsIsVisible() {
        titleNews.check(matches(isDisplayed()));
    }
}
