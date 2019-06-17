package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData("test8", null, null));
      app.contact().create(new ContactData("Вася5", "Бублик", null, null, null, null, null, null, null, null, "---", null, null, null, null, "15", "October", "2000", "10", "July", "2019", null, null, null, "test8"), true);
      app.goTo().homePage();
    }
  }

  @Test

  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(),"Вася", null, "Корочкин", null, null, null, null, null, null, null, null, null, null, null, null, "15", "October", "2000", "10", "July", "2019", null, null, null, null);
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    app.session().logout();
  }

}
