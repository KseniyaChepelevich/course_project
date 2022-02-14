package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import ru.netology.mode.DataHelper;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.util.concurrent.TimeUnit.SECONDS;


public class PaymentPage {

    private SelenideElement headingCardPayment = $x("//*[text()='Оплата по карте']");
    private SelenideElement  cardNumberField = $x("//*[text()='Номер карты']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldMonth = $x("//*[text()='Месяц']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldYear = $x("//*[text()='Год']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldOwner =  $x("//*[text()='Владелец']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldCVC = $x("//*[text()='CVC/CVV']/following-sibling::*/input[@class='input__control']");
    private SelenideElement buttonContinue = $x("//*[text()='Продолжить']");
    private SelenideElement notificationStatusOk = $x("//*[text()='Операция одобрена Банком.']");
    private SelenideElement errorCardNumberFieldRequired = $x("//*[text()='Номер карты']/following-sibling::*/input[@class='input__sub']");
    private SelenideElement errorMonthFieldRequired = $x("//*[text()='Месяц']/following-sibling::*/input[@class='input__sub']");
    private SelenideElement errorYearFieldRequired = $x("//*[text()='Год']/following-sibling::*/input[@class='input__sub']");
    private SelenideElement errorOwnerFieldRequired = $x("//*[text()='Владелец']/following-sibling::*/input[@class='input__sub']");
    private SelenideElement errorCVCFieldRequired = $x("//*[text()='CVC/CVV']/following-sibling::*/input[@class='input__sub']");
    private SelenideElement wrongFormatMonthField = $x("//*[text()='Месяц']/following-sibling::*/input[@class='input__sub']");
    private SelenideElement wrongFormatYearField = $x("//*[text()='Год']/following-sibling::*/input[@class='input__sub']");
    private SelenideElement wrongFormatOwnerField = $x("//*[text()='Владелец']/following-sibling::*/input[@class='input__sub']");
    private SelenideElement notificationStatusError = $x("//*[text()='Ошибка! Банк отказал в проведении операции.']");
    private SelenideElement buttonSendARequest = $x("//*[text()='Отправляем запрос в Банк...']");
    //private SelenideElement notificationStatusError = $$(".notification__content").find(text("Ошибка! Банк отказал в проведении операции."));
    //private SelenideElement notificationStatusError = $(withText("Ошибка! Банк отказал в проведении операции."));




    public PaymentPage() {
        headingCardPayment.shouldBe(visible);
    }


    public void getNotificationOk() {
        notificationStatusOk.shouldBe(visible, enabled).shouldHave(text("Операция одобрена Банком."), Duration.ofSeconds(360));
    }

    public void getButtonSendARequest() {
        buttonSendARequest.shouldBe(visible, enabled).shouldHave(text("Отправляем запрос в Банк..."));
    }

    public void getNotificationError() {
        notificationStatusError.shouldBe(visible, enabled).shouldHave(text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(120));
    }

    public void clickButtonContinue() {
        buttonContinue.click();
    }

    public void fillingOutTheForm(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumber());
        fieldMonth.setValue(String.valueOf(info.getMonth()));
        fieldYear.setValue(info.getYear());
        fieldOwner.setValue(info.getOwner());
        fieldCVC.setValue(info.getCvc());
        clickButtonContinue();
    }

    public void getErrorNotificationCardNumberRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationMonthRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationYearRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationOwnerRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationCVCRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getWrongFormatMonthField() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void getWrongFormatYearField() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void getWrongFormatOwnerField() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(text("Неверный формат заполнения"));
    }




}
