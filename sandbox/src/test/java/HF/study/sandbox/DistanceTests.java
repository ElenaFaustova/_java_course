package HF.study.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void DistanceTests() {
    Point A = new Point(4, 3);
    Point B = new Point (6, 3);
    Assert.assertEquals(A.distance(B), 2.0);
  }
}
