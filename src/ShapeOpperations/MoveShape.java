/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShapeOpperations;

import Shapes.Shapes;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class MoveShape {
    Scanner in;
    SimpleApplication app;
    
    public MoveShape(SimpleApplication app) {
        this.app = app;
        in = new Scanner(System.in);
    }
    
    public void move(Shapes shapes, int currentShape) {
        System.out.print("Choose the seperate cooridinates with a 'x y z' location: ");
        String input = in.nextLine();
        Scanner inputScan = new Scanner(input);
        int x = inputScan.nextInt();
        int y = inputScan.nextInt();
        int z = inputScan.nextInt();
        System.out.println("Now moving shape " + currentShape + " to < " + x + ", " + y + ", " + z + "> ");
        shapes.get(currentShape).move(new Vector3f(x, y, z) );
    }
    
}
