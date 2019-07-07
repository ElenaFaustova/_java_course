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

public class ContactRemoveFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    if (groups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test8"));
      app.goTo().homePage();
    } else if (groups.iterator().next().getName() == "test8") {
      app.goTo().homePage();
    } else {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test8"));
      app.goTo().homePage();
    }
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/Avatar.png");
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Вася3").withLastname("Корочкин3")
              .inGroup(groups.iterator().next()).withPhoto(photo), true, false);
      app.goTo().homePage();
    }
    app.goTo().homePage();
    app.contact().selectContact();
    app.contact().chooseGroupToAdd();
    app.contact().submitGroupAddition();
    app.contact().isContactAddedToGroup();;
    app.goTo().homePage();
    app.contact().showAllContacts();
  }

  @Test
  public void testContactRemoveFromGroup() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData removedFromGroupContact = before.iterator().next();
    ContactData contact = new ContactData().withId(removedFromGroupContact.getId()).withFirstname(removedFromGroupContact.getFirstname()).withLastname(removedFromGroupContact.getLastname()).inGroup(groups.iterator().next());
    app.goTo().homePage();
    app.contact().removeContactFromGroupe(removedFromGroupContact);
    app.goTo().homePage();
    app.contact().showAllContacts();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(removedFromGroupContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
