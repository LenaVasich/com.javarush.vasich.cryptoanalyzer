//package Application;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class LargeFileGenerator {
//    public static void main(String[] args) {
//        String fileName = "largeFile.txt";
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            for (int i = 0; i < 1000000; i++) { // Увеличивайте количество циклов для нужного размера
//                writer.write("Это пример строки текста, которая будет повторяться в файле.\n");
//            }
//            System.out.println("Файл создан: " + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
