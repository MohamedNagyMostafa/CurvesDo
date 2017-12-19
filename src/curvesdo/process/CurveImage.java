/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.process;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *  Holder for image path
 * @author mohamednagy
 */
public class CurveImage {
    private BufferedImage mBufferedImage;
    private static final String IMAGE_PATH = 
            "E:\\Seasons1\\2009\\autumn-avg.png";
    
    private CurveImage(){
        try{
            init();
        }catch(IOException e){
            printStackTrace();
        }
    }
    
    private static class Image{
        public static final CurveImage CURVE_IMAGE = new CurveImage();
    }
    
    public static CurveImage getInstance(){
        return Image.CURVE_IMAGE;
    }
    
    private void init() throws IOException{
        mBufferedImage = ImageIO.read(new File(IMAGE_PATH));
    }
    
    public BufferedImage getImage(){
        return mBufferedImage;
    }
    
    
}
