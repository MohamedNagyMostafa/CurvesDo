/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.ui;

import curvesdo.process.CurveImage;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author mohamednagy
 */
public class ImagePanel extends JPanel{
    private static final int IMAGE_X_POSIITION = 0;
    private static final int IMAGE_Y_POSISITON = 0;
    
    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(CurveImage.getInstance().getImage(), IMAGE_X_POSIITION, IMAGE_Y_POSISITON, this);
    }  

    @Override
    public int getWidth() {
        return CurveImage.getInstance().getImage().getWidth();
    }

    @Override
    public int getHeight() {
        return CurveImage.getInstance().getImage().getHeight();
    }
    
    
    
}
