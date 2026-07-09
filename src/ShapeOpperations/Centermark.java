/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShapeOpperations;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import java.awt.DisplayMode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class Centermark {
    
    SimpleApplication app;
    Geometry geom;
    
    public Centermark(SimpleApplication app) {
        this.app = app;
        Geometry c = myBox(app,"center mark", Vector3f.ZERO, ColorRGBA.White);
        c.scale(4);
        // move to center of x/y, with y (depth) zero.
        c.setLocalTranslation( app.getCamera().getWidth()/2, app.getCamera().getWidth()/2,0);  
        app.getGuiNode().attachChild(c);
    }
    
    private Geometry myBox(SimpleApplication app,String name, Vector3f loc, ColorRGBA color) {
        geom = new Geometry(name, new Box(1, 1, 1));
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.setLocalTranslation(loc);
        return geom;
    }
    
    @Override
    public void finalize() {
        app.getGuiNode().detachChildNamed(geom.getName() );
        try { this.wait(); } catch (InterruptedException ex) { }
        catch (IllegalMonitorStateException e) { }
        
    }
    
}
