package Application;//Это главный класс, откуда начинается выполнение программы.
// Отвечает за обработку команд пользователя, вызов соответствующих методов и управление потоком работы программы

import Data.Texts;
import Utils.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        int answer = 0;
        int cipherKey;
        Path sourseFile, destinationFile;

        drawMainMenu();

        do {
            try {
                answer = Integer.parseInt(console.nextLine());

                switch (answer) {
                    case 1:
                        System.out.println(Texts.MENU_1);
                        sourseFile = FileManager.getSourceFilePath();// C:\Users\Elena\IdeaProjects\com.javarush.vasich.cryptoanalyzer\src\Data\text.txt
                        if (sourseFile == null){
                            drawMainMenu();
                            continue;
                        }
                        cipherKey = Cipher.getCipherKey();
                        if (cipherKey == 0) {
                            drawMainMenu();
                            continue;
                        }
                        destinationFile = FileManager.getDestinationFilePath();
                        if (destinationFile == null){
                            drawMainMenu();
                            continue;
                        }
                        Cipher.cipherText(sourseFile, cipherKey, destinationFile, true);
                        break;

                    case 2:
                        System.out.println(Texts.MENU_2);
                        sourseFile = FileManager.getSourceFilePath();
                        if (sourseFile == null){
                            drawMainMenu();
                            continue;
                        }
                        cipherKey = Cipher.getCipherKey();
                        if (cipherKey == 0) {
                            drawMainMenu();
                            continue;
                        }
                        destinationFile = FileManager.getDestinationFilePath(); // C:\Users\Elena\IdeaProjects\com.javarush.vasich.cryptoanalyzer\result.txt
                        if (destinationFile == null){
                            drawMainMenu();
                            continue;
                        }
                        Cipher.cipherText(sourseFile, cipherKey, destinationFile, false);
                        break;

                    case 3:
                        System.out.println(Texts.MENU_3);
                        sourseFile = FileManager.getSourceFilePath();
                        if (sourseFile == null){
                            drawMainMenu();
                            continue;
                        }
                        destinationFile = FileManager.getDestinationFilePath();
                        if (destinationFile == null){
                            drawMainMenu();
                            continue;
                        }
                        Cipher.decryptByBruteForce(sourseFile, destinationFile);
                        break;

                    case 4:
                        System.out.println(Texts.MENU_4);
                        //sourseFile = FileManager.getSourceFilePath();
                        //destinationFile = FileManager.getDestinationFilePath();
                        StatisticalAnalyzer.findMostLikelyShift();
                        break;

                    case 5:
                        System.out.println(Texts.MENU_5);
                        console.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println(Texts.MENU_ERROR_TEXT);
                }

            } catch (NumberFormatException e) {
                System.out.println(Texts.MENU_ERROR_TEXT);
            } catch (IOException e) {
                System.out.println(Texts.IO_ERROR_TEXT);
            }
        } while (answer != 5);
    }

    private static void drawMainMenu(){
        for (String s : Texts.menuText)
            System.out.println(s);
    }
}
