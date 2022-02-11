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
        String date = LocalDate.now().plusMonths(3).format(format);
        return date;
    }

    public String generateYear() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().plusYears(3).format(format);
        return date;
    }

    public String generateOwner(){
        String owner = faker.name().firstName() + " " + faker.name().lastName();
        return owner;
    }

    public String generateCVC(){
        return (faker.numerify("###"));
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
        return new CardInfo(getValidCardNumber(),generateMonth(), generateYear(), generateOwner(), generateCVC());
    }


}
