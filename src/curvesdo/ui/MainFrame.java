/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo.ui;

import curvesdo.process.ImageProcessing;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author mohamednagy
 */
public class MainFrame {
    
    private static JFrame mFrame;
    
    public static void main(String args[]) {
        initComponent();
    }
    
    private static void initComponent(){
        ImagePanel imagePanel = new ImagePanel();
        ImageProcessing imageProcessing = new ImageProcessing();
        mFrame = new JFrame();
        
        mFrame.add(imagePanel);
        mFrame.setSize(imagePanel.getWidth(), imagePanel.getHeight());
        mFrame.setMaximumSize(new Dimension(imagePanel.getWidth(), imagePanel.getHeight()));
        mFrame.setVisible(true);
    }
    
}
