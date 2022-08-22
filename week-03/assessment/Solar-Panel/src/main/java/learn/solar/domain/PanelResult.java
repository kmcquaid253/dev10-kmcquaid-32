package learn.solar.domain;

import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelResult {
    private List<String> errorMessages = new ArrayList<>();
    private Panel payload;
    public boolean isSuccess(){
        return errorMessages.size() == 0;
    }

    public void setPayload(Panel payload) {
        this.payload = payload;
    }

    public Panel getPayLoad() {
        return payload;
    }

    public void addErrorMessage(String message){
        errorMessages.add(message);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

}
