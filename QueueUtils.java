//Задание 6.5
import java.util.*;

public class QueueUtils {

    public static <T> void reverseQueue(Queue<T> source, Queue<T> destination) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Очереди не могут быть null");
        }

        Stack<T> stack = new Stack<>();

        while (!source.isEmpty()) {
            stack.push(source.poll());
        }

        while (!stack.isEmpty()) {
            destination.offer(stack.pop());
        }
    }

    public static <T> Queue<T> reverseQueue(Queue<T> source) {
        if (source == null) {
            throw new IllegalArgumentException("Исходная очередь не может быть null");
        }

        Queue<T> result = new LinkedList<>();
        reverseQueue(new LinkedList<>(source), result);
        return result;
    }
}