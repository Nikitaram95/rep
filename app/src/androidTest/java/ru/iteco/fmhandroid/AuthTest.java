package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;

import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;
import ru.iteco.fmhandroid.pageObject.AppBar;
import ru.iteco.fmhandroid.pageObject.Authorization;

import ru.iteco.fmhandroid.pageObject.Main;

import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {
    Authorization authorization = new Authorization();
    Main main = new Main();
    AppBar appBar = new AppBar();

    private final String successLogin = "login2";
    private final String successPassword = "password2";
    private final String registerLogin = "LOGIN2";
    private final String registerPassword = "PASSWORD2";
    private final String russianLogin = "логин";
    private final String russianPassword = "пассворд";
    private final String specSymbol = "#%@`<|&?>*";
    private final String more50Symbol = "lllllllllllllllllllllllllllllllllllllll";
    private final String oneSymbol = "l";


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        onView(isRoot()).perform(waitDisplayed(appBar.getAppBarFragmentMain(), 5000));
        if (main.isDisplayedButtonProfile()) {
            appBar.pressOut();
        }
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @Epic("Авторизация")
    @Feature("Успешная авторизация в приложении и выход из профиля")
    @Description("После авторизации с помощью иконки выход из профиля и переход на экран авторизации")
    @Test
    public void shouldLogInAndShowTheMainScreenAndLogOut() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(successPassword);
        authorization.pressButton();

        onView(isRoot()).perform(waitDisplayed(appBar.getPressProfile(), 6000));
        main.checkNews();
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature("Авторизация невалидного пользователя при использовании регистра")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringDataUsingCase() {
        authorization.inputLogin(registerLogin);
        authorization.inputPassword(registerPassword);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле логин символов на кириллице")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringInTheLoginFieldCharactersInCyrillic() {
        authorization.inputLogin(russianLogin);
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле логин спецсимволов")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringInTheLoginFieldSpecialCharacters() {
        authorization.inputLogin(specSymbol);
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле логин 1 символ")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringInTheLoginFieldOneCharacter() {
        authorization.inputLogin(oneSymbol);
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле логин 50 символов")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringInTheLoginField50Characters() {
        authorization.inputLogin(more50Symbol);
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature(" Авторизация при введении в поле пароль символов на кириллице")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringInThePasswordFieldCharactersInCyrillic() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(russianPassword);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле пароль спецсимволов")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringInThePasswordFieldSpecialCharacters() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(specSymbol);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature(" Авторизация при введении в поле пароль 1 символа")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringInThePasswordField1Character() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(oneSymbol);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature(" Авторизация при введении в поле пароль 50 символов")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEnteringInThePasswordField50Characters() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(more50Symbol);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature(" Авторизация с пустым логином")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEmptyLogin() {
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature(" Авторизация с пустым паролем")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEmptyPassword() {
        authorization.inputLogin(successLogin);
        authorization.pressButton();
        authorization.checkAuth();
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature(" Авторизация с пустым логином и паролем")
    @Description("Авторизация с невалидными данными, появление ошибки "неверные данные" с невозможностью авторизоваться")
    @Test
    public void shouldRemainOnTheMainScreenWhenEmptyLoginAndPassword() {
        authorization.pressButton();
        authorization.checkAuth();
    }

}
