package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.Contacts;
import HF.study.addressbook.model.GroupData;
import HF.study.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      File photo = new File("src/test/resources/Avatar.png");
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test8"));
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("����3").withLastname("��������3")
              .inGroup(groups.iterator().next()).withPhoto(photo), true, false);
      app.goTo().homePage();
    }
  }

  @Test

  public void testContactModification() {
    File photo = new File("src/test/resources/Avatar.png");
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("����7").withLastname("��������7").withPhoto(photo);
    app.goTo().homePage();
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

}
