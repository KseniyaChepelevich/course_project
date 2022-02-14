package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.mode.DBHelper;
import ru.netology.mode.DataHelper;

import ru.netology.web.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentTest {

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
    public void shouldPaymentApprovedCard() {

        val cardInfo = new DataHelper().getValidCardInfo();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getButtonSendARequest();
        paymentPage.getNotificationOk();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);

    }

    @Test
    public void shouldDeniedPurchaseDeclinedCard() {

        val cardInfo = new DataHelper().getInvalidCardInfo();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationError();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("DECLINED", paymentStatus);
        assertNull(creditStatus);
    }

    @Test
    public void shouldRefuseToSubmitAnEmptyForm() {
        Configuration.holdBrowserOpen = true;
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.clickButtonContinue();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val creditStatus = new DBHelper().getCreditStatus();
        paymentPage.getErrorNotificationCardNumberRequired();
        paymentPage.getErrorNotificationMonthRequired();
        paymentPage.getErrorNotificationYearRequired();
        paymentPage.getErrorNotificationOwnerRequired();
        paymentPage.getErrorNotificationCVCRequired();
        assertNull(paymentStatus);
        assertNull(creditStatus);
    }

    @Test
    public void shouldPaymentApprovedCardOwnerNameWithoutSpace() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoWithNameWithoutSpace();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertNull(creditStatus);
    }

    @Test
    public void shouldPaymentApprovedCardOwnerNameInLatinWithAHyphen() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoNameInLatinWithAHyphen();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertNull(creditStatus);
    }

    @Test
    public void shouldPaymentApprovedCardOwnerNameInLatinWithADot() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoNameInLatinWithADot();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertNull(creditStatus);
    }

    @Test
    public void shouldPaymentApprovedCardOwnerNameToUpperCase() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoNameToUpperCase();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertNull(creditStatus);
    }

    @Test
    public void shouldGetAnErrorApprovedCardOwnerNameToLowerCase() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoNameToLowerCase();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertNull(creditStatus);
    }
}
