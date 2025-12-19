//Задание 3.1
import java.util.Iterator;
import java.util.List;

public class ListUtils {

    public static <T> void removeAllOccurrences(List<T> list, T element) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }

        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                iterator.remove();
            }
        }
    }
}