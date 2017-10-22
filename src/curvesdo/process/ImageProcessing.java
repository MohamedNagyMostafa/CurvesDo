/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process;

import Exceptions.ColorNotFound;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import curvesdo.process.backgroundColor.ImageColorWatcher;
import curvesdo.process.max_min_point.MaxMinPointDetector;
import curvesdo.process.scale.ScaleDetector;
import curvesdo.properties.Curve;
import curvesdo.properties.ImageDetails;
import curvesdo.properties.Scale;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import methods.ScheduleGThread;

/**
 *
 * @author mohamednagy
 */
public class ImageProcessing extends Thread{
    
    public ImageProcessing(){
        init();
    }

    private void init(){
        start();
    }
    
    @Override
    public void run() {
        // Detect Image Color.
        final ImageDetails imageColors = new ImageDetails();
        
        new ImageColorWatcher() {
            @Override
            public void onFinished(List<Color> imageColorsList, Color backgroundColor) {
                imageColors.addColors(imageColorsList);
                imageColors.setBackgroundColor(backgroundColor);
                
                try {
                    new ScaleDetector(imageColors.getBackgroundColor()) {
                        @Override
                        public void onFinished(Scale scale) {
                            scale.refresh();
                            imageColors.setScale(scale);
                         
                            
                            
                        }
                    };
                } catch (ColorNotFound ex) {
                    Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        };
    }
    
    
}
