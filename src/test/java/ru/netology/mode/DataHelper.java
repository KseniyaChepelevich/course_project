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
        int randomNumber = ThreadLocalRandom.current().nextInt(60);
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

    public CardInfo getValidCardInfoEmptyYear() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "", generateOwner(), generateCVC());
    }

    public CardInfo getValidCardInfoEmptyMonth() {
        return new CardInfo(getValidCardNumber(), "", getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getValidCardInfoEmptyOwner() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "", generateCVC());
    }

    public CardInfo getValidCardInfoEmptyCVC() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), "");
    }

    public CardInfo getValidCardInfoWithNameWithoutSpace() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwnerNameWithoutSpace(), generateCVC());
    }

    public CardInfo getValidCardInfoNameInLatinWithAHyphen() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Mihail Petrov-Vodkin", generateCVC());
    }

    public CardInfo getValidCardInfoNameInLatinWithADot() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(2), "M. Petrov-Vodkin", generateCVC());
    }

    public CardInfo getValidCardInfoNameToUpperCase() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(2), generateOwnerToUpperCase(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNameToLowerCase() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwnerToLowerCase(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNameInCyrillic() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Иванов Иван", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithHieroglyphsName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "小名小名", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithNumbersName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "345126586", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithNonNumericAndNonAlphabeticCharactersName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "+*-=№?", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatinNameWithASpaceAtTheBeginning() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), " " + generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatinThreeLetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Yan", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatinTwoLetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "In", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatinFourLetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Yana", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatin21LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Innokentiy Kozlovskiy", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatin22LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Innokentiy Kozelovskiy", generateCVC());
    }

    public CardInfo getInvalidCardInfoWithLatin20LetterName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Innokentiy Kozlovski", generateCVC());
    }

    public CardInfo getInvalidCardInfoLastMonth() {
        return new CardInfo(getValidCardNumber(), generateDate(-1, "MM"), generateDate(-1, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getValidCardInfoCurrentMonth() {
        return new CardInfo(getValidCardNumber(), generateDate(0, "MM"), generateDate(0, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNonExistentMonth() {
        return new CardInfo(getValidCardNumber(), "22", generateDate(0, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfo00Month() {
        return new CardInfo(getValidCardNumber(), "00", generateDate(0, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNegativeMeaningMonth() {
        return new CardInfo(getValidCardNumber(), "-01", generateDate(0, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoMonthWithASpaceAtTheBeginning() {
        return new CardInfo(getValidCardNumber(), " " + getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoMonthWithASpaceInTheMiddle() {
        return new CardInfo(getValidCardNumber(), "0 5", getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoMonthWithNonAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), "*/", getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoMonthWithAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), "ab", getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoLastYear() {
        return new CardInfo(getValidCardNumber(), generateDate(-12, "MM"), generateDate(-12, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoUnrealYear() {
        return new CardInfo(getValidCardNumber(), generateDate(300, "MM"), generateDate(300, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfo00Year() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "00", generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoNegativeNumberYear() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "-" + getValidDate().substring(3), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearPlus6() {
        return new CardInfo(getValidCardNumber(), generateDate(72, "MM"), generateDate(72, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearPlus5() {
        return new CardInfo(getValidCardNumber(), generateDate(60, "MM"), generateDate(60, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearPlus4() {
        return new CardInfo(getValidCardNumber(), generateDate(48, "MM"), generateDate(48, "yy"), generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearWithNonAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "*/", generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoYearWithAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "ab", generateOwner(), generateCVC());
    }

    public CardInfo getInvalidCardInfoCVCWithNonAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), "*/=");
    }

    public CardInfo getInvalidCardInfoCVCWithAlphabeticCharacters() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), "abc");
    }

    public CardInfo getInvalidCardInfoCVCWith2Numbers() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC2Numbers());
    }

    public CardInfo getInvalidCardInfoCVCWith4Numbers() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC4Numbers());
    }

    public CardInfo getInvalidCardInfo() {
        return new CardInfo(getInvalidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateOwner(), generateCVC());
    }


}
