package HF.study.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    addNewContact("add new");
    fillContactForm(new ContactData("Вася", "Бублик", "Корочкин", "Булка", "Г-н", "ИП Пекарь", "Волгоград", "123456", "1234567890", "654321", "---", "вася.корочкинбублик@ип-пекарь.ру", "вася@ип-пекарь.ру", "бублик@ип-пекарь.ру", "ип-пекарь.ру", "1", "September", "2000", "10", "July", "2019", "Владивосток", "3333333", "Комментарии"));
    submitContactCreation("(//input[@name='submit'])[2]");
    logout("Logout");
  }

}
