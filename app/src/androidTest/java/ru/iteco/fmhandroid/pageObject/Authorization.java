package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;

import io.qameta.allure.android.Allure
import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.R

class Authorization {

    private val main = Main()
    private val appBar = AppBar()

    private val inputLogin = R.id.login_text_input_layout
    private val inputPassword = R.id.password_text_input_layout
    private val materialButton = onView(withId(R.id.enter_button))
    private val textViewAuth = onView(withText("Авторизация"))

    fun getInputLogin(): Int = inputLogin

    @Step("Ввод в поле логин {login}")
    fun inputLogin(login: String) {
        Allure.step("Ввод в поле логин $login")
        onView(allOf(
                isDescendantOfA(withId(inputLogin)),
                isAssignableFrom(EditText::class)).apply {
            check(matches(isDisplayed()))
            perform(replaceText(login), closeSoftKeyboard())
        }
    }

    @Step("Ввод в поле пароль {password}")
    fun inputPassword(password: String) {
        Allure.step("Ввод в поле пароль $password")
        onView(allOf(
                isDescendantOfA(withId(inputPassword)),
                isAssignableFrom(EditText::class)).apply {
            check(matches(isDisplayed()))
            perform(replaceText(password), closeSoftKeyboard())
        }
    }

    @Step("Авторизация через кнопку \"войти\"")
    fun pressButton() {
        Allure.step("Авторизация через кнопку \"войти\"")
        materialButton.apply {
            check(matches(isDisplayed()))
            perform(click())
        }
    }

    @Step("Успешная авторизация пользователя")
    fun loginSuccessful() {
        Allure.step("Успешная авторизация пользователя")
        inputLogin("login2")
        inputPassword("password2")
        pressButton()
        onView(isRoot()).perform(waitDisplayed(appBar.getPressProfile(), 6000))
        main.checkNews()
    }

    @Step("Проверка видимости элемента с текстом Авторизация")
    fun checkAuth() {
        Allure.step("Проверка видимости элемента с текстом Авторизация")
        textViewAuth.apply {
            check(matches(isDisplayed()))
            check(matches(withText("Авторизация")))
        }
    }

}