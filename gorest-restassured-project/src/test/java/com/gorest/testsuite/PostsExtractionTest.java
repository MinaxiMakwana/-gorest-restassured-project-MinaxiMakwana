package com.gorest.testsuite;

import com.gorest.propertyreader.PropertyReader;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {

    static ValidatableResponse response;

    String posts = PropertyReader.getInstance().getProperty("resourcePosts");

    @BeforeClass
    public void getUser() {
        Map<String, Integer> qParams = new HashMap<>();
        qParams.put("page", 1);
        qParams.put("per_page", 20);
        response = given()
                .queryParams(qParams)
                .when()
                .get(posts)
                .then().statusCode(200);
    }

        // 1. Extract the title
        @Test
        public void test1() {
            List<String> listOfTitles = response.extract().path("title");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of titles are : " + listOfTitles );
            System.out.println("------------------End of Test---------------------------");
        }
        //2. Extract the total number of record
        @Test
        public void test2() {
            List<String> listOfNames = response.extract().path("total");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Total number of record : " + listOfNames );
            System.out.println("------------------End of Test---------------------------");
        }
        // 3. Extract the body of 15th record
        @Test
        public void test3() {
            String record15th = response.extract().path("[14].body");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("15th record : " + record15th);
            System.out.println("------------------End of Test---------------------------");
        }
        //4. Extract the user_id of all the records
        @Test
        public void test4() {
            List<Integer> listOfIds = response.extract().path("user_id");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of Ids are : " + listOfIds);
            System.out.println("------------------End of Test---------------------------");
        }

        //5. Extract the title of all the records
        @Test
        public void test5() {
            List<String> listOfTitle = response.extract().path("title");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of titles are : " + listOfTitle);
            System.out.println("------------------End of Test---------------------------");
        }
        //6. Extract the title of all records whose user_id = 5914200
        @Test
        public void test6() {
            List <Integer> title = response.extract().path(("findAll{it.user_id == '5914181'}.title"));
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Ids 5914181 : " + title);
            System.out.println("------------------End of Test---------------------------");
        }
        //7. Extract the body of all records whose id = 93957
        @Test
        public void test7() {
            List <Integer> record = response.extract().path(("findAll{it.id == '93942'}.body"));
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Ids 93944 : " + record);
            System.out.println("------------------End of Test---------------------------");
        }
}
