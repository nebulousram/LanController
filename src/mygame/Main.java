package mygame;

import ScanController.ScanController;
import com.jme3.app.SimpleApplication;
import com.jme3.input.FlyByCamera;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.*;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

// ScanController Project
public class Main extends SimpleApplication {
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
        ScanController scan = new ScanController(app);
    }
    
    @Override
    public void simpleInitApp() {
        
        settings.setFrameRate(60);
        flyCam.setMoveSpeed(50);
        System.out.println("We still have a problem!");
        inputManager.setCursorVisible(true); // make mouse cursor visible
        rootNode.attachChild( new Landscape(assetManager).getCourse() );
        
        
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
