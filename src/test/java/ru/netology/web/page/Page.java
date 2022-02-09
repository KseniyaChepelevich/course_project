package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.mode.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class Page {
    private SelenideElement heading = $("[id=root]");
    private SelenideElement buttonBuy = $(".button__text").$(withText("Купить"));
    private SelenideElement buttonBuyInCredit = $(".button__text").$(withText("Купить в кредит"));
    private SelenideElement headingCardPayment = $(".heading").$(withText("Оплата по карте"));
    private SelenideElement  cardNumberField = $(".input").$(withText("Номер карты"));
    private SelenideElement fieldMonth = $(".input").$(withText("Месяц"));
    private SelenideElement fieldYear = $(".input").$(withText("Год"));
    private SelenideElement fieldOwner = $(".input").$(withText("Владелец"));
    private SelenideElement fieldCVC = $(".input").$(withText("CVC/CVV"));
    private SelenideElement buttonContinue = $(".button__text").$(withText("Продолжить"));

    private SelenideElement headingCardCredit= $(".heading").$(withText("Кредит по данным карты"));


    public Page() {
        heading.shouldBe(visible);
    }

    public void fillingOutTheForm(DataHelper.CardInfo info) {
        buttonBuy.click();
        headingCardPayment.shouldBe(visible);
        cardNumberField.setValue(info.getNumber());
        fieldMonth.setValue(info.getMonth());
        fieldYear.setValue(info.getYear());
        fieldOwner.setValue(info.getOwner());
        fieldCVC.setValue(info.getCvc());
        buttonContinue.click();



    }

}
