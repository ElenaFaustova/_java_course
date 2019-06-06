package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("����5", "������", "��������", "�����", "�-�", "�� ������", "���������", "123456", "1234567890", "654321", "---", "����.��������������@��-������.��", "����@��-������.��", "������@��-������.��", "��-������.��", "15", "October", "2000", "10", "July", "2019", "�����������", "3333333", "�����������", "test1"), true);
    }
    app.getContactHelper().chooseContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().submitContactDeletion();
  }
}
