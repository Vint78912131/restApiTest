import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tasks {
    @BeforeEach
    public void preparation() {
        TestStorage.setCookies();
    }

    @Test
    @DisplayName("Get Tasks info list")
    public void getTasksInfo () {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/tasks/");
        try {
            response.then()
                    .assertThat()
                    .statusCode(200)
                    .contentType("application/json")
                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
            System.out.println(response.getBody().prettyPrint());
        }
    }

    @Test
    @DisplayName("Get Task info by id")
    public void getTaskInfoBy () {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/tasks/12f6d6a5-f6d2-487b-a3f4-e4bbf3e9b934");
        try {
            response.then()
                    .assertThat()
                    .statusCode(200)
                    .contentType("application/json")
                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
            System.out.println(response.getBody().prettyPrint());
        }
    }
}
