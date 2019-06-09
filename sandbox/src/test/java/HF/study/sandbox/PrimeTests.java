package HF.study.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test
  public void testPrime() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrime() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }

  @Test(enabled = false)
  public void testPrimeWhile() {
    Assert.assertTrue(Primes.isPrimeWhileShort(Integer.MAX_VALUE));
  }

  @Test(enabled = false)  // - ���������� �����
  public void testPrimeLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test
  public void testPrimeFaster() {
    Assert.assertTrue(Primes.isPrimeFaster(Integer.MAX_VALUE));
  }
}
