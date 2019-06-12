package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("test8", null, null));
      app.getContactHelper().createContact(new ContactData("Вася5", "Бублик", "Корочкин", "Булка", "Г-н", "ИП Пекарь", "Волгоград", "123456", "1234567890", "654321", "---", "вася.корочкинбублик@ип-пекарь.ру", "вася@ип-пекарь.ру", "бублик@ип-пекарь.ру", "ип-пекарь.ру", "15", "October", "2000", "10", "July", "2019", "Владивосток", "3333333", "Комментарии", "test8"), true);
    }
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getConactCount();
    app.getContactHelper().chooseContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().submitContactDeletion();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getConactCount();
    Assert.assertEquals(after, before - 1);
    app.getSessionHelper().logout();
  }
}
