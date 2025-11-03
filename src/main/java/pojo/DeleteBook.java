package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBook {

    @JsonProperty("ID") // It Forces JSON key to remain uppercase
    private String ID;

    public DeleteBook(String ID) {
        this.ID = ID;
    }

    @JsonProperty("ID")
    public String getID() {
        return ID;
    }
    @JsonProperty("ID")
    public void setID(String ID) {
        this.ID = ID;
    }
}
