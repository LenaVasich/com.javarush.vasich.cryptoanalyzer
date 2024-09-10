package Application;//Это главный класс, откуда начинается выполнение программы.
// Отвечает за обработку команд пользователя, вызов соответствующих методов и управление потоком работы программы

import Utils.BruteForce;
import Utils.Cipher;
import Utils.StatisticalAnalyzer;
import Utils.Validator;

import java.util.Scanner;
import java.util.TreeMap;

public class MainApp {
    public static void main(String[] args) {

        final String ERROR_TEXT = "Такого варианта ответа нет! Пожалуйста, введите целое число от 1 до 5.";

        System.out.println("Вас приветствует Криптоанализатор!");
        System.out.println("Введите цифру для выбора действия:");
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл с помощью ключа");
        System.out.println("3. Расшифровать файл с помощью brute force");
        System.out.println("4. Расшифровать файл с помощью  статистического анализа (в разработке)");
        System.out.println("5. Выход");

        Scanner console = new Scanner(System.in);

        int answer = 0;
        String sourceFilePath;
        int cipherKey;


        do {
            try {
                answer = Integer.parseInt(console.nextLine());

                switch (answer){
                    case 1:
                        System.out.println("Вы выбрали зашифровать файл.");
                        //переделать на файл
                        System.out.println("Введите строку:");
                        sourceFilePath = console.nextLine();
                        //Utils.Validator.checkFile(sourceFilePath);

                        System.out.println("Введите ключ шифрования:");
                        cipherKey = Validator.checkKey(console.nextLine());
                        String encryptedLine = Utils.Cipher.encryption(sourceFilePath, cipherKey);
                        System.out.println("Файл успешно зашифрован! Вот результат...");
                        System.out.println(encryptedLine);
                        break;

                    case 2:
                        System.out.println("Вы выбрали расшифровать файл.");
                        //переделать на файл
                        System.out.println("Введите строку:");
                        sourceFilePath = console.nextLine();
                        //Utils.Validator.checkFile(sourceFilePath);

                        System.out.println("Введите ключ шифрования:");
                        cipherKey = Validator.checkKey(console.nextLine());

                        String decryptedLine = Cipher.decryption(sourceFilePath, cipherKey);
                        System.out.println("Файл успешно расшифрован по указанному ключу! Вот результат...");
                        System.out.println(decryptedLine);
                        break;

                    case 3:
                        System.out.println("Вы выбрали расшифровать файл с помощью brute force");
                        //System.out.println("Введите путь к файлу:");
                        System.out.println("Введите строку:");
                        sourceFilePath = console.nextLine();
                        //Utils.Validator.checkFile(sourceFilePath);
                        TreeMap<Integer, String> result = BruteForce.bruteforseDecryption(sourceFilePath);
                        System.out.println("Файл со всеми вариантами расшифровки успешно создан! Путь к файлу: ...");
                        System.out.println(result);
                        break;

                    case 4:
                        System.out.println("Вы выбрали расшифровать файл с помощью  статистического анализа");
                        sourceFilePath = console.nextLine();
                        //Utils.Validator.checkFile(sourceFilePath);
                        StatisticalAnalyzer.staticAnalyzerEncryption(sourceFilePath);
                        break;

                    case 5:
                        System.out.println("Программа завершена. Пока!");
                        console.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println(ERROR_TEXT);
                }

            } catch (NumberFormatException e) {
                System.out.println(ERROR_TEXT);
            }
        } while (answer != 5);
    }
}
