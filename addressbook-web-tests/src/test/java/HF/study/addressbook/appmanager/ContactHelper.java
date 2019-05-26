package HF.study.addressbook.appmanager;

import HF.study.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation(String s) {
    wd.findElement(By.xpath(s)).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomeTelephone());
    type(By.name("mobile"), contactData.getMobileTelephone());
    type(By.name("work"), contactData.getWorkTelephone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    click(By.name("bday"));
    select("bday", "(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])[1]/following::option[3]", contactData.getBday());
    click(By.name("bmonth"));
    select("bmonth", "(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])[1]/following::option[43]", contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
    click(By.name("aday"));
    select("aday", "(.//*[normalize-space(text()) and normalize-space(.)='Anniversary:'])[1]/following::option[12]", contactData.getAday());
    click(By.name("amonth"));
    select("amonth", "(.//*[normalize-space(text()) and normalize-space(.)='Anniversary:'])[1]/following::option[41]", contactData.getAmonth());
    type(By.name("ayear"), contactData.getAyear());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());
  }

  public void addNewContact(String s) {
    wd.findElement(By.linkText(s)).click();
  }
}
