package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test8"));
      app.contact().create(new ContactData().withFirstname("Вася3").withLastname("Корочкин3").withBday("10").withBmonth("May").withAday("11").withAmonth("June").withGroup("test8"),true);
      app.goTo().homePage();
    }
  }

  @Test

  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);

    app.session().logout();
  }

}
