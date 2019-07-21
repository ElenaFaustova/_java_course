package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.Contacts;
import HF.study.addressbook.model.GroupData;
import HF.study.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.stream.Collectors;

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
    Contacts contacts = app.db().contacts();
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactToAddToGroup = new ContactData().withId(contact.getId()).withFirstname(contact.getFirstname()).withLastname(contact.getLastname()).inGroup(groups.iterator().next());

    Groups beforeAdd = app.db().inGroup(contactToAddToGroup);
    GroupData chosenGroupToAdd = groups.stream().filter(group -> !contact.getGroups().contains(group)).collect(Collectors.toSet()).iterator().next();

    app.goTo().homePage();
    app.contact().addToGroup(contactToAddToGroup, chosenGroupToAdd);
    app.goTo().homePage();
    app.contact().showAllContacts();

    Groups afterAdd = app.db().inGroup(contactToAddToGroup);

    assertThat(afterAdd.size(), equalTo(beforeAdd.size() + 1));
    assertThat(afterAdd, equalTo(beforeAdd.withChosenGroupToAdd(chosenGroupToAdd)));
    verifyContactListInUI();

  }
}
