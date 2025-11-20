import java.io.*;
import java.util.*;



public class Main {

    public static void main(String[] args) throws IOException {
        String source = "CACABABABCCCAABAC";

        System.out.println(hasRepeats(source, 4)); // true, тк ABAB встречается два раза, хоть эти куски и пересекаются
        System.out.println(hasRepeats(source, 5)); // false
    }

    public static boolean hasRepeats(String source, int size) {
        Set<LazyString> slices = new HashSet<>(); // множество всех подстрок длины size
        LazyString prev = null; // переменная для сохранения предыдущей подстроки
        for (int i = 0; i <= source.length() - size; i++) { // перебор всех мест старта подстроки
            LazyString slice; // вырезание подстроки
            if (prev == null) {
                // первую подстроку создаём конструктором за линейную асимптотику
                slice = new LazyString(source, i, i + size);
            } else {
                // все остальные через сдвиг вправо от предыдущей подстроки, за O(1)
                slice = prev.shiftRight();
            }
            if (slices.contains(slice)) { // проверка на наличие повтора этой подстроки
                return true; // если уже встречали, значит есть повтор
            } else {
                slices.add(slice);  // иначе запоминаем подстроку и перебираем дальше
            }
            prev = slice; // не забываем обновить переменную для предыдущей подстроки для следующей итерации цикла
        }
        return false; // если бы нашли, то вышли бы по return true, а значит повторов нет
    }
}