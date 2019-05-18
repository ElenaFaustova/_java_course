package HF.study.sandbox;

public class MyProgram {

  public static void main(String[] args) {
    hello("day");
    hello("world");
    hello("life");

    double l = 4;
    System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

    double a = 5;
    double b = 8;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));
  }

  public static void hello(String something) {
    System.out.println("What a wonderful " + something + "!");
  }

  public static double area (double len) {
    return len * len;
  }

  public static double area(double a, double b) {
    return a * b;
  }

}