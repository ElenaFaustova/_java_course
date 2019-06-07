package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test8", null, null));
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Вася5", "Бублик", null, null, null, null, null, null, null, null, "---", null, null, null, null, "15", "October", "2000", "10", "July", "2019", null, null, null, "test8"), true);
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().chooseContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Вася5", "Бублик", "Корочкин", "Булка", "Г-н", "ИП Пекарь", "Волгоград", "123456", "1234567890", "654321", "---", "вася.корочкинбублик@ип-пекарь.ру", "вася@ип-пекарь.ру", "бублик@ип-пекарь.ру", "ип-пекарь.ру", "15", "October", "2000", "10", "July", "2019", "Владивосток", "3333333", "Комментарии", "test8"), false);
    app.getContactHelper().submitContactModification();
    app.getSessionHelper().logout();
  }
}
