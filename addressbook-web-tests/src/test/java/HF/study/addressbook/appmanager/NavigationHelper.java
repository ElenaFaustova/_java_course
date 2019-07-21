package HF.study.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }
// провер€ем, если элемент и кнопка присутствуют, то выходим из метода (return), если нет, то переходим к методу click(By.linkText("groups"));
// дл€ отрицани€ ("!") знак "&&" мен€етс€ на "| |". Ќапример: "| | ! wd.find..."


  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));

  }

}
