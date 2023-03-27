import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Nodes {
    @BeforeEach
    public void preparation() {
        TestStorage.setCookies();
    }

    @Test
    @DisplayName("Get Node disks info")
    @Epic(value = "Nodes Disks")
    @Story("Disks")
    @Link("https://documenter.getpostman.com/view/607407/UVRGFjMf#1e5e785f-e77e-433b-afb4-93d3425c601f")
    @Feature("Get Node disks info by ip")
    @Description("Get Node disks info by ip")
    @Severity(SeverityLevel.MINOR)
    public void getNodeDisksInfo() {
        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/disks/192.168.12.45/");
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
            System.out.println(response.prettyPrint());
        }
    }


    @Test
    @DisplayName("Get Nodes list")
    @Epic(value = "Nodes Nodes")
    @Story("Nodes")
    @Link("https://documenter.getpostman.com/view/607407/UVRGFjMf#6ddec5dd-5cb8-4b70-ae58-298c9c0049a3")
    @Feature("Get Nodes list by cluster_id")
    @Description("Get Nodes list")
    @Severity(SeverityLevel.MINOR)
    public void getNodesList() {

        Response response = RestAssured
                .given()
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .param("cluster",TestStorage.cluster_id)
//params
//{"cluster": [clusterId1, clusterId2, ..., clusterIdN, null]}
                .when()
                .get("/api/v2/nodes/");
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
            System.out.println(response.prettyPrint());
        }
    }




}
