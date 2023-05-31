package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost"; //set the port
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("stores")
                .then().statusCode(200);
        //response.log().all();
    }

    //1.Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store : " + name);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> nameAllStore = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all the store : " + nameAllStore);
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<String> storeIDAll = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all the store : " + storeIDAll);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> dataSize = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all the store : " + dataSize);
        System.out.println("------------------End of Test---------------------------");

    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> storeValue = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all the storeValue : " + storeValue);
        System.out.println("------------------End of Test---------------------------");

    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> storeName = response.extract().path("data.findAll{it.name == 'Rochester'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all the storeValue : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }
//9. Get all the services of 8th store
        @Test
        public void test009 () {
            // List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name == '8th store'}");
            String store = response.extract().path("limit");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("The name of all the storeValue : " + store);
            System.out.println("------------------End of Test---------------------------");
        }
//10. Get store services of the store where service name = Windows Store
@Test
public void test010 () {
    List<String> storeservices = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store services : " + storeservices);
    System.out.println("------------------End of Test---------------------------");
}
//11. Get all the storeId of all the store
    @Test
    public void test11() {
        List<Integer> storeIDAll = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store IDs : " + storeIDAll);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test12() {
        List<Integer> storeID = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store IDs : " + storeID);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test13() {
        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store Name : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test14() {
        List<?> totalService = response.extract().path("data.find{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total Number of Services : " + totalService.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test15() {
        List<?> services = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Created At : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test16() {
        List<HashMap<String, ?>> totalService = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total Number of Services : " + totalService);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test17() {
        List<HashMap<Objects, ?>> zipCode = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Zip Number of All Store : " + zipCode);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test18() {
        List<HashMap<String, ?>> zipStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Zip Number of All Store : " + zipStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeServices details of the service name = Magnolia Home Theater
    @Test
    public void test19() {
        List<HashMap<String, ?>> storeService = response.extract().path("data[2].services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store Service of Mongolia Home Theater : " + storeService);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test20() {
        List<HashMap<Objects, ?>> latStore = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of All Store : " + latStore);
        System.out.println("------------------End of Test---------------------------");
    }
    }
