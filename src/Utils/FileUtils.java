package Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static String readFile(String filePath) {
        String resultLine = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resultLine = line;
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так...");
        }
        return resultLine;
    }

    public static void writeToNewFile(String fileName) throws IOException {


    }

}
