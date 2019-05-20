package HF.study.sandbox;

public class Point {
  public double x;
  public double y;


  public Point(double x, double y) {
    this.x = x;
    this.y = y;
      }

  //       _______________________
  //AB = V(xB - xA)2 + (yB - yA)2

  public double distance(Point B){
    return Math.sqrt(Math.pow((B.x - this.x), 2) + Math.pow((B.y - this.y), 2));
  }
}
