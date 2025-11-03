package Apipayloads;


    public class BookLibraryAPIPayload {


        public static String AddBook(String isbn, String aisle,String authorName) {
            String payload = "{\n" +
                    "  \"name\": \"Learn Appium Automation with Java\",\n" +
                    "  \"isbn\": \"" + isbn + "\",\n" +
                    "  \"aisle\": \"" + aisle + "\",\n" +
                    "  \"author\": \""+authorName+"\"\n" +
                    "}";
            return payload;
        }


        public static String deleteBookPayload(String ID) {

            return "{\n" +
                    " \n" +
                    "\"ID\" : \"" + ID + "\"\n" +
                    " \n" +
                    "} ";

        }

    }