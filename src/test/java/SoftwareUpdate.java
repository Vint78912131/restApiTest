import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SoftwareUpdate {

    @BeforeEach
    public void preparation() {
        TestStorage.setCookies();
    }

    @Test
    @DisplayName("Get Software update status")
    public void getSoftUpdateStatus() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/" + TestStorage.cluster_id + "/software_updates/");
        try {
            response.then()
                    .assertThat()
                    .statusCode(404)
//                    .contentType("application/json")
//                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
            System.out.println(response.getBody().prettyPrint());
        }
    }

    @Test
    @DisplayName("Start Software update")
    public void startSoftUpdate() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .post("/" + TestStorage.cluster_id + "/software_updates/start/");
        try {
            response.then()
                    .assertThat()
                    .statusCode(404)
//                    .contentType("application/json")
//                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
            System.out.println(response.getBody().prettyPrint());
        }
    }
    @Test
    @DisplayName("Check Software update status")
    public void checkSoftUpdate() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .post("/" + TestStorage.cluster_id + "/software_updates/check/");
        try {
            response.then()
                    .assertThat()
                    .statusCode(404)
//                    .contentType("application/json")
//                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
            System.out.println(response.getBody().prettyPrint());
        }
    }



}
