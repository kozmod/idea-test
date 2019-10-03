package ru.idea.test.core.functional.style;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void shouldExBothMethod_WhenUse_orElse() {
        Optional.of("V_1")
                .map(this::runIfPresent)
                .orElse(runIfAbsent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldExOne_WhenUse_orElseGet() {
        Optional.<String>ofNullable(null)
                .map(this::runIfPresent)
                .orElseGet(this::runIfAbsent);
    }

    private String runIfPresent(String value) {
        System.out.println("Optional value is present: " + value);
        return value;
    }

    private String runIfAbsent() {
        System.out.println("Optional value is absent!");
        return "EMPTY!!!!";
    }
}
