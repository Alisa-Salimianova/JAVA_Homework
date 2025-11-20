import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = { 21, 1, 20, 23 };
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day + 3, new int[day + 4]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {
        // Проверяем, есть ли уже вычисленное значение в памяти
        if (day < memory.length && memory[day] != 0) {
            return memory[day];
        }

        // Базовые случаи - начальные числа
        if (day == 0) {
            memory[day] = startNumbers[0];
            return startNumbers[0];
        }
        if (day == 1) {
            memory[day] = startNumbers[1];
            return startNumbers[1];
        }
        if (day == 2) {
            memory[day] = startNumbers[2];
            return startNumbers[2];
        }
        if (day == 3) {
            memory[day] = startNumbers[3];
            return startNumbers[3];
        }

        // Рекурсивный случай: вычисляем предыдущее и пре-пре-предыдущее значения
        int prev = chooseHobbyRecursive(startNumbers, day - 1, memory);
        int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3, memory);

        // Вычисляем текущее значение по формуле
        int result = (prev * prePrePrev) % 10 + 1;

        // Сохраняем результат в память перед возвратом
        memory[day] = result;

        return result;
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day) {
        // Базовые случаи - начальные числа
        if (day == 0) return startNumbers[0];
        if (day == 1) return startNumbers[1];
        if (day == 2) return startNumbers[2];
        if (day == 3) return startNumbers[3];

        // Рекурсивный случай: вычисляем предыдущее и пре-пре-предыдущее значения
        int prev = chooseHobbyRecursive(startNumbers, day - 1);
        int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3);

        // Вычисляем текущее значение по формуле
        return (prev * prePrePrev) % 10 + 1;
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4;
            int prev = numbers.get(index - 1);
            int prePrePrev = numbers.get(index - 3);
            numbers.add((prev * prePrePrev) % 10 + 1);
        }

        return numbers.get(numbers.size() - 1);
    }
}