package ca.tierslieux.iou.lib.logic.exception;

public class SingletonNotInstanciatedException extends Exception{
    public SingletonNotInstanciatedException(String errorMessage){
        super(errorMessage);
    }
}
