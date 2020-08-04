package tsi.too.model;

import org.jetbrains.annotations.NotNull;

public enum Gender {
    MALE(Constants.MALE, "M"),
    FEMALE(Constants.FEMALE, "F"),
    RATHER_NOT_SAY(Constants.RATHER_NOT_SAY, "N");

    private String abbreviation;
    private String description;

    Gender(String description, String abbreviation) {
        this.abbreviation = abbreviation;
        this.description = description;
    }

    @NotNull
    public static Gender from(int code) {
        for (Gender g : values()) {
            if (g.ordinal() == code)
                return g;
        }

        throw new IllegalArgumentException("No such gender");
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}