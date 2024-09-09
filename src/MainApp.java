//Это главный класс, откуда начинается выполнение программы.
// Отвечает за обработку команд пользователя, вызов соответствующих методов и управление потоком работы программы

import java.util.Scanner;

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
        do {
            try {
                answer = Integer.parseInt(console.nextLine());

                switch (answer){
                    case 1:
                        System.out.println("Ваш вариант - " + answer);
                        break;
                    case 2:
                        System.out.println("Ваш вариант - " + answer);
                        break;
                    case 3:
                        System.out.println("Ваш вариант - " + answer);
                        break;
                    case 4:
                        System.out.println("Ваш вариант - " + answer);
                        break;
                    case 5:
                        System.out.println("Программа завершена. Спасибо за использование.");
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
