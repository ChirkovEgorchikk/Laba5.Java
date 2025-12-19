//Задание 5.1
import java.io.*;
import java.util.*;

public class TextProcessor {
    private static final Set<Character> VOICED_CONSONANTS = new HashSet<>(Arrays.asList(
            'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'л', 'м', 'н', 'р'
    ));

    private String text;
    private Set<Character> consonants;

    public TextProcessor(String filename) throws IOException {
        loadFromFile(filename);
        processText();
    }

    private void loadFromFile(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(" ");
            }
        }
        text = sb.toString().toLowerCase();
    }

    private void processText() {
        consonants = new TreeSet<>();
        String[] words = text.split("[^а-яё]+");

        for (String word : words) {
            if (!word.isEmpty()) {
                for (char c : word.toCharArray()) {
                    if (VOICED_CONSONANTS.contains(c)) {
                        consonants.add(c);
                    }
                }
            }
        }
    }

    public Set<Character> getConsonants() {
        return new TreeSet<>(consonants);
    }

    public void printConsonants() {
        System.out.println("Звонкие согласные буквы (в алфавитном порядке):");
        if (consonants.isEmpty()) {
            System.out.println("  Не найдено звонких согласных букв.");
        } else {
            for (char c : consonants) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "TextProcessor [найдено букв: " + consonants.size() + ", буквы: " + consonants + "]";
    }
}