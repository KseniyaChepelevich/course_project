package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreditPage {
    private SelenideElement headingCardCredit= $x("//*[text()='Кредит по данным карты']");
    private SelenideElement cardNumberField = $("[maxlength=\"19\"]");
    private SelenideElement fieldMonth = $x("//*[text()='Месяц']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldYear = $x("//*[text()='Год']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldOwner =  $x("//*[text()='Владелец']/following-sibling::*/input[@class='input__control']");
    private SelenideElement fieldCVC = $x("//*[text()='CVC/CVV']/following-sibling::*/input[@class='input__control']");
    private SelenideElement buttonContinue = $x("//*[text()='Продолжить']");
    private SelenideElement notificationStatusOk = $x("//*[text()='Операция одобрена Банком.']");
}
