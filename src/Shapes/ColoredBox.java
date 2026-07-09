package Shapes;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import com.jme3.math.*;

public class ColoredBox {
    private Box box;
    private Material mat;
    private Geometry geom;
    
    ColoredBox(AssetManager assetManager, int num) {
        box = new Box(3, 3, 3); // defualt location
        geom = new Geometry("" + num, box);
        mat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md"); 
        mat.setColor("Color", ColorRGBA.randomColor());
        geom.setMaterial(mat);
    }
    
    public Geometry createColored() {
        return geom;
    }
    
}

//        mat.setColor("Color", ColorRGBA.Blue);
//        mat.setColor("Color", ColorRGBA.Red);

