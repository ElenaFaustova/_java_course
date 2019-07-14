package HF.study.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
  }

  protected void click(By name) {
    wd.findElement(name).click();
  }
  protected void clickX(By xpath) {
    wd.findElement(xpath).click();
  }

  protected void clickCSS(By css) {
    wd.findElement(css).click();
  }

  protected void clickId(By id) {
    wd.findElement(id).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {

    if (file != null) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
      }
    }


// null - для сохранения дефолтных полей.
// ! - отрицание.


  //protected void select(String bday, String s, String option) {
    //new Select(wd.findElement(By.name(bday))).selectByVisibleText(option);
  //}

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
