package ru.idea.test.jmh.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public final class WarningUtils {

    private static final String ILLEGAL_ACCESS_LOGGER = "jdk.internal.module.IllegalAccessLogger";
    private static final String THE_UNSAFE = "theUnsafe";
    private static final String LOGGER = "logger";

    private WarningUtils() {
    }

    public static void disable() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField(THE_UNSAFE);
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName(ILLEGAL_ACCESS_LOGGER);
            Field logger = cls.getDeclaredField(LOGGER);
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }
}
