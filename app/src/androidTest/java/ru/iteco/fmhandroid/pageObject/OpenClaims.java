package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.Utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class OpenClaims {

    EditClaims editClaims = new EditClaims();
    private val buttonEdit = R.id.edit_processing_image_button;
    private val ViewInteraction buttonStatus = onView(withId(R.id.status_processing_image_button));
    private val ViewInteraction textStatus = onView(withId(R.id.status_label_text_view));
    private val ViewInteraction addComment = onView(withId(R.id.editText));
    private val ViewInteraction buttonOk = onView(withId(android.R.id.button1));
    private val ViewInteraction textComment = onView(withId(R.id.comment_description_text_view));
    private val ViewInteraction statusCompleted = onView(withText("Исполнить"));
    private val ViewInteraction statusCanceled = onView(withText("Отменить"));

    private val ViewInteraction textTitle = onView(withId(R.id.title_text_view));

    public int getButtonEdit() {
        return buttonEdit;
    }

    @Step("Press Edit Claims Button")
    public void pressEditClaims() {
        Allure.step("Press Edit Claims Button");
        onView(withId(buttonEdit)).check(matches(isDisplayed()));
        onView(withId(buttonEdit)).perform(click());
        onView(isRoot()).perform(waitDisplayed(editClaims.getButtonSave(), 5000));
    }

    @Step("Press Status Claims Button")
    public void pressStatusClaims() {
        Allure.step("Press Status Claims Button");
        buttonStatus.check(matches(isDisplayed()));
        buttonStatus.perform(click());
    }

    @Step("Add Comment: {text}")
    public void addComment(String text) {
        Allure.step("Add Comment: " + text);
        addComment.check(matches(isDisplayed()));
        addComment.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Press OK Button")
    public void pressOk() {
        Allure.step("Press OK Button");
        buttonOk.check(matches(isDisplayed()));
        buttonOk.perform(click());
    }

    @Step("Search for Title containing {text} and check its visibility")
    public void searchTitleAndCheckIsDisplayed(String text) {
        Allure.step("Search for Title containing " + text + " and check its visibility");
        onView(isRoot()).perform(waitDisplayed(buttonEdit, 5000));
        ViewInteraction textClaims = onView(withText(text));
        textClaims.check(matches(isDisplayed()));
    }

    @Step("Check Comment matches the entered text")
    public void commentCheckWithText(String text) {
        Allure.step("Check Comment matches the entered text");
        textComment.check(matches(isDisplayed()));
        textComment.check(matches(withText(text)));
    }

    @Step("Check Status of Claims")
    public void statusClaims(String text) {
        Allure.step("Check Status of Claims");
        textStatus.check(matches(isDisplayed()));
        textStatus.check(matches(withText(text)));
    }

    @Step("Change Claims Status to Canceled")
    public void pressStatusCanceled() {
        Allure.step("Change Claims Status to Canceled");
        statusCanceled.perform(click());
    }

    @Step("Change Claims Status to Completed")
    public void pressStatusCompleted() {
        Allure.step("Change Claims Status to Completed");
        statusCompleted.perform(click());
    }
}
