package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.mode.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


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
    private SelenideElement errorMonthFieldRequired = $x("//*[text()='Месяц']//following-sibling::span[@class='input__sub']");
    private SelenideElement errorYearFieldRequired = $x("//*[text()='Год']//following-sibling::span[@class='input__sub']");
    private SelenideElement errorOwnerFieldRequired = $x("//*[text()='Владелец']//following-sibling::span[@class='input__sub']");
    private SelenideElement errorCVCFieldRequired = $x("//*[text()='CVC/CVV']//following-sibling::span[@class='input__sub']");
    private SelenideElement wrongFormatMonthField = $x("//*[text()='Месяц']//following-sibling::span[@class='input__sub']");
    private SelenideElement wrongFormatYearField = $x("//*[text()='Год']//following-sibling::span[@class='input__sub']");
    private SelenideElement wrongFormatOwnerField = $x("//*[text()='Владелец']//following-sibling::span[@class='input__sub']");
    private SelenideElement notificationStatusError = $x("//*[text()='Ошибка! Банк отказал в проведении операции.']");
    private SelenideElement buttonSendARequest = $x("//*[text()='Отправляем запрос в Банк...']");



    public PaymentPage() {
        headingCardPayment.shouldBe(visible);
    }


    public void getNotificationOk() {
        notificationStatusOk.shouldBe(visible, Duration.ofSeconds(360)).shouldHave(text("Операция одобрена Банком."));
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
        errorMonthFieldRequired.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationYearRequired() {
        errorYearFieldRequired.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationOwnerRequired() {
        errorOwnerFieldRequired.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationCVCRequired() {
        errorCVCFieldRequired.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void getWrongFormatMonthField() {
        wrongFormatMonthField.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void getWrongFormatYearField() {
        wrongFormatYearField.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void getWrongCardExpiredYearField() {
        wrongFormatYearField.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    public void getWrongFormatOwnerField() {
        wrongFormatOwnerField.shouldBe(visible).shouldHave(text("Неверный формат заполнения"));
    }

    public void notGetErrorNotificationOwnerRequired() {
        errorOwnerFieldRequired.shouldBe(hidden);
    }

    public void notGetErrorNotificationCardNumberRequired() {
        errorCardNumberFieldRequired.shouldBe(hidden);
    }

    public void notGetErrorNotificationMonthRequired() {
        errorMonthFieldRequired.shouldBe(hidden);
    }

    public void notGetErrorNotificationYearRequired() {
        errorYearFieldRequired.shouldBe(hidden);
    }

    public void notGetErrorNotificationCVCRequired() {
        errorCVCFieldRequired.shouldBe(hidden);
    }

    public void notGetWrongFormatMonthField() {
        wrongFormatMonthField.shouldBe(hidden);
    }

    public void notGetWrongFormatYearField() {
        wrongFormatYearField.shouldBe(hidden);
    }

    public void notGetWrongFormatOwnerField() {
        wrongFormatOwnerField.shouldBe(hidden);
    }

    public String getValue(SelenideElement field) {
        String fieldValue = field.val();
        return fieldValue;
    }

    public String getValueOwner(){
       return getValue(fieldOwner);
    }

    public String getValueMonth(){
        return getValue(fieldMonth);
    }

    public String getValueYear(){
        return getValue(fieldYear);
    }

    public String getValueCVC(){
        return getValue(fieldCVC);
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
    }











}
