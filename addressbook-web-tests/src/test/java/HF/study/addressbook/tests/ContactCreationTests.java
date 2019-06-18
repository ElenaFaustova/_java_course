package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test8"));
    app.goTo().homePage();
  }

  //игнор теста: @Test(enabled = false)
  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Вася3").withLastname("Корочкин3").withBday("10").withBmonth("May").withAday("11").withAmonth("June").withGroup("test8");
    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    //int max = 0;
    //for (ContactData c : after) {
      //if (c.getId() > max) {
        //max = c.getId();
      //}
    //}

    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    app.session().logout();
  }

}
