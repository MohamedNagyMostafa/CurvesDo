/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process;

import Exceptions.ColorNotFound;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import curvesdo.process.backgroundColor.ImageColorWatcher;
import curvesdo.process.curve_route.CurveRouteDetector;
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
        final ImageDetails imageDetails = new ImageDetails();
        
        new ImageColorWatcher() {
            @Override
            public void onFinished(List<Color> imageColorsList, Color backgroundColor) {
                imageDetails.addColors(imageColorsList);
                imageDetails.setBackgroundColor(backgroundColor);
                
                try {
                    new ScaleDetector(imageDetails.getBackgroundColor()) {
                        @Override
                        public void onFinished(Scale scale) {
                            try {
                                scale.refresh();
                                imageDetails.setScale(scale);
                                
                                CurveRouteDetector curveRouteDetector = 
                                        new CurveRouteDetector(
                                        imageDetails.getCurves(),
                                        imageDetails.getScale().getVerticalLine().getStartPoint(),
                                        imageDetails.getScale().getVerticalLine().getEndPoint(),
                                        imageDetails.getScale().getHorizontalLine().getStartPoint(),
                                        imageDetails.getScale().getHorizontalLine().getEndPoint());
                                
                                int workers = 1;
                                
                                ScheduleGThread scheduleGThread = new ScheduleGThread(
                                        workers,
                                        curveRouteDetector.getGThreads())
                                {
                                    @Override
                                    public void onScheduleFinished() {
                                        imageDetails.setCurves(curveRouteDetector.getCurves());
                                        Util.println("done " + imageDetails.getCurves().get(0).getCurveRoutePoints().size());
                                       
                                    }
                                };
                                
                                scheduleGThread.start();
                            } catch (ColorNotFound ex) {
                                Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
                            }  
                        }
                    };
                } catch (ColorNotFound ex) {
                    Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        };
    }
    
    
}
