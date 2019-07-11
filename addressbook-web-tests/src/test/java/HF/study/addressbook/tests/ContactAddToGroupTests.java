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

public class ContactAddToGroupTests extends TestBase {


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
    }


  @Test
  public void testContactAddToGroup() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    Contacts beforeAdd = app.db().inGroup();

    Contacts beforeListGroups = app.db().contact.getGroups();

    ContactData addedToGroupContact = before.iterator().next();
    ContactData contact = new ContactData().withId(addedToGroupContact.getId()).withFirstname(addedToGroupContact.getFirstname()).withLastname(addedToGroupContact.getLastname()).inGroup(groups.iterator().next());
    app.goTo().homePage();
    app.contact().addToGroup(addedToGroupContact);
    app.goTo().homePage();
    app.contact().showAllContacts();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    Contacts afterAdd = app.db().inGroup();

    Contacts afterListGroups = app.db().contact.getGroups();

    assertThat(afterListGroups, equalTo(beforeListGroups.without(addedToGroupContact).withAdded(contact)));

    assertThat(after, equalTo(before.without(addedToGroupContact).withAdded(contact)));
    assertThat(afterAdd, equalTo(beforeAdd.without(addedToGroupContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
