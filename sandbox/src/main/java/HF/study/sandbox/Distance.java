package HF.study.sandbox;

public class Distance {

  public static void main(String[] args) {
    Point AB = new Point(4, 3, 7, 10);
    System.out.println("Расстояние между точками A (" + AB.x1 + ", " +  AB.y1 + ") и B (" + AB.x2 + ", " + AB.y2 + ") = " + distance(AB));
  }

    public static double distance (Point AB){
      return Math.sqrt((AB.x2 - AB.x1) * (AB.x2 - AB.x1) + (AB.y2 - AB.y1) * (AB.y2 - AB.y1));
    }
}
