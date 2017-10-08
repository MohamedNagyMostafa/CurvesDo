/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.backgroundColor;

import curvesdo.process.CurveImage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamednagy
 */
public abstract class ImageColorWatcher {
    
    public ImageColorWatcher(){
        initWatcher();
    }
    
    private void initWatcher(){
        new Thread(()-> {
            
            CurveImage curveImage = CurveImage.getInstance();
            ImageColorRates imageColor = new ImageColorRates();
            
            for(int xPixelPosition = 0; xPixelPosition < curveImage.getImage().getWidth() ; xPixelPosition++){
                for(int yPixelPosition = 0 ; yPixelPosition < curveImage.getImage().getHeight() ; yPixelPosition++){
                    imageColor.add(new Color(curveImage.getImage().getRGB(xPixelPosition, yPixelPosition)));
                }
            }
            
            onFinished(imageColor, imageColor.getBackgroundColor());
        }).start();
    }
    
    public abstract void onFinished(List<Color> colorsInImage, Color backgroundColor);
}
