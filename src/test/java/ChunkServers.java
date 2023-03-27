import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChunkServers {
    @BeforeEach
    public void preparation() {
        TestStorage.setCookies();
    }


    @Test
    @DisplayName("Get chunks server list")
    public void getChunksServerList() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/"+ TestStorage.cluster_id+"/css/");
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
    @DisplayName("Start chunks server")
    public void startChunksServer() {
        String requestBody = "{\n" +
                "    \"node_id\":\"1\",\n" +
                "    \"path\":\"https://192.168.12.47:8888/\",\n" +
                "    \"journal_path\" : \"https://192.168.12.47:8888/\",\n" +
                "    \"journal_data_size\":\"1073741824\",\n" +
                "    \"journal_metadata_size\": \"1073741824\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/"+TestStorage.cluster_id+"/css/");
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
    @DisplayName("Get chunks server work info")
    public void getChunksServerInfo() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .post("/api/v2/"+TestStorage.cluster_id+"/css/1/");
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
    @Disabled
    @DisplayName("Delete chunks server")
    public void deleteChunksServer() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .delete("/api/v2/"+TestStorage.cluster_id+"/css/1/");
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
