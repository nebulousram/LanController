package Shapes;

import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.*;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

public class Sprite {
    private Quad quad;
    private Material mat;
    private Geometry geom;
    
    Sprite(AssetManager assetManager, int num) {
        
        /** This material turns the box into a stained glass window. 
         * The texture has an alpha channel and is partially transparent. */
        Quad windowMesh = new Quad(1f, 1.4f);
        
        geom = new Geometry(num + "", windowMesh);
        geom.addLight(new AmbientLight());
        
        Material windowMat = new Material(assetManager,"Common/MatDefs/Light/Lighting.j3md");
        
        windowMat.setTexture("DiffuseMap",assetManager.loadTexture("Textures/gunslinger.png"));
        windowMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        
        geom.setMaterial(windowMat);
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);
        geom.setMaterial(windowMat);
    }
    
    public Geometry createQuad() {
        return geom;
    }
    
    void move(int x,int y,int z) {
        Vector3f v = geom.getLocalTranslation();
        v = v.add( new Vector3f(x,y,z) );
        geom.setLocalTranslation(v);                 // test 
    }
    
    
}
//        mat.setTexture("AlphaMap",assetManager.loadTexture("Textures/gunslingerTex.jpg"));
//        mat.setTransparent(true);
