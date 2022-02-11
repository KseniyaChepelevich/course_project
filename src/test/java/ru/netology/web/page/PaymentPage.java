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
    private SelenideElement  cardNumberField = $("[maxlength=\"19\"]");
    private SelenideElement fieldMonth = $x("//*[text()='Месяц']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldYear = $x("//*[text()='Год']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldOwner =  $x("//*[text()='Владелец']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldCVC = $x("//*[text()='CVC/CVV']/following-sibling::*/input[@class='input__control']");
    private SelenideElement buttonContinue = $x("//*[text()='Продолжить']");
    private SelenideElement notificationStatusOk = $x("//*[text()='Операция одобрена Банком.']");

    public PaymentPage() {
        headingCardPayment.shouldBe(visible);
    }

    public void getNotificationOk() {
        notificationStatusOk.shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(30));
    }

    public void fillingOutTheForm(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumber());
        fieldMonth.setValue(String.valueOf(info.getMonth()));
        fieldYear.setValue(info.getYear());
        fieldOwner.setValue(info.getOwner());
        fieldCVC.setValue(info.getCvc());
        buttonContinue.click();
    }

}
