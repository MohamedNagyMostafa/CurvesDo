/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.properties;

import curvesdo.process.scale.Line;
import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public class Scale {
    
    private Line mHorizontalLine;
    private Line mVerticalLine;

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
    
    
}
