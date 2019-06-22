package HF.study.addressbook.appmanager;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.Contacts;
import HF.study.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void select(String dropdown, String s, String option) {
    new Select(wd.findElement(By.name(dropdown))).selectByVisibleText(option);
  }

  public void editContactById(int id) {
    wd.findElement(By.xpath(".//a[@href='edit.php?id=" + id + "']")).click();
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

  public void create(ContactData contact, boolean creation) {
    addNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    submitContactDeletion();
    contactCache = null;
    isContactDeleted();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    isContactModified();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("entry")).size();
  }

  public void isContactDeleted() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void isContactModified() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      //String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      //String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withHomeTelephone(phones[0]).withMobileTelephone(phones[1]).withWorkTelephone(phones[2]));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomeTelephone(home).withMobileTelephone(mobile).withWorkTelephone(work);
  }
}

//List<WebElement>  cells = element.findElements(By.tagName("td"));
//String lastname = cells.get(1).getText();
//String firstname = cells.get(2).getText();
