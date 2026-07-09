/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MouseTool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class Monitor {
    
    void waitMouse() {
        synchronized(this) {
            System.out.println("Thread is going to sleep...");
            try {  wait(); } catch (InterruptedException ex) { }
            try {  wait(); } catch (InterruptedException ex) { }
            System.out.println("Thread is waking up =)");
        }
    }
    
    void wakeMouse() {
        synchronized(this) {
            notify();
        }
    }
    
}
