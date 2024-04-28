package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import androidx.test.espresso.contrib.RecyclerViewActions;

import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.android.Allure
import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.R

class Claims {
    private val createClaims = CreateClaims()
    private val openClaims = OpenClaims()

    private val buttonAddClaims = R.id.addnewclaimmaterialbutton

    fun getButtonAddClaims(): Int = buttonAddClaims

    @Step("Создание заявки через кнопку \"Добавить новость\"")
    fun pressAddClaim() {
        Allure.step("Создание новости через кнопку \"Добавить новость\"")
        onView(withId(buttonAddClaims)).apply {
            check(matches(isDisplayed()))
            perform(click())
        }
        onView(isRoot()).perform(waitDisplayed(createClaims.getButtonSave(), 6000))
    }

    @Step("Поиск заявки с темой {text} и открытие ее")
    fun searchClaimsAndSwitch(text: String) {
        Allure.step("Поиск заявки с темой $text и открытие ее")
        onView(withId(R.id.claimlistrecyclerview)).apply {
            perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))))
            check(matches(isDisplayed()))
            perform(click())
        }
        onView(isRoot()).perform(waitDisplayed(openClaims.getButtonEdit(), 6000))
    }

    @Step("Поиск заявки с темой {text} и проверка ее видимости")
    fun searchClaimsAndCheckIsDisplayed(text: String) {
        Allure.step("Поиск заявки с заголовком $text и проверка ее видимости")
        onView(isRoot()).perform(waitDisplayed(buttonAddClaims, 5000))
        onView(withId(R.id.claimlistrecyclerview)).apply {
            perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))))
            check(matches(isDisplayed()))
        }
    }
}