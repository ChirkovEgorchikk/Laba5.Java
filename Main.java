import java.io.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== РЕШЕНИЕ ВСЕХ ЗАДАНИЙ ===");

        try {
            boolean exit = false;
            while (!exit) {
                printMenu();
                int choice = getIntInput();

                switch (choice) {
                    case 1: demoFraction(); break;
                    case 2: demoCat(); break;
                    case 3: demoList(); break;
                    case 4: demoLoginGenerator(); break;
                    case 5: demoTextProcessor(); break;
                    case 6: demoQueue(); break;
                    case 7: demoPointPolyline(); break;
                    case 8: demoStreamProcessing(); break;
                    case 9: demoPeopleProcessing(); break;
                    case 0: exit = true; break;
                    default: System.out.println("Неверный выбор!");
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("\nПрограмма завершена.");
        }
    }

    private static void printMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ГЛАВНОЕ МЕНЮ");
        System.out.println("=".repeat(50));
        System.out.println("1. Дробь с кэшированием (Задание 1)");
        System.out.println("2. Кот с подсчетом мяуканий (Задание 2)");
        System.out.println("3. Удаление элементов из списка (Задание 3)");
        System.out.println("4. Генерация уникальных логинов (Задание 4) -> task4.txt");
        System.out.println("5. Звонкие согласные буквы (Задание 5) -> task5.txt");
        System.out.println("6. Обратный порядок очереди (Задание 6)");
        System.out.println("7. Работа с Point и Polyline (Задание 7)");
        System.out.println("8. Обработка данных из файла (Задание 7.2) -> task7.txt");
        System.out.println("9. Тестовый запуск всех файловых операций");
        System.out.println("0. Выход");
        System.out.print("Ваш выбор: ");
    }

    private static void demoFraction() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание 1: Дробь с кэшированием");
        System.out.println("=".repeat(50));

        try {
            System.out.print("Введите числитель: ");
            int num = getIntInput();
            System.out.print("Введите знаменатель: ");
            int den = getIntInput();

            CachedFraction fraction = new CachedFraction(num, den);
            System.out.println("Создана дробь: " + fraction);
            System.out.println("Вещественное значение: " + fraction.getDoubleValue());

            System.out.println("\nПовторный вызов (должен быть из кэша):");
            System.out.println("Значение: " + fraction.getDoubleValue());

            System.out.print("\nВведите новый числитель: ");
            fraction.setNumerator(getIntInput());
            System.out.println("Дробь после изменения: " + fraction);
            System.out.println("Значение (пересчитано): " + fraction.getDoubleValue());

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void demoCat() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание 2: Кот с подсчетом мяуканий");
        System.out.println("=".repeat(50));

        try {
            System.out.print("Введите имя кота: ");
            scanner.nextLine(); // Очистка буфера
            String name = scanner.nextLine();

            Cat cat = new Cat(name);
            CountingCatDecorator countingCat = new CountingCatDecorator(cat);

            System.out.println("\nСоздан кот: " + cat);

            System.out.print("Сколько раз кот должен мяукнуть? ");
            int count = getIntInput();

            System.out.println("\nНачинаем мяукать:");
            for (int i = 0; i < count; i++) {
                countingCat.meow();
            }

            System.out.println("\nРезультат: кот мяукал " + countingCat.getMeowCount() + " раз");

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void demoList() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание 3: Удаление элементов из списка");
        System.out.println("=".repeat(50));

        List<String> list = new ArrayList<>();
        System.out.print("Сколько элементов добавить в список? ");
        int n = getIntInput();
        scanner.nextLine(); // Очистка буфера

        System.out.println("Введите элементы списка:");
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            list.add(scanner.nextLine());
        }

        System.out.println("Исходный список: " + list);

        System.out.print("Введите элемент для удаления: ");
        String element = scanner.nextLine();

        ListUtils.removeAllOccurrences(list, element);
        System.out.println("Список после удаления всех вхождений \"" + element + "\": " + list);
    }

    private static void demoLoginGenerator() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание 4: Генерация уникальных логинов");
        System.out.println("Читает данные из файла: task4.txt");
        System.out.println("=".repeat(50));

        try {
            String filename = "task4.txt";

            // Проверяем существование файла
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("Файл " + filename + " не найден!");
                System.out.println("Создаю пример файла...");
                createTask4File();
            }

            System.out.println("Чтение данных из файла: " + filename);
            LoginGenerator generator = new LoginGenerator(filename);
            generator.printResults();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка в данных: " + e.getMessage());
        }
    }

    private static void demoTextProcessor() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание 5: Звонкие согласные буквы");
        System.out.println("Читает данные из файла: task5.txt");
        System.out.println("=".repeat(50));

        try {
            String filename = "task5.txt";

            // Проверяем существование файла
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("Файл " + filename + " не найден!");
                System.out.println("Создаю пример файла...");
                createTask5File();
            }

            System.out.println("Чтение текста из файла: " + filename);
            TextProcessor processor = new TextProcessor(filename);
            processor.printConsonants();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static void demoQueue() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание 6: Обратный порядок очереди");
        System.out.println("=".repeat(50));

        Queue<Integer> queue1 = new LinkedList<>();
        System.out.print("Сколько элементов добавить в очередь? ");
        int n = getIntInput();

        System.out.println("Введите элементы очереди:");
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            queue1.offer(getIntInput());
        }

        System.out.println("Исходная очередь L1: " + queue1);

        Queue<Integer> queue2 = QueueUtils.reverseQueue(queue1);
        System.out.println("Очередь L2 (элементы из L1 в обратном порядке): " + queue2);
    }

    private static void demoPointPolyline() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание 7: Работа с Point и Polyline");
        System.out.println("=".repeat(50));

        System.out.print("Сколько точек создать? ");
        int n = getIntInput();
        scanner.nextLine(); // Очистка буфера

        List<Point> points = new ArrayList<>();
        System.out.println("Введите координаты точек (x y):");
        for (int i = 0; i < n; i++) {
            System.out.print("Точка " + (i + 1) + ": ");
            String[] coords = scanner.nextLine().split(" ");
            if (coords.length == 2) {
                try {
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    points.add(new Point(x, y));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка! Введите два целых числа через пробел.");
                    i--; // Повторить ввод
                }
            } else {
                System.out.println("Ошибка! Введите два числа через пробел.");
                i--; // Повторить ввод
            }
        }

        System.out.println("\nИсходные точки:");
        for (Point p : points) {
            System.out.print(p + " ");
        }
        System.out.println();

        Polyline polyline = StreamProcessor.processPoints(points);
        System.out.println("\nЛоманая линия после обработки:");
        System.out.println(polyline);
    }

    private static void demoStreamProcessing() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание 7: Обработка данных из файла (стрим)");
        System.out.println("Читает данные из файла: task7.txt");
        System.out.println("=".repeat(50));

        try {
            String filename = "task7.txt";

            // Проверяем существование файла
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("Файл " + filename + " не найден!");
                System.out.println("Создаю пример файла...");
                createTask7File();
            }

            System.out.println("Чтение данных из файла: " + filename);
            Map<Integer, List<String>> result = StreamProcessor.processPeopleFile(filename);

            System.out.println("\nРезультат группировки по номерам:");
            for (Map.Entry<Integer, List<String>> entry : result.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static void demoPeopleProcessing() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Тестовый запуск всех файловых операций");
        System.out.println("=".repeat(50));

        System.out.println("1. Проверка task4.txt (логины)");
        System.out.println("2. Проверка task5.txt (согласные)");
        System.out.println("3. Проверка task7.txt (имена и номера)");
        System.out.println("4. Создать все тестовые файлы");
        System.out.print("Ваш выбор: ");

        int choice = getIntInput();
        scanner.nextLine(); // Очистка буфера

        try {
            switch (choice) {
                case 1:
                    checkTask4File();
                    break;
                case 2:
                    checkTask5File();
                    break;
                case 3:
                    checkTask7File();
                    break;
                case 4:
                    createAllTaskFiles();
                    System.out.println("Все тестовые файлы созданы!");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void checkTask4File() throws IOException {
        String filename = "task4.txt";
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Файл " + filename + " не найден!");
            return;
        }

        System.out.println("\nСодержимое файла " + filename + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        System.out.println("\nОбработка файла:");
        LoginGenerator generator = new LoginGenerator(filename);
        generator.printResults();
    }

    private static void checkTask5File() throws IOException {
        String filename = "task5.txt";
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Файл " + filename + " не найден!");
            return;
        }

        System.out.println("\nСодержимое файла " + filename + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        System.out.println("\nОбработка файла:");
        TextProcessor processor = new TextProcessor(filename);
        processor.printConsonants();
    }

    private static void checkTask7File() throws IOException {
        String filename = "task7.txt";
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Файл " + filename + " не найден!");
            return;
        }

        System.out.println("\nСодержимое файла " + filename + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        System.out.println("\nОбработка файла:");
        Map<Integer, List<String>> result = StreamProcessor.processPeopleFile(filename);

        System.out.println("Результат группировки по номерам:");
        for (Map.Entry<Integer, List<String>> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void createTask4File() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("task4.txt"))) {
            writer.println("Иванова Мария");
            writer.println("Петров Сергей");
            writer.println("Бойцова Екатерина");
            writer.println("Петров Иван");
            writer.println("Иванова Наташа");
            writer.println("Сидоров Алексей");
            writer.println("Иванова Ольга");
            writer.println("Петров Дмитрий");
            System.out.println("Создан файл task4.txt с примером данных для задания 4.");
        }
    }

    private static void createTask5File() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("task5.txt"))) {
            writer.println("В лесу родилась ёлочка, в лесу она росла.");
            writer.println("Зимой и летом стройная, зелёная была.");
            writer.println("Метель ей пела песенку: \"Спи, ёлочка, бай-бай!\"");
            writer.println("Мороз снежком укутывал: \"Смотри, не замерзай!\"");
            writer.println("Трусишка зайка серенький под ёлочкой скакал.");
            writer.println("Порою волк, сердитый волк, рысцою пробегал.");
            System.out.println("Создан файл task5.txt с примером данных для задания 5.");
        }
    }

    private static void createTask7File() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("task7.txt"))) {
            writer.println("Вася:5");
            writer.println("Петя:3");
            writer.println("Аня:5");
            writer.println("Маша:");
            writer.println("Коля:3");
            writer.println("Оля:5");
            writer.println("Сергей:1");
            writer.println(":10");
            writer.println("Наташа:abc");
            writer.println("Дима:2");
            writer.println("Света:5");
            writer.println("Миша:2");
            System.out.println("Создан файл task7.txt с примером данных для задания 7.");
        }
    }

    private static void createAllTaskFiles() throws IOException {
        createTask4File();
        createTask5File();
        createTask7File();
    }

    private static int getIntInput() {
        while (true) {
            try {
                int value = scanner.nextInt();
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите целое число: ");
                scanner.nextLine();
            }
        }
    }
}