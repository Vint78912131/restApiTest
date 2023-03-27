import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Statistics {
    @BeforeEach
    public void preparation() {
        TestStorage.setCookies();
    }

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
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/"+TestStorage.cluster_id+"/stat/");
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
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/"+TestStorage.cluster_id+"/stat/cs/1/");
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
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/"+TestStorage.cluster_id+"/stat/mds/1/");
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
                .cookie(TestStorage.cookies)
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
                .cookie(TestStorage.cookies)
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
                .cookie(TestStorage.cookies)
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
                .cookie(TestStorage.cookies)
                .contentType("application/json")
                .param("params",params)
                .when()
                .get("/api/v2/" + TestStorage.cluster_id + "/stat/s3/");
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
