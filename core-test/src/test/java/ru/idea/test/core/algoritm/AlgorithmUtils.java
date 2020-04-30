package ru.idea.test.core.algoritm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public final class AlgorithmUtils {

    public static int[] newShuffledArray(int[] array) {
        if (array != null && array.length != 0) {
            final ArrayList<Integer> list = new ArrayList<>();
            for (Integer value : array) {
                list.add(value);
            }
            Collections.shuffle(list);
            final int[] newArray = new int[array.length];
            for (int i = 0; i < list.size(); i++) {
                newArray[i] = list.get(i);
            }
            return newArray;
        }
        throw new IllegalArgumentException();
    }

    @SuppressWarnings("unchecked")
    public static<T> T[] newShuffledArray(T[] array) {
        if (array != null && array.length != 0) {
            final ArrayList<T> list = new ArrayList<>();
            Collections.addAll(list, array);
            Collections.shuffle(list);

            final Class<?> entityClass = array[0].getClass();
            final T[] newArray = (T[]) Array.newInstance(entityClass,array.length);
            for (int i = 0; i < list.size(); i++) {
                newArray[i] = list.get(i);
            }
            return newArray;
        }
        throw new IllegalArgumentException();
    }
}
