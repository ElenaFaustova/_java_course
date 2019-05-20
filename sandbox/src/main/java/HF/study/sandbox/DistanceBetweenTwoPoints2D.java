package HF.study.sandbox;

public class DistanceBetweenTwoPoints2D {

  public static void main(String[] args) {
    Point A = new Point(4, 3);
    Point B = new Point (7, 10);
    System.out.println("Расстояние между точками A (" + A.x + ", " +  A.y + ") и B (" + B.x + ", " + B.y + ") = " + A.distance(B));
  }

}
