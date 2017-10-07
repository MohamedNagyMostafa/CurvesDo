/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public abstract class ScaleDetector {
    private Scale curveScale;
    
    public ScaleDetector(Color backgroundColor){
        init(backgroundColor);
    }
    
    private void init(Color backgroundColor){
        curveScale = new Scale();
        
        Thread horizontalDetect = new Thread(()->{
        
        });
        Thread verticalDetect =new Thread(()->{
        
        });
        
        horizontalDetect.start();
        verticalDetect.start();
        
        try{
            horizontalDetect.join();
            verticalDetect.join();
        }catch(InterruptedException e){
            printStackTrace();
        }
        
        onFinished(curveScale);
    }
    
    public abstract void onFinished(Scale scale);
    
}
