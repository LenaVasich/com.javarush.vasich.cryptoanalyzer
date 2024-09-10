package Utils;//Реализация метода перебора всех ключей для взлома.

import Data.Alphabet;
import java.util.TreeMap;

public class BruteForce {
    public static TreeMap<Integer, String> bruteforseDecryption(String line) {
        TreeMap<Integer, String> result = new TreeMap<>();
        for (int i = 1; i < Alphabet.getAlphabetSmallRusSize(); i++) {
            result.put(i, Cipher.decryption(line, i));
        }
        return result;
    }
}