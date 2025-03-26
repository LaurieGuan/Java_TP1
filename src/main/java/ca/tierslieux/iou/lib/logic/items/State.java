package ca.tierslieux.iou.lib.logic.items;

public enum State {
    STORAGE,
    LENT,
    STOLEN,
    BROKEN;

    public static String getStatusString(State status) {
        return switch (status) {
            case STOLEN -> "Volé";
            case STORAGE -> "En ma possession";
            case LENT -> "Prêté";
            case BROKEN -> "Brisé";
        };
    }
}