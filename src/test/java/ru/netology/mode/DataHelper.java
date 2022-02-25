package ru.netology.mode;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class DataHelper {

    private Faker faker = new Faker();

    private String getValidCardNumber() {
        return "1111 2222 3333 4444";
    }

    private String getInvalidCardNumber() {
        return "5555 6666 7777 8888";
    }

    public String generateDate(int plusMonth, String formatPattern) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public String getValidDate() {
        int randomNumber = ThreadLocalRandom.current().nextInt(1,60);
        String date = generateDate(randomNumber, "MM.yy");
        return date;
    }

    public String generateOwner() {
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        return owner;
    }

    public String generateCVC() {
        return (faker.numerify("###"));
    }

    public String generateOwnerNameWithoutSpace() {
        String owner = faker.name().firstName() + faker.name().lastName();
        return owner;
    }

    public String generateOwnerToUpperCase() {
        String owner = faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
        return owner;
    }

    public String generateOwnerToLowerCase() {
        String owner = faker.name().firstName().toLowerCase() + " " + faker.name().lastName().toLowerCase();
        return owner;
    }

    public String generateCVC4Numbers() {
        return (faker.numerify("####"));
    }

    public String generateCVC2Numbers() {
        return (faker.numerify("##"));
    }

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String owner;
        private String cvc;
    }

    public CardInfo getValidCardInfo() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInfoEmptyYear() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "", generateOwner(), generateCVC());
    }

    public CardInfo getInfoEmptyMonth() {
        return new CardInfo(getValidCardNumber(), "", getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInfoEmptyOwner() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "", generateCVC());
    }

    public CardInfo getInfoEmptyCVC() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), "");
    }

    public CardInfo getInfoWithNameWithoutSpace() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwnerNameWithoutSpace(), generateCVC());
    }

    public CardInfo getInfoNameInLatinWithAHyphen() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Mihail Petrov-Vodkin", generateCVC());
    }

    public CardInfo getInfoNameInLatinWithADot() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(2), "M. Petrov-Vodkin", generateCVC());
    }

    public CardInfo getInfoNameToUpperCase() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(2), generateOwnerToUpperCase(), generateCVC());
    }

    public CardInfo getInfoNameToLowerCase() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwnerToLowerCase(), generateCVC());
    }

    public CardInfo getInfoNameInCyrillic() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Иванов Иван", generateCVC());
    }

    public CardInfo getInfoHieroglyphsName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "小名小名", generateCVC());
    }

    public CardInfo getInfoNumbersName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "345126586", generateCVC());
    }

    public CardInfo getInfoNonNumericNonAlphabeticName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "+*-=№?", generateCVC());
    }

    public CardInfo getInfoLatinNameBeginningSpace() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), " " + generateOwner(), generateCVC());
    }

    public CardInfo getInfoWithLatin3LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Yan", generateCVC());
    }

    public CardInfo getInfoWithLatin2LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "In", generateCVC());
    }

    public CardInfo getInfoWithLatin4LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Yana", generateCVC());
    }

    public CardInfo getInfoWithLatin21LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Innokentiy Kozlovskiy", generateCVC());
    }

    public CardInfo getInfoWithLatin22LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Innokentiy Kozelovskiy", generateCVC());
    }

    public CardInfo getInfoWithLatin20LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Innokentiy Kozlovski", generateCVC());
    }

    public CardInfo getInfoLastMonth() {
        return new CardInfo(getValidCardNumber(), generateDate(-1, "MM"), generateDate(-1, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfoCurrentMonth() {
        return new CardInfo(getValidCardNumber(), generateDate(0, "MM"), generateDate(0, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfoNonExistentMonth() {
        return new CardInfo(getValidCardNumber(), "22", generateDate(0, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfo00Month() {
        return new CardInfo(getValidCardNumber(), "00", generateDate(0, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfoNegativeMeaningMonth() {
        return new CardInfo(getValidCardNumber(), "-01", generateDate(12, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfoMonthBeginningSpace() {
        return new CardInfo(getValidCardNumber(), " " + getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInfoMonthSpaceInTheMiddle() {
        return new CardInfo(getValidCardNumber(), "0 5", generateDate(12, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfoMonthNonAlphabetic() {
        return new CardInfo(getValidCardNumber(), "*/", getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInfoMonthLetters() {
        return new CardInfo(getValidCardNumber(), "ab", getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInfoLastYear() {
        return new CardInfo(getValidCardNumber(), generateDate(-12, "MM"), generateDate(-12, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoUnrealYear() {
        return new CardInfo(getValidCardNumber(), generateDate(300, "MM"), generateDate(300, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfo00Year() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "00", generateOwner(), generateCVC());
    }

    public CardInfo getInfoNegativeNumberYear() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "-" + getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInfoYearPlus6() {
        return new CardInfo(getValidCardNumber(), generateDate(72, "MM"), generateDate(72, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfoYearPlus5() {
        return new CardInfo(getValidCardNumber(), generateDate(60, "MM"), generateDate(60, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfoYearPlus4() {
        return new CardInfo(getValidCardNumber(), generateDate(48, "MM"), generateDate(48, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInfoYearNonAlphabetic() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "*/", generateOwner(), generateCVC());
    }

    public CardInfo getInfoLettersYear() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "ab", generateOwner(), generateCVC());
    }

    public CardInfo getInfoCVCNonAlphabetic() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), "*/=");
    }

    public CardInfo getInfoWithLettersCVC() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), "abc");
    }

    public CardInfo getInfoWith2NumbersCVC() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC2Numbers());
    }

    public CardInfo getInfoWith4NumbersCVC() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC4Numbers());
    }

    public CardInfo getInvalidCardInfo() {
        return new CardInfo(getInvalidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC());
    }


}
