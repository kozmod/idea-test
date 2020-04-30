package ru.idea.test.core.utils;

public final class PrimitiveTypesBitUtils {

    private PrimitiveTypesBitUtils() {
    }

    public static String toBinaryPrettyString(long l) {
        return insertSpaceEveryFourChar(toBinaryString(l));
    }

    public static String toBinaryString(long l) {
        return replaceAllSpacesToZero(
                String.format("%64s", Long.toBinaryString(l))
        );
    }

    public static String toBinaryPrettyString(int i) {
        return insertSpaceEveryFourChar(toBinaryString(i));
    }

    public static String toBinaryString(int i) {
        return replaceAllSpacesToZero(
                String.format("%32s", Integer.toBinaryString(i))
        );
    }

    public static String toBinaryPrettyString(short s) {
        return insertSpaceEveryFourChar(toBinaryString(s));
    }

    public static String toBinaryString(short s) {
        return replaceAllSpacesToZero(
                String.format("%16s", Integer.toBinaryString(s))
        );
    }

    public static String toBinaryPrettyString(char ch) {
        return insertSpaceEveryFourChar(toBinaryString(ch));
    }

    public static String toBinaryString(char ch) {
        return replaceAllSpacesToZero(
                String.format("%16s", Integer.toBinaryString(ch))
        );
    }

    public static String toBinaryPrettyString(byte b) {
        return insertSpaceEveryFourChar(toBinaryString(b));
    }

    public static String toBinaryString(byte b) {
        return replaceAllSpacesToZero(
                String.format("%8s", Integer.toBinaryString(b))
        );
    }

    private static String replaceAllSpacesToZero(String value) {
        return value.replaceAll(" ", "0");
    }

    private static String insertSpaceEveryFourChar(String value) {
        return value.replaceAll("(.{4})", "$0 ").trim();
    }
}
