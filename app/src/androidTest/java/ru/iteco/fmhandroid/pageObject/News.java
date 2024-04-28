package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static org.hamcrest.Matchers.instanceOf;
import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class News {

    private final int buttonControlPanelNews = R.id.edit_news_material_button;
    private ControlPanelNews controlPanelNews = new ControlPanelNews();

    public int getButtonControlPanelNews() {
        return buttonControlPanelNews;
    }

    @Step("Переход на Панель управления новостями")
    public void switchControlPanelNews() {
        Allure.step("Переход на Панель управления новостями");
        onView(withId(buttonControlPanelNews)).check(matches(isDisplayed())).perform(click());
        onView(isAssignableFrom(ControlPanelNews.class)).perform(waitDisplayed(controlPanelNews.getButtonAddNews(), 6000));
    }
}