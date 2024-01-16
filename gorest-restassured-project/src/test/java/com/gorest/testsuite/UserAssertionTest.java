package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import com.gorest.propertyreader.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserAssertionTest extends TestBase {

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
// 1. Verify the if the total record is 20
    @Test
    public void test1(){
        List<Map<String,?>> list=response.extract().path("$");
        Assert.assertEquals(list.size(),20);
    }
//2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
    @Test
    public void test2(){
        response.body("findAll{it.id=5914074}.get(0).Name",equalTo("Rev. Brahmaanand Khanna"));
    }

// 3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
    @Test
    public void test3(){
        response.body("name", hasItem("Dev Bhattacharya"));
    }

// 4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test4(){
        response.body("name", hasItems("Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy"));
    }

// 5. Verify the email of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
    @Test
    public void test5(){
        response.body("findAll{it.id=5914074}.get(0).email",equalTo("rev_khanna_brahmaanand@gleason-volkman.test"));
    }
// 6. Verify the status is “Active” of username is “Amaresh Rana”
    @Test
    public void test6(){
        response.body("findAll{it.name= Amaresh Rana}.status.get(0)",equalTo("Active"));
    }

//7. Verify the Gender = female of user name is “Dhanalakshmi Pothuvaal”
    @Test
    public void test7(){
        response.body("findAll{it.name= Dhanalakshmi Pothuvaal}.gender.get(0)",equalTo("female"));
    }
}
