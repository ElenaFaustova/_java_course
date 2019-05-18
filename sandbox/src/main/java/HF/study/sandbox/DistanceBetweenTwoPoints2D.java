package HF.study.sandbox;

public class DistanceBetweenTwoPoints2D {

  public static void main(String[] args) {
    Point AB = new Point(4, 3, 7, 10);
    System.out.println("Расстояние между точками A (" + AB.x1 + ", " +  AB.y1 + ") и B (" + AB.x2 + ", " + AB.y2 + ") = " + AB.distance());
  }

}
