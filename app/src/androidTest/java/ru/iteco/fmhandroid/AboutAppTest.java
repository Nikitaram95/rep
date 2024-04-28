package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
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
import ru.iteco.fmhandroid.pageObject.AboutApp;
import ru.iteco.fmhandroid.pageObject.AppBar;
import ru.iteco.fmhandroid.pageObject.Authorization;

import ru.iteco.fmhandroid.pageObject.Main;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class AboutAppTest {

    private final String urlPrivacyPolicy = "https://vhospice.org/#/privacy-policy";
    private final String urlTermsOfUse = "https://vhospice.org/#/terms-of-use";

    private Main main = new Main();
    private AboutApp aboutApp = new AboutApp();

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public TestRule chain = RuleChain.outerRule(activityScenarioRule);

    @DisplayName("Открытие политики конфиденциальности")
    @Epic("О приложении")
    @Feature("Просмотр политики конфиденциальности")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenPrivacyPolicy() {
        try (ActivityScenario<AppActivity> scenario = activityScenarioRule.getScenario()) {
            main.waitForDisplayedButtonProfile();
            aboutApp.openAboutApp();
            aboutApp.intentPrivatePolicy(urlPrivacyPolicy);
            aboutApp.navigateBack();
        }
    }

    @DisplayName("Открытие пользовательского соглашения")
    @Epic("О приложении")
    @Feature("Просмотр пользовательского соглашения")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldOpenTermsOfUse() {
        try (ActivityScenario<AppActivity> scenario = activityScenarioRule.getScenario()) {
            main.waitForDisplayedButtonProfile();
            aboutApp.openAboutApp();
            aboutApp.intentTermOfUse(urlTermsOfUse);
            aboutApp.navigateBack();
        }
    }
}