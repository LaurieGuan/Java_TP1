package ca.tierslieux.iou.lib.logic.items;

public enum Type {
    BOOK,
    GAME,
    TOOL;

    public static String getTypeString(Type type) {
        return switch (type) {
            case Type.BOOK -> "Livre";
            case Type.GAME -> "Jeu";
            case Type.TOOL -> "Outil";
        };
    }

}
