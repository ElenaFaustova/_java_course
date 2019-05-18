package HF.study.sandbox;

public class MyProgram {

  public static void main(String[] args) {
    hello("day");
    hello("world");
    hello("life");

    Square s = new Square(4);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(5,8);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }

  public static void hello(String something) {
    System.out.println("What a wonderful " + something + "!");
  }

}