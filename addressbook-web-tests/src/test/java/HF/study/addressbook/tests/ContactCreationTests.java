package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.Contacts;
import HF.study.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    File photo = new File("src/test/resources/TuxAvatar.png");
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1]).withMobileTelephone(split[2]).withPhoto(photo).withGroup("test8")});
      line = reader.readLine();
    }
    return list.iterator();

  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test8"));
    app.goTo().homePage();
  }

  //игнор теста: @Test(enabled = false)
  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    app.session().logout();
  }

  //@Test
  //public void testCurrentDir() {
    //File currentDir = new File(".");
    //System.out.println(currentDir.getAbsolutePath());
    //File photo = new File("src/test/resources/TuxAvatar.png");
    //System.out.println(photo.getAbsolutePath());
    //System.out.println(photo.exists());
  //}

}
