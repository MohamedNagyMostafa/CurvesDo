/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.properties;

import curvesdo.properties.Point;
import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public class Line {
    private Point mStartPoint;
    private Point mEndPoint;
    private Color mColor;

    public Color getColor() {
        return mColor;
    }

    public Point getEndPoint() {
        return mEndPoint;
    }

    public Point getStartPoint() {
        return mStartPoint;
    }

    public void setColor(Color mColor) {
        this.mColor = mColor;
    }

    public void setEndPoint(Point mEndPoint) {
        this.mEndPoint = mEndPoint;
    }

    public void setStartPoint(Point mStartPoint) {
        this.mStartPoint = mStartPoint;
    }
    
    public double getLength(){
        return Math.sqrt(Math.pow((Math.abs(mEndPoint.getX() - mStartPoint.getX())), 2) + 
                Math.pow((Math.abs(mEndPoint.getY() - mStartPoint.getY())), 2));
    }
    
}
