package ru.iteco.fmhandroid.pages;

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import io.qameta.allure.android.step

class CreateNews {

    private val category: ViewInteraction = onView(withId(R.id.news_item_category_text_auto_complete_text_view))
    private val title: ViewInteraction = onView(withId(R.id.news_item_title_text_input_edit_text))
    private val time: ViewInteraction = onView(withId(R.id.news_item_publish_time_text_input_edit_text))
    private val date: ViewInteraction = onView(withId(R.id.news_item_publish_date_text_input_edit_text))
    private val description: ViewInteraction = onView(withId(R.id.news_item_description_text_input_edit_text))
    private val save: ViewInteraction = onView(withId(R.id.save_button))
    private val buttonSave: ViewInteraction = onView(withId(R.id.save_button))

    @Step("Ввод в поле категория {text}")
    fun addCategory(text: String) {
        step("Ввод в поле категория $text")
        category.check(matches(isDisplayed()))
        category.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Ввод в поле заголовок {text}")
    fun addTitle(text: String) {
        step("Ввод в поле заголовок $text")
        title.check(matches(isDisplayed()))
        title.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Ввод в поле дата {text}")
    fun addDate(text: String) {
        step("Ввод в поле дата $text")
        date.check(matches(isDisplayed()))
        date.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Ввод в поле время {text}")
    fun addTime(text: String) {
        step("Ввод в поле время $text")
        time.check(matches(isDisplayed()))
        time.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Ввод в поле описание {text}")
    fun addDescription(text: String) {
        step("Ввод в поле описание $text")
        description.check(matches(isDisplayed()))
        description.perform(replaceText(text), closeSoftKeyboard())
    }

    @Step("Создание новости через кнопку "сохранить"")
    fun pressSave() {
        step("Нажатие на кнопку Сохранить")
        closeSoftKeyboard()
        onView(isRoot()).perform(waitDisplayed(buttonSave, 10000))
        save.check(matches(isDisplayed()))
        save.perform(scrollTo(), click())
    }

    @Step("Создание новости с полями: категория {category}, заголовок {title}, дата {date}, время {time}, описание {description}")
    fun createNews(category: String, title: String, date: String, time: String, description: String) {
        step("Создание новости с полями: категория $category, заголовок $title, дата $date, время $time, описание $description")
        addCategory(category)
        addTitle(title)
        addDate(date)
        addTime(time)
        addDescription(description)
        onView(isRoot()).perform(waitDisplayed(buttonSave, 5000))
        pressSave()
    }

    @Step("Проверка отображения ошибки")
    fun checkErrorDisplay(text: String) {
        step("Проверка отображения ошибки")
        onView(withText(text)).check(matches(isDisplayed()))
    }

    private fun waitDisplayed(viewInteraction: ViewInteraction, millis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return viewInteraction.check(matches(isDisplayed()))
            }

            override fun getDescription(): String {
                return "wait for a specific view with id $viewInteraction to be displayed"
            }

            override fun perform(uiController: UiController, view: View) {
                val endTime = System.currentTimeMillis() + millis
                do {
                    if (viewInteraction.matches(isDisplayed())) {
                        return
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
            }
        }
    }
}
            }