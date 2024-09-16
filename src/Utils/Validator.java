package Utils;//Валидация входных данных, таких как существование файла, допустимость ключа.

import Data.Alphabet;
import Data.Texts;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class Validator {

    static Scanner console = new Scanner(System.in);

    /*______КЛЮЧ_____*/

    public static int isValidKey(String key) {

        int checkedKey = 0;

        while (checkedKey < 1)
            try {
                if (key.equalsIgnoreCase("exit")) {
                    System.out.println(Texts.RETURNING);
                    return 0;
                } else {
                    checkedKey = correctKeyNumber(Integer.parseInt(key));
                    if (checkedKey < 1) {
                        System.out.println(Texts.KEY_IS_ZERO);
                        key = console.nextLine();
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(Texts.KEY_IS_NAN);
                key = console.nextLine();
            }

        System.out.println(Texts.KEY_IS_CORRECT);
        return checkedKey;
    }

    //если ключ больше, чем кол-во символов алфавита, то возващаем остаток от деления
    private static int correctKeyNumber(int key) {
        return key < Alphabet.alphabetSmallRusLength ? key : key % Alphabet.alphabetSmallRusLength;
    }

    /*______ФАЙЛ_____*/

    public static Path isFileExists(String filePath) {
        while (true) {
            if (filePath.equals("5")) {
                System.out.println(Texts.RETURNING);
                return null;
            } else {
                System.out.println(filePath);
                if (filePath.startsWith("\"") && filePath.endsWith("\""))
                    filePath = filePath.substring(1, filePath.length() - 1);
                try {
                    System.out.println(filePath);
                    Path file = Path.of(filePath);
                    if (Files.exists(file)) {
                        System.out.println(Texts.FILE_FOUND);
                        return file;
                    } else {
                        System.out.println(Texts.FILE_NOT_FOUND);
                        filePath = console.nextLine();
                    }
                } catch (InvalidPathException e) {
                    System.out.println(Texts.FILE_NOT_FOUND);
                    filePath = console.nextLine();
                }
            }
        }
    }

    public static Path ifFileDoesNotExist(String filePath) {

        while (true) {
            if (filePath.equals("5")) {
                System.out.println(Texts.RETURNING);
                return null;
            }
            try {
                Path file = Path.of(filePath);
                if (Files.exists(file)) {
                    System.out.println(Texts.FILE_ALREADY_EXISTS);
                    filePath = console.nextLine();
                } else
                    return file;
            } catch (InvalidPathException e) {
                System.out.println(Texts.FILE_WRONG_NAME);
                filePath = console.nextLine();
            }

        }
    }
}
