package HF.study.sandbox;

public class Primes {

  //����������� �������� ����� (n - �����, ������� ���������, i - �����, ��������� �����):
  public static boolean isPrime(int n) {
    //"i = i + 1" = "i += 1" = "i++" (i++ - ������ ���� ������������ 1 (���������)) (i-- - ���������).
    for (int i = 2; i < n; i = i + 1) {
      //���� n �������(%) �� i ��� ������� (==0)
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i < n) {
      if (n % i == 0) {
        return false;
      }
      i++;
    }
    return true;
  }

  public static boolean isPrimeWhileShort(int n) {
    int i = 2;
    while (i < n && n % i != 0) {
      i++;
    }
    return i == n;
  }

  // int - ��� 32-������ �����, long - 64-������.
  public static boolean isPrime(long n) {
    for (long i = 2; i < n; i = i + 1) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFast(int n) {
    for (int i = 2; i < n / 2; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFaster(int n) {
    int m = (int) Math.sqrt(n); //���� ������ - ������� �����, ��� ����� �������� � ������ - (int)
    for (int i = 2; i < m / 2; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

}