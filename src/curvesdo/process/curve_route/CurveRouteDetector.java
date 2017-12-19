/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.curve_route;

import Exceptions.ColorNotFound;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import curvesdo.process.CurveImage;
import curvesdo.properties.Curve;
import curvesdo.properties.ImageDetails;
import curvesdo.properties.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final ImageDetails mImageDetails;
    
    public CurveRouteDetector(List<Curve> curves, Point startVerticalPoint,
            Point endVerticalPoint, Point startHorizontalPoint, Point endHorizontalPoint, ImageDetails imageDetails){
        mCurves = curves;
        mStartHorizontalPoint= startHorizontalPoint;
        mStartVerticalPoint = startVerticalPoint;
        mEndVerticalPoint = endVerticalPoint;
        mEndHorizontalPoint = endHorizontalPoint;
        mGThreads = new GThread[mCurves.size()];
        mImageDetails = imageDetails;
        init();
    }
    
    private void init(){
        for(int gthreadIndex = 0; gthreadIndex < mGThreads.length; gthreadIndex++){
            final int curveIndex = gthreadIndex;
            
            mGThreads[gthreadIndex] = new GThread<List<Point>>(){
                @Override
                public List<Point> onProgress() {
                    Util.println("start");
                    Point curveStartPoint = findStartPoint(mCurves.get(curveIndex).getCurveColor());
                    
                    Color curveColor = mCurves.get(curveIndex).getCurveColor();
                    List<Point> points = new ArrayList<>();
      
                    return detectCurve(points,curveStartPoint, curveColor);
                }

                @Override
                public void onFinished(List<Point> points) {
                    mCurves.get(curveIndex).setCurveRoutePoints(points);
                }
                
            };
        }
    }
    
    private List<Point> detectCurve(List<Point> points, Point point, Color color){
        if(point != null){
            try {
                points.add(point);
                int pointsNumber = points.size();
                points = lineWatcher(points, point, color);
                if(points.size() == pointsNumber)
                    return detectCurve(points, null, color);
                else{
                    point = nextLinePoint(color, point.getX() + 1);
                    return detectCurve(points, point, color);
                }
            } catch (ColorNotFound ex) {
                Logger.getLogger(CurveRouteDetector.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                return null;
            }
        }else{
            return points;
        }
    }
    
    private Point nextLinePoint(Color color, int xCoor){
        int y_startCoor = mStartVerticalPoint.getY();
        int y_endCoor = mEndVerticalPoint.getY();
        CurveImage curveImage = CurveImage.getInstance();
        
        for(int yCoor = y_startCoor; yCoor >= y_endCoor; yCoor--){
            int pixelColor = curveImage.getImage().getRGB(xCoor, yCoor);
            if(pixelColor == color.getRGB()){
                return new Point(xCoor, yCoor);
            }
        }
        
        return null;
    }
    /**
     * Detect Line 
     *          if the pixel have color of curve then add to list.
     *          if the pixel have color of another curve check the left and top. until find bg.
     *          if the pixel have color of background stop.
     * @param points
     * @param point
     * @param color
     * @return
     * @throws ColorNotFound 
     */
    private List<Point> lineWatcher(List<Point> points, Point point, Color color) throws ColorNotFound{
        int xCoor = point.getX();
        int yCoor = point.getY();
        List<Point> holder = new ArrayList<>();
        Color background = mImageDetails.getBackgroundColor();
        Color pixelColor;
        CurveImage curveImage = CurveImage.getInstance();
        
        do{
            pixelColor = new Color(curveImage.getImage().getRGB(xCoor, --yCoor));
            
            if(pixelColor.getRGB() == color.getRGB()){
                
                emptying(holder, points);
                points.add(new Point(xCoor, yCoor));
            }else if(pixelColor.getRGB() != background.getRGB()){
                int neighborhoodPixel = curveImage.getImage().getRGB(xCoor + 1, yCoor);
                
                if(neighborhoodPixel == color.getRGB()){
                    
                    emptying(holder, points);
                    points.add(new Point(xCoor, yCoor));
                }else{
                    holder.add(new Point(xCoor, yCoor));
                }
                
            }
            
        }while(pixelColor.getRGB() != background.getRGB() && yCoor >= mEndVerticalPoint.getY());
        
        return points;
    }
    
    private void emptying(List<Point> holder, List<Point> points){
        for(int i = 0 ; i < holder.size(); i++){
            points.add(holder.remove(i));
        }
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
