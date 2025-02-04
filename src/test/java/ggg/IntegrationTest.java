package ggg;

//import io.jooby.JoobyTest;

import io.jooby.StatusCode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.jooby.Jooby.runApp;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.when;

//@JoobyTest(App.class)
public class IntegrationTest {

  static OkHttpClient client = new OkHttpClient();

  private App app;

  @BeforeEach
  public void setup() {
    app = new App();
    runApp(new String[] {"server.join=false"}, () -> app);
  }

  @AfterEach
  public void tearDown() {
    app.stop();
  }

  /**
   * Integration test example using OKHttpClient. The serverPort(int) argument is generated by
   * Jooby. Jooby automatically set it as long it is declared as int and name it: serverPort.
   *
   * Please refer to: https://jooby.io/#testing-integration-testing for more information.
   *
   * @param serverPort Server port (must be compiled with --parameters enabled).
   * @throws IOException If something goes wrong.
   */
  @Test
  public void shouldSayWelcome() throws IOException {
    Request req = new Request.Builder()
        .url("http://localhost:" + 8080)
        .build();

    try (Response rsp = client.newCall(req).execute()) {
      assertEquals("Welcome to Jooby!", rsp.body().string());
      assertEquals(StatusCode.OK.value(), rsp.code());
    }

    when().
            get("http://localhost:" + 8080).
    then().
            body(equalTo("Welcome to Jooby!")).
            statusCode(200).
            contentType("text/plain");
  }

  @Test
  public void shouldSayWelcome2() throws IOException {
    shouldSayWelcome();
  }

  @Test
  public void shouldSayWelcome3() throws IOException {
    shouldSayWelcome();
  }


}
