import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RoomPOJO {

    //this is for the nested values
    String south;
    String item;
    String item_status;
    String item2;
    String item_status2;
    String randenc;
    String desc;


    @SuppressWarnings("unchecked")
    @JsonProperty("ROOM")
    private void unpackNestedItemsAndDescriptions(Map<String,Object> ROOM){
        this.south = (String) ROOM.get("south");
        Map<String, String> owner = (Map<String, String>)ROOM.get("item");
        this.item = owner.get("item");

    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem_status() {
        return item_status;
    }

    public void setItem_status(String item_status) {
        this.item_status = item_status;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem_status2() {
        return item_status2;
    }

    public void setItem_status2(String item_status2) {
        this.item_status2 = item_status2;
    }

    public String getRandenc() {
        return randenc;
    }

    public void setRandenc(String randenc) {
        this.randenc = randenc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}