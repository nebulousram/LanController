/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import com.jme3.app.Application;
import com.jme3.scene.Geometry;
import java.util.ArrayList;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Michael
 */
public class Shapes {
    
    private ArrayList<Geometry> shapes;
    private int currentShape;
    Application app;
    String[] name;
    
    public Shapes(Application app) {
        this.app = app;
        shapes = new ArrayList<Geometry>();
        currentShape = 0;
    }
    
    public Geometry add(String str) {
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
    }
    
    public Geometry get(int num) {
        return shapes.get(num);
    }
    
}
