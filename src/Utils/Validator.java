package Utils;//Валидация входных данных, таких как существование файла, допустимость ключа.

import Data.Alphabet;
import Data.Texts;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Validator {

    static Scanner console = new Scanner(System.in);

    public static int isValidKey(String key) {

        int checkedKey = 0;

        do {
            try {
                checkedKey = correctKeyNumber(Integer.parseInt(key));
                if (checkedKey < 1) {
                    System.out.println(Texts.KEY_IS_ZERO);
                    key = console.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println(Texts.KEY_IS_NAN);
                key = console.nextLine();
            }

        }
        while (checkedKey < 1);

        System.out.println(Texts.KEY_IS_CORRECT);
        return checkedKey;
    }

    //если ключ больше, чем кол-во символов алфавита, то возващаем остаток от деления
    private static int correctKeyNumber(int key) {
        return key < Alphabet.alphabetSmallRusLength ? key : key % Alphabet.alphabetSmallRusLength;
    }

    public static Path isFileExists(String filePath) {

        while (true) {
            if (filePath.equals("5")) {
                System.out.println(Texts.RETURNING);
                return null;
            }
            Path file = Path.of(filePath);
            if (Files.exists(file)) {
                System.out.println(Texts.FILE_FOUND);
                return file;
            } else {
                System.out.println(Texts.FILE_NOT_FOUND);
                filePath = console.nextLine();
            }
        }

    }

    public static Path checkIfFileDoesNotExist(Path path) {
        Path file = path;

        while (true){
            if (Files.exists(file)){
                System.out.println(Texts.FILE_ALREADY_EXISTS);
                file = Path.of(console.nextLine());
            }
            else
                return file;
        }
    }
}
