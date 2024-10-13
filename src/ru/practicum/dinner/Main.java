package ru.practicum.dinner;
/*Спасибо за полезные комментарии! Очень ценю все советы и подсказки — они помогают
не только улучшить код, но и узнавать много нового.
Как начинающий программист, каждый комментарий действительно важен.
Извиняюсь, если не получилось учесть все изменения в прошлый раз.
Учусь с нуля, некоторые темы ещё не прошли, но всё обязательно применю по мере освоения теории.
Кстати, добавлено краткое описание изменений в файл README.md. Спасибо за внимание к деталям!*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверная команда. Пожалуйста, введите 1, 2 или 3.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        String dishType = readUserInput("Введите тип блюда:");
        String dishName = readUserInput("Введите название блюда:");

        dc.addDish(dishType, dishName);
        System.out.println("Блюдо добавлено: " + dishType + " - " + dishName);
    }

    private static String readUserInput(String prompt) {
        System.out.println(prompt);
        String userInput;
        while (!(userInput = scanner.nextLine()).isEmpty()) {
            if (userInput.trim().isEmpty()) {
                System.out.println("Вы ввели пустую строку. " + prompt);
                continue;
            }
            break;
        }
        return userInput;
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        List<String> types = new ArrayList<>();
        System.out.println("Вводите типы блюда, разделяя символом переноса строки (Enter). Для завершения введите пустую строку");
        String nextItem = scanner.nextLine();

        while (!nextItem.isEmpty()) {
            if (!dc.checkType(nextItem)) {
                System.out.println("Такой тип блюда не существует. Попробуйте снова.");
            } else {
                types.add(nextItem);
            }
            nextItem = scanner.nextLine();
        }

        List<List<String>> combos = dc.generateCombos(types, numberOfCombos);
        for (List<String> combo : combos) {
            System.out.println("Комбо: " + combo);
        }
    }
}