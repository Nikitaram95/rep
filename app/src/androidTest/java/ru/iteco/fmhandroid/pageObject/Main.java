package ru.iteco.fmhandroid.pages;

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import io.qameta.allure.android.Step
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

class Main {

    private val textViewMainNews = onView(withText("Новости"))
    private val containerNews = R.id.container_list_news_include_on_fragment_main

    fun getContainerNews(): Int {
        return containerNews
    }

    @Step("Определение состояния системы")
    fun isButtonProfileDisplayed(): Boolean {
        return try {
            onView(withId(containerNews)).check(matches(isDisplayed()))
            true
        } catch (e: NoMatchingViewException) {
            false
        }
    }

    @Step("Проверка видимости 'Новости'")
    fun checkNewsVisibility() {
        step("Проверка видимости 'Новости'") {
            textViewMainNews.check(matches(allOf(isDisplayed(), withText("Новости"))))
        }
    }
}