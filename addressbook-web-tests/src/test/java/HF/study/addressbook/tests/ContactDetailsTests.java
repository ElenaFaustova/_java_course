package HF.study.addressbook.tests;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.GroupData;
import HF.study.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test8"));
      app.contact().create(new ContactData().withFirstname("����3").withLastname("��������3")
            .withAddress("������").withEmail("1@1").withEmail2("2@2").withEmail3("3@3")
              .withHomeTelephone("111").withMobileTelephone("222").withWorkTelephone("333")
              .inGroup(groups.iterator().next()),true, false);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHome(), contact.getMobileTelephone(), contact.getWorkTelephone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDetailsTests::cleanedPhones)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedPhones(String phones) {
    return phones.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDetailsTests::cleanedEmails)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedEmails(String emailes) {
    return emailes.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
