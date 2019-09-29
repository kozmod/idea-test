package ru.idea.test.core.sort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest {


    @Test
    public void shouldSort() {
        int[] ar = {11, 3, 9, 5, 9, 2, 0, 111};
        mergeSort(ar);
        System.out.println(Arrays.toString(ar));
    }

    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    static void merge(int[] array, int left, int mid, int right) {
        // вычисляем длину
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // создаем временные подмассивы
        int[] leftArray = new int[lengthLeft];
        int[] rightArray = new int[lengthRight];

        // копируем отсортированные массивы во временные
        System.arraycopy(array, left, leftArray, 0, lengthLeft);
        System.arraycopy(array, mid + 1, rightArray, 0, lengthRight);

        // итераторы содержат текущий индекс временного подмассива
        int leftIndex = 0;
        int rightIndex = 0;

        // копируем из leftArray и rightArray обратно в массив
        for (int i = left; i < right + 1; i++) {
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {   // если остаются нескопированные элементы в R и L, копируем минимальный
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < lengthLeft) {    // если все элементы были скопированы из rightArray, скопировать остальные из leftArray
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < lengthRight) { // если все элементы были скопированы из leftArray, скопировать остальные из rightArray
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
}
