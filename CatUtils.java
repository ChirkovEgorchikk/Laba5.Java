//Задание 2.1
import java.util.List;

public class CatUtils {

    public static void makeAllMeow(List<Meowable> meowables) {
        if (meowables == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }

        for (Meowable meowable : meowables) {
            meowable.meow();
        }
    }
}