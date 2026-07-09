package ScanController;

import mygame.Landscape;
import Shapes.Shapes;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.*;
import com.jme3.scene.shape.*;
import com.jme3.bullet.*;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.*;
import com.jme3.input.ChaseCamera;
import com.jme3.input.JoyInput;
import com.jme3.input.Joystick;
import com.jme3.input.JoystickButton;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.JoyAxisTrigger;
import com.jme3.input.controls.JoyButtonTrigger;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.*;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.*;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.control.Control;
import com.jme3.scene.Spatial;
import java.io.File;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import mygame.Landscape;
import mygame.Landscape;
import com.jme3.scene.debug.WireFrustum;

public class LocalController extends AbstractControl implements ActionListener {
    private boolean rotateLeft = false;
    private boolean rotateRight = false;
    private boolean forward = false;
    private boolean backward = false;
    private BetterCharacterControl playerControl;
    private Node player;
    private Vector3f walkDirection = new Vector3f(0,0,0);
    private Vector3f viewDirection = new Vector3f(0,0,1);
    private float speed = 50;
    private Node mappy;
    boolean running;
    Landscape land;
    float tpf;
    SimpleApplication app;
    Geometry geom;
    Joystick[] joysticks;
    Joystick joy;
    
    LocalController(SimpleApplication app, Landscape land, int index) { // Shapes contains a Array list of box geometries
        this.app = app;
        this.land = land;
        joysticks = app.getInputManager().getJoysticks();
        app.getInputManager().setAxisDeadZone( (float) 0.2 );
    }
    
    public void controlGeom(Geometry inputGeom) {
        geom = inputGeom;
//        Node mappy = land.getSceneNode();
        BulletAppState bulletAppState = new BulletAppState();
        app.getStateManager().attach(bulletAppState);
        RigidBodyControl scenePhy = new RigidBodyControl(0f);
        
        // </editor-fold> // This following once working line of code refused compile because the geometry
        land.getSceneNode().addControl( scenePhy);
        bulletAppState.getPhysicsSpace().add(land.getSceneNode());
        walkDirection.set(0, 0, 0);
        app.getRootNode().attachChild(land.getSceneNode());
        
        
//        player = (Node) this.spatial;
//        player.setLocalTranslation( this.spatial.getLocalTranslation() );
        bulletAppState.getPhysicsSpace().setGravity( new Vector3f(0,-9.81f,0) );
        walkDirection.set(0, 0, 0);
        playerControl = new BetterCharacterControl(1.5f , 4, 0.5f);
        playerControl.setGravity( new Vector3f(0,-10,0) );
        playerControl.setJumpForce( new Vector3f(0,10,0) );
        geom.addControl(playerControl);
        bulletAppState.getPhysicsSpace().add(geom);
        
        //<editor-fold defaultstate="collapsed" desc="KeyboardMapping();">
        /*\
        app.getInputManager().addMapping("Forward", new KeyTrigger(KeyInput.KEY_W) );
        app.getInputManager().addMapping("Back", new KeyTrigger(KeyInput.KEY_S) );
        app.getInputManager().addMapping("Rotate Left", new KeyTrigger(KeyInput.KEY_A) );
        app.getInputManager().addMapping("Rotate Right", new KeyTrigger(KeyInput.KEY_D) );
        app.getInputManager().addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE) );
        */
        // </editor-fold>
        
        app.getInputManager().addMapping("Forward", new JoyAxisTrigger(0,0,true ) );
        app.getInputManager().addMapping("Back", new JoyAxisTrigger(0,0,false ) );
        app.getInputManager().addMapping("Rotate Left", new JoyAxisTrigger(0,1,false ) );
        app.getInputManager().addMapping("Rotate Right", new JoyAxisTrigger(0,1,true ) );
        app.getInputManager().addMapping("Jump", new JoyButtonTrigger(0,0) );
        app.getInputManager().addListener(this, "Rotate Left", "Rotate Right" );
        app.getInputManager().addListener(this, "Forward", "Back", "Jump" );
        land.getSceneNode().attachChild(geom);
        
        // Third person camera
        app.getFlyByCamera().setEnabled(false);
        ChaseCamera chaseCam = new ChaseCamera( app.getCamera(), geom, app.getInputManager() );
        chaseCam.setDefaultDistance(100);
        chaseCam.setMinDistance((float) 100);
        chaseCam.setDragToRotate(true);
    }
    
    public void run() {
            
    }
    
    
    public void onAction(String binding, boolean isPressed, float tpf) {
        if (binding.equals("Rotate Left")) {
            rotateLeft = isPressed;
        } else if (binding.equals("Rotate Right")) {
            rotateRight = isPressed;
        } else 
        if (binding.equals("Forward")) {
            forward = isPressed;
        } else if (binding.equals("Back")) {
            backward = isPressed;
        } else
        if (binding.equals("Jump")) {
            playerControl.jump();
        }
    }
    
    public void Stop() {
        
    }
    
    @Override
    protected void controlUpdate(float tpf) {
         // Get current forward and left vectors of the player: 
        Vector3f modelForwardDir = geom.getWorldRotation().mult(Vector3f.UNIT_Z);
        Vector3f modelLeftDir    = geom.getWorldRotation().mult(Vector3f.UNIT_X);
        // Depending on which nav keys are pressed, determine the change in direction

        walkDirection.set(0, 0, 0);
        if (forward) {
            walkDirection.addLocal(modelForwardDir.mult(speed));
        } else if (backward) {
            walkDirection.addLocal(modelForwardDir.mult(speed).negate());
        }
        playerControl.setWalkDirection(walkDirection);
        // Depending on which nav keys are pressed, determine the change in rotation
        if (rotateLeft) {
            Quaternion rotateL = new Quaternion().fromAngleAxis(FastMath.PI * tpf, Vector3f.UNIT_Y);
            rotateL.multLocal(viewDirection);
        } else if (rotateRight) {
            Quaternion rotateR = new Quaternion().fromAngleAxis(-FastMath.PI * tpf, Vector3f.UNIT_Y);
            rotateR.multLocal(viewDirection);
        }
        playerControl.setViewDirection(viewDirection);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
