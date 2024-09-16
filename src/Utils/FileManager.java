package Utils;

import Data.Texts;

import java.io.*;  //Для работы с файлами рекомендуется использовать библиотеку Java NIO
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public static Scanner console = new Scanner(System.in);
    private static final long SIZE_OF_BIG_FILE = 10 * 1024 * 1024; //10 Mb

    /*______СОЗДАНИЕ_____*/

    public static void createFile(Path file) {
//        String fileName = file.toString();
//        if (!fileName.contains("."))
//            fileName.concat(".txt");
//        file = Path.of(fileName);
        try {
            Files.createFile(file); //создавать в заданной папке если указано только название
            System.out.println(Texts.SUCCESS_CREATED);
        } catch (IOException e) {
            System.out.println(Texts.ERROR_CREATED);
        }
    }

    /*______ПОЛУЧЕНИЕ ПУТИ_____*/

    public static Path getSourceFilePath() {
        System.out.println(Texts.ENTER_FILE);
        return Utils.Validator.isFileExists(console.nextLine());
    }

    public static Path getDestinationFilePath() {
        System.out.println(Texts.ENTER_RESULT_FILE);
        return Validator.ifFileDoesNotExist(console.nextLine());
    }

    /*______ЧТЕНИЕ_____*/

    public static char[] readSmallFile(Path file) {
        byte[] byteArray;
        try {
            byteArray = Files.readAllBytes(file);
            if (byteArray.length == 0) {
                System.out.println(Texts.FILE_IS_EMPTY);
                return new char[0];
            }
            String stringLine = new String(byteArray, StandardCharsets.UTF_8);
            System.out.println(Texts.SUCCESS_READ);
            return stringLine.toCharArray();
        } catch (IOException e) {
            System.out.println(Texts.ERROR_READ_SMALL_FILE);
            return new char[0];
        }
    }

    public static char[] readFirstLineOfBigFile(Path file) {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = reader.readLine();
            if (line.isEmpty()) {
                System.out.println(Texts.FILE_IS_EMPTY);
                return new char[0];
            }
            else {
                System.out.println(Texts.SUCCESS_READ_FIRST_LINE);
                return line.toCharArray();
            }
        } catch (IOException e) {
            System.out.println(Texts.ERROR_READ_BIG_FILE);
            return new char[0];
        }
    }

    public static char[] readFirstThousandChars(Path file) {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            char[] buffer = new char[1000];
            int charsRead = reader.read(buffer, 0, 1000);
            if (charsRead != -1) {
                String firstThousand = new String(buffer, 0, charsRead);
                return firstThousand.toCharArray();
            } else {
                System.out.println(Texts.LESS_THEN_1000);
                return new char[0];
            }
        } catch (IOException e) {
            System.out.println(Texts.ERROR_READ_BIG_FILE);
            return new char[0];
        }
    }


    public static ArrayList<char[]> readBigFile(Path file) {
        ArrayList<char[]> listOfCharLines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                listOfCharLines.add(line.toCharArray());
            }
            System.out.println(Texts.SUCCESS_READ);
        } catch (IOException e) {
            System.out.println(Texts.ERROR_READ_BIG_FILE);
        }
        return listOfCharLines;
    }

    /*______ЗАПИСЬ_____*/

    public static void writeToSmallFile(Path file, char[] sourseCharArray) {
        try (Writer writer = new FileWriter(String.valueOf(file.getFileName()))) {
            writer.write(sourseCharArray);
            System.out.println(Texts.SUCCESS_ENCRYPTION_TEXT + file.toAbsolutePath() + "\n" + Texts.ENTER_NEXT_NUMBER);
        } catch (IOException e) {
            System.out.println(Texts.ERROR_WRITE_SMALL_FILE);
        }
    }

    public static void writeToBigFile(Path file, ArrayList<char[]> sourseCharArrayList) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file)) {
            for (char[] line : sourseCharArrayList) {
                bufferedWriter.write(line);
            }
            System.out.println(Texts.SUCCESS_ENCRYPTION_TEXT + file.toAbsolutePath() + "\n" + "\n" + Texts.ENTER_NEXT_NUMBER);
        } catch (IOException e) {
            System.out.println(Texts.ERROR_WRITE_BIG_FILE);
        }
    }

    /*______РАЗМЕР ФАЙЛА_____*/

    public static boolean isFileSmall(Path file) throws IOException {
        return Files.size(file) < SIZE_OF_BIG_FILE;
    }
}
