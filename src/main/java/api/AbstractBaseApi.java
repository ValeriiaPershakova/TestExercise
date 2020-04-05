package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import utils.PropertyReader;

import java.util.Map;

import static org.hamcrest.Matchers.lessThan;

public abstract class AbstractBaseApi {
    public static ResponseSpecification baseResponse =
            new ResponseSpecBuilder()
                    .expectContentType(ContentType.JSON)
                    .expectHeader("Connection", "keep-alive")
                    .expectResponseTime(lessThan(20000L))
                    .build();
    public static ResponseSpecification baseSuccessfulResponse =
            new ResponseSpecBuilder()
                    .addResponseSpecification(baseResponse)
                    .expectStatusCode(HttpStatus.SC_OK)
                    .build();

    private static Map<String, String> url = PropertyReader.getProperties(System.getProperty("user.dir") + "/target/test-classes/url.properties");
    public static RequestSpecification baseRequestConfiguration =
            new RequestSpecBuilder()
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.ANY)
                    .setRelaxedHTTPSValidation()
                    .setBaseUri(url.get("baseUrl"))
                    .setBasePath(url.get("basePath"))
                    .log(LogDetail.ALL)
                    .build();
}
