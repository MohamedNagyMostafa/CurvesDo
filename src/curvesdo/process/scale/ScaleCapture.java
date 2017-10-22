/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.scale;

import curvesdo.properties.Line;
import curvesdo.process.CurveImage;
import curvesdo.properties.Point;
import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public class ScaleCapture {
      
    private Line mLineTester = null;
    private Line mLineTaller = null;
   
    public void add(Point point){
        if(mLineTester == null){
            mLineTester = new Line();
            mLineTester.setStartPoint(point);
            mLineTester.setColor(new Color(
                    CurveImage
                    .getInstance()
                    .getImage()
                    .getRGB(point.getX(), point.getY())
                )
            );
        }else{
            if(CurveImage.getInstance().getImage().getRGB(point.getX(), point.getY())
                    != mLineTester.getColor().getRGB()){
                setTallerLine();
            }else{
                mLineTester.setEndPoint(point);
            }
        }
    }
    
    private void setTallerLine(){
        if(mLineTester != null && mLineTester.getEndPoint() != null){
            if(mLineTaller != null){
                if(mLineTaller.getLength() < mLineTester.getLength()){
                    mLineTaller.setStartPoint(mLineTester.getStartPoint());
                    mLineTaller.setEndPoint(mLineTester.getEndPoint());
                    mLineTaller.setColor(mLineTester.getColor());
                }
            }else{
                mLineTaller = mLineTester;
                mLineTaller.setStartPoint(mLineTester.getStartPoint());
                mLineTaller.setEndPoint(mLineTester.getEndPoint());
                mLineTaller.setColor(mLineTester.getColor());
            }
            mLineTester = null;
        }
    }
    
    public Line getTallerLine(){
        return mLineTaller;
    }
    
    public void detectBackground(){
        setTallerLine();
    }
}
