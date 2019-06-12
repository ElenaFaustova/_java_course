package HF.study.addressbook.appmanager;

import HF.study.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    findElement(By.xpath("(//input[@name='submit'])[2]"));
  }

  private void findElement(By xpath) {
    wd.findElement(xpath).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
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

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void addNewContact() {
    findElement(By.linkText("add new"));
  }

  public void chooseContact() {
    findElement(By.name("selected[]"));
  }

  public void editContact() {
    findElement(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    findElement(By.xpath("(//input[@name='update'])[2]"));
  }

  public void deleteContact() {
    findElement(By.xpath("//input[@value='Delete']"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact, boolean creation) {
    addNewContact();
    fillContactForm(contact, creation);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getConactCount() {
    return wd.findElements(By.name("entry")).size();
  }
}

