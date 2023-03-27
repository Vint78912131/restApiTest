import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Clasters {

    @BeforeEach
    public void setCookies () {
        RestAssured.baseURI = TestStorage.endpoint;
        JSONObject requestBody = new JSONObject()
                .put("password", "1234")
                .put("username", "root");
        TestStorage.cookies = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("/api/v2/login/")
                .getDetailedCookies().toString();
        TestStorage.cluster_id = 3;
        TestStorage.cluster_name = "newCluster";
    }


    @Test
    @DisplayName("Register new Cluster")
    @Disabled
    public void regNewCluster() {
        String requestBody ="{" +
                "\"cluster_name\":\"newCluster\",\n" +
                "\"host\":\"192.168.12.50:8888\",\n" +
                "\"node_id\":\"059ac5a5-0724-4d66-a09b-a9163ba00859\",\n" +
                "\"password\":\"1234\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/clusters/");
        try {
            response.then()
                    .assertThat()
                    .statusCode(200)
                    .contentType("application/json")
                    .statusLine("HTTP/1.1 200 OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(response.prettyPrint());
        }
    }

    @Test
    @DisplayName("Get clusters info")
    public void getClustersInfo() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/clusters/");

        try {
            response.then()
                    .assertThat()
                    .statusCode(200)
                    .contentType("application/json")
                    .statusLine("HTTP/1.1 200 OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(response.prettyPrint());
    }

    @Test
    @DisplayName("Get cluster password by id")
    public void getClusterPassword() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/clusters/"+TestStorage.cluster_id+"/password/");

        try {
            response.then()
                    .assertThat()
                    .statusCode(200)
                    .contentType("application/json")
                    .statusLine("HTTP/1.1 200 OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(response.prettyPrint());
    }

    @Test
    @DisplayName("Get cluster info by id")
    @Disabled
    public void getClusterInfo() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/clusters/"+TestStorage.cluster_id+"/");

        try {
            response.then()
                    .assertThat()
                    .statusCode(200)
                    .contentType("application/json")
                    .statusLine("HTTP/1.1 200 OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(response.prettyPrint());
    }

    @DisplayName("Delete cluster by id")
    @Disabled
    public void deleteCluster() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .delete("/api/v2/clusters/"+TestStorage.cluster_id+"/");
        try {
            response.then()
                    .assertThat()
                    .statusCode(204)
                    .statusLine("HTTP/1.1 204 NO CONTENT");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(response.prettyPrint());
    }

}
