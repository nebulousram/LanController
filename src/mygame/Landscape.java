/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.light.*;
import com.jme3.math.*;
import com.jme3.scene.Node;

/**
 *
 * @author mike
 */
public class Landscape {
    private Node sceneNode = new Node("Scene");
<<<<<<< HEAD

    public Landscape(AssetManager assetManager) {
        assetManager.registerLocator("Resources.zip",ZipLocator.class);
        if (sceneNode != null) {
            sceneNode = (Node) assetManager.loadModel("MichaelMountain.j3o");
            System.out.println("Scene loaded: " + sceneNode);
        } else {
            System.out.println("Scene failed to load!");
            sceneNode = new Node("EmptyScene"); // fallback so later code doesn’t crash
        }
=======
    public Landscape(AssetManager assetManager) {
        assetManager.registerLocator("Resources.zip",ZipLocator.class);
        sceneNode = (Node) assetManager.loadModel("MichaelMountain.j3o");
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
        lights();
        
    }
    
<<<<<<< HEAD
    public Node getCourse() {
=======
    public Landscape getCourse() {
        return this;
    }
    
    public Node getSceneNode() {
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
        return sceneNode;
    }
    
    void lights() {
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(1.4f,-1.4f,-1.4f));
        sceneNode.addLight(sun);
    }
<<<<<<< HEAD
=======
    
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
}
