//Задание 7.1
import java.util.*;

public class Polyline {
    private final List<Point> points;

    public Polyline(List<Point> points) {
        if (points == null) {
            throw new IllegalArgumentException("Список точек не может быть null");
        }
        this.points = new ArrayList<>(points);
    }

    public Polyline() {
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Точка не может быть null");
        }
        points.add(point);
    }

    public List<Point> getPoints() {
        return new ArrayList<>(points);
    }

    @Override
    public String toString() {
        if (points.isEmpty()) {
            return "Линия []";
        }

        StringBuilder sb = new StringBuilder("Линия [");
        for (int i = 0; i < points.size(); i++) {
            sb.append(points.get(i));
            if (i < points.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}