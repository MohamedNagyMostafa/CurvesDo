/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author mohamednagy
 */
public class ColorNotFound extends Exception{
    
    public ColorNotFound(){
        super();
    }
    
    public ColorNotFound(String message){
        super(message);
    }
}
