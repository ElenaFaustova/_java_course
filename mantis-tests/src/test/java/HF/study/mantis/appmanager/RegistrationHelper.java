package HF.study.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {


  public RegistrationHelper(ApplicationManager app) {
    super(app);

  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    clickX(By.xpath(".//input[@value='Войти']"));
    clickX(By.xpath(".//a[@class='pull-right']"));
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.xpath("//input[@value='OK']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
