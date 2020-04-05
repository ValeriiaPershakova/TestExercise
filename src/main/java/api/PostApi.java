package api;

import constants.Fields;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class PostApi extends AbstractBaseApi {
    private Map<String, Object> params = new HashMap<>();
    private Method requestMethod = Method.GET;

    private PostApi() {
    }

    public static ApiBuilder with() {
        PostApi api = new PostApi();
        return new ApiBuilder(api);
    }

    public static class ApiBuilder {
        private PostApi api;

        private ApiBuilder(PostApi api) {
            this.api = api;
        }

        public ApiBuilder userId(Object userId) {
            api.params.put(Fields.USER_ID, userId);
            return this;
        }

        public ApiBuilder title(Object title) {
            api.params.put(Fields.TITLE, title);
            return this;
        }

        public ApiBuilder request(Method req) {
            api.requestMethod = req;
            return this;
        }

        public Response callApi() {
            return RestAssured.given(baseRequestConfiguration)
                    .with()
                    .queryParams(api.params)
                    .request(api.requestMethod).prettyPeek();
        }
    }

}
