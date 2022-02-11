package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.mode.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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


    public PaymentPage() {
        headingCardPayment.shouldBe(visible);
    }

    public void getNotificationOk() {
        notificationStatusOk.shouldBe(visible).shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(30));
    }

    public void fillingOutTheForm(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumber());
        fieldMonth.setValue(String.valueOf(info.getMonth()));
        fieldYear.setValue(info.getYear());
        fieldOwner.setValue(info.getOwner());
        fieldCVC.setValue(info.getCvc());
        buttonContinue.click();
    }

    public void getErrorNotificationCardNumberRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationMonthRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationYearRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationOwnerRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void getErrorNotificationCVCRequired() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void wrongFormatMonthField() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void wrongFormatYearField() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void wrongFormatOwnerField() {
        errorCardNumberFieldRequired.shouldBe(visible).shouldHave(Condition.text("Неверный формат заполнения"));
    }




}
