/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScanController;

import MouseTool.CursorPickTarget;
import Shapes.Shapes;
import ShapeOpperations.Rotate;
import ShapeOpperations.Runaway;
import ShapeOpperations.SelectShape;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.AnalogListener;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.light.AmbientLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.util.ArrayList;
import ShapeOpperations.MoveShape;
import ShapeOpperations.SelectShape2;
/**
 *
 * @author Michael
 */
public class ShapeController {
    
    private static final String MAPPING_ROTATE = "Rotate";
    private static final Trigger TRIGGER_ROTATE = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    SimpleApplication app;
    Shapes arrayModifer;
    int currentShape;
    
    public ShapeController(SimpleApplication app) {
        this.app = app;
        arrayModifer = new Shapes(app);
        currentShape= -1;
    }
    
    public void moveShape() {
        selectShape2();
        MoveShape mover = new MoveShape(app);
        mover.move(arrayModifer,currentShape);
    }
    
    public Geometry createShape(String shape, AssetManager ast) {
        return arrayModifer.add(shape);
    }
    
    public void selectShape() {
        SelectShape ss = new SelectShape(this.app);
        currentShape = ss.getShapeIndex();
        ss.finalize();
        try { Thread.sleep(1000); } catch (InterruptedException ex) { }
    }
    
    
    public void selectShape2() {
        SelectShape2 ss = new SelectShape2(this.app);
        currentShape = ss.getShapeIndex();
        try { Thread.sleep(1000); } catch (InterruptedException ex) { }
        
    }
    
    public void chaseShape() {
        Runaway shape = new Runaway();
        app.getStateManager().attach(shape);
    }
    
    public void rotateShape() {
        selectShape();
        Rotate control = new Rotate();
        arrayModifer.get(currentShape).addControl( control );
        app.getRootNode().detachChild(arrayModifer.get(currentShape));
        app.getRootNode().attachChild(arrayModifer.get(currentShape)); 
        try { Thread.sleep(1000); } catch (InterruptedException ex) { }
        
    }
    
}
    // if (input.equalsIgnoreCase("control box")) {prompt = "Control box which box: "; }
