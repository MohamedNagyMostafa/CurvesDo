/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo;

import javax.swing.JFrame;

/**
 *
 * @author mohamednagy
 */
public class MainFrame {
    
    private static JFrame mFrame;
    
    public static void main(String args[]) {
        mFrame = new JFrame();
        ImagePanel imagePanel = new ImagePanel();
        mFrame.setSize(imagePanel.getWidth(), imagePanel.getHeight());
        mFrame.setVisible(true);
    }
    
}
