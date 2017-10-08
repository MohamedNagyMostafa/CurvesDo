/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.scale;

import curvesdo.properties.Scale;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import curvesdo.process.CurveImage;
import curvesdo.properties.Point;
import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public abstract class ScaleDetector {
    
    public ScaleDetector(Color backgroundColor){
        init(backgroundColor);
    }
    
    private void init(Color backgroundColor){
        final Scale curveScale = new Scale();
        
        Thread horizontalDetect = new Thread(()->{
            CurveImage curveImage = CurveImage.getInstance();
            ScaleCapture scaleCapture = new ScaleCapture();
            
            int ImageHeight = curveImage.getImage().getHeight();
            int ImageWidth = curveImage.getImage().getWidth();
            
            for(int yPixelPosition = 0; yPixelPosition < ImageHeight; yPixelPosition++){
                for(int xPixelPosition = 0; xPixelPosition < ImageWidth; xPixelPosition++){
                    if(curveImage.getImage().getRGB(xPixelPosition, yPixelPosition) == backgroundColor.getRGB()){
                        scaleCapture.detectBackground();
                    }else{
                        scaleCapture.add(new Point(xPixelPosition, yPixelPosition));

                    }
                }
            }
            curveScale.setHorizontalLine(scaleCapture.getTallerLine());

        });
        Thread verticalDetect =new Thread(()->{
            CurveImage curveImage = CurveImage.getInstance();
            ScaleCapture scaleCapture = new ScaleCapture();
            
            int ImageHeight = curveImage.getImage().getHeight();
            int ImageWidth = curveImage.getImage().getWidth();
            
            for(int xPixelPosition = 0; xPixelPosition < ImageWidth; xPixelPosition++){
                for(int yPixelPosition = 0; yPixelPosition < ImageHeight; yPixelPosition++){
                    if(curveImage.getImage().getRGB(xPixelPosition, yPixelPosition) == backgroundColor.getRGB()){
                        scaleCapture.detectBackground();
                    }else{
                        scaleCapture.add(new Point(xPixelPosition, yPixelPosition));
                    }
                }
            }
            curveScale.setVerticalLine(scaleCapture.getTallerLine());
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
