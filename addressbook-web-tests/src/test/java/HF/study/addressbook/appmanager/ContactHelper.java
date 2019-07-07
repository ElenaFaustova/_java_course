package HF.study.addressbook.appmanager;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

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

  public void fillContactForm(ContactData contactData, boolean creation, boolean creationWithDates) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobileTelephone());
    type(By.name("work"), contactData.getWorkTelephone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    //select("bday", "(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])[1]/following::option[3]", contactData.getBday());
    //select("bmonth", "(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])[1]/following::option[43]", contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
    //select("aday", "(.//*[normalize-space(text()) and normalize-space(.)='Anniversary:'])[1]/following::option[12]", contactData.getAday());
    //select("amonth", "(.//*[normalize-space(text()) and normalize-space(.)='Anniversary:'])[1]/following::option[41]", contactData.getAmonth());
    type(By.name("ayear"), contactData.getAyear());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());
    attach(By.name("photo"), contactData.getPhoto());


    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

    //if (creation) {
    //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    //} else {
    //Assert.assertFalse(isElementPresent(By.name("new_group")));
    //}

    if (creationWithDates) {
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
      new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAday());
      new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAmonth());

    }

  }


  public void addNewContact() {
    findElement(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  //public void select(String dropdown, String s, String option) {
  //new Select(wd.findElement(By.name(dropdown))).selectByVisibleText(option);
  //}

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

  public void create(ContactData contact, boolean creation, boolean creationWithDates) {
    addNewContact();
    fillContactForm(contact, true, false);
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
    fillContactForm(contact, false, false);
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
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
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
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomeTelephone(home).withMobileTelephone(mobile).withWorkTelephone(work)
            .withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }


  public void addToGroup(ContactData contact) {
    selectContactById(contact.getId());
    chooseGroupToAdd();
    submitGroupAddition();
    isContactAddedToGroup();

      }

  public void isContactAddedToGroup() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void submitGroupAddition() {
    findElement(By.name("add"));
  }

  public void chooseGroupToAdd() {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText("test8");
  }
//toGroup.getGroups().iterator().next().getName()
  public void showAllContacts() {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
  }

  public void removeContactFromGroupe(ContactData contact) {
    showContactsInGroup8();
    selectContact();
    initRemoveContactFromGroup();
    isContactRemovedFromGroup();
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void isContactRemovedFromGroup() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void initRemoveContactFromGroup() {
    click(By.name("remove"));
  }

  public void showContactsInGroup8() {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("test8");
  }
}

