/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.properties;

import Exceptions.ColorNotFound;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamednagy
 */
public class ImageDetails {
    private Color mBackgroundColor;
    private Scale mScale;
    
    private List<Color> mImageColors;
    private List<Curve> mCurves;
    
    public void addColors(List<Color> colors){
        mImageColors = colors;
    }
    
    public void setBackgroundColor(Color backgroundColor){
        remove(backgroundColor.getRGB());
        mBackgroundColor = backgroundColor;
    }
    
    public void setScale(Scale scale){
        remove(scale.getScaleColorRGB());
        mScale = scale;
    }

    public Color getBackgroundColor() throws ColorNotFound {
        if(mBackgroundColor != null){
            return mBackgroundColor;
        }else{
            throw new ColorNotFound("No color for background");
        }
    }

    public Scale getScale() throws ColorNotFound {
        if(mScale != null){
            return mScale;
        }else{
            throw new ColorNotFound("No scale is found");
        }
    }
    
    public void remove(int colorRGB){
        for(Color color : mImageColors){
            if(color.getRGB() == colorRGB){
                mImageColors.remove(mImageColors.indexOf(color));
                break;
            }
        }
    }
    
    public List<Curve> getCurves(){
        if(mCurves == null)
            setCurves();
        return mCurves;
    }
    
    private void setCurves(){
        mCurves = new ArrayList<>();
        mImageColors.forEach((color) -> {
            mCurves.add(new Curve(color));
        });
    }
    
    public void setCurves(List<Curve> curves){
        mCurves = curves;
    }
}
