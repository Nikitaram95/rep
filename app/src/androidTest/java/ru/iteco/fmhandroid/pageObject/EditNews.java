package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

class EditNews {
    private val editCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view))
    private val editTitle = onView(withId(R.id.news_item_title_text_input_edit_text))
    private val editTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text))
    private val editDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text))
    private val editDescription = onView(withId(R.id.news_item_description_text_input_edit_text))
    private val save = onView(withId(R.id.save_button))

    @Step("Редактирование значения в поле категория на {text}")
    fun ViewInteraction.editCategoryText(text: String) {
        Allure.step("Редактирование значения в поле категория на $text")
        check(matches(isDisplayed()))
        perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Редактирование значения в поле заголовок на {text}")
    fun ViewInteraction.editTitleText(text: String) {
        Allure.step("Редактирование значения в поле заголовок на $text")
        check(matches(isDisplayed()))
        perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Редактирование значения в поле дата на {text}")
    fun ViewInteraction.editDateText(text: String) {
        Allure.step("Редактирование значения в поле дата на $text")
        check(matches(isDisplayed()))
        perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Редактирование значения в поле время на {text}")
    fun ViewInteraction.editTimeText(text: String) {
        Allure.step("Редактирование значения в поле время на $text")
        check(matches(isDisplayed()))
        perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Редактирование значения в поле описание на {text}")
    fun ViewInteraction.editDescriptionText(text: String) {
        Allure.step("Редактирование значения в поле описание на $text")
        check(matches(isDisplayed()))
        perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Нажатие на кнопку Сохранить")
    fun ViewInteraction.pressSaveButton() {
        Allure.step("Нажатие на кнопку Сохранить")
        closeSoftKeyboard()
        check(matches(isDisplayed()))
        perform(scrollTo(), click())
    }
}
