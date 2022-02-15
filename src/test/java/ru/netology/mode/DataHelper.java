package ru.netology.mode;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


public class DataHelper {

    private Faker faker = new Faker();

    private String getValidCardNumber() {
        return "1111 2222 3333 4444";
    }

    private String getInvalidCardNumber() {
        return "5555 6666 7777 8888";
    }



    public  String generateMonth() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM");
        int randomNumber = ThreadLocalRandom.current().nextInt(13);
        String date = LocalDate.now().plusMonths(randomNumber).format(format);
        return date;
    }

    public String generateYear() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        int randomNumber = ThreadLocalRandom.current().nextInt(6);
        String date = LocalDate.now().plusYears(randomNumber).format(format);
        return date;
    }

    public String generateOwner(){
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        return owner;
    }

    public String generateCVC(){
        return (faker.numerify("###"));
    }

    public String generateOwnerNameWithoutSpace(){
        String owner = faker.name().firstName() + faker.name().lastName();
        return owner;
    }

    public String generateOwnerToUpperCase(){
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        return owner;
    }

    public String generateOwnerToLowerCase(){
        String owner = faker.name().firstName().toLowerCase() + " " + faker.name().lastName().toLowerCase();
        return owner;
    }

    public  String generateLastMonth() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM");
        String date = LocalDate.now().plusMonths(-3).format(format);
        return date;
    }

    public  String generateCurrentMonth() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM");
        String date = LocalDate.now().format(format);
        return date;
    }

    public String generateCurrentYear() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().format(format);
        return date;
    }

    public String generateLastYear() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().plusYears(-1).format(format);
        return date;
    }

    public String generateUnrealYear() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().plusYears(25).format(format);
        return date;
    }

    public String generatePlus6Year() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().plusYears(6).format(format);
        return date;
    }

    public String generatePlus5Year() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().plusYears(5).format(format);
        return date;
    }

    public String generatePlus4Year() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().plusYears(4).format(format);
        return date;
    }

    public String generateCVC4Numbers(){
        return (faker.numerify("####"));
    }

    public String generateCVC2Numbers(){
        return (faker.numerify("##"));
    }





//    public static String generateValidMonth() {
//        Month now = Month.
//        int randomNumber = ThreadLocalRandom.current().nextInt(13);
//
//        Month randomDate = now.plusMonths(randomNumber);
//        DateFormat df = new SimpleDateFormat("MM");
//        return (df.format(randomDate.getMonthValue()));
//        var d1 = LocalDate.now();
//        var d2 = LocalDate.now().plusYears(5);
//        Date randomYear = new Date(ThreadLocalRandom.current().nextLong(d1.getYear(), d2.getYear()));
//        DateFormat df = new SimpleDateFormat("yy");
//        return (df.format(randomYear));
//    }






    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String owner;
        private String cvc;

    }

    public CardInfo getValidCardInfo() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwner(), generateCVC());
    }

    public CardInfo getValidCardInfoEmptyYear() {
        return new CardInfo(getValidCardNumber(), generateMonth(), "", generateOwner(), generateCVC());
    }

    public CardInfo getValidCardInfoEmptyOwner() {
        return new CardInfo(getValidCardNumber(), "", generateYear(), generateOwner(), generateCVC());
    }

    public CardInfo getValidCardInfoEmptyMonth() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "", generateCVC());
    }

    public CardInfo getValidCardInfoEmptyCVC() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwner(), "");
    }


    public CardInfo getValidCardInfoWithNameWithoutSpace() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwnerNameWithoutSpace(), generateCVC());
    }

    public CardInfo getValidCardInfoNameInLatinWithAHyphen(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "Mihail Petrov-Vodkin", generateCVC());
    }

    public CardInfo getValidCardInfoNameInLatinWithADot(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "M. Petrov-Vodkin", generateCVC());
    }

    public CardInfo getValidCardInfoNameToUpperCase(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwnerToUpperCase(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNameToLowerCase(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwnerToLowerCase(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNameInCyrillic(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "Иванов Иван", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithHieroglyphsName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "小名小名", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithNumbersName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "345126586", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithNonNumericAndNonAlphabeticCharactersName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "+*-=№?", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatinNameWithASpaceAtTheBeginning(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), " " + generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatinThreeLetterName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "Yan", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatinTwoLetterName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "In", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatinFourLetterName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "Yana", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatin21LetterName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "Innokentiy Kozlovskiy", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatin22LetterName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "Innokentiy Kozelovskiy", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatin20LetterName(){
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), "Innokentiy Kozlovski", generateCVC());
    }

    public CardInfo getVInvalidCardInfoLastMonth() {
        return new CardInfo(getValidCardNumber(), generateLastMonth(), generateCurrentYear(), generateOwner(), generateCVC());
    }

    public CardInfo getValidCardInfoCurrentMonth() {
        return new CardInfo(getValidCardNumber(), generateCurrentMonth(), generateCurrentYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNonExistentMonth() {
        return new CardInfo(getValidCardNumber(), "22", generateCurrentYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfo00Month() {
        return new CardInfo(getValidCardNumber(), "00", generateCurrentYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNegativeMeaningMonth() {
        return new CardInfo(getValidCardNumber(), "-01", generateYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoMonthWithASpaceAtTheBeginning() {
        return new CardInfo(getValidCardNumber(), " " + generateMonth(), generateYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoMonthWithASpaceInThrMiddle() {
        return new CardInfo(getValidCardNumber(), "0 5", generateYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoMonthWithNonAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), "*/", generateYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoMonthWithAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), "ab", generateYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoLastYear() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateLastYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoUnrealYear() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateUnrealYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfo00Year() {
        return new CardInfo(getValidCardNumber(), generateMonth(), "00", generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNegativeNumberYear() {
        return new CardInfo(getValidCardNumber(), generateMonth(), "-" + generateYear(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearPlus6() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generatePlus6Year(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearPlus5() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generatePlus5Year(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearPlus4() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generatePlus4Year(), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearWithNonAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), generateMonth(), "*/", generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearWithAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), generateMonth(), "ab", generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoCVCWithNonAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwner(), "*/=");
    }

    public CardInfo getInvalidCardInfoCVCWithAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwner(), "abc");
    }

    public CardInfo getInvalidCardInfoCVCWith2Numbers() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwner(), generateCVC2Numbers());
    }

    public CardInfo getInvalidCardInfoCVCWith4Numbers() {
        return new CardInfo(getValidCardNumber(), generateMonth(), generateYear(), generateOwner(), generateCVC4Numbers());
    }

    public CardInfo getInvalidCardInfo() {
        return new CardInfo(getInvalidCardNumber(), generateMonth(), generateYear(), generateOwner(), generateCVC());
    }


















}
