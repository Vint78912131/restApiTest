import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class License {
    @BeforeEach
    public void preparation() {
        TestStorage.setCookies();
    }

    @Test
    @DisplayName("Get cluster license info")
    public void getClusterLicenseInfo() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/"+ TestStorage.cluster_id+"/license/");
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
    @DisplayName("Update cluster license")
    public void updateClusterLicense() {
        String requestBody = "{" +
                "\"server\":\"849e6c831b10489f855adc2fa80271e2\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/"+TestStorage.cluster_id+"/license/keys/update/");
        try {
            response.then()
                    .assertThat()
//                    .statusCode(200)
                    .contentType("application/json")
//                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
        }
    }

    @Test
    @DisplayName("Update cluster license")
    public void registerClusterLicense() {
        String requestBody = "{" +
                "\"key\":\"someKeyFor_849e6c831b10489f855adc2fa80271e2\"" +
                "}";
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/"+TestStorage.cluster_id+"/license/keys/update/");
        try {
            response.then()
                    .assertThat()
//                    .statusCode(200)
                    .contentType("application/json")
//                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
        }
    }

    @Test
    @DisplayName("Register new cluster license")
    public void registerNewClusterLicense() {
        String requestBody = "{" +
                "\"key\":\"someKeyFor_849e6c831b10489f855adc2fa80271e2\"" +
                "}";
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/"+TestStorage.cluster_id+"/license/keys/register/");
        try {
            response.then()
                    .assertThat()
//                    .statusCode(200)
                    .contentType("application/json")
//                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
        }
    }

    @Test
    @DisplayName("Get cluster license info")
    public void getClusterLicense() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .post("/api/v2/"+TestStorage.cluster_id+"/license/as/");
        try {
            response.then()
                    .assertThat()
//                    .statusCode(200)
                    .contentType("application/json")
//                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
        }
    }

    @Test
    @DisplayName("Check cluster license")
    public void checkClusterLicense() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .param("key","keyNumber")
                .param("type","prolong") //prolong, update
                .when()
                .post("/api/v2/"+TestStorage.cluster_id+"/license/as/test/");
        try {
            response.then()
                    .assertThat()
//                    .statusCode(200)
                    .contentType("application/json")
//                    .statusLine("HTTP/1.1 200 OK")
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(response.statusCode() + "\n" +response.getBody().jsonPath().getString("message"));
        }
    }

}
