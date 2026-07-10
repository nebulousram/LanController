/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import com.jme3.app.Application;
<<<<<<< HEAD
import com.jme3.scene.Geometry;
import java.util.ArrayList;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
=======
import com.jme3.light.AmbientLight;
import com.jme3.scene.Geometry;
import java.util.ArrayList;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.*;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import java.awt.Color;
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3

/**
 *
 * @author Michael
 */
public class Shapes {
    
<<<<<<< HEAD
    private ArrayList<Geometry> shapes;
=======
    public ArrayList<Geometry> shapes;
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
    private int currentShape;
    Application app;
    String[] name;
    
    public Shapes(Application app) {
        this.app = app;
        shapes = new ArrayList<Geometry>();
        currentShape = 0;
    }
    
    public Geometry add(String str) {
<<<<<<< HEAD
        if (str.contains("box") ) shapes.add( new ColoredBox(app.getAssetManager(),currentShape).createColored() );
        if (str.contains("sprite") ) shapes.add( new Sprite( app.getAssetManager(),currentShape ).createQuad()  );
        if (str.contains("window") ) shapes.add( new StainWindow( app.getAssetManager(),currentShape ).createWindow()  );
        if (str.contains("rock") ) shapes.add( new LumpyPebbles( app.getAssetManager(),currentShape ).createRock()  );
        move(FastMath.nextRandomInt(-10, -50), FastMath.nextRandomInt(10, 30), FastMath.nextRandomInt(20, 50 ) );
        currentShape++;
        return shapes.get(currentShape-1);
    }
    
    public void move(int num, int x,int y,int z) {
        Vector3f v = shapes.get( num ).getLocalTranslation();
        v = v.add( new Vector3f(x,y,z) );
        shapes.get( num).setLocalTranslation(v);                 // test 
    }
    
    private void move(int x,int y,int z) {
        Vector3f v = shapes.get( currentShape ).getLocalTranslation();
        v = v.add( new Vector3f(x,y,z) );
        shapes.get(currentShape).setLocalTranslation(v);                 // test 
=======
        if (str.contains("box") ) shapes.add( createBox() );
        if (str.contains("sprite") ) shapes.add( createSprite() );
        if (str.contains("window") ) { shapes.add( new StainWindow( app.getAssetManager(),currentShape ).createWindow()  ); currentShape++; }
        if (str.contains("rock") ) { shapes.add( new LumpyPebbles( app.getAssetManager(),currentShape ).createRock()  ); currentShape++; }
        move(FastMath.nextRandomInt(-10, -50), FastMath.nextRandomInt(10, 30), FastMath.nextRandomInt(20, 50 ) );
        return shapes.get(currentShape-1);
    }
    
    private void move(int x,int y,int z) {
        Vector3f v = shapes.get( currentShape-1 ).getLocalTranslation();
        v = v.add( new Vector3f(x,y,z) );
        shapes.get(currentShape-1).setLocalTranslation(v);                 // test 
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
    }
    
    public Geometry get(int num) {
        return shapes.get(num);
    }
    
<<<<<<< HEAD
=======
    public void setGeom(Geometry geom) {
        shapes.set(currentShape, geom);
    }
    
    //AssetManager assetManager,
    private Geometry createBox() {
        Box box = new Box(3, 3, 3); // defualt location
        ColorRGBA color = ColorRGBA.randomColor();
        Geometry geom = new Geometry("Box" + color.toString() + currentShape+1, box);
        currentShape++;
        Material mat = new Material(app.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md"); 
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        return geom;
    }
    
    private Geometry createSprite() {
        
        /** This material turns the box into a stained glass window. 
         * The texture has an alpha channel and is partially transparent. */
        Quad windowMesh = new Quad(1f, 1.4f);
        
        Geometry geom = new Geometry(currentShape + "", windowMesh);
        currentShape++;
        geom.addLight(new AmbientLight());
        
        Material windowMat = new Material(app.getAssetManager(),"Common/MatDefs/Light/Lighting.j3md");
        
        windowMat.setTexture("DiffuseMap",app.getAssetManager().loadTexture("Textures/gunslinger.png"));
        windowMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        
        geom.setMaterial(windowMat);
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);
        geom.setMaterial(windowMat);
        
        return geom;
    }
    
    
>>>>>>> 4e7b250ab95bf0c478c004c258e592e0edb483b3
}
