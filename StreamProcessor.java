//Задание 7.1-7.2
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StreamProcessor {

    public static Polyline processPoints(List<Point> points) {
        if (points == null) {
            return new Polyline();
        }

        List<Point> processedPoints = points.stream()
                .distinct()
                .sorted(Comparator.comparingInt(Point::getX))
                .map(p -> new Point(p.getX(), Math.abs(p.getY())))
                .collect(Collectors.toList());

        return new Polyline(processedPoints);
    }

    public static Map<Integer, List<String>> processPeopleFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        }

        return processPeopleData(lines);
    }

    public static Map<Integer, List<String>> processPeopleData(List<String> lines) {
        if (lines == null) {
            return new HashMap<>();
        }

        return lines.stream()
                .filter(line -> line.contains(":"))
                .map(line -> {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String name = parts[0].trim();
                        String numberStr = parts[1].trim();

                        if (name.isEmpty() || numberStr.isEmpty()) {
                            return null;
                        }

                        name = name.substring(0, 1).toUpperCase() +
                                name.substring(1).toLowerCase();

                        try {
                            int number = Integer.parseInt(numberStr);
                            return new AbstractMap.SimpleEntry<>(number, name);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }
}