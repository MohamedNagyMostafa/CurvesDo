/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvesdo;

/**
 *
 * @author mohamednagy
 */
public class ImageException extends Exception {
    public ImageException(){
        super();
    }
    public ImageException(String message){
        super(message);
    }
    public ImageException(String message, Throwable cause){
        super(message, cause);
    }
    public ImageException(Throwable cause){
        super(cause);
    }
}
