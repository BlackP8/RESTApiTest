package project.testcases;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.models.Post;
import project.testcases.steps.Steps;
import restframework.universalutils.DataGeneratorUtil;
import restframework.universalutils.DataProviderUtil;
import restframework.universalutils.JsonUtil;

/**
 * @author Pavel Romanov 06.03.2023
 */

public class CreatePostTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void postRequestTest(String userId, String stringLength) {
        Post postToSend = Post.builder().userId(userId).title(DataGeneratorUtil.generateRandomString(Integer.valueOf(stringLength)))
                .body(DataGeneratorUtil.generateRandomString(Integer.valueOf(stringLength))).build();
        Post actualPost = Steps.createPost(Endpoints.ALL_POSTS.getStringValue(), JsonUtil.getJsonFromModel(postToSend),
                HttpStatus.SC_CREATED);
        Steps.checkPostParams(actualPost, postToSend);
    }
}
