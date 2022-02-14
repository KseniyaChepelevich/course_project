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



    @BeforeEach
    void setUp(){
        open("http://localhost:8080");
    }

    @BeforeEach
    void deletingDataFromTheDb() {
        DBHelper.DeleteInfo.deletingData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
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
        val creditStatus = new DBHelper().getCreditStatus();
        val paymentID = new DBHelper().getPaymentId();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);

    }

    @Test
    public void shouldDeniedPurchaseDeclinedCard() {

        val cardInfo = new DataHelper().getInvalidCardInfo();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationError();
        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val creditStatus = new DBHelper().getCreditStatus();
        val paymentID = new DBHelper().getPaymentId();
        assertEquals("DECLINED", paymentStatus);
        assertEquals(transactionId, paymentID);
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
        //Проверить что не появилось записей в бд
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
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
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
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
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
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
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
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldGetAnErrorApprovedCardOwnerNameToLowerCase() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoNameToLowerCase();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldGetAnErrorApprovedCardNameInCyrillic() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoNameInCyrillic();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldGetAnErrorApprovedCardNameInHieroglyphs() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithHieroglyphsName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldGetAnErrorApprovedCardNameNumbers() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithNumbersName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldGetAnErrorApprovedCardNameNonNumericAndNonAlphabeticCharacters() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithNonNumericAndNonAlphabeticCharactersName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldPaymentApprovedCardNameWithASpaceAtTheBeginning() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatinNameWithASpaceAtTheBeginning();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        //Добавить проверку на игнорирование пробела в начале имени владельца
        paymentPage.getNotificationOk();

        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldPaymentApprovedCardNameWithThreeLetter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatinThreeLetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();

        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldPaymentApprovedCardNameWithTwoLetter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatinTwoLetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();

        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    @Test
    public void shouldPaymentApprovedCardNameWithFourLetter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatinFourLetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();

        val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = new DBHelper().getTransactionId();
        val paymentID = new DBHelper().getPaymentId();
        val creditStatus = new DBHelper().getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }
}
