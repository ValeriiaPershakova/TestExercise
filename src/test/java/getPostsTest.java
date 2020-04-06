import api.AbstractBaseApi;
import api.PostApi;
import beans.Post;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class getPostsTest {
    private Type postListType = new TypeToken<List<Post>>() {
    }.getType();


    @AfterClass
    public void teardown() {
        RestAssured.reset();
    }

    @Test
    public void getPostsByUserIdAndTitleTest() {
        List<Post> posts = PostApi.with()
                .userId("1")
                .title("qui est esse")
                .callApi()
                .then()
                .spec(AbstractBaseApi.baseSuccessfulResponse)
                .extract().as(postListType);

        assertThat(posts, everyItem(hasProperty("userId", equalTo(1))));
        assertThat(posts, everyItem(hasProperty("title", equalTo("qui est esse"))));
    }

    @Test(dataProvider = "Invalid Data Sets Provider", dataProviderClass = InvalidDataSetDataProvider.class)
    public void getPostsByNonexistentSetOfUserIdAndTitleTest(Object userId, Object title) {
        List<Post> posts = PostApi.with()
                .userId(userId)
                .title(title)
                .callApi()
                .then()
                .spec(AbstractBaseApi.baseSuccessfulResponse)
                .extract().as(postListType);

        assertThat(posts, empty());
    }
}
