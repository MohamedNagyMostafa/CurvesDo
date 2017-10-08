/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process.backgroundColor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author mohamednagy
 */
public class ImageColorRates  extends ArrayList<Color>{
    private final ArrayList<Integer> colorExistance;
    private static final int INITIAL_VALUE = 1;
    private static final int COUNTER_VALUE = INITIAL_VALUE;
    
    public ImageColorRates(){
        colorExistance = new ArrayList<>();
    }       
    
    @Override
    public boolean add(Color color) {
        if(super.contains(color)){
            int index = super.indexOf(color);
            colorExistance.set(index, colorExistance.get(index) + COUNTER_VALUE);
        }else{
            colorExistance.add(INITIAL_VALUE);
            super.add(color);
        }
        return true;
    }
    
    public Iterator<Color> getImageColors(){
        return super.iterator();
    }
    public Color getBackgroundColor(){
        int index = 0;
        
        for(int newIndex = 0; newIndex < colorExistance.size(); newIndex++){
            if(colorExistance.get(index) < colorExistance.get(newIndex))
                index = newIndex;
        }
        return super.get(index);
    }
        
    
}
