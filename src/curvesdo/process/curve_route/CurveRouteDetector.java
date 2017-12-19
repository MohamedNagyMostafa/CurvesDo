/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.curve_route;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import curvesdo.process.CurveImage;
import curvesdo.properties.Curve;
import curvesdo.properties.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import methods.GThread;


/**
 *
 * @author mohamednagy
 */
public class CurveRouteDetector {
    
    private final Point mStartVerticalPoint;
    private final Point mEndVerticalPoint;
    private final Point mStartHorizontalPoint;
    private final Point mEndHorizontalPoint;
    
    private final List<Curve> mCurves;
    private final GThread[] mGThreads;
    
    public CurveRouteDetector(List<Curve> curves, Point startVerticalPoint,
            Point endVerticalPoint, Point startHorizontalPoint, Point endHorizontalPoint){
        mCurves = curves;
        mStartHorizontalPoint= startHorizontalPoint;
        mStartVerticalPoint = startVerticalPoint;
        mEndVerticalPoint = endVerticalPoint;
        mEndHorizontalPoint = endHorizontalPoint;
        mGThreads = new GThread[mCurves.size()];
        
        init();
    }
    
    private void init(){
        for(int gthreadIndex = 0; gthreadIndex < 1; gthreadIndex++){
            final int curveIndex = gthreadIndex;
            
            mGThreads[gthreadIndex] = new GThread<List<Point>>(){
                @Override
                public List<Point> onProgress() {
                    Point curveStartPoint = findStartPoint(mCurves.get(curveIndex).getCurveColor());
                    
                    Color curveColor = mCurves.get(curveIndex).getCurveColor();
                    Graphics graphics = CurveImage.getInstance().getImage().getGraphics();
                        graphics.drawLine(curveStartPoint.getX(), curveStartPoint.getY(), curveStartPoint.getX(), curveStartPoint.getY());
                    graphics.setColor(Color.WHITE);
                    List<Point> points = new ArrayList<>();
                    
                    while(curveStartPoint != null){
                        points.add(curveStartPoint);
                        curveStartPoint = nextPoint(curveStartPoint, curveColor);
                    }
                    
                    return points;
                }

                @Override
                public void onFinished(List<Point> points) {
                    mCurves.get(curveIndex).setCurveRoutePoints(points);
                }
                
            };
        }
    }
    
    // Get next point of curve.
    private Point nextPoint(Point point, Color color){
        Point nextPoint = null;
        
        BufferedImage bufferedImage = CurveImage.getInstance().getImage();
        mover:
        for(int x_pixel = point.getX(); x_pixel <= point.getX() +1 ; x_pixel++){
            for(int y_pixel = point.getY() -1; y_pixel <= point.getY() +1; y_pixel++){
                if(!(x_pixel == point.getX() && y_pixel == point.getY())){
                    if(bufferedImage.getRGB(x_pixel, y_pixel) == color.getRGB()){
                        nextPoint = new Point(x_pixel, y_pixel);
                        break mover;
                    }
                }    
            }
        }
       
        return nextPoint;
    }
    // Get start point of curve from left.
    private Point findStartPoint(Color color){
        Point startPoint = null;
        BufferedImage bufferedImage = CurveImage.getInstance().getImage();
        
        for(int x_pixel = mStartHorizontalPoint.getX(); x_pixel <= mEndHorizontalPoint.getX(); x_pixel++){
            for(int y_pixel = mStartVerticalPoint.getY(); y_pixel >= mEndVerticalPoint.getY(); y_pixel--){
                if(bufferedImage.getRGB(x_pixel, y_pixel) == color.getRGB()){
                    startPoint = new Point(x_pixel, y_pixel);
                    break;
                }else{
                        Graphics graphics = CurveImage.getInstance().getImage().getGraphics();
                        graphics.drawLine(x_pixel, y_pixel, x_pixel, y_pixel);
                    
                    }
            }
        }
        if(startPoint != null)
        Util.println("not error " + startPoint.getX() + " Ys: " + mStartHorizontalPoint.getX() + " Ye: " + mEndHorizontalPoint.getX());
        return startPoint;
    }
    
    public List<Curve> getCurves(){
        return mCurves;
    }
    
    public GThread[] getGThreads(){
        return mGThreads;
    }
}
