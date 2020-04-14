/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

/**
 *
 * @author Mamey
 */
public final class ExceptionCustom extends Exception{
    private int codeError;
    private String message;

    public ExceptionCustom() {
    }

    public ExceptionCustom(int codeError, String message) {
        this.codeError = codeError;
        this.message = message;
    }
    
    public ExceptionCustom(int codError) {
        if (codError == 333){
            this.setMessage("No se encuentra el fichero");
        }
    }
    
    @Override
    public String getMessage() {
        return message;
    }

    public int getCodeError() {
        return codeError;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCodeError(int codeError) {
        this.codeError = codeError;
    }
}
