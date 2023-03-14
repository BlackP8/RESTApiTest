package project.testcases;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import project.base.BaseTest;
import project.endpoints.Endpoints;
import project.models.Post;
import project.testcases.steps.Steps;
import restframework.universalutils.DataGeneratorUtil;
import restframework.universalutils.DataProviderUtil;

/**
 * @author Pavel Romanov 06.03.2023
 */

public class CreatePostTest extends BaseTest {
    @Test(dataProviderClass = DataProviderUtil.class, dataProvider = "testData")
    public void postRequestTest(String userId, String stringLength) {
        Post postToSend = Post.builder().userId(Integer.valueOf(userId))
                .title(DataGeneratorUtil.generateRandomString(Integer.valueOf(stringLength)))
                .body(DataGeneratorUtil.generateRandomString(Integer.valueOf(stringLength))).build();
        Steps.checkPostRequest(Endpoints.ALL_POSTS.getStringValue(), postToSend, HttpStatus.SC_CREATED);
    }
}
