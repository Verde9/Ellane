package com.ellane.jsonparsing;

import com.ellane.jsonparsing.pojo.AuthorPOJO;
import com.ellane.jsonparsing.pojo.BooksPOJO;
import com.ellane.jsonparsing.pojo.DayPojo;
import com.ellane.jsonparsing.pojo.SimpleTestCaseJsonPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JsonTest {


    private String simpleTestCaseJsonSource = "{\n" +
            "  \"title\": \"Holes\",\n" +
            "  \"author\": \"JD Salinger\"\n" +
            "}";

    private String dayScenario1 = "{\n" +
            "  \"date\": \"2019-12-25\",\n" +
            "  \"name\": \"Christmas Day\"\n" +
            "}";

    private String authorAndBookPOJOTestCase = "{\n" +
            "  \"authorName\": \"Dave Brown\",\n" +
            "  \"books\": [{\n" +
            "    \"title\": \"title1\",\n" +
            "    \"inPrint\": true,\n" +
            "    \"publishDate\":\"2019-12-25\"\n" +
            "  },{\n" +
            "    \"title\": \"title1\",\n" +
            "    \"inPrint\": false,\n" +
            "    \"publishDate\":\"2019-01-01\"\n" +
            "  }\n" +
            "  ]\n" +
            "}";


    @Test
    public void parse_Should_Grab_Key_From_SimpleTestCaseJsonSource() throws IOException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        System.out.println(node.get("title").asText());
        assertEquals(node.get("title").asText(), "Holes");
    }

    @Test
    public void fromJson_Should_Grab_SimpleTestCaseJsonPOJO_Class_Title() throws IOException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);
        System.out.println("POJO title: " + pojo.title);
        assertEquals(pojo.title, "Holes");

    }

    @Test
    public void toJson_Will_Take_The_SimpleTestCaseJsonPOJO_Set_The_Title_And_Pass_Correctly() {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Test String");
        JsonNode node = Json.toJson(pojo);
        System.out.println(Json.toJson(pojo));

        assertEquals(node.get("title").asText(), "Test String");
    }

    @Test
    public void stingifyJson_Will_Turn_Key_Value_Pair_Into_String() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Test String");

        JsonNode node = Json.toJson(pojo);

        System.out.println(Json.stingifyJson(node));


    }

    @Test
    public void prettyPrintJson_Will_Turn_Json_Into_An_Indented_And_Readable_file() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Test String");

        JsonNode node = Json.toJson(pojo);
        System.out.println(Json.prettyPrintJson(node));
    }

    @Test
    public void dayTestScenario1_Should_Grab_DayPojo_Class_Title_And_Show_GET_date()
            throws IOException {
        JsonNode node = Json.parse(dayScenario1);
        DayPojo pojo = Json.fromJson(node, DayPojo.class);
        assertEquals("2019-12-25", pojo.getDate().toString());
    }


    @Test
    public void authorAndBookPOJOTestCase_Should_Grab_AuthorPojo_Class_Title_And_Show_GET_Data_correctly()
            throws IOException {
        JsonNode node = Json.parse(authorAndBookPOJOTestCase);
        AuthorPOJO pojo = Json.fromJson(node, AuthorPOJO.class);
        System.out.println("Author :" + pojo.getAuthorName());
        System.out.println("Date " + pojo.getBooks());
        for (BooksPOJO booksPOJO: pojo.getBooks()){
            System.out.println("Book :" + booksPOJO.getTitle());
            System.out.println("IS in Print? " + booksPOJO.isInPrint());
            System.out.println("Date: " + booksPOJO.getPublishDate());
        }
    }
}