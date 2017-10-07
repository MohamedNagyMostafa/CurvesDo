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
    
    
    @Override
    public void paint(Graphics graphics) {
        insertImage(graphics);
    }
    
    private void insertImage(Graphics graphics){
        try{
            BufferedImage imageIO = ImageIO.read(
                    new File("C:\\Users\\mohamednagy.mohamednagy-PC\\Desktop\\Seasons1\\2009\\autumn-avg.png"));
            graphics.drawImage(imageIO, 0, 0, this);
        }catch(IOException iOException){
            
        }
    }
    
    
    
}
