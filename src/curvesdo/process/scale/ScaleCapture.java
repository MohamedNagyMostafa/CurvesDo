/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.scale;

import com.sun.istack.internal.localization.NullLocalizable;
import curvesdo.process.CurveImage;
import curvesdo.properties.Point;
import curvesdo.properties.Scale;
import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public class ScaleCapture {
      
    private Line mLineTester;
    private Line mLineTaller;
   
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
            if(new Color(CurveImage.getInstance().getImage().getRGB(point.getX(), point.getY()))
                    != mLineTester.getColor()){
                setTallerLine();
            }else{
                mLineTester.setEndPoint(point);
            }
        }
    }
    
    private void setTallerLine(){
        if(mLineTester != null){
            if(mLineTaller != null){
                if(mLineTaller.getLength() < mLineTester.getLength())
                    mLineTaller = mLineTester;
            }else{
                mLineTaller = mLineTester;
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
