package Utils;//Валидация входных данных, таких как существование файла, допустимость ключа.

import Data.Alphabet;

import java.util.Scanner;

public class Validator {

    private static final String ERROR_TEXT_KEY_NAN = "Ошибка ввода! Нужно ввести целое число больше 0";
    private static final String ERROR_TEXT_KEY_ZERO = "Не подойдет! Число должно быть больше 0 и не кратно " + Alphabet.getAlphabetSmallRusSize() + " иначе ничего не произойдет";
    private static final String SUCCESS_TEXT_KEY = "Ключ прошел проверку и принят";

    public static int checkKey(String key) {
        Scanner console = new Scanner(System.in);
        int checkedKey = 0;

        do {
            try {
                checkedKey = correctKeyNumber(Integer.parseInt(key));
                if (checkedKey <= 0) {
                    System.out.println(ERROR_TEXT_KEY_ZERO);
                    key = console.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_TEXT_KEY_NAN);
                key = console.nextLine();
            }

        }
        while (checkedKey < 1);

        System.out.println(SUCCESS_TEXT_KEY);
        return checkedKey;
    }

    //если ключ больше, чем кол-во символов алфавита, то возващаем остаток от деления
    private static int correctKeyNumber(int key) {
        return key < Alphabet.getAlphabetSmallRusSize() ? key : key % Alphabet.getAlphabetSmallRusSize();
    }

    public static boolean checkFile(String filePath) {
        //проверка файла
        return true;
    }
}
