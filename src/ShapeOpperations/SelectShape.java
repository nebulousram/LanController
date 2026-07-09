/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShapeOpperations;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import MouseTool.MouseTool;


public class SelectShape {
    
    SimpleApplication app;
    int currentShape =-1;
    Centermark mark;
    MouseTool mousy;
    
    public SelectShape(SimpleApplication app) {
        
        this.app = app;
        mark = new Centermark(this.app);
        mousy  = new MouseTool(app) {
            
            @Override
            public void mouseClicked() {

            }

            @Override
            public void mousePressed() {

            }

            @Override
            public void mouseReleased() {
                selectShape();
            }
        };
        
        while (currentShape == -1) {
            mousy.waitMouse();
            currentShape = getShape();
        }
        
    }
    
    private void selectShape() {
        if ( mousy.canBreak ) {
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
    }
    
    public int getShapeIndex() {
        return currentShape;
    }
    
    private int getShape() {
        return currentShape;
    }
    
    public void finalize() {
        mousy.finalize();
        mark.finalize();
    }
}

    
    // <editor-fold defaultstate="collapsed" desc="public void selectBox">
    /*if ( clickPressed ) {
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
    
}
*/
// </editor-fold>
    
