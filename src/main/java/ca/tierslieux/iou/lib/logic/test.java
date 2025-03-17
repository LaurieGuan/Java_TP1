package ca.tierslieux.iou.lib.logic;

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

    public static test getInstance() {
        test inventory = null;
        if (isInstanciated){
            inventory = instance;
        }
        return inventory;
    }

    public String getName() {
        return this.name;
    }

    public static boolean isInstanciated() {
        return isInstanciated;
    }
}
