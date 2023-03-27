import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Events {
    @BeforeEach
    public void preparation() {
        TestStorage.setCookies();
    }
    @Test
    @DisplayName("Get cluster events")
    public void getClusterEvents() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/"+TestStorage.cluster_id+"/events/");
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
    @DisplayName("Get cluster events by params")
    public void getClusterEventBy() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .param("page",1)
                .when()
                .get("/api/v2/"+TestStorage.cluster_id+"/events/");
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
