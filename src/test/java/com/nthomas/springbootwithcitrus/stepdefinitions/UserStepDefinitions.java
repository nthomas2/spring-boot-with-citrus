package com.nthomas.springbootwithcitrus.stepdefinitions;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nthomas.springbootwithcitrus.config.CitrusConfig;
import com.nthomas.springbootwithcitrus.model.UserProfileData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;

import javax.sql.DataSource;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CucumberContextConfiguration
@SpringBootTest(classes = {CitrusConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserStepDefinitions {
    @Autowired
    private TestRunner testRunner;
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private DataSource datasource;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        testRunner.sql(action -> action
                .dataSource(datasource)
                .statement("DELETE FROM USER_PROFILE")
        );
    }

    @When("we get on {string}")
    @CitrusTest
    public void weGetOn(String arg0) {
        testRunner.http(action -> action
                .client(httpClient)
                .send()
                .get(arg0)
                .contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE));
    }

    @Then("we get a Not Found response with the message {string}")
    public void weGetAResponseWithTheMessage(String arg0) {
        testRunner.http(action -> action
                .client(httpClient)
                .receive()
                .response(HttpStatus.NOT_FOUND)
                .jsonPath("$.reason", arg0));
    }

    @Value("classpath:sqlData/selectBob.json")
    private Resource selectBob;

    @Given("the following user exists")
    public void theEmailExists(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMaps().get(0);
        testRunner.sql(action -> action
                .dataSource(datasource)
                .statement(
                        String.format(
                                "INSERT INTO user_profile(email, first_name, last_name) values('%s', '%s', '%s')",
                                dataMap.get("email"), dataMap.get("first_name"), dataMap.get("last_name"))
                )
        );
    }

    @Then("we get an Ok response with the user")
    public void weGetAResponseWithTheUser(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMaps().get(0);

        testRunner.http(action -> action
                .client(httpClient)
                .receive()
                .response(HttpStatus.OK)
                .jsonPath("$.email", dataMap.get("email"))
                .jsonPath("$.firstName", dataMap.get("first_name"))
                .jsonPath("$.lastName", dataMap.get("last_name"))
        );
    }

    @When("we put on {string} with the user")
    public void wePutOnWithTheUser(String arg0, DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMaps().get(0);
        UserProfileData userProfileData = UserProfileData.builder()
                .email(dataMap.get("email"))
                .firstName(dataMap.get("first_name"))
                .lastName(dataMap.get("last_name"))
                .build();
        testRunner.http(action -> action
                .client(httpClient)
                .send()
                .put(arg0)
                .payload(userProfileData, objectMapper)
                .contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE));
    }
}
