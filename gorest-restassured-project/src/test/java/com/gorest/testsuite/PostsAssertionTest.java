package com.gorest.testsuite;

import com.gorest.propertyreader.PropertyReader;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {


    static ValidatableResponse response;

    String posts = PropertyReader.getInstance().getProperty("resourcePosts");

    @BeforeClass
    public void getUser() {
        Map<String, Integer> qParams = new HashMap<>();
        qParams.put("page", 1);
        qParams.put("per_page", 20);
        response = given()
                    .when()
                    .get(posts)
                    .then().statusCode(200);
    }
        //  1. Verify the if the total record is 25
        @Test
        public void test1(){
            //response.body("total", equalTo(25));
            List<Map<String,?>> list=response.extract().path("$");
            Assert.assertEquals(list.size(),10);
        }
        // 2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero.”
        @Test
        public void test2(){
            response.body("findAll{it.user_id=5914181}.get(0).title",equalTo("Ambitus thesaurus contabesco tero amplitudo confugo et tutamen vulgivagus."));

        }
        // 3. Check the single user_id in the Array list (5914181)
        @Test
        public void test3(){
            response.body("user_id", hasItem(5914181));
        }
        // 4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
        @Test
        public void test4(){
            response.body("user_id", hasItems(5914181, 5914184, 5914184, 5914181, 5914161, 5914156, 5914151, 5914070, 5914070, 5914068));
        }
        //5. Verify the body of userid = 5914197 is equal
        @Test
        public void test5() {
            response.body("findAll{it.user_id==5914184}.get(0).body", equalTo("Cinis non solum. Decretum auctus artificiose. Bos umerus totam. Sed cicuta debitis. Crur unde tum. Et tabella dignissimos. Cognomen vito bardus. Deduco ara una. Desparatus amet caste. Quis taedium sollers."));
        }
}
