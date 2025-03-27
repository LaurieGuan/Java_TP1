package ca.tierslieux.iou.lib.logic.file;

import ca.tierslieux.iou.lib.logic.exception.FileAlreadyExists;
import java.io.*;

public class CustomFile {
    private BufferedWriter writer = null;
    private BufferedReader reader = null;
    private final String filePath;


    public static CustomFile saveAtFromText(String path, String text, FileType fileType) {
        try {
            return saveAtFromText(path, text);
        } catch (FileAlreadyExists e) {
            throw new FileAlreadyExists();
        }
    }

    public static CustomFile saveAtFromText(String path, String text) {
        File file = new File(path);

        CustomFile customFile = new CustomFile(path);
        customFile.write(text, false);
        return customFile;
    }

    public CustomFile(String path) {
        this.filePath = path;
    }

    private BufferedWriter getWriter(boolean append) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            writer = new BufferedWriter(new FileWriter(filePath, append));
        } catch (IOException e) {
            System.err.println("Le fichier n'a pas pu être ouvert pour l'écriture: " + filePath);
        }
        return writer;
    }

    private BufferedReader getReader() {
        if (reader == null) {
            try {
                reader = new BufferedReader(new FileReader(filePath));
            } catch (IOException e) {
                System.err.println("Le fichier suivant n'a pas pu être ouvert pour la lecture: " + filePath);
            }
        }
        return reader;
    }

    public String getFilePath() {
        return filePath;
    }

    public String read() {
        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = getReader();
        if (bufferedReader != null) {
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
            } catch (IOException e) {
                System.err.println("Il y eu une erreur de lecture de fichier: " + filePath);
            }
        }
        return content.toString();
    }

    public void write(String text, boolean append) {
        BufferedWriter bufferedWriter = getWriter(append);
        if (bufferedWriter != null) {
            try {
                bufferedWriter.write(text);
                bufferedWriter.newLine();
            } catch (IOException e) {
                System.err.println("Il y eu une erreur d'écriture de fichier: " + filePath);
            } finally {
                try {
                    bufferedWriter.close();
                    writer = null;
                } catch (IOException e) {
                    System.err.println("Il n'a pas été possible de fermer le fichier en écriture.");
                }
            }
        }
    }

    private static String getExtension(FileType fileType) {
        String extension = "";
        switch (fileType) {
            case CSV:
                extension = ".csv";
                break;
            case JSON:
               extension = ".json";
               break;
        }

        return extension;
    }
}