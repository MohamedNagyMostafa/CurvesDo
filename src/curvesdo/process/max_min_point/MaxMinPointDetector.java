/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.max_min_point;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import curvesdo.process.CurveImage;
import curvesdo.properties.Curve;
import curvesdo.properties.Point;
import curvesdo.ui.ImagePanel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import methods.GThread;

/**
 *
 * @author mohamednagy
 */
public class MaxMinPointDetector {
    
    private final Curve[] mCurves;
    private final int mStartVerticalPoint;
    private final int mEndVerticalPoint;
    private final int mStartHorizontalPoint;
    private final int mEndHorizontalPoint;
    private GThread[] mGThreads;
    
    public MaxMinPointDetector(Curve[] curves, int startHorizontalPoint, int endHorizontalPoint, 
            int startVerticalPoint, int endVerticalPoint){
        mCurves = curves;
        mStartVerticalPoint = startVerticalPoint;
        mEndVerticalPoint = endVerticalPoint;
        mStartHorizontalPoint = startHorizontalPoint;
        mEndHorizontalPoint = endHorizontalPoint;
        init();
    }
    
    private <T> void init(){
        mGThreads = new GThread[mCurves.length * 2];
        
        for(int curveIndex = 0, gthreadIndex = 0; curveIndex < mCurves.length; 
                curveIndex++, gthreadIndex = gthreadIndex + 2){
            final int index = curveIndex;
            
            mGThreads[gthreadIndex] = new GThread<T>(){
                @Override
                public T onProgress() {
                    
                    T maxDetection = searchMaxPoint(mCurves[index].getCurveColor());
                    
                    return maxDetection;
                }

                @Override
                public void onFinished(T maxDetection) {
                    mCurves[index].setCurveMaxPoint(maxDetection);
                }
                
            };
            
            mGThreads[gthreadIndex + 1] = new GThread<T>(){
                @Override
                public T onProgress() {
                    
                    T minDetection = searchMinPoint(mCurves[index].getCurveColor());
                    
                    return minDetection;
                }

                @Override
                public void onFinished(T minDetection) {
                    mCurves[index].setCurveMinPoint(minDetection);
                }
                
            };
        }
    }
    
    private <T> T searchMaxPoint(Color color){
        List<Point> points = new ArrayList<>();
        BufferedImage bufferedImage = CurveImage.getInstance().getImage();
        int imageHeight = bufferedImage.getHeight();
        int imageWidth = bufferedImage.getWidth();
    
        
        for(int verticalPixels = imageHeight - 1; verticalPixels >= 0; verticalPixels--){
            for(int horizontalPixels = 0; horizontalPixels < imageWidth; horizontalPixels++){
                if(bufferedImage.getRGB(verticalPixels, horizontalPixels) == color.getRGB()){
                    points.add(new Point(verticalPixels, horizontalPixels));
                }
            }
            if(!points.isEmpty()){
              break;  
            }
        }
        
        if(points.size() > 1){
            return (T)points;
        }else{
            return (T)points.get(0);
        }
    }
    
    private <T> T searchMinPoint(Color color){
        List<Point> points = new ArrayList<>();
        BufferedImage bufferedImage = CurveImage.getInstance().getImage();
        int imageHeight = bufferedImage.getHeight();
        int imageWidth = bufferedImage.getWidth();
        
        for(int verticalPixels = 0; verticalPixels < imageHeight; verticalPixels++){
            for(int horizontalPixels = imageWidth - 1; horizontalPixels >= 0 ; horizontalPixels--){
                if(bufferedImage.getRGB(verticalPixels, horizontalPixels) == color.getRGB()){
                    points.add(new Point(verticalPixels, horizontalPixels));
                }
            }
            if(!points.isEmpty()){
              break;  
            }
        }
        
        if(points.size() > 1){
            return (T)points;
        }else{
            return (T)points.get(0);
        }
    }

    public Curve[] getCurves() {
        return mCurves;
    }
    
    public GThread[] getGthreads(){
        return mGThreads;
    }
    
    public int getGhtreadWorkers(){
        return mCurves.length;
    }
}
