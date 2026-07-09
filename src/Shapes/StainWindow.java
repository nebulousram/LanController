
package Shapes;

import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.*;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.util.TangentBinormalGenerator;

public class StainWindow {
    
    Geometry geom;
    
    StainWindow(AssetManager assetManager, int num) {
        
        /** This material turns the box into a stained glass window. 
         * The texture has an alpha channel and is partially transparent. */
        Box windowMesh = new Box(new Vector3f(0f, 0f, 0f), 1f, 1.4f, 0.01f);
        geom = new Geometry(num + "", windowMesh);
        geom.addLight(new AmbientLight());
        Material windowMat = new Material(assetManager,"Common/MatDefs/Light/Lighting.j3md");
        windowMat.setTexture("DiffuseMap",assetManager.loadTexture("Textures/mucha-window.png"));
        windowMat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        geom.setMaterial(windowMat);
        geom.setQueueBucket(Bucket.Transparent);
        geom.setMaterial(windowMat);
    }
    
    public Geometry createWindow() {
        return geom;
    }
    
    void move(int x,int y,int z) {
        Vector3f v = geom.getLocalTranslation();
        v = v.add( new Vector3f(x,y,z) );
        geom.setLocalTranslation(v);                 // test 
    }
    
    
    
}
