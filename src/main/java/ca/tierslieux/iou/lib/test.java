package ca.tierslieux.iou.lib;
import ca.tierslieux.iou.lib.exception.SingletonNotInstanciatedException;

public class test {
    private String name;

    private static boolean isInstanciated = false;
    private static test instance;

    private test(String name){
        this.name = name;
    }

    public static int createInstance(String name){
        int returnCode = -1;
        if (!isInstanciated) {
            instance = new test(name);
            isInstanciated = true;
            returnCode = 0;
        }
        return returnCode;
    }

    public static test getInstance(){
        if (!isInstanciated){
            throw new SingletonNotInstanciatedException("La liste n'a pas été instanciée.");
        } else {
            
        }

    }

}
