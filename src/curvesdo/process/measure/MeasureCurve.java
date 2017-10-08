/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.measure;

import java.awt.Color;

/**
 *
 * @author mohamednagy
 */
public class MeasureCurve {
    public static final int MAXIMUM_POINT = 0;
    public static final int MINMUM_POINT = 1;
    private int mMeasureType = -1;
    private int mPointDetectedPosition = -1;
    private Color mCurveColor = null;
    
    public MeasureCurve(int measureCurveType){
        mMeasureType = measureCurveType;
    }
    
    public MeasureCurve(int measureCurveType, Color curveColor){
        mMeasureType = measureCurveType;
        mCurveColor = curveColor;
    }
    
    public MeasureCurve(int measureCurveType, Color curveColor, int pointPositionDetected){
        mPointDetectedPosition = pointPositionDetected;
        mMeasureType = measureCurveType;
        mCurveColor = curveColor;
    }

    public Color getmCurveColor() {
        return mCurveColor;
    }

    public int getmPointDetectedPosition() {
        return mPointDetectedPosition;
    }

    @Override
    public String toString() {
        switch(mMeasureType){
            case MAXIMUM_POINT:
                return "Maximum point for the curve";
            case MINMUM_POINT:
                return "Minimum point for the curve";
            default:
                return "There is no point detected";
        }
    }
}
