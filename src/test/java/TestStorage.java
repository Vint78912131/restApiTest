import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

public class TestStorage {
    static String endpoint = "https://192.168.12.50:8888/";

    public static String session = null;
    public static String cookies = null;
    public static Integer cluster_id = null;
    public static String cluster_name = null;

    @BeforeEach
    public void setCookies () {
        RestAssured.baseURI = endpoint;
        JSONObject requestBody = new JSONObject()
                .put("password", "1234")
                .put("username", "root");
        cookies = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .post("/api/v2/login/")
                .getDetailedCookies().toString();
        cluster_id = 3;
        cluster_name = "newCluster";
    }

//    @BeforeEach
//    public void setClusterInfo () {
//        Response data = RestAssured
//                .given()
//                .cookie(cookies)
//                .contentType("application/json")
//                .when()
//                .get("/api/v2/clusters/")
//                .then()
//                .extract()
//                .response();
////        System.out.println(data.jsonPath().getJsonObject("data.id").toString());
//        cluster_id = data.jsonPath().getJsonObject("data.id");
//        cluster_name = data.jsonPath().getJsonObject("data.name").toString();
//
//        System.out.println(cluster_id);
//        System.out.println(cluster_name);
//    }


    //Cluster

    //License
    @Test
    @DisplayName("Get cluster license info")
    public void getClusterLicenseInfo() {
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/"+cluster_id+"/license/");
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
                .cookie(cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/"+cluster_id+"/license/keys/update/");
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
                .cookie(cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/"+cluster_id+"/license/keys/update/");
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
                .cookie(cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/"+cluster_id+"/license/keys/register/");
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
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .post("/api/v2/"+cluster_id+"/license/as/");
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
                .cookie(cookies)
                .contentType("application/json")
                .param("key","keyNumber")
                .param("type","prolong") //prolong, update
                .when()
                .post("/api/v2/"+cluster_id+"/license/as/test/");
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

    //Events
    @Test
    @DisplayName("Get cluster events")
    public void getClusterEvents() {
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/"+cluster_id+"/events/");
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
                .cookie(cookies)
                .contentType("application/json")
                .param("page",1)
                .when()
                .get("/api/v2/"+cluster_id+"/events/");
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

    //Chunk Servers

    @Test
    @DisplayName("Get chunks server list")
    public void getChunksServerList() {
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/"+cluster_id+"/css/");
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
                .cookie(cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/"+cluster_id+"/css/");
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
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .post("/api/v2/"+cluster_id+"/css/1/");
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

    @Ignore
    @DisplayName("Delete chunks server")
    public void deleteChunksServer() {
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .delete("/api/v2/"+cluster_id+"/css/1/");
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

    //Users
    @Test
    @DisplayName("Get Storage users info")
    public void getUsersInfo() {
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .get("/api/v2/users/");
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
    @DisplayName("Create new Storage user")
    public void addNewStorageUser() {
        String requestBody = "{\n" +
                "    \"username\": \"new_user\",\n" +
                "    \"password\": \"1q2w3e\",\n" +
                "    \"description\": \"another user\",\n" +
                "    \"is_enabled\": \"true\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v2/users/");
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
    @DisplayName("Get Storage user info by id")
    public void getStorageUserInfo () {
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .post("/api/v2/user/1/");
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
    @DisplayName("Change Storage user info")
    public void setStorageUserInfo () {
        String requestBody = "{\n" +
                "    \"username\":\"newUser\",\n" +
                "    \"password\":\"newPassword123\",\n" +
                "    \"description\":\"testing\",\n" +
                "    \"is_enabled\":\"false\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/api/v2/user/1/");
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
    @DisplayName("Delete Storage user by id")
    public void deleteStorageUser () {
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .delete("/api/v2/user/1/");
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

//Tasks
    @Test
    @DisplayName("Get Tasks info list")
    public void getTasksInfo () {
        Response response = RestAssured
                .given()
                .cookie(cookies)
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
                .cookie(cookies)
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
//Statistic
    @Test
    @DisplayName("Get cluster statistics")
    public void getClusterStat() {
        String params = "" +
                "{\"types\": [\"io_reads\"], " +
                "\"filters\": " +
                "{\"from\": \"2023-03-23T21:10:00\", " +
                "\"to\": \"2023-03-23T21:20:00\"}}";
        //type - include some of values:
        // io_reads,
        // io_writes,
        // io_read_ops,
        // io_write_ops,
        // io_repl_reads,
        // io_repl_writes,
        // io_sync,
        // io_datasync,
        // cs_total,
        // cs_active,
        // mds_total,
        // mds_avail
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/"+cluster_id+"/stat/");
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
    @DisplayName("Get Chunks Server statistics")
    public void getCSStat() {
        String params = "" +
                "{\"types\": [\"io_reads\"], " +
                "\"filters\": " +
                "{\"from\": \"2023-03-23T21:10:00\", " +
                "\"to\": \"2023-03-23T21:20:00\"}}";
        //type - include some of values:
        // io_reads,
        // io_writes,
        // io_read_ops,
        // io_write_ops,
        // io_repl_reads,
        // io_repl_writes,
        // io_sync,
        // io_datasync,
        // cs_total,
        // cs_active,
        // mds_total,
        // mds_avail
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/"+cluster_id+"/stat/cs/1/");
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
    @DisplayName("Get Metadata Server statistics")
    public void getMDSStat() {
        String params = "" +
                "{\"types\": [\"ctime\"], " +
                "\"filters\": " +
                "{\"from\": \"2023-03-23T21:10:00\", " +
                "\"to\": \"2023-03-23T21:20:00\"}}";
        //type - include some of values:
        // `ctime`,
        // `cpu_usage`,
        // `mem_usage`,
        // `uptime`
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/"+cluster_id+"/stat/mds/1/");
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
    @DisplayName("Get node statistics")
    public void getNodeStat() {
        String params = "" +
                "{\"types\": [\"cpu_cores\"], " +
                "\"filters\": " +
                "{\"from\": \"2023-03-23T21:10:00\", " +
                "\"to\": \"2023-03-23T21:20:00\"}}";
        //`type` - include some of values:
        // `cpu_idle',
        // `cpu_cores',
        // `mem_free',
        // `mem_total',
        // `dsk_reads',
        // `dsk_read_ops',
        // `dsk_writes',
        // `dsk_write_ops',
        // `net_tx'
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/stat/nodes/059ac5a5-0724-4d66-a09b-a9163ba00859/");
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
    @DisplayName("Get disk statistics")
    public void getDiskStat() {
        String params = "" +
                "{\"types\": [\"reads\",\"writes\"], " +
                "\"filters\": " +
                "{\"from\": \"2023-03-23T21:10:00\", " +
                "\"to\": \"2023-03-23T21:20:00\"}}";
        //type - include some of values:
        // reads,
        // read_ops,
        // writes,
        // write_ops
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/stat/nodes/059ac5a5-0724-4d66-a09b-a9163ba00859/disks/1/");
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
    @DisplayName("Get network statistics")
    public void getNetStat() {
        String params = "" +
                "{\"types\": [\"status\",\"tx_drops\"], " +
                "\"filters\": " +
                "{\"from\": \"2023-03-23T21:10:00\", " +
                "\"to\": \"2023-03-23T21:20:00\"}}";
        //type - include some of values:
        // status,
        // tx,
        // rx,
        // tx_drops,
        // rx_drops
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/stat/nodes/059ac5a5-0724-4d66-a09b-a9163ba00859/network/br0/");
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
    @DisplayName("Get Object Store cluster statistics")
    public void getS3Stat() {
        String params = "" +
                "{\"types\": [\"os_total\",\"s3gw_total\"], " +
                "\"filters\": " +
                "{\"from\": \"2023-03-23T21:10:00\", " +
                "\"to\": \"2023-03-23T21:20:00\"}}";
        //type - include some of values:
        // os_total,
        // os_avail,
        // ns_total,
        // ns_avail,
        // s3gw_total,
        // s3gw_avail,
        // ns_rps,
        // os_rps,
        // s3gw_rps
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/" + cluster_id + "/stat/s3/");
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
    @DisplayName("Get Software update status")
    public void getSoftUpdateStatus() {
        Response response = RestAssured
                .given()
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .get("/" + cluster_id + "/software_updates/");
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
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .post("/" + cluster_id + "/software_updates/start/");
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
                .cookie(cookies)
                .contentType("application/json")
                .when()
                .post("/" + cluster_id + "/software_updates/check/");
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

    //Disks


}
