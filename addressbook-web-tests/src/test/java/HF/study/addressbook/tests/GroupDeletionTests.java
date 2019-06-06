package HF.study.addressbook.tests;

import HF.study.addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    // проверяем наличие группы для удаления:
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
