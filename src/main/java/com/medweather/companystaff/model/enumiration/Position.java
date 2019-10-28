package com.medweather.companystaff.model.enumiration;

public enum Position {

    HEAD("руководитель"),
    FINANCIAL_ANALYST("финансовый аналитик"),
    PERSONAL_FINANCIAL_ADVISOR("персональный финансовый консультант"),
    ACCOUNTANT("бухгалтер"),
    AUDITORS("аудитор"),
    LOAN_OFFICER("кредитный эксперт"),
    BANK_TELLERS("операционист"),
    TRADER("трейдер"),
    CHIEF_ACCOUNTANT("главный бухгалтер"),
    LEGAL("юрист");

    private String description;

    Position(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
