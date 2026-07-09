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
    public Landscape(AssetManager assetManager) {
        assetManager.registerLocator("Resources.zip",ZipLocator.class);
        sceneNode = (Node) assetManager.loadModel("MichaelMountain.j3o");
        lights();
        
    }
    
    public Landscape getCourse() {
        return this;
    }
    
    public Node getSceneNode() {
        return sceneNode;
    }
    
    void lights() {
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(1.4f,-1.4f,-1.4f));
        sceneNode.addLight(sun);
    }
    
}
