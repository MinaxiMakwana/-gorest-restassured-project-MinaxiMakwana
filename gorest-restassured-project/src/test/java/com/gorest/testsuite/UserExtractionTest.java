package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import com.gorest.propertyreader.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {

    static ValidatableResponse response;

    String user = PropertyReader.getInstance().getProperty("resourceUser");

    @BeforeClass
    public void getUser() {
        Map<String, Integer> qParams = new HashMap<>();
        qParams.put("page", 1);
        qParams.put("per_page", 20);
        response = given()
                .queryParams(qParams)
                .when()
                .get(user)
                .then().statusCode(200);
    }

        //1. Extract the All Ids
        @Test
        public void test1() {

            List<Integer> listOfIds = response.extract().path("id");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of Ids are : " + listOfIds);
            System.out.println("------------------End of Test---------------------------");
        }

        //2. Extract the all Names
        @Test
        public void test2() {

            List<String> listOfNames = response.extract().path("name");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of names are : " + listOfNames );
            System.out.println("------------------End of Test---------------------------");
        }
        //3. Extract the name of 5th object
        @Test
        public void test3() {

            String BhargaviShahJD = response.extract().path("[4].name");

            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of names : " + BhargaviShahJD);
            System.out.println("------------------End of Test---------------------------");
        }

        //4. Extract the names of all object whose status = inactive
        @Test
        public void test4() {
          List <String> inactive = response.extract().path(("findAll{it.status == 'inactive'}.name"));
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Inactive list : " + inactive);
            System.out.println("------------------End of Test---------------------------");
        }
        //5. Extract the gender of all the object whose status = active
        @Test
        public void test5() {
            List <String> gender = response.extract().path(("findAll{it.gender == 'active'}.name"));
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Active list : " + gender);
            System.out.println("------------------End of Test---------------------------");
        }
        //6. Print the names of the object whose gender = female
        @Test
        public void test6() {
            List <String> female = response.extract().path(("findAll{it.gender == 'female'}.name"));
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of all females : " + female);
            System.out.println("------------------End of Test---------------------------");
        }
        //7. Get all the emails of the object where status = inactive
        @Test
        public void test7() {
            List <String> emails = response.extract().path(("findAll{it.email == 'inactive'}.name"));
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Inactive users emails : " + emails);
            System.out.println("------------------End of Test---------------------------");
        }
        //8. Get the ids of the object where gender = male
        @Test
        public void test8() {
            List <String> emails = response.extract().path(("findAll{it.id == 'male'}.gender"));
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Ids of all male : " + emails);
            System.out.println("------------------End of Test---------------------------");
        }
        //9. Get all the status
        @Test
        public void test9() {
            List<String> listOfStatus = response.extract().path("status");
                System.out.println("------------------StartingTest---------------------------");
                System.out.println("List of all the status : " + listOfStatus );
                System.out.println("------------------End of Test---------------------------");
        }
        //10. Get email of the object where name = Lal Dwivedi
        @Test
        public void test10() {
            String AkshatEmbranthiri = response.extract().path("[5].email");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of names : " + AkshatEmbranthiri);
            System.out.println("------------------End of Test---------------------------");
        }

        //11. Get gender of id = 5914189
        @Test
        public void test11() {
            List <String> gender = response.extract().path(("findAll{it.id == '93944'}.gender"));
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Ids 93944 : " + gender);
            System.out.println("------------------End of Test---------------------------");
        }

}
