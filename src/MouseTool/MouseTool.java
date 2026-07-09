package MouseTool;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
//import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.io.IOException;
import java.io.InputStream;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MouseTool {
    
    private final String MAPPING_CLICK = "Click";
    private final Trigger TRIGGER_CLICK = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    private SimpleApplication app;
    private boolean clickPressed = false;
    private boolean clickPrevious = false;
    private boolean clickPreviouslyPressed = false;
    private boolean clickReleased = true;
    public boolean canBreak = false;
    private int currentShape;
    
    public MouseTool(SimpleApplication app) {
        this.app = app;
        app.getInputManager().addMapping(MAPPING_CLICK, TRIGGER_CLICK );
        app.getInputManager().addListener( analogListener , MAPPING_CLICK );
        currentShape = -1;
    }
    
    private ActionListener analogListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            clickPressed = isPressed;
        }
    };
    
    public void waitMouse() {
        
        while ( true ) {
            whileRunning();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                
            }
            if (canBreak) { canBreak = false; break; }
        };
        
    }
    
    public abstract void mouseClicked(); 
    
    public abstract void mousePressed(); 
    
    public abstract void mouseReleased();
    
    private void whileRunning() {
        if ( clickPressed ) {
            mouseClicked();
            clickPressed = true;
            clickReleased = false;
        }
        if ( logicalAnd(clickPrevious,clickPressed) ) {
            mousePressed();
            clickPreviouslyPressed = true;
            clickReleased = false;
        }
        if ( logicalNot(clickPressed) ) {
            clickPressed = false;
            clickReleased = true;
        }
        if ( logicalAnd(clickPreviouslyPressed,clickReleased ) ) {
            canBreak = true;
            mouseReleased();
            clickPreviouslyPressed = false;
        }
        clickPrevious = clickPressed;
    }
    
    private boolean logicalNot(boolean one) {
        return !one;
    }
    
    private boolean logicalAnd(boolean one, boolean two) {
        return (one && two);
    }
    
    @Override
    public void finalize() {
        canBreak = false;
        app.getInputManager().deleteTrigger(MAPPING_CLICK, TRIGGER_CLICK);
        app.getInputManager().deleteMapping(MAPPING_CLICK);
        try {
            this.analogListener.wait();
            this.analogListener = null;
        } catch (InterruptedException ex) { }
        catch (IllegalMonitorStateException e) { }
    }
    
    // <editor-fold defaultstate="collapsed" desc="debug(String toggle) {}">
    /*void debug(String toggle) {
        if (toggle.contains("a") ) {
            if ( logicalAnd(clickPrevious,clickPressed) ) System.out.println("clickPrevious && clickPressed: " + logicalAnd(clickPrevious,clickPressed)  );
            if ( logicalAnd(clickPreviouslyPressed,clickReleased) ) System.out.println("clickPreviouslyPressed & clickReleased: " + logicalAnd( clickPreviouslyPressed,clickReleased ) );
            
        } if (toggle.contains("b") ) {
            if ( clickPressed ) System.out.println("Is clickPressed: " + ( clickPressed ) );
            if ( logicalNot(clickPressed) ) System.out.println("NOT clickPressed: " + logicalNot(clickPressed) );
            if (clickPreviouslyPressed) System.out.println("clickPreviouslyPressed = true");
            if ( logicalNot(clickPressed) ) System.out.println("clickReleased: " + clickReleased);
            if (clickReleased) System.out.println("canBreak: " + canBreak);
            
        } if ( toggle.contains("c") ) {
            if ( clickPreviouslyPressed & clickReleased )  System.out.println("( clickPreviouslyPressed == (clickReleased == true) ): " + ( clickPreviouslyPressed && (clickReleased == true) ) );
            if ( clickPreviouslyPressed & clickReleased )  System.out.println("( clickPreviouslyPressed == (clickReleased == true) ): " + ( clickPreviouslyPressed && (clickReleased == true) ) );
            if ( clickPreviouslyPressed & clickReleased ) System.out.println("( clickPreviouslyPressed & (clickReleased == true) ): " + ( clickPreviouslyPressed && (clickReleased == true) ) );
            if ( clickPreviouslyPressed & clickReleased ) System.out.println("clickPreviouslyPressed: " + clickPreviouslyPressed + ", clickReleased: " + clickReleased );
            if ( clickPreviouslyPressed ) System.out.println("clickPreviouslyPressed: " + clickPreviouslyPressed + ", clickReleased: " + clickReleased );
            if ( clickReleased ) System.out.println("clickPreviouslyPressed: " + clickPreviouslyPressed + ", clickReleased: " + clickReleased );
        }
    }*/
    
// </editor-fold>
    
}