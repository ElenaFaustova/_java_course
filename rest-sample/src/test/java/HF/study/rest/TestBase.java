package HF.study.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;


public class TestBase {

  public boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    String issuesString = parsed.getAsJsonObject().get("issues").toString();
    System.out.println(parsed.getAsJsonObject().get("issues").toString());
    return issuesString.contains("Resolved");
  }

//Open

/*  public boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> singleElementSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    String oneIssue = singleElementSet.toString();
    JsonElement parsedIssue = new JsonParser().parse(oneIssue);
    String issueStatus = parsedIssue.getAsJsonObject().get("state").toString();

    return issueStatus.equals("2");
  }*/

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}

