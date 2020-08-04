package tsi.too.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tsi.too.model.Gender;
import tsi.too.model.HealthProfile;
import tsi.too.util.InputDialog;
import tsi.too.util.MessageDialog;

import javax.swing.*;
import java.time.LocalDate;

import static tsi.too.model.Constants.*;

public class HealthRecords {
    private HealthProfile healthProfile;

    public void readData() {
        String name = InputDialog.showStringInputDialog(USER_DATA, NAME, input -> !input.isEmpty());
        if (name == null)
            return;

        String surName = InputDialog.showStringInputDialog(USER_DATA, SURNAME, input -> !input.isEmpty());
        if (surName == null)
            return;

        LocalDate birthDate = InputDialog.showBrazilianDateInputDialog(
                USER_DATA,
                BIRTH_DATE_WITH_HINT,
                this::validateBirthDate
        );
        if (birthDate == null)
            return;

        Gender gender = readGender();
        if (gender == null)
            return;

        Double height = InputDialog.showDoubleInputDialog(USER_DATA, HEIGHT_WITH_HINT, input -> input < MAX_HEIGHT && input > 0);
        if (height == null)
            return;

        Double weight = InputDialog.showDoubleInputDialog(USER_DATA, WEIGHT_WITH_HINT, input -> input > 0 && input < MAX_WEIGHT);
        if (weight == null)
            return;

        healthProfile = new HealthProfile(name, surName, birthDate, gender, height, weight);
    }

    private boolean validateBirthDate(@NotNull LocalDate birthDate) {
        LocalDate minBirthDate = LocalDate.now().withYear(MIN_BIRTH_YEAR);

        return birthDate.isBefore(LocalDate.now()) && birthDate.isAfter(minBirthDate);
    }

    @Nullable
    private Gender readGender() {
        int option = JOptionPane.showOptionDialog(null,
                GENDER,
                USER_DATA,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                Gender.values(),
                Gender.MALE
        );

        if (option != JOptionPane.CLOSED_OPTION)
            try {
                return Gender.from(option);
            } catch (Exception ignored) {
            }

        return null;
    }

    public void showData() {
        if (healthProfile == null) {
            MessageDialog.showAlertDialog(HEALTH_RECORDS, NO_DATA_TO_SHOW);
            return;
        }

        MessageDialog.showInformationDialog(HEALTH_RECORDS, buildMessage());
    }

    @NotNull
    private String buildMessage() {
        return String.format("%s: %s\n", NAME, healthProfile.getName()) +
                String.format("%s: %s\n", AGE, healthProfile.getAge()) +
                String.format("%s: %s\n", BIRTH_DATE, healthProfile.getBirthDate()) +
                String.format("%s: %s\n", GENDER, healthProfile.getGender()) +
                String.format("%s: %d %s\n", MAXIMUM_FREQUENCY, healthProfile.getMaximumHeartRate(), BPM) +
                String.format("%s: %1.2f %s\n", MAX_TARGET_RATE, healthProfile.getMaxTargetRate(), BPM) +
                String.format("%s: %1.2f %s\n", MIN_TARGET_RATE, healthProfile.getMinTargetRate(), BPM) +
                String.format("%s: %1.3f %s\n", BODY_MAX_INDEX, healthProfile.getIMC(), BMI_UNIT_OF_MEASURE)
                ;
    }
}
