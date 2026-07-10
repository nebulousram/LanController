package ScanController;

import com.jme3.app.SimpleApplication;
import mygame.*;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScanController implements Runnable {
    
    private Scanner in;
    private String input;
    private boolean running;
    private Thread thread;
    private String prompt;
    private int currentInt;
    private SimpleApplication app;
    private ShapeController shapeController;
    
    public ScanController(SimpleApplication app) {
        shapeController = new ShapeController(app);
        this.app = app;
        in = new Scanner(System.in);
        prompt = "What would you like to do: ";
        this.start();
    }
    
    
    public void prompt() {
        System.out.println(prompt);
        input = in.nextLine();
    }
    
    public void run() {
        while (running) {
            prompt();
            if (input.contains("create"))   app.getRootNode().attachChild( shapeController.createShape(  input.substring(7,input.length()), app.getAssetManager()) );
            if (input.contains("move")) shapeController.moveShape();
            if (input.equalsIgnoreCase("chase")) shapeController.chaseShape();
            if (input.equalsIgnoreCase("rotate")) shapeController.rotateShape();
            
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
    
}

// The splitup version
//    shapeController.createShape(  input.substring(7,input.length()), app.getAssetManager());
//    app.getRootNode().attachChild( geom );
