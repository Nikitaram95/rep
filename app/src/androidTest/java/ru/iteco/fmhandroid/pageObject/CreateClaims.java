package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.R

class CreateClaims {

    private val title = onView(withId(R.id.title_edit_text))
    private val executor = onView(withId(R.id.executor_drop_menu_auto_complete_text_view))
    private val date = onView(withId(R.id.date_in_plan_text_input_edit_text))
    private val time = onView(withId(R.id.time_in_plan_text_input_edit_text))
    private val description = onView(withId(R.id.description_edit_text))
    private val save = onView(withId(R.id.save_button))

    private val textToScreen = onView(withId(R.id.custom_app_bar_title_text_view))
    private val buttonOk = onView(withId(android.R.id.button1))

    private val buttonSave = R.id.save_button
    fun getButtonSave(): Int {
        return buttonSave
    }

    @Step("Ввод в поле тема {text}")
    fun addTitle(text: String) {
        Allure.step("Ввод в поле тема $text")
        title.check(matches(isDisplayed()))
        title.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Ввод в поле исполнитель {text}")
    fun addExecutor(text: String) {
        Allure.step("Ввод в поле исполнитель $text")
        executor.check(matches(isDisplayed()))
        executor.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Ввод в поле дата {text}")
    fun addDate(text: String) {
        Allure.step("Ввод в поле дата $text")
        date.check(matches(isDisplayed()))
        date.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Ввод в поле время {text}")
    fun addTime(text: String) {
        Allure.step("Ввод в поле время $text")
        time.check(matches(isDisplayed()))
        time.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Ввод в поле описание {text}")
    fun addDescription(text: String) {
        Allure.step("Ввод в поле описание $text")
        description.check(matches(isDisplayed()))
        description.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Нажатие на кнопку Сохранить")
    fun pressSave() {
        Allure.step("Нажатие на кнопку Сохранить")
        closeSoftKeyboard()
        scrollTo()
        onView(isRoot()).perform(waitDisplayed(buttonSave, 10000))
        save.check(matches(isDisplayed()))
        save.perform(scrollTo()).perform(click())
    }

    @Step("Нажатие на кнопку ОК")
    fun pressOk() {
        Allure.step("Нажатие на кнопку ОК")
        buttonOk.check(matches(isDisplayed()))
        buttonOk.perform(click())
    }

    @Step("Создание заявки с полями: тема {title}, исполнитель {executor}, дата {date}, время {time}, описание {description}")
    fun createClaims(title: String, executor: String, date: String, time: String, description: String) {
        Allure.step("Создание заявки с полями: тема $title, исполнитель $executor, дата $date, время $time, описание $description")
        addTitle(title)
        addExecutor(executor)
        addDate(date)
        addTime(time)
        addDescription(description)
        onView(isRoot()).perform(waitDisplayed(buttonSave, 5000))
        pressSave()
    }

    @Step("Проверка отображения ошибки")
    fun checkErrorDisplay(text: String) {
        Allure.step("Проверка отображения ошибки")
        onView(withText(text)).check(matches(isDisplayed()))
    }
}