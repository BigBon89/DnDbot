package org.example;

import java.util.HashSet;
import java.util.Set;

public class MathUtils {
    public static Set<Integer> getFactors(int n) {
        Set<Integer> factors = new HashSet<>();
        int step = n % 2 == 0 ? 1 : 2; // Шаг 1 для четных, 2 для нечетных
        for (int i = 1; i <= Math.sqrt(n); i += step) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i); // Добавляем парный делитель
            }
        }
        return factors;
    }
}
