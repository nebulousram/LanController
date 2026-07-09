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
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CursorPickTarget {
    
    SimpleApplication app;
    private static boolean canBreak = false;
    private Thread t = new Thread();
    private final String LEFT_CLICK = "Left-Click";
    private final String RIGHT_CLICK = "Right-Click";
    private final Trigger TRIGGER_LEFT = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    private final Trigger TRIGGER_RIGHT = new MouseButtonTrigger(MouseInput.BUTTON_RIGHT);
    boolean left;
    boolean right;
    
    Monitor monitor = new Monitor();
    MouseEvent moo = null;
    
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            
            if (name.equals(RIGHT_CLICK) ) right = isPressed;
            if (name.equals(TRIGGER_LEFT) ) left = isPressed;
            
            MouseListener mouse = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    monitor.wakeMouse();
                    if ( right )  app.getFlyByCamera().setDragToRotate(true);
                    mouseClicked2();
                }
                @Override
                public void mousePressed(MouseEvent me) {
                    mousePressed2();
                }
                @Override
                public void mouseReleased(MouseEvent me) {
                    monitor.wakeMouse();
                    mouseReleased2();
                    
                }
                @Override
                public void mouseEntered(MouseEvent me) {
                    mouseEntered2();
                }
                @Override
                public void mouseExited(MouseEvent me) {
                    mouseExited2();
                }
                
            };
            mouse.mouseClicked(moo);
            mouse.mousePressed(moo);
            mouse.mouseEntered(moo);
            mouse.mouseReleased(moo);
            mouse.mouseExited(moo);
        }
    };
    
    public CursorPickTarget(SimpleApplication app) {
        this.app = app;
        this.app.getInputManager().addMapping(LEFT_CLICK, TRIGGER_LEFT );
        this.app.getInputManager().addListener( actionListener , LEFT_CLICK );
        
        this.app.getInputManager().addMapping(RIGHT_CLICK, TRIGGER_RIGHT );
        this.app.getInputManager().addListener( actionListener , RIGHT_CLICK );
        
    }
    
    public abstract void mouseClicked2(); 
    public abstract void mousePressed2(); 
    public abstract void mouseEntered2();
    public abstract void mouseReleased2();
    public abstract void mouseExited2();
    
    public void waitMouse() {
        monitor.waitMouse();
        try { Thread.sleep(1000); } catch (InterruptedException ex) { }
    }
    
    public int getShapeIndex() {return 0; }
    
    private int getShape() {return 0; }
    
    public void finalize() { }
    
}


    // <editor-fold defaultstate="collapsed" desc="selectShape() {}">
    /*private void selectShape() {
        if ( true ) {
            // Reset results list.
            CollisionResults results = new CollisionResults();
            // Aim the ray from camera location in camera direction 
            // (assuming crosshairs in center of screen).
            Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
            // Collect intersections between ray and all nodes in results list.
            app.getRootNode().collideWith(ray, results);
            // (Print the results so we see what is going on)
            for (int i = 0; i < results.size(); i++) {
                // (For each “hit”, we know distance, impact point, geometry.)
                float dist = results.getCollision(i).getDistance();
                Vector3f pt = results.getCollision(i).getContactPoint();
                String target = results.getCollision(i).getGeometry().getName();
            }
            // 5. Use the results -- we rotate the selected geometry.
            if (results.size() > 0) {
                // The closest result is the target that the player picked:
                Geometry target = results.getClosestCollision().getGeometry();
                if ( currentShape == -1) {
                    target.rotate(0, (float) 0.03, 0);
                    currentShape = Integer.parseInt( target.getName().trim() );
                    System.out.println("Box " + currentShape + " Selected!");
                }
                // --------------------- --------------------- ---------------------
                
            } else {
                
            }
        }
    }*/
// </editor-fold>
