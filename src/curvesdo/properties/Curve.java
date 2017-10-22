/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.properties;

import java.awt.Color;
import java.util.List;

/**
 *
 * @author mohamednagy
 */
public class Curve {
    private Color mCurveColor;
    
    private List<Point> mCurveMaxPointList;
    private List<Point> mCurveMinPointList;
    private List<Point> mCurveRoutePoints;
    
    private Point mCurveMaxPoint;
    private Point mCurveMinPoint;
    
    public Curve(Color color){
        mCurveColor = color;
    }

    public void setCurveColor(Color curveColor) {
        this.mCurveColor = curveColor;
    }

    public <T>void setCurveMaxPoint(T curveMaxPoint) {
        if(curveMaxPoint instanceof List)
            this.mCurveMaxPointList = (List<Point>)curveMaxPoint;
        else
            this.mCurveMaxPoint = (Point) curveMaxPoint;
    }

    public <T>void setCurveMinPoint(T curveMinPoint) {
        if(curveMinPoint instanceof List)
            this.mCurveMinPointList = (List<Point>)curveMinPoint;
        else
            this.mCurveMinPoint = (Point) curveMinPoint;
    }

    public Color getCurveColor() {
        return mCurveColor;
    }

    public <T> T getCurveMaxPoint() {
        if(mCurveMaxPoint != null)
            return (T)mCurveMaxPoint;
        else
            return (T)mCurveMaxPointList;
    }

    public <T> T getCurveMinPoint() {
        if(mCurveMinPoint != null)
            return (T)mCurveMinPoint;
        else
            return (T)mCurveMinPoint;
    }
    
    public void setCurveRoutePoints(List<Point> points){
        mCurveRoutePoints = points;
    }
    
    public List<Point> getCurveRoutePoints(){
        return mCurveRoutePoints;
    }
}
