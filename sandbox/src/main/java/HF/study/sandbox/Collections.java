package HF.study.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  //public static void main(String[] args) {
    //String[] langs = new String[4]; //массив может хранить 4 элемента:
    //langs[0] = "Java";
    //langs[1] = "C#";
    //langs[2] = "Python";
    //langs[3] = "PHP";
  //}

  // ИЛИ:
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }
  }
}
