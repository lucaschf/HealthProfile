package tsi.too.model;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;

public class HealthProfile {
    private String firstName;
    private String surName;

    private int birthYear;
    private int birthMonth;
    private int birthDay;

    private Gender gender;

    private double height;
    private double weight;

    public HealthProfile(
            String firstName,
            String surName,
            int birthYear,
            int birthMonth,
            int birthDay,
            Gender gender,
            double height,
            double weight
    ) {
        this.firstName = firstName;
        this.surName = surName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public HealthProfile(
            String firstName,
            String surName,
            @NotNull LocalDate birthDate,
            Gender gender,
            double height,
            double weight
    ) {
        this.firstName = firstName;
        this.surName = surName;
        this.birthYear = birthDate.getYear();
        this.birthMonth = birthDate.getMonthValue();
        this.birthDay = birthDate.getDayOfMonth();
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return MessageFormat.format("{0} {1}", firstName, surName);
    }

    public LocalDate getBirthDate() {
        return LocalDate.of(birthYear, birthMonth, birthDay);
    }

    public int getAge() {
        return Period.between(getBirthDate(), LocalDate.now()).getYears();
    }

    public int getMaximumHeartRate() {
        return 220 - getAge();
    }

    @NotNull
    public BigDecimal getMinTargetRate() {
        return BigDecimal.valueOf(0.6 * getMaximumHeartRate());
    }

    @NotNull
    public BigDecimal getMaxTargetRate() {
        return BigDecimal.valueOf(0.85 * getMaximumHeartRate());
    }

    public BigDecimal getIMC() {
        return BigDecimal.valueOf(weight / (height * height));
    }

    @Override
    public String toString() {
        return "HealthProfile{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", birthYear=" + birthYear +
                ", birthMonth=" + birthMonth +
                ", birthDay=" + birthDay +
                ", gender=" + gender +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}