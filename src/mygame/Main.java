package mygame;

import ScanController.ScanController;
<<<<<<< HEAD
import com.jme3.app.SimpleApplication;
import com.jme3.input.FlyByCamera;
=======
import Shapes.Shapes;
import com.jme3.app.SimpleApplication;
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.*;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

// ScanController Project
public class Main extends SimpleApplication {
    
<<<<<<< HEAD
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
=======
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
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
        
        
    }
    
    @Override
    public void simpleUpdate(float tpf) {
<<<<<<< HEAD
        //TODO: add update code
=======
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
<<<<<<< HEAD
=======
    
    
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
}
