package HF.study.addressbook.appmanager;

import HF.study.addressbook.model.ContactData;
import HF.study.addressbook.model.Contacts;
import HF.study.addressbook.model.GroupData;
import HF.study.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;


  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
          sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery("from GroupData").list();
      session.getTransaction().commit();
      session.close();
      return new Groups(result);
    }

    public Contacts contacts() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
      session.getTransaction().commit();
      session.close();
      return new Contacts(result);
    }

  public Groups inGroup(ContactData contact) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where id = " + contact.getId()).list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next().getGroups();
  }

  public Contacts contactInGroupCount() {

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    for (ContactData contact : result) {
      Groups groups = contact.getGroups();
          }
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);

  }

}
