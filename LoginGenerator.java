//Задание 4.1
import java.io.*;
import java.util.*;

public class LoginGenerator {
    private List<String> students;
    private List<String> logins;

    public LoginGenerator(String filename) throws IOException {
        students = new ArrayList<>();
        logins = new ArrayList<>();
        loadFromFile(filename);
        generateLogins();
    }

    private void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    validateStudentFormat(line);
                    students.add(line);
                }
            }
        }

        if (students.size() > 100) {
            throw new IllegalArgumentException("Количество учеников не должно превышать 100");
        }
    }

    private void validateStudentFormat(String student) {
        String[] parts = student.split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Некорректный формат строки: " + student);
        }

        String surname = parts[0];
        String name = parts[1];

        if (surname.length() > 20) {
            throw new IllegalArgumentException("Фамилия не должна превышать 20 символов: " + surname);
        }

        if (name.length() > 15) {
            throw new IllegalArgumentException("Имя не должно превышать 15 символов: " + name);
        }
    }

    private void generateLogins() {
        Map<String, Integer> surnameCount = new HashMap<>();

        for (String student : students) {
            String surname = extractSurname(student);
            int count = surnameCount.getOrDefault(surname, 0) + 1;
            surnameCount.put(surname, count);

            String login = (count == 1) ? surname : surname + count;
            logins.add(login);
        }
    }

    private String extractSurname(String student) {
        return student.split("\\s+")[0];
    }

    public List<String> getStudents() {
        return new ArrayList<>(students);
    }

    public List<String> getLogins() {
        return new ArrayList<>(logins);
    }

    public void printResults() {
        System.out.println("Результаты генерации логинов:");
        System.out.println("=".repeat(40));
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%-25s -> %s%n", students.get(i), logins.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginGenerator [студентов: ").append(students.size()).append("]\n");
        for (int i = 0; i < students.size(); i++) {
            sb.append(String.format("  %s -> %s%n", students.get(i), logins.get(i)));
        }
        return sb.toString();
    }
}