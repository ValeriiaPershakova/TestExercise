import api.AbstractBaseApi;
import api.PostApi;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static constants.Fields.TITLE;
import static constants.Fields.USER_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class getPostsTest {


    @AfterClass
    public void teardown() {
        RestAssured.reset();
    }

    @Test
    public void getPostsByUserIdAndTitleTest() {
        JsonPath posts = PostApi.with()
                .userId(1)
                .title("qui est esse")
                .callApi()
                .then()
                .spec(AbstractBaseApi.baseSuccessfulResponse)
                .extract().jsonPath();

        assertThat(posts.getList(USER_ID), everyItem(equalTo(1)));
        assertThat(posts.getList(TITLE), everyItem(equalTo("qui est esse")));

    }

    @Test
    public void getPostsByNonexistingSetOfUserIdAndTitleTest() {
        JsonPath posts = PostApi.with()
                .userId(1)
                .title("in quibusdam tempore odit est dolorem")
                .callApi()
                .then()
                .spec(AbstractBaseApi.baseSuccessfulResponse)
                .extract().jsonPath();

        assertThat(posts.get(), empty());
    }
}
