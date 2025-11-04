package org.apiTestCases;
import Apipayloads.BookLibraryAPIPayload;
import apiUits.apiUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.ApiBaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pojo.AddBook;
import pojo.AddBookResponse;
import pojo.DeleteBook;
import pojo.DeleteBookResponse;
import utils.CommonUtilities;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;

public class LibraryAPITestCases extends ApiBaseTest {


    @Test(dataProvider = "BookDetails", testName = "End to End Verify book has been added and delete successfully")
    public void verifyAddDeleteBookEndToEnd(String bookName,String authorName){

        String isbn=CommonUtilities.generateRandomString(5);
        String aisle=CommonUtilities.generateRandomNumeric(5);
        //From String Payload method
       //Response AddBookresponse=postRequest(BookLibraryAPIPayload.AddBook(isbn,aisle,"Divyansh Bansal"),"/Library/Addbook.php");

        //From Pojo class
        AddBook addBook=new AddBook(bookName,isbn,aisle,authorName);
        Response AddBookresponse=postRequestPojo("/Library/Addbook.php",addBook);

        String Add_book_response=extractResponse(AddBookresponse);
        System.out.println("Response of Add Book :"+Add_book_response);
        JsonPath jsonPath=apiUtils.jsonPath(Add_book_response);

        Assert.assertEquals(jsonPath.get("Msg"),"successfully added");
        String Book_ID=jsonPath.get("ID");
        System.out.println(Book_ID);

        //Get Book by Author's Name
        Map<String,String> map=new HashMap<>();
        map.put("AuthorName",authorName);
        Response response1=getRequest("Library/GetBook.php",map);
        String GetResponseByAuthor=extractResponse(response1);
        System.out.println(GetResponseByAuthor);

        JsonPath js=apiUtils.jsonPath(GetResponseByAuthor);
        Assert.assertTrue(js.getList("$").size()>=1);
        System.out.println("First Book "+js.get("[0].book_name"));
        System.out.println("Second Book: "+js.get("[1].book_name"));

        //Get Book details by Book ID
        Map<String,String> map1=new HashMap<String,String>();
        map1.put("ID",Book_ID);

        String BookIdResponse=extractResponse(getRequest("Library/GetBook.php",map1));
        JsonPath js1=apiUtils.jsonPath(BookIdResponse);

        Assert.assertEquals(js1.get("[0].book_name"),bookName);
        Assert.assertEquals(js1.get("[0].isbn"),isbn);
        Assert.assertEquals(js1.get("[0].aisle"),aisle);
        Assert.assertEquals(js1.get("[0].author"),authorName);

        //Delete Book
        DeleteBook deleteBook=new DeleteBook(Book_ID);
        String deleteResponse=extractResponse(deleteRequestPojo(deleteBook,"Library/DeleteBook.php"));


       // DeleteBookResponse deleteBookResponse=deserializeJson(deleteRequestPojo(deleteBook,"Library/DeleteBook.php"),DeleteBookResponse.class);

        JsonPath js2=apiUtils.jsonPath(deleteResponse);

        Assert.assertEquals(js2.get("msg"),"book is successfully deleted");

      //  Assert.assertEquals(deleteBookResponse.getMsg(),"book is successfully deleted");
    }


 @DataProvider(name = "BookDetails")
 public Object[][] bookData()
 {
     return new Object[][]{
             {"Selenium Automation Framework Book","Divyansh Bansal"},
             {"RestAssured Automation Framework Book","Divyansh second user"}

 };

 }

}
