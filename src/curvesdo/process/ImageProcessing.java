/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process;

import curvesdo.process.backgroundColor.ImageColorWatcher;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import curvesdo.process.scale.ScaleDetector;
import curvesdo.properties.Scale;
import java.awt.Color;

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
        new ImageColorWatcher() {
            @Override
            public void onFinished(Color backGroundColor) {
                new ScaleDetector(backGroundColor) {
                    @Override
                    public void onFinished(Scale scale) {
                        Util.println("dy:" + scale.getVerticalLine().getLength() + " dx " + scale.getHorizontalLine().getLength());
                    }
                };
              
            }
        };
    }
    
    
}
