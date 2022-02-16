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
    void setUp() {
        open("http://localhost:8080");
    }

    @BeforeEach
    void deletingDataFromTheDb() {
        DBHelper.deletingData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    //Тест №1
    @Test
    public void shouldPaymentApprovedCard() {
        val cardInfo = new DataHelper().getValidCardInfo();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getButtonSendARequest();
        paymentPage.getNotificationOk();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val creditStatus = DBHelper.getCreditStatus();
        val paymentID = DBHelper.getPaymentId();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }


    //Тест №2
    @Test
    public void shouldPaymentApprovedCardOwnerNameWithoutSpace() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoWithNameWithoutSpace();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №3
    @Test
    public void shouldPaymentApprovedCardOwnerNameInLatinWithAHyphen() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoNameInLatinWithAHyphen();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №4
    @Test
    public void shouldPaymentApprovedCardOwnerNameInLatinWithADot() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoNameInLatinWithADot();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №5
    @Test
    public void shouldPaymentApprovedCardOwnerNameToUpperCase() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoNameToUpperCase();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №6
    @Test
    public void shouldGetAnErrorApprovedCardOwnerNameToLowerCase() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoNameToLowerCase();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        //val paymentStatus = new DBHelper().getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }


    //Тест №7
    @Test
    public void shouldRefuseToSubmitAnEmptyForm() {
        Configuration.holdBrowserOpen = true;
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.clickButtonContinue();
        val paymentStatus = DBHelper.getPaymentStatus();
        val creditStatus = DBHelper.getCreditStatus();
        paymentPage.getErrorNotificationCardNumberRequired();
        paymentPage.getErrorNotificationMonthRequired();
        paymentPage.getErrorNotificationYearRequired();
        paymentPage.getErrorNotificationOwnerRequired();
        paymentPage.getErrorNotificationCVCRequired();
        //Проверить что не появилось записей в бд
        assertNull(paymentStatus);
        assertNull(creditStatus);
    }

    //Тест №8
    @Test
    public void shouldGetErrorApprovedCardEmptyCVC() {
        val cardInfo = new DataHelper().getValidCardInfoEmptyCVC();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationCVCRequired();
        //Проверяем, что нет лишних сообщений об ошибках
        paymentPage.notGetErrorNotificationCardNumberRequired();
        paymentPage.notGetErrorNotificationMonthRequired();
        paymentPage.notGetErrorNotificationYearRequired();
        paymentPage.notGetErrorNotificationOwnerRequired();
        paymentPage.notGetWrongFormatMonthField();
        paymentPage.notGetWrongFormatOwnerField();
        paymentPage.notGetWrongFormatYearField();
        //Проверяем, что не появилось в бд записей
        val transactionId = DBHelper.getTransactionId();
        val creditStatus = DBHelper.getCreditStatus();
        val paymentID = DBHelper.getPaymentId();
        assertNull(transactionId);
        assertNull(creditStatus);
    }

    //Тест №9
    @Test
    public void shouldGetErrorApprovedCardEmptyOwner() {
        val cardInfo = new DataHelper().getValidCardInfoEmptyOwner();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationOwnerRequired();
        //Проверяем, что нет лишних сообщений об ошибках
        paymentPage.notGetErrorNotificationCardNumberRequired();
        paymentPage.notGetErrorNotificationMonthRequired();
        paymentPage.notGetErrorNotificationYearRequired();
        paymentPage.notGetErrorNotificationCVCRequired();
        paymentPage.notGetWrongFormatYearField();
        paymentPage.notGetWrongFormatMonthField();
        //Проверяем, что не появилось в бд записей
        val transactionId = DBHelper.getTransactionId();
        val creditStatus = DBHelper.getCreditStatus();
        val paymentID = DBHelper.getPaymentId();
        assertNull(transactionId);
        assertNull(creditStatus);
    }

    //Тест №10
    @Test
    public void shouldGetErrorApprovedCardEmptyMonth() {
        val cardInfo = new DataHelper().getValidCardInfoEmptyMonth();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationMonthRequired();
        //Проверяем, что нет лишних сообщений об ошибках
        paymentPage.notGetErrorNotificationCardNumberRequired();
        paymentPage.notGetErrorNotificationOwnerRequired();
        ;
        paymentPage.notGetErrorNotificationYearRequired();
        paymentPage.notGetErrorNotificationCVCRequired();
        paymentPage.notGetWrongFormatYearField();
        paymentPage.notGetWrongFormatOwnerField();
        //Проверяем, что не появилось в бд записей
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val creditStatus = DBHelper.getCreditStatus();
        val paymentID = DBHelper.getPaymentId();
        assertNull(transactionId);
        assertNull(creditStatus);
    }

    //Тест №11
    @Test
    public void shouldGetErrorApprovedCardEmptyYear() {
        val cardInfo = new DataHelper().getValidCardInfoEmptyYear();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationYearRequired();
        //Проверяем, что нет лишних сообщений об ошибках
        paymentPage.notGetErrorNotificationCardNumberRequired();
        paymentPage.notGetErrorNotificationOwnerRequired();
        ;
        paymentPage.notGetErrorNotificationMonthRequired();
        paymentPage.notGetErrorNotificationCVCRequired();
        paymentPage.notGetWrongFormatMonthField();
        paymentPage.notGetWrongFormatOwnerField();
        //Проверяем, что не появилось в бд записей
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val creditStatus = DBHelper.getCreditStatus();
        val paymentID = DBHelper.getPaymentId();
        assertNull(transactionId);
        assertNull(creditStatus);
    }

    //Тест №12
    @Test
    public void shouldGetAnErrorApprovedCardNameInCyrillic() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoNameInCyrillic();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №13
    @Test
    public void shouldGetAnErrorApprovedCardNameInHieroglyphs() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithHieroglyphsName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №14
    @Test
    public void shouldGetAnErrorApprovedCardNameNumbers() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithNumbersName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №15
    @Test
    public void shouldGetAnErrorApprovedCardNameNonNumericAndNonAlphabeticCharacters() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithNonNumericAndNonAlphabeticCharactersName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();
        //Проверить, что не появилось новых записей в бд
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №16
    @Test
    public void shouldPaymentApprovedCardNameWithASpaceAtTheBeginning() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatinNameWithASpaceAtTheBeginning();
        val paymentPage = new OrderPage().getPaymentPage();

        paymentPage.fillingOutTheForm(cardInfo);
        //Получаем фактическое значение в поле Владелец и ожидаемое (без пробела в начале)
        val actualValueOwner = paymentPage.getValueOwner();
        val expectedValueOwner = cardInfo.getOwner().trim();

        paymentPage.getNotificationOk();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        //Проверяем игнорирует ли поле Владелец пробел в начале имени
        assertEquals(expectedValueOwner, actualValueOwner);
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №17
    @Test
    public void shouldPaymentApprovedCardNameWithThreeLetter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatinThreeLetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №18
    @Test
    public void shouldErrorOwnerFieldApprovedCardNameWithTwoLetter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatinTwoLetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatOwnerField();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №19
    @Test
    public void shouldPaymentApprovedCardNameWithFourLetter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatinFourLetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №20
    @Test
    public void shouldPaymentApprovedCardNameWith21Letter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatin21LetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №21
    @Test
    public void shouldPaymentApprovedCardNameWith22Letter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatin22LetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);

        //Получаем фактическое значение в поле Владелец
        val actualValueOwner = paymentPage.getValueOwner();
        paymentPage.getNotificationOk();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        //Проверка удаляет ли последнюю букву имени
        assertEquals("Innokentiy Kozelovski", actualValueOwner);
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №22
    @Test
    public void shouldPaymentApprovedCardNameWith20Letter() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoWithLatin20LetterName();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №23
    @Test
    public void shouldErrorApprovedCardLastMonth() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoLastMonth();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatYearField();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №24
    @Test
    public void shouldPaymentApprovedCardCurrentMonth() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getValidCardInfoCurrentMonth();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №25
    @Test
    public void shouldErrorApprovedCardNonExistentMonth() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoNonExistentMonth();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatYearField();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №26
    @Test
    public void shouldErrorApprovedCard00Month() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfo00Month();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatYearField();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №27
    @Test
    public void shouldPaymentApprovedCardNegativeMonth() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoNegativeMeaningMonth();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        // Получаем фактическое значение в поле Месяц
        val actualValueMonth = paymentPage.getValueMonth();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        //Проверяем, что минус отсекся
        assertEquals("01", actualValueMonth);
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №28
    @Test
    public void shouldPaymentApprovedCardMonthWithASpaceInBeginning() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoMonthWithASpaceAtTheBeginning();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        // Получаем фактическое и ожидаемое значение поля Месяц
        val actualValueMonth = paymentPage.getValueMonth();
        val expectedValueMonth = cardInfo.getMonth().trim();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        //Проверяем что пробел в начале отсекся
        assertEquals(expectedValueMonth, actualValueMonth);
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №29
    @Test
    public void shouldPaymentApprovedCardMonthWithASpaceInTheMiddle() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoMonthWithASpaceInTheMiddle();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        // Получаем фактическое значение поля Месяц
        val actualValueMonth = paymentPage.getValueMonth();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        //Проверяем что пробел в начале отсекся
        assertEquals("05", actualValueMonth);
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №30
    @Test
    public void shouldErrorApprovedCardMonthNonAlphabeticCharacters() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoMonthWithNonAlphabeticCharacters();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationMonthRequired();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №31
    @Test
    public void shouldErrorApprovedCardMonthAlphabeticCharacters() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoMonthWithAlphabeticCharacters();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationMonthRequired();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №32
    @Test
    public void shouldErrorApprovedCardLastYear() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoLastYear();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatYearField();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №33
    @Test
    public void shouldErrorApprovedCardUnrealYear() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoUnrealYear();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatYearField();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №34
    @Test
    public void shouldErrorApprovedCard00Year() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfo00Year();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatYearField();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №35
    @Test
    public void shouldPaymentApprovedCardNegativeNumberYear() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoNegativeNumberYear();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        // Получаем фактическое и ожидаемое значение поля Год
        val actualValueYear = paymentPage.getValueYear();
        val expectedValueYear = cardInfo.getYear().substring(1);
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals(expectedValueYear, actualValueYear);
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №36
    @Test
    public void shouldErrorApprovedCardPlus6Year() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoYearPlus6();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getWrongFormatYearField();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест № 37
    @Test
    public void shouldPaymentApprovedCardPlus5Year() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoYearPlus5();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №38
    @Test
    public void shouldPaymentApprovedCardPlus4Year() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoYearPlus4();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №39
    @Test
    public void shouldErrorApprovedCardNonAlphabeticYear() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoYearWithNonAlphabeticCharacters();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationYearRequired();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №40
    @Test
    public void shouldErrorApprovedCardAlphabeticYear() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoYearWithAlphabeticCharacters();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationYearRequired();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №41
    @Test
    public void shouldErrorApprovedCardNonAlphabeticCVC() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoCVCWithNonAlphabeticCharacters();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationCVCRequired();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №42
    @Test
    public void shouldErrorApprovedCardAlphabeticCVC() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoCVCWithAlphabeticCharacters();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationCVCRequired();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №43
    @Test
    public void shouldErrorApprovedCard2NumbersCVC() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoCVCWith2Numbers();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getErrorNotificationCVCRequired();

        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertNull(transactionId);
        assertNull(paymentID);
        assertNull(creditStatus);
    }

    //Тест №44
    @Test
    public void shouldPaymentApprovedCard4NumbersCVC() {
        Configuration.holdBrowserOpen = true;
        val cardInfo = new DataHelper().getInvalidCardInfoCVCWith4Numbers();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationOk();
        // Получаем фактическое и ожидаемое значение поля CVC
        val actualValueCVC = paymentPage.getValueCVC();
        val expectedValueCVC = paymentPage.removeLastChar(cardInfo.getCvc());
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val paymentID = DBHelper.getPaymentId();
        val creditStatus = DBHelper.getCreditStatus();
        assertEquals(expectedValueCVC, actualValueCVC);
        assertEquals("APPROVED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }

    //Тест №45
    @Test
    public void shouldDeniedPurchaseDeclinedCard() {
        val cardInfo = new DataHelper().getInvalidCardInfo();
        val paymentPage = new OrderPage().getPaymentPage();
        paymentPage.fillingOutTheForm(cardInfo);
        paymentPage.getNotificationError();
        val paymentStatus = DBHelper.getPaymentStatus();
        val transactionId = DBHelper.getTransactionId();
        val creditStatus = DBHelper.getCreditStatus();
        val paymentID = DBHelper.getPaymentId();
        assertEquals("DECLINED", paymentStatus);
        assertEquals(transactionId, paymentID);
        assertNull(creditStatus);
    }
}
