package HF.study.mantis.tests;

import HF.study.mantis.appmanager.ApplicationManager;
import HF.study.mantis.model.Issue;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;


public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }

  //public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException {
  //  MantisConnectPortType mc = getMantisConnect();
  //  IssueData issueData = new IssueData();

  //  IssueData issueStatus = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);

  //для проверки статуса баг-репорта с заданным идентификатором лучше использовать метод mc_issue_get


 // public void skipIfNotFixed(int issueId) {
 //   if (isIssueOpen(issueId)) {
  //    throw new SkipException("Ignored because of issue " + issueId)
  //  }
  //}

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("web.soap.url")));
  }
}