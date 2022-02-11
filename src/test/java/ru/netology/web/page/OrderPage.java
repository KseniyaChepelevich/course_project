package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {
    private SelenideElement heading = $x("//*[@id='root']");
    private SelenideElement buttonBuy = $x("//*[text()='Купить']");
    private SelenideElement buttonBuyInCredit = $x("//*[text()='Купить в кредит']");

    public OrderPage() {
        heading.shouldBe(visible);
    }

    public PaymentPage getPaymentPage(){
        buttonBuy.click();
        return new PaymentPage();
    }

    public CreditPage getCreditPage(){
        buttonBuyInCredit.click();
        return new CreditPage();
    }



}
