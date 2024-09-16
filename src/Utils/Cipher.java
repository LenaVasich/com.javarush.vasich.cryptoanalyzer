package Utils;//Класс, реализующий функциональность шифра Цезаря и дешифровки

import Data.Alphabet;
import Data.Texts;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Cipher {

    private static final int AB_S_RUS_LENGTH = Alphabet.alphabetSmallRusLength;
    public static Scanner console = new Scanner(System.in);

    /*______ПОЛУЧЕНИЕ КЛЮЧА_____*/

    public static int getCipherKey() {
        System.out.println(Texts.ENTER_KEY);
        return Validator.isValidKey(console.nextLine());
    }

    /*______МЕХАНИЗМ ШИФРОВАНИЯ_____*/
//private
    public static char[] cipherCharArrayMechanism(char[] sourceCharArray, int key, boolean keyPlus) { //KeyPlus - true - шифруем, false - дешифруем
        char[] decryptedCharArray = new char[sourceCharArray.length];
        for (int i = 0; i < sourceCharArray.length; i++) {
            if (Alphabet.ALPHABET_SMALL_RUS.contains(Character.toLowerCase(sourceCharArray[i]))) {
                int origIndex = Alphabet.ALPHABET_SMALL_RUS.indexOf(Character.toLowerCase(sourceCharArray[i]));
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

        if (FileManager.isFileSmall(sourceFile)) {
            char[] sourceCharArray = FileManager.readSmallFile(sourceFile);
            char[] decryptedCharArray = cipherCharArrayMechanism(sourceCharArray, key, keyPlus);
            FileManager.createFile(destinationFile);
            FileManager.writeToSmallFile(destinationFile, decryptedCharArray);
        } else {
            ArrayList<char[]> listOfCharLines = FileManager.readBigFile(sourceFile);
            ArrayList<char[]> resultCharArray = new ArrayList<>();
            for (char[] line : listOfCharLines) {
                resultCharArray.add(cipherCharArrayMechanism(line, key, keyPlus));
            }
            FileManager.createFile(destinationFile);
            FileManager.writeToBigFile(destinationFile, resultCharArray);
        }
    }


    public static void decryptByBruteForce(Path sourceFile, Path destinationFile) throws IOException {

        if (FileManager.isFileSmall(sourceFile)) {
            System.out.println(Texts.DECRYPTING_BRUTE_FORCE);
            char[] sourceCharArray = FileManager.readSmallFile(sourceFile);
            char[] resultCharArray = bruteForceMechanism(sourceCharArray);
            Utils.FileManager.createFile(destinationFile);
            FileManager.writeToSmallFile(destinationFile, resultCharArray);
            //добавить предположение согласно статистике....
        } else {
            System.out.println(Texts.DECRYPTING_BRUTE_BIG_FILE);
            char[] firstLine = FileManager.readFirstLineOfBigFile(sourceFile);
            System.out.println(Texts.DECRYPTING);
            char[] firstLineBruteForce = bruteForceMechanism(firstLine);
            System.out.println(firstLineBruteForce);
            System.out.println("Выберите ключ. Ведите число от 1 до ...");
            int key = Validator.isValidKey(console.nextLine());
            cipherText(sourceFile, key, destinationFile, false);
        }

    }

    private static char[] bruteForceMechanism(char[] sourceCharArray){

        TreeMap<Integer, char[]> result = new TreeMap<>();

        for (int i = 1; i < Alphabet.alphabetSmallRusLength; i++) {
            result.put(i, cipherCharArrayMechanism(sourceCharArray, i, false));
        }

        StringBuilder data = new StringBuilder();
        for (Map.Entry<Integer, char[]> entry : result.entrySet()) {
            Integer key = entry.getKey();
            char[] charArray = entry.getValue();
            data.append(key).append(": ").append(new String(charArray)).append(System.lineSeparator()).append(System.lineSeparator());
        }

        return data.toString().toCharArray();
    }
}
