/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.properties;

import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public class Scale {
    private static final int MAX_SWAPPING = 4;
    private Line mHorizontalLine;
    private Line mVerticalLine;
    private int mHorizontalLineCounter;
    private int mVerticalLineCounter;

    public void setHorizontalLine(Line mHorizontalLine) {
        this.mHorizontalLine = mHorizontalLine;
    }

    public void setVerticalLine(Line mVerticalLine) {
        this.mVerticalLine = mVerticalLine;
    }

    public Line getHorizontalLine() {
        return mHorizontalLine;
    }

    public Line getVerticalLine() {
        return mVerticalLine;
    }
    
    public void refresh(){
        refreshScaleLines();
        
        mHorizontalLineCounter = (mHorizontalLine.getEndPoint().getX() - mHorizontalLine.getStartPoint().getX())/
                Math.abs(mHorizontalLine.getEndPoint().getX() - mHorizontalLine.getStartPoint().getX());
        mVerticalLineCounter = (mVerticalLine.getEndPoint().getY() - mVerticalLine.getStartPoint().getY())/
                Math.abs(mVerticalLine.getEndPoint().getY() - mVerticalLine.getStartPoint().getY());
    }
    
    private void refreshScaleLines(){
        for(int swaperCount = 0 ; swaperCount < MAX_SWAPPING ; swaperCount++){
            if(!checker()){
                if(swaperCount % 2 == 0){
                    swapLine(mVerticalLine);
                }else{
                    swapLine(mHorizontalLine);
                }
            }else{
                break;
            }
        }
    }
    
    private void swapLine(Line lineScale){
        Point startPoint = lineScale.getStartPoint();
        lineScale.setStartPoint(lineScale.getEndPoint());
        lineScale.setEndPoint(startPoint);
    }
    
    private boolean checker(){
        return (mVerticalLine.getStartPoint().getX() == mHorizontalLine.getStartPoint().getX() &&
                mVerticalLine.getStartPoint().getY() == mHorizontalLine.getStartPoint().getY());
    }
    
}
