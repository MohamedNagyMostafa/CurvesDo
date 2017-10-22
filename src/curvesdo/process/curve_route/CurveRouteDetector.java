/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.curve_route;

import curvesdo.process.CurveImage;
import curvesdo.properties.Curve;
import curvesdo.properties.Point;
import java.awt.Color;
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
        for(int gthreadIndex = 0; gthreadIndex < mCurves.size(); gthreadIndex++){
            final int curveIndex = gthreadIndex;
            
            mGThreads[gthreadIndex] = new GThread<List<Point>>(){
                @Override
                public List<Point> onProgress() {
                    Point point = null;
                    List<Point> points = new ArrayList<>();
                    
                    while((point = nextPoint(point, mCurves.get(curveIndex).getCurveColor())) != null){
                        points.add(point);
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
            for(int y_pixel = point.getY() +1; y_pixel >= point.getY() -1; y_pixel++){
                if(x_pixel != point.getX() && y_pixel != point.getY()){
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
            for(int y_pixel = mStartVerticalPoint.getY(); y_pixel <= mEndVerticalPoint.getY(); y_pixel++){
                if(bufferedImage.getRGB(x_pixel, y_pixel) == color.getRGB()){
                    startPoint = new Point(x_pixel, y_pixel);
                    break;
                }
            }
        }
        
        return startPoint;
    }
    
    private List<Curve> getCurves(){
        return mCurves;
    }
    
    private GThread[] getGThreads(){
        return mGThreads;
    }
}
