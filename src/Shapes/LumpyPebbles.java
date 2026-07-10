package Shapes;

import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.TangentBinormalGenerator;

public class LumpyPebbles {
    Geometry rockGeo;
    
    LumpyPebbles(AssetManager assetManager, int num) {
        Sphere rockMesh = new Sphere(16, 16, 1);
        // better texture quality on spheres
        rockMesh.setTextureMode(Sphere.TextureMode.Projected);
        // Generate normal vector data for normal map!
        TangentBinormalGenerator.generate(rockMesh);
        rockGeo = new Geometry(num + "", rockMesh);
        rockGeo.addLight(new AmbientLight());
        
        Material rockMat = new Material(assetManager,"Common/MatDefs/Light/Lighting.j3md");
        rockMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/Pebbles/Pebbles_diffuse.png"));
        rockMat.setTexture("NormalMap", assetManager.loadTexture("Textures/Pebbles/Pebbles_normal.png"));
        
        rockMat.setFloat("Shininess", 56);    // [0,128]
        rockMat.setBoolean("UseMaterialColors", true);
        rockMat.setColor("Ambient", ColorRGBA.Gray);
        rockMat.setColor("Specular", ColorRGBA.White);
        rockMat.setColor("Diffuse", ColorRGBA.Gray);
        
        rockGeo.setMaterial(rockMat);
        /** A wall with a rough bricky surface */
    }
    
    public Geometry createRock() {
        return rockGeo;
    }
    
    void move(int x,int y,int z) {
        Vector3f v = rockGeo.getLocalTranslation();
        v = v.add( new Vector3f(x,y,z) );
        rockGeo.setLocalTranslation(v);                 // test 
    }
}
