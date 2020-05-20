package ru.idea.test.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

@SuppressWarnings("WeakerAccess")
public final class DecimalFormatFactory {

    private DecimalFormatFactory() {
    }

    static final String DEFAULT_INTEGER_PATTERN = "###,##0";
    static final String DEFAULT_DOUBLE_PATTERN = "###,##0.00";

    static final char SPACE_GROUPING_SEPARATOR = ' ';
    static final char DOT_DECIMAL_SEPARATOR = '.';

    public static NumberFormat defaultInteger() {
        return defaultFormat(DEFAULT_INTEGER_PATTERN);
    }

    public static NumberFormat defaultDouble() {
        return defaultFormat(DEFAULT_DOUBLE_PATTERN);
    }

    public static NumberFormat defaultFormat(String pattern) {
        return new DecimalFormat(
                pattern,
                new DecimalFormatSymbols() {{
                    setGroupingSeparator(SPACE_GROUPING_SEPARATOR);
                    setDecimalSeparator(DOT_DECIMAL_SEPARATOR);
                }}
        );
    }
}
