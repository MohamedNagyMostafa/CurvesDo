/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
    
}
