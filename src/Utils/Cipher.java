package Utils;//Класс, реализующий функциональность шифра Цезаря и дешифровки

import Data.Alphabet;

import java.util.Scanner;

public class Cipher {

    public static int alphabetLength = Alphabet.getAlphabetSmallRusSize();


    public static String encryption(String line, int key) {

        char[] lineCharArray = line.toCharArray();
        StringBuilder encryptedLine = new StringBuilder();

        for (char c : lineCharArray) {
            if (Alphabet.ALPHABET_SMALL_RUS.contains(c)){
                int origIndex = Alphabet.ALPHABET_SMALL_RUS.indexOf(c);
                int destIndex = (origIndex + key) < alphabetLength ? (origIndex + key) : (origIndex + key) % alphabetLength;
                encryptedLine.append(Alphabet.ALPHABET_SMALL_RUS.get(destIndex));
            } else
                encryptedLine.append("-");
        }
        return encryptedLine.toString();
    }

    public static String decryption(String line, int key) {
        char[] lineCharArray = line.toCharArray();
        StringBuilder decryptedLine = new StringBuilder();

        for (char c : lineCharArray) {
            if (Alphabet.ALPHABET_SMALL_RUS.contains(c)){
                int origIndex = Alphabet.ALPHABET_SMALL_RUS.indexOf(c);
                int destIndex = (origIndex - key) >= 0 ? (origIndex - key) : alphabetLength + origIndex - key;
                decryptedLine.append(Alphabet.ALPHABET_SMALL_RUS.get(destIndex));
            } else
                decryptedLine.append("-");
        }
        return decryptedLine.toString();
    }

}
