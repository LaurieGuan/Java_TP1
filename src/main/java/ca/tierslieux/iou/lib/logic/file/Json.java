package ca.tierslieux.iou.lib.logic.file;

public interface Json {
    //  Cette méthode est censée retourner une string formattée en json qui sera ensuite convertie en fichier.
    public String toJson();

    // Cette méthode est censée prendre en paramètre une string Json, puis appeler le constructeur de la classe pour
    // instancier l'objet. Puisqu'elle est statique, elle ne fait pas partie officiellement de l'interface,
    // mais elle devrait s'y retrouver quand même.
    /* public static Object fromJson(String json); */
}
