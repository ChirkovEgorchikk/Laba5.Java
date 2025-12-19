//Задание 2.1
public class Cat implements Meowable {
    private final String name;

    public Cat(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя кота не может быть пустым");
        }
        this.name = name.trim();
    }

    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "кот: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cat cat = (Cat) obj;
        return name.equals(cat.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}