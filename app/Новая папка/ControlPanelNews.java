package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import io.qameta.allure.android.Allure
import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.R

class ControlPanelNews {
    private val createNews = CreateNews()
    private val editNews = EditNews()

    private val buttonAddNews = R.id.add_news_image_view
    private val buttonEditNews = R.id.edit_news_item_image_view
    private val buttonDeleteNews = R.id.delete_news_item_image_view

    fun getButtonAddNews(): Int = buttonAddNews

    @Step("Нажатие на кнопку Добавить новость")
    fun addNews() {
        Allure.step("Нажатие на кнопку Добавить новость")
        onView(withId(buttonAddNews)).apply {
            check(matches(isDisplayed()))
            perform(click())
        }
        onView(isRoot()).perform(waitDisplayed(createNews.getButtonSave(), 6000))
    }

    @Step("Нажатие на кнопку Редактирование новостей")
    fun pressEditPanelNews(text: String) {
        Allure.step("Нажатие на кнопку Редактирование новостей")
        onView(allOf(withId(buttonEditNews), withContentDescription(text))).apply {
            check(matches(isDisplayed()))
            perform(click())
        }
        onView(isRoot()).perform(waitDisplayed(editNews.getButtonSave(), 6000))
    }

    @Step("Нажатие на кнопку Удалить новость")
    fun deleteNews(text: String) {
        Allure.step("Нажатие на кнопку Удалить новость")
        onView(allOf(withId(buttonDeleteNews), withContentDescription(text))).apply {
            check(matches(isDisplayed()))
            perform(click())
        }
        onView(withId(android.R.id.button1)).apply {
            check(matches(isDisplayed()))
            perform(click())
        }
    }

    @Step("Поиск новости с заголовком {text} и проверка ее видимости")
    fun searchNewsAndCheckIsDisplayed(text: String) {
        Allure.step("Поиск новости с заголовком $text и проверка ее видимости")
        onView(withText(text)).check(matches(isDisplayed()))
    }

    @Step("Проверка отсутствия новости с заголовком {text}")
    fun checkDoesNotExistNews(text: String) {
        Allure.step("Проверка отсутствия новости с заголовком $text")
        onView(withText(text)).check(doesNotExist())
    }
}
