package apiEngine.constants;

import lombok.Getter;

public enum Gender {
    MALE("M"),FEMALE("F");

    @Getter
    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
