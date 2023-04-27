// Superclass
class Animal {
String name;
int age;

public Animal(String name, int age) {
    this.name = name;
    this.age = age;
}

public void eat() {
    System.out.println(name + " is eating.");
}

public void sleep() {
    System.out.println(name + " is sleeping.");
}
}

// Subclass
class Cat extends Animal {
String breed;

public Cat(String name, int age, String breed) {
    super(name, age);
    this.breed = breed;
}

public void meow() {
    System.out.println(name + " is meowing.");
}
}

// Contoh penggunaan
public class Main {
public static void main(String[] args) {
    Animal cat1 = new Cat("Whiskers", 3, "Persian");

    System.out.println(cat1 instanceof Cat);
}
}
