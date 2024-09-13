package Utils;//Класс, реализующий функциональность шифра Цезаря и дешифровки

import Data.Alphabet;
import Data.Texts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Cipher {

    private static final int AB_S_RUS_LENGTH = Alphabet.alphabetSmallRusLength;
    private static final long SIZE_OF_BIG_FILE = 10 * 1024 * 1024; //10 Mb

    /*______ПОЛУЧЕНИЕ КЛЮЧА_____*/

    public static int getCipherKey() {
        Scanner console = new Scanner(System.in);
        System.out.println(Texts.ENTER_KEY);
        return Validator.isValidKey(console.nextLine());
    }

    /*______МЕХАНИЗМ ШИФРОВАНИЯ_____*/
//private
    public static char[] cipherCharArrayMechanism(char[] sourceCharArray, int key, boolean keyPlus) { //KeyPlus - true - шифруем, false - дешифруем
        char[] decryptedCharArray = new char[sourceCharArray.length];
        for (int i = 0; i < sourceCharArray.length; i++) {
            if (Alphabet.ALPHABET_SMALL_RUS.contains(sourceCharArray[i])) {
                int origIndex = Alphabet.ALPHABET_SMALL_RUS.indexOf(sourceCharArray[i]);
                int destinationIndex = keyPlus ?
                        ((origIndex + key) < AB_S_RUS_LENGTH ? (origIndex + key) : (origIndex + key) % AB_S_RUS_LENGTH) :
                        ((origIndex - key) >= 0 ? (origIndex - key) : AB_S_RUS_LENGTH + origIndex - key);
                
                decryptedCharArray[i] = Alphabet.ALPHABET_SMALL_RUS.get(destinationIndex);
            } else
                decryptedCharArray[i] = '-';
        }
        return decryptedCharArray;
    }

    /*______ШИФРОВАНИЕ/ДЕШИФРОВАНИЕ ФАЙЛА_____*/

    public static void cipherText(Path sourceFile, int key, Path destinationFile, boolean keyPlus) throws IOException {

        if (keyPlus)    System.out.println(Texts.ENCRYPTING);
        else            System.out.println(Texts.DECRYPTING);

        if (isFileSmall(sourceFile)) {
            char[] sourceCharArray = Utils.FileManager.readSmallFile(sourceFile);
            char[] decryptedCharArray = cipherCharArrayMechanism(sourceCharArray, key, keyPlus);
            Utils.FileManager.createFile(destinationFile);
            Utils.FileManager.writeToSmallFile(destinationFile, decryptedCharArray);
        } else {
            ArrayList<char[]> listOfCharLines = Utils.FileManager.readBigFile(sourceFile);
            ArrayList<char[]> resultCharArray = new ArrayList<>();
            for (char[] line : listOfCharLines) {
                resultCharArray.add(cipherCharArrayMechanism(line, key, keyPlus));
            }
            Utils.FileManager.createFile(destinationFile);
            Utils.FileManager.writeToBigFile(destinationFile, resultCharArray);
        }
    }


    public static void decryptByBruteForce(Path sourceFile, Path destinationFile) throws IOException {

        System.out.println(Texts.DECRYPTING);

        if (isFileSmall(sourceFile)) {
            char[] sourceCharArray = Utils.FileManager.readSmallFile(sourceFile);
            TreeMap<Integer, char[]> result = new TreeMap<>();

            for (int i = 1; i < Alphabet.alphabetSmallRusLength; i++) {
                result.put(i, cipherCharArrayMechanism(sourceCharArray, i, false));
            }

            StringBuilder data = new StringBuilder();
            for (Map.Entry<Integer, char[]> entry : result.entrySet()) {
                Integer key = entry.getKey();
                char[] charArray = entry.getValue();
                data.append(key).append(": ").append(new String(charArray)).append(System.lineSeparator());
            }

            char[] dataToWrite = data.toString().toCharArray();
            Utils.FileManager.createFile(destinationFile);
            FileManager.writeToSmallFile(destinationFile, dataToWrite);

            //добавить предположение согласно статистике....
        } else {
            System.out.println("Слишком много, я пока так не могуууу....");
        }

    }


    /*______РАЗМЕР ФАЙЛА_____*/

    private static boolean isFileSmall(Path file) throws IOException {
        return Files.size(file) < SIZE_OF_BIG_FILE;
    }

}
