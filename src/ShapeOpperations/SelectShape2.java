/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShapeOpperations;

import MouseTool.CursorPickTarget;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

/**
 *
 * @author Michael
 */
public class SelectShape2 {
    SimpleApplication app;
    int currentShape =-1;
    CursorPickTarget chooser;
    public SelectShape2(SimpleApplication app) {
        
        this.app = app;
        chooser = new CursorPickTarget(app) {
            @Override
            public void mouseClicked2() {}
            @Override
            public void mousePressed2() {}
            @Override
            public void mouseEntered2() {}
            @Override
            public void mouseReleased2() { selectShape2(); }
            @Override
            public void mouseExited2() {}
            
        };
        
        while (currentShape == -1) {
            chooser.waitMouse();
            currentShape = getShape();
        }
    }
    
    private int getShape() {
        return currentShape;
    }
    
    public int getShapeIndex() {
        return currentShape;
    }
    
    private void selectShape2() {
        // Reset results list.
        CollisionResults results = new CollisionResults();
        // Convert screen click to 3d position
        Vector2f click2d = app.getInputManager().getCursorPosition();
        Vector3f click3d = app.getCamera().getWorldCoordinates(new Vector2f(click2d.getX(), click2d.getY()), 0f);
        Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(click2d.getX(), click2d.getY()), 1f).subtractLocal(click3d);
        Ray ray = new Ray(click3d, dir);
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
