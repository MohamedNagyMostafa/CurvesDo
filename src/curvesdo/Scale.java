/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo;

import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public class Scale {
    
    private Point mStartPoint = null;
    private Point mEndPoint = null;
    private Color mColor = null;
    
    public Scale(){}
    public Scale(Point startPoint, Point endPoint, Color color){
        mStartPoint = startPoint;
        mEndPoint = endPoint;
        mColor = color;
    }

    public void setEndPoint(Point mEndPoint) {
        this.mEndPoint = mEndPoint;
    }

    public void setStartPoint(Point mStartPoint) {
        this.mStartPoint = mStartPoint;
    }

    public void setmColor(Color mColor) {
        this.mColor = mColor;
    }
    

    public Point getEndPoint() {
        return mEndPoint;
    }

    public Point getStartPoint() {
        return mStartPoint;
    }

    public Color getmColor() {
        return mColor;
    }
    
    
    
}
