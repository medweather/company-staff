package com.medweather.companystaff.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    private static final String DATE_PATTERN = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";

    private static final String FIO_PATTERN = "^([а-яА-ЯёЁ\\-])+$";

    private static final String PHONE_PATTERN = "^([0-9\\-+()])+$";

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    private static final String SALARY_PATTERN = "^([0-9])+$";


    public static boolean isValidDate(String date) {
        return isValid(DATE_PATTERN, date);
    }

    public static boolean isValidFIO(String name, String lastName, String patronymic) {
        String fio = name + lastName + patronymic;
        return isValid(FIO_PATTERN, fio);
    }

    public static boolean isValidPhone(String phone) {
        return isValid(PHONE_PATTERN, phone);
    }

    public static boolean isValidEmail(String email) {
        return isValid(EMAIL_PATTERN, email);
    }

    public static boolean isValidSalary(String salary) {
        return isValid(SALARY_PATTERN, salary);
    }

    private static boolean isValid(String PATTERN, String example) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(example);
        return matcher.matches();
    }
}
