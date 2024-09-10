package Utils;//Валидация входных данных, таких как существование файла, допустимость ключа.

import Data.Alphabet;

import java.io.File;
import java.util.Scanner;

public class Validator {

    private static final String KEY_IS_NAN = "Ошибка ввода! Нужно ввести целое число больше 0";
    private static final String KEY_IS_ZERO = "Не подойдет! Число должно быть больше 0 и не кратно " + Alphabet.getAlphabetSmallRusSize() + " иначе ничего не произойдет";
    private static final String KEY_IS_CORRECT = "Ключ прошел проверку и принят";
    private static final String FILE_NOT_FOUND = "Упс, файл не найден... Введите корректный путь или цифру 5 для выхода в главное меню";
    private static final String FILE_FOUND = "Файл найден!";
    private static final String RETURNING = "Выход в главное меню...";

    public static int checkKey(String key) {
        Scanner console = new Scanner(System.in);
        int checkedKey = 0;

        do {
            try {
                checkedKey = correctKeyNumber(Integer.parseInt(key));
                if (checkedKey <= 0) {
                    System.out.println(KEY_IS_ZERO);
                    key = console.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println(KEY_IS_NAN);
                key = console.nextLine();
            }

        }
        while (checkedKey < 1);

        System.out.println(KEY_IS_CORRECT);
        return checkedKey;
    }

    //если ключ больше, чем кол-во символов алфавита, то возващаем остаток от деления
    private static int correctKeyNumber(int key) {
        return key < Alphabet.getAlphabetSmallRusSize() ? key : key % Alphabet.getAlphabetSmallRusSize();
    }

    public static String checkFileExists(String filePath) {
        Scanner console = new Scanner(System.in);
        while (true) {
            if (filePath.equals("5")) {
                System.out.println(RETURNING);
                return null;
            }
            File file = new File(filePath);
            if (file.exists()) {
                System.out.println(FILE_FOUND);
                return filePath;
            } else {
                System.out.println(FILE_NOT_FOUND);
                filePath = console.nextLine();
            }
        }

    }
}
