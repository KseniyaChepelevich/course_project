package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.mode.DataHelper;

import ru.netology.web.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;

public class PositiveTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp(){
        open("http://localhost:8080");
    }

    @Test
    public void shouldAuthorizationIsSuccessful() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfo();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();

    }
}
