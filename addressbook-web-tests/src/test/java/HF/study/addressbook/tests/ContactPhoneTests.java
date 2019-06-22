package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.Contacts;
import HF.study.addressbook.model.GroupData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

  //@BeforeMethod

  //public void ensurePreconditions() {
    //app.goTo().homePage();
    //if (app.contact().all().size() == 0) {
      //app.goTo().groupPage();
      //app.group().create(new GroupData().withName("test8"));
      //app.contact().create(new ContactData().withFirstname("Вася3").withLastname("Корочкин3")
              //.withHomeTelephone("111").withMobileTelephone("222").withWorkTelephone("333")
              //.withBday("10").withBmonth("May").withAday("11").withAmonth("June").withGroup("test8"),true);
      //app.goTo().homePage();
    //}
  //}

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomeTelephone(), equalTo(contactInfoFromEditForm.getHomeTelephone()));
    assertThat(contact.getMobileTelephone(), equalTo(contactInfoFromEditForm.getMobileTelephone()));
    assertThat(contact.getWorkTelephone(), equalTo(contactInfoFromEditForm.getWorkTelephone()));
  }
}
