package mygame;

import ScanController.ScanController;
import Shapes.Shapes;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.*;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

// ScanController Project
public class Main extends SimpleApplication {
    
    private static ScanController scan;
    private int controlCount = 0;
    private static Main app = new Main();
    
    public static void main(String[] args) {
        app.start();
        app.initialize();
        app.settings.setUseJoysticks(true);
        scan = new ScanController(app);
    }
    
    
    
    @Override
    public void simpleInitApp() {
        settings.setFrameRate(60);
        flyCam.setMoveSpeed(450);
        inputManager.setCursorVisible(true); // make mouse cursor visible
        
        
    }
    
    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    
}
