package ru.idea.test.core.algoritm;

import java.util.ArrayList;
import java.util.Collections;

final class AlgorithmUtils {

    static int[] newShuffledArray(int[] array) {
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
}