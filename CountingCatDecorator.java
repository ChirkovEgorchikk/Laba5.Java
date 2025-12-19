//Задание 2.1
public class CountingCatDecorator implements Meowable {
    private final Cat cat;
    private int meowCount;

    public CountingCatDecorator(Cat cat) {
        if (cat == null) {
            throw new IllegalArgumentException("Кот не может быть null");
        }
        this.cat = cat;
        this.meowCount = 0;
    }

    @Override
    public void meow() {
        meowCount++;
        cat.meow();
    }

    public int getMeowCount() {
        return meowCount;
    }

    public void resetCount() {
        meowCount = 0;
    }

    public Cat getOriginalCat() {
        return cat;
    }

    @Override
    public String toString() {
        return cat.toString() + " [мяукал: " + meowCount + " раз]";
    }
}