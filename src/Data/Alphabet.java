package Data;

import java.util.ArrayList;
import java.util.Arrays;

public class Alphabet {

//    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
//            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
//            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '}; // 40элементов - нет Ю =)

    public static final ArrayList<Character> ALPHABET_SMALL_RUS = new ArrayList<>(Arrays.asList(
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х',
            'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '));
             // 41элемент


    private static final int ALPHABET_SMALL_RUS_SIZE = ALPHABET_SMALL_RUS.size();

    public static int getAlphabetSmallRusSize() {
        return ALPHABET_SMALL_RUS_SIZE;
    }


}
