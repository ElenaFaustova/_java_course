package HF.study.mantis.tests;

import HF.study.mantis.model.MailMessage;
import HF.study.mantis.model.UserData;
import HF.study.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testRegistration() throws Exception {
    skipIfNotFixed(0000001);
    Users users = app.db().users();
    UserData user = app.db().users().iterator().next();
    //UserData user = new UserData().withId(user1.getId()).withUsername(user1.getUsername()).withEmail(user1.getEmail());
    int userId = user.getId();
    String username = user.getUsername();
    String password = "password";

    long now = System.currentTimeMillis();
    app.resetPassword().start("administrator", "root", userId);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages);
    app.resetPassword().finish(confirmationLink, "password");
    assertTrue(app.newSession().login(username, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages) {
    MailMessage mailMessage = mailMessages.get(0);
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}


