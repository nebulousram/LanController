package nointerruptclient;

public class Message {
    
    private boolean hasMessage;
    private String message;
    
    void setMessage(String str) {
        message = str;
    }
    
    String getMessage() {
        return message;
    }
    
    public void setHasMessage(boolean value) {
        hasMessage = value;
    }
    
    public boolean hasMessage() {
        return hasMessage;
    }
}
