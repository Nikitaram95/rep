package ru.iteco.fmhandroid.pages;

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.R

class EditClaims {

    private val editTitle = onView(withId(R.id.title_edit_text))
    private val editExecutor = onView(withId(R.id.executor_drop_menu_auto_complete_text_view))
    private val editDate = onView(withId(R.id.date_in_plan_text_input_edit_text))
    private val editTime = onView(withId(R.id.time_in_plan_text_input_edit_text))
    private val editDescription = onView(withId(R.id.description_edit_text))
    private val save = onView(withId(R.id.save_button))
    private val buttonSave = R.id.save_button

    @Step("Редактирование значения в поле тема на {text}")
    fun editTitle(text: String) {
        Allure.step("Редактирование значения в поле тема на $text")
        editTitle.check(matches(isDisplayed()))
        editTitle.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Редактирование значения в поле исполнитель на {text}")
    fun editExecutor(text: String) {
        Allure.step("Редактирование значения в поле исполнитель на $text")
        editExecutor.check(matches(isDisplayed()))
        editExecutor.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Редактирование значения в поле дата на {text}")
    fun editDate(text: String) {
        Allure.step("Редактирование значения в поле дата на $text")
        editDate.check(matches(isDisplayed()))
        editDate.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Редактирование значения в поле время на {text}")
    fun editTime(text: String) {
        Allure.step("Редактирование значения в поле время на $text")
        editTime.check(matches(isDisplayed()))
        editTime.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Редактирование значения в поле описание на {text}")
    fun editDescription(text: String) {
        Allure.step("Редактирование значения в поле описание на $text")
        editDescription.check(matches(isDisplayed()))
        editDescription.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Нажатие на кнопку Сохранить")
    fun pressSave() {
        Allure.step("Нажатие на кнопку Сохранить")
        closeSoftKeyboard()
        onView(isRoot()).perform(scrollTo())
        save.check(matches(isDisplayed()))
        save.perform(click())
    }
}
