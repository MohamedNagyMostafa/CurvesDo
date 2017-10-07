/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.properties;

/**
 *
 * @author mohamednagy
 */
public class Point {
    private int mX;
    private int mY;
    
    public Point(int x, int y){
        mX = x;
        mY = y;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public void setX(int mX) {
        this.mX = mX;
    }

    public void setY(int mY) {
        this.mY = mY;
    }
    
    
    
}
