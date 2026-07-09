package nointerruptclient;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;
import java.util.ArrayList;

public class NoInterruptClient {
    
    
    // <editor-fold defaultstate="collapsed" desc="declareVariables();">
    public static ArrayList<Client> clients = new ArrayList<Client>();
    public static int clientIndex = 0;
    public static int selectedClient = 0;
    
    // </editor-fold>
    
    public NoInterruptClient() {
        createNextClient();
        createNextClient();
        createNextClient();
        createNextClient();
    }
    
    public static void main(String[] args) {
        NoInterruptClient game = new NoInterruptClient();
        clients.get(selectedClient).sendInitMessages();
        clients.get(0).clientFrame.setVisible(true);
        selectedClient++;
        clients.get(selectedClient).sendInitMessages();
        selectedClient++;
        clients.get(selectedClient).sendInitMessages();
        selectedClient++;
        clients.get(selectedClient).sendInitMessages();
    }
    
    public void createNextClient() {
        clients.add( new Client() );
        if (clientIndex == 0) { 
            System.out.println("This is where you should make a frame!");
            clients.get(clientIndex).clientFrame = new NewJFrame();
            System.out.println("Look details on your frame object: " + clients.get(0).clientFrame.toString() );
            
        }
        else { clients.get(clientIndex).clientFrame = clients.get(0).clientFrame; }
        clientIndex++;
    }
    
}
    

//<editor-fold defaultstate="collapsed" desc="editorFold();">
/*
    private boolean running = false;
    private String message = "";
    private boolean hasMessage;
    public static Message ms = new Message();
    private Socket clientSock;
*/
// </editor-fold>
// End Sender
//            courier.sendMessage(clientSock);
//            courier.doneSending();
//    private String message = "What assets do you want to load? ";
    
//    String sentence = "UdpClient";

