package HF.study.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void start (String adminLogin, String adminPassword, int id) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), adminLogin);
    clickX(By.xpath(".//input[@value='Войти']"));
    type(By.name("password"), adminPassword);
    clickX(By.xpath(".//input[@value='Войти']"));
    clickX(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Статистика'])[1]/following::span[1]"));
    clickX(By.xpath(".//a[@href='/mantisbt-2.21.1/manage_user_page.php']"));
    clickX(By.xpath(".//a[@href='manage_user_edit_page.php?user_id=" + id + "']"));
    clickX(By.xpath(".//input[@value='Сбросить пароль']"));
  }

    public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("span.bigger-110"));
  }
}
