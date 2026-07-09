package ScanController;
import Shapes.Shapes;
import nointerruptclient.NoInterruptClient;
import com.jme3.app.SimpleApplication;
import mygame.*;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScanController implements Runnable {
    
    //<editor-fold defaultstate="collapsed" desc="void declareVariables()">
    private Scanner in;
    private String input;
    private boolean running;
    private Thread thread;
    private String prompt;
    private int currentInt;
    private SimpleApplication app;
    private ShapeController shapeController;
    int controlCount =1;
    int shapeNum;
    Boolean controllerNeeded = false;
    int intInput;
    private Landscape land;
    LocalController localControl;
    NoInterruptClient console;
    // </editor-fold>
    
    public ScanController(SimpleApplication app) {
        
        //<editor-fold defaultstate="collapsed" desc="connect();">
        /*
        console = new NoInterruptClient();
        console.clients.get(console.selectedClient).sendInitMessages();
        console.clients.get(0).clientFrame.setVisible(true);
        console.selectedClient++;
        console.clients.get(console.selectedClient).sendInitMessages();
        console.selectedClient++;
        console.clients.get(console.selectedClient).sendInitMessages();
        console.selectedClient++;
        console.clients.get(console.selectedClient).sendInitMessages();
        */
        // </editor-fold>
        land = new Landscape(app.getAssetManager());
        app.getRootNode().attachChild( land.getSceneNode() );
        shapeController = new ShapeController(app);
        this.app = app;
        initialize();
        
        in = new Scanner(System.in);
        prompt = "What would you like to do: ";
        this.start();
    }
    
    void initialize() {
        app.getRootNode().attachChild( shapeController.createShape("box", app.getAssetManager()) );
        System.out.println("Created Shape");
        selectShape(1);
        controlShape();
        
    }
    
    public void prompt() {
        System.out.println(prompt);
        input = in.nextLine();
        extractInt(input);
    }
    
    public void run() {
        while (running) {
            prompt();
            if (input.contains("create"))  create();
            if (input.contains("move")) shapeController.moveShape();
            if (input.equalsIgnoreCase("chase")) shapeController.chaseShape();
            if (input.equalsIgnoreCase("rotate")) shapeController.rotateShape();
            if (input.equalsIgnoreCase("control")) controlShape();
            if (input.toLowerCase().contains("select")) { selectShape( intInput ); }
            
        }
    }
    
    public synchronized void start() {
	running = true;
        thread = new Thread(this, "CourseThread");
	thread.start();
    }
    
    public synchronized void stop() {
	try {
		thread.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    }
    
    void selectShape(int desiredShape) {
        shapeController.currentShape = (desiredShape-1);
        System.out.println("Select Shape " + desiredShape);
        System.out.println("    Shape Exits: " + shapeController.arrayModifer.get(desiredShape-1).toString() );
        
    }
    
    public int getCurrentShape() {
        return shapeController.currentShape;
    }
    
    public Shapes getArrayModifer() {
        return shapeController.arrayModifer;
    }
    
    void extractInt(String str) {
        str = str.replaceAll("[^\\d]", "");
        Scanner tmp = new Scanner(str);
        if (tmp.hasNextInt() ) { intInput = tmp.nextInt(); return; }
        intInput = 0;
    }
    
    void create() {
        app.getRootNode().attachChild( shapeController.createShape(  input.substring(7,input.length()), app.getAssetManager())  );
    }
    
    void controlShape() {
        localControl = new LocalController(app,land,shapeController.currentShape);
        Geometry temp = shapeController.arrayModifer.get(shapeController.currentShape);
        Vector3f tmpVec = shapeController.arrayModifer.get((shapeController.currentShape)).getLocalTranslation();
        shapeController.arrayModifer.shapes.remove(shapeController.currentShape);
        localControl.controlGeom(temp);
        temp.addControl(localControl);
//        shapeController.arrayModifer.get(shapeController.currentShape).addControl(localControl);
        
    }
    
}

//<editor-fold defaultstate="collapsed" desc="editorFold();">
/*

*/
// </editor-fold>

// The splitup version
//    shapeController.createShape(  input.substring(7,input.length()), app.getAssetManager());
//    app.getRootNode().attachChild( geom );
