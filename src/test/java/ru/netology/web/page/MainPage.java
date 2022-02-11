package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.mode.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private SelenideElement heading = $x("//*[@id='root']");
    private SelenideElement buttonBuy = $x("//*[text()='Купить']");
    private SelenideElement buttonBuyInCredit = $x("//*[text()='Купить в кредит']");

    public MainPage() {
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
