package HF.study.sandbox;

public class Equality {
  public static void main(String[] args) {
    String s1 = "firefox";
    String s2 = new String(s1);

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
  }
}

 // '==' - сравнивает ссылки на объект (физическое сравнение). ≈сли s1 передаем как параметр, он находитс€ по другому адресу и ссылки на объект s1 не равны.
 // 'equals' - логическое сравнение, сравниваетс€ фактическое значение.
 // ƒл€ сравнени€ чисел и сравнени€ с 'null' можно использовать '==', дл€ всего остального - 'equals'