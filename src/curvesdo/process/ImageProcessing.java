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
import curvesdo.properties.ImageColors;
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
        final ImageColors imageColors = new ImageColors();
        
        new ImageColorWatcher() {
            @Override
            public void onFinished(List<Color> imageColorsList, Color backgroundColor) {
                imageColors.addColors(imageColorsList);
                imageColors.setBackgroundColor(backgroundColor);
                
                try {
                    new ScaleDetector(imageColors.getBackgroundColor()) {
                        @Override
                        public void onFinished(Scale scale) {
                            imageColors.setScaleColor(scale.getScaleColorRGB());
                            Graphics g= CurveImage.getInstance().getImage().getGraphics();
                            g.setColor(Color.GREEN);
                            g.drawLine(scale.getVerticalLine().getStartPoint().getX(), scale.getVerticalLine().getStartPoint().getY(),
                                    scale.getVerticalLine().getEndPoint().getX(), scale.getVerticalLine().getEndPoint().getY());
                            g.drawLine(scale.getHorizontalLine().getStartPoint().getX(), scale.getHorizontalLine().getStartPoint().getY(),
                                    scale.getHorizontalLine().getEndPoint().getX(), scale.getHorizontalLine().getEndPoint().getY());
                            Util.println("sVx : (" + scale.getVerticalLine().getStartPoint().getX() +"," + scale.getVerticalLine().getStartPoint().getY() +")");
                            Util.println("eVx : (" + scale.getVerticalLine().getEndPoint().getX() +"," + scale.getVerticalLine().getEndPoint().getY() +")");
                            Util.println("sHx : (" + scale.getHorizontalLine().getStartPoint().getX() +"," + scale.getHorizontalLine().getStartPoint().getY() +")");
                            Util.println("eHx : (" + scale.getHorizontalLine().getEndPoint().getX() +"," + scale.getHorizontalLine().getEndPoint().getY() +")");
                            Curve[] curves = imageColors.getCurves();
                            
                            /**
                            final MaxMinPointDetector maxMinPointDetector = new MaxMinPointDetector(
                                    curves,
                                    scale.getHorizontalLine().getStartPoint().getX(),
                                    scale.getHorizontalLine().getEndPoint().getX(),
                                    scale.getVerticalLine().getStartPoint().getY(),
                                    scale.getVerticalLine().getEndPoint().getY()
                            );
                            ScheduleGThread scheduleGThread = new ScheduleGThread(maxMinPointDetector.getGhtreadWorkers(),
                                    maxMinPointDetector.getGthreads()) {
                                @Override
                                public void onScheduleFinished() {
                                    Curve[] curves = maxMinPointDetector.getCurves();
                                      Util.println("done");
                                }
                            };
                            scheduleGThread.start(); **/
                        }
                    };
                } catch (ColorNotFound ex) {
                    Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        };
    }
    
    
}
