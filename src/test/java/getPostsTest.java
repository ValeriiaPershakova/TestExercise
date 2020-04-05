import api.AbstractBaseApi;
import api.PostApi;
import beans.Post;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static constants.Fields.*;

public class getPostsTest {
    private Type postListType = new TypeToken<List<Post>>() {
    }.getType();


    @AfterClass
    public void teardown() {
        RestAssured.reset();
    }

    @Test
    public void getPostsByUserIdAndTitleTest() {
//        Map<String,Object> dataset = g.fromJson(jo.get("$.valid[0].set"), new TypeToken<Map<String, Object>>(){}.getType());
//        List<Post> posts = PostApi.with()
//                .userId("1")
//                .title("qui est esse")
//                .callApi()
//                .then()
//                .spec(AbstractBaseApi.baseSuccessfulResponse)
//                .extract().as(postListType);
        JsonPath posts = PostApi.with()
                .userId(1)
                .title("qui est esse")
                .callApi()
                .then()
                .spec(AbstractBaseApi.baseSuccessfulResponse)
                .extract().jsonPath();

        assertThat(posts.getList(USER_ID), everyItem(equalTo(1)));
        assertThat(posts.getList(TITLE), everyItem(equalTo("qui est esse")));
//        assertThat(posts, everyItem(hasProperty("userId", equalTo(1))));
//        assertThat(posts, everyItem(hasProperty("title", equalTo("qui est esse"))));
//

    }

    @Test
    public void getPostsByInexistingSetOfUserIdAndTitleTest() {
//        List<Post> posts = PostApi.with()
//                .userId("1")
//                .title("in quibusdam tempore odit est dolorem")
//                .callApi()
//                .then()
//                .spec(AbstractBaseApi.baseSuccessfulResponse)
//                .extract().as(postListType);

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
