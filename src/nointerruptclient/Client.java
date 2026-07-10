package nointerruptclient;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

public class Client {
    
    // <editor-fold defaultstate="collapsed" desc="declareVariables();">
    private int port = 4040;
    InetAddress serverIP;
    public Socket clientSock;
    public PrintWriter outWriter;
    public BufferedReader inReader;
    public Message ms;
    public NewJFrame clientFrame;
    NoInterruptClient[] clients;
    public int clientIndex = 0;
    private String message = "";
    // </editor-fold>
    
    public Client() {
        ms = new Message();
        try {
            serverIP = InetAddress.getByName("192.168.1.3");
            clientSock = new Socket(serverIP, port);
            outWriter = new PrintWriter(clientSock.getOutputStream(), true);
            System.out.println("Opening Socket IP=" + serverIP + " Port=" + port);
            inReader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
            System.out.println("Connected to Server");
        } catch (IOException ex) {
            System.out.println("Failed to Connect to Server");
        }
    }
    
    
    public String readMessage() {
        String resp = new String("");
        try {
            if (inReader.ready()) resp = inReader.readLine();  
        }   
        catch (Exception ex) {
            System.out.println("Client readMessage FAILED");    
        }
        return resp;
    }
    
    public String waitForMessage() {
        String resp = new String("");
        try {
            resp = inReader.readLine();  
        }   
        catch (Exception ex) {
            System.out.println("Client readMessage FAILED");    
        }
        return resp;
    }
    
    public void sendMessage(String msg) {
        try {
            outWriter.println(toString()+" "+msg);
        }   
        catch (Exception ex) {
            System.out.println("Client sendMessage FAILED");    
        }
    }            
    
    public boolean messageAvailable() {
        boolean avail = false;
        try {
            avail = inReader.ready();
        }   
        catch (Exception ex) {
          System.out.println("Client MessageAvailable FAILED");    
        }
        return avail;
    }
    
    public void sendInitMessages() {
        
        try {
            //<editor-fold defaultstate="collapsed" desc="sendingIntialMessage();">
            message = "Client" +toString()+ " connecting on port " + port;
            System.out.println("Sending message: " + message + "\n");            // Echo message to screen
            outWriter.println(message);
            clientFrame.getTextArea().append("SENT: " + message + "\n");            // Echo message to screen
//          </editor-fold>
            //<editor-fold defaultstate="collapsed" desc="recievingMessage();">
            System.out.println("Recieving message... \n");
            inReader.readLine();
            clientFrame.getTextArea().append("RECIEVED: " + message + "\n");            // Echo message to screen
            System.out.println("Recieved message: " + message + "\n");
            // </editor-fold>
        }  catch (UnknownHostException ex ) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    
    private void findMessage() {
        while (!ms.hasMessage()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NoInterruptClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public String toString() {
        return "000"+(NoInterruptClient.selectedClient+1);
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
