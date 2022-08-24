package learn.solar.domain;

import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelResult {
    List<String> messages = new ArrayList<>();
    Panel payload;
    public boolean isSuccess(){
        return messages.size() == 0;
    }

    public void setPayload(Panel payload) {
        this.payload = payload;
    }

    public Panel getPayLoad() {
        return payload;
    }

    public void addErrorMessage(String message){
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        messages.add( message );
    }

    public Panel getPayload() {
        return payload;
    }

}


