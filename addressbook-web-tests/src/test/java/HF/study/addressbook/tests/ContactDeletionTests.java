package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Вася5", "Бублик", "Корочкин", "Булка", "Г-н", "ИП Пекарь", "Волгоград", "123456", "1234567890", "654321", "---", "вася.корочкинбублик@ип-пекарь.ру", "вася@ип-пекарь.ру", "бублик@ип-пекарь.ру", "ип-пекарь.ру", "15", "October", "2000", "10", "July", "2019", "Владивосток", "3333333", "Комментарии", "test1"), true);
    }
    app.getContactHelper().chooseContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().submitContactDeletion();
  }
}
