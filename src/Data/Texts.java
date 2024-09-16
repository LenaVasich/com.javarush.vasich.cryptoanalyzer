package Data;

public class Texts {

    //menu
    public static final String[] menuText = {
            "",
            "Вас приветствует Криптоанализатор!",
            "Введите цифру для выбора действия:",
            "1. Зашифровать файл",
            "2. Расшифровать файл с помощью ключа",
            "3. Расшифровать файл с помощью brute force",
            "4. Расшифровать файл с помощью  статистического анализа (не факт, что сработает)",
            "5. Выход"
    };
    public static final String MENU_ERROR_TEXT = "Такого варианта ответа нет! Пожалуйста, введите целое число от 1 до 5.";
    public static final String IO_ERROR_TEXT = "IO ошибка";
    public static final String MENU_1 = "Вы выбрали зашифровать файл.";
    public static final String MENU_2 = "Вы выбрали расшифровать файл.";
    public static final String MENU_3 = "Вы выбрали расшифровать файл с помощью brute force.";
    public static final String MENU_4 = "Вы выбрали расшифровать файл с помощью  статистического анализа.";
    public static final String MENU_5 = "Программа завершена. Пока!";
    public static final String ENTER_FILE = "Введите адрес и имя исходного файла:";
    public static final String ENTER_KEY = "Введите ключ шифрования (целое число больше 0) или exit для выхода в главное меню:";
    public static final String ENTER_RESULT_FILE = "Супер! Осталость только ввести имя файла для создания файла с результатом:";
    public static final String ENCRYPTING = "Шифрую.....";
    public static final String DECRYPTING = "Расшифровываю.....";
    public static final String ENTER_NEXT_NUMBER = "Введите от 1 до 5 для выбора следующего действия (1 - зашифровать, " +
            "2 - расшифровать с ключом, 3 - брутфорс, 4 - статистический анализ, 5 - выход)";

    //validator
    public static final String KEY_IS_NAN = "Ошибка ввода! Нужно ввести целое число больше 0 или exit для выхода в главное меню";
    public static final String KEY_IS_ZERO = "Не подойдет! Число должно быть больше 0 и не кратно " +
            Alphabet.alphabetSmallRusLength + " иначе ничего не произойдет";
    public static final String KEY_IS_CORRECT = "Ключ прошел проверку и принят";
    public static final String FILE_NOT_FOUND = "Упс, файл не найден... Введите корректный путь или цифру 5 для выхода в главное меню";
    public static final String FILE_WRONG_NAME = "Такое имя не пойдет... Введите нормальное";
    public static final String FILE_FOUND = "Файл найден!";
    public static final String RETURNING = "Выход в главное меню...";
    public static final String FILE_ALREADY_EXISTS = "Такой файл уже существует! Не будем его переписывать. " +
            "Давайте новый или введите 5 для выхода в главное меню ";

    //fileManager
    public static final String SUCCESS_ENCRYPTION_TEXT = "Успешный успех! Результат записан в файле по адресу: ";
    public static final String SUCCESS_READ = "Файл успешно прочитан...";
    public static final String SUCCESS_READ_FIRST_LINE = "Первая строка файла успешно прочитана...";
    public static final String SUCCESS_CREATED = "Файл для записи результата успешно создан...";
    public static final String ERROR_CREATED = "Что-то пошло не так при создании файла...";
    public static final String ERROR_READ_SMALL_FILE = "Что-то пошло не так при чтении маленького файла.... =(";
    public static final String ERROR_WRITE_SMALL_FILE = "Что-то пошло не так при записи маленького файла.... =(";
    public static final String ERROR_READ_BIG_FILE = "Что-то пошло не так при чтении большого файла.... =(";
    public static final String ERROR_WRITE_BIG_FILE = "Что-то пошло не так при записи большого файла.... =(";
    public static final String FILE_IS_EMPTY = "УПС! Файл пустой.";
    public static final String LESS_THEN_1000 = "УПС! В вашем тяжеленном файле менее 1000 символов, как так то? Ничего не получится...";

    //cipher
    public static final String DECRYPTING_BRUTE_FORCE = "Расшифровываю брутфорсом... В файл будут записаны все возможные " +
            "варианты расшифровки.";
    public static final String DECRYPTING_BRUTE_BIG_FILE = "Расшифровываю брутфорсом... У вас огромный файл. " +
            "Я выведу варианты расшифровки его первой строки. Введите подходящий ключ и я расшифрую оставшийся текст с этим ключом";

    //statistical analysis
    public static final String TEXT_IS_TOO_SHORT_FOR_STATISTICAL_ANALYSIS = "В файле менее 1000 символов, результат " +
            "расшифрови может быть не корректным.....";
}
