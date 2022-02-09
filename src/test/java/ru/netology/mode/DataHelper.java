package ru.netology.mode;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


public class DataHelper {
    private DataHelper() {

    }

    @Value
    public static class CardInfo {
        private String number;
        private int month;
        private String year;
        private String owner;
        private String cvc;
    }


    public static String generateYear() {
        var d1 = LocalDate.now();
        var d2 = LocalDate.now().plusYears(5);
        Date randomYear = new Date(ThreadLocalRandom.current().nextLong(d1.getYear(), d2.getYear()));
        DateFormat df = new SimpleDateFormat("yy");
        return (df.format(randomYear.getTime()));
    }



    public static CardInfo generateInfo(String locale) {
        Faker faker = new Faker(new Locale("en"));
        return new CardInfo("1111222233334444",
                faker.number().numberBetween(1, 12),
                generateYear(),
                faker.name().fullName(),
                faker.numerify("###"));
    }





}
