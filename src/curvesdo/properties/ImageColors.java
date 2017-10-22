/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.properties;

import Exceptions.ColorNotFound;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamednagy
 */
public class ImageColors {
    private Color mBackgroundColor;
    private Color mScaleColor;
    
    private List<Color> mCurvesColors;
    
    public void addColors(List<Color> colors){
        mCurvesColors = colors;
    }
    
    public void setBackgroundColor(Color backgroundColor){
        remove(backgroundColor.getRGB());
        mBackgroundColor = backgroundColor;
    }
    
    public void setScaleColor(int scaleColorRGB){
        remove(scaleColorRGB);
        mScaleColor = new Color(scaleColorRGB);
    }

    public Color getBackgroundColor() throws ColorNotFound {
        if(mBackgroundColor != null){
            return mBackgroundColor;
        }else{
            throw new ColorNotFound("No color for background");
        }
    }

    public Color getScaleColor() throws ColorNotFound {
        if(mScaleColor != null){
            return mScaleColor;
        }else{
            throw new ColorNotFound("No color for scale");
        }
    }
    
    public void remove(int colorRGB){
        for(Color color : mCurvesColors){
            if(color.getRGB() == colorRGB){
                mCurvesColors.remove(mCurvesColors.indexOf(color));
                break;
            }
        }
    }
    
    public Curve[] getCurves(){
        Curve[] curves = new Curve[mCurvesColors.size()];
        
        for(int curveColorIndex = 0; curveColorIndex < mCurvesColors.size(); curveColorIndex++){
            curves[curveColorIndex].setCurveColor(mCurvesColors.get(curveColorIndex));
        }
        
        return curves;
    }
    
}
