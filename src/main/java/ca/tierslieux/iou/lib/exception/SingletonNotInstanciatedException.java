package ca.tierslieux.iou.lib.exception;

public class SingletonNotInstanciatedException extends Exception{
    public SingletonNotInstanciatedException(String errorMessage){
        super(errorMessage);
    }
}
