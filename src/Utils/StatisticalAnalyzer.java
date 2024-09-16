package Utils;//Для статистического анализа при расшифровке.

import Data.Alphabet;
import Data.Texts;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class StatisticalAnalyzer {

    public static void staticAnalyzer(Path sourceFile, Path destinationFile) throws IOException {
        char[] sourceCharArray = FileManager.isFileSmall(sourceFile) ? FileManager.readSmallFile(sourceFile) : FileManager.readFirstThousandChars(sourceFile);
        if (sourceCharArray.length < 1000)
            System.out.println(Texts.TEXT_IS_TOO_SHORT_FOR_STATISTICAL_ANALYSIS);
        int key = findMostLikelyKey(sourceCharArray);
        Cipher.cipherText(sourceFile, key, destinationFile, false);

    }

    private static int findMostLikelyKey(char[] sourceCharArray) {
        int key;
        LinkedHashMap<Character, Integer> statistics = new LinkedHashMap<>();
        for (Character alphabet : Alphabet.ALPHABET_SMALL_RUS) { //заполняем шаблон мапы чтобы не брать в расчет символы, которых нет в алфавите
            statistics.put(alphabet, 0);
        }
        for (char c : sourceCharArray) { //считаем статистику символов
            if (statistics.containsKey(c)) {
                int i = statistics.get(c) + 1;
                statistics.put(c, i);
            }
        }

        char maxChar = 0;
        int maxFr = 0;
        for (Map.Entry<Character, Integer> entry : statistics.entrySet()) { //дописать проверку на второй символ и если есть два макс. значения...
            if (entry.getValue() > maxFr) {
                maxChar = entry.getKey();
                maxFr = entry.getValue();
            }
        }
        int spaceIndexSource = Alphabet.ALPHABET_SMALL_RUS.indexOf(' ');
        int spaceIndexResult = Alphabet.ALPHABET_SMALL_RUS.indexOf(maxChar);
        System.out.println("spaceIndexSource - " + spaceIndexSource + "   spaceIndexResult - " + spaceIndexResult);
        if (spaceIndexResult < spaceIndexSource)
            key = Alphabet.alphabetSmallRusLength - spaceIndexSource + spaceIndexResult;
        else
            key = spaceIndexResult - spaceIndexSource;
        System.out.println("Похоже, что ключ шифрования - " + key);
        return key;
    }

}
