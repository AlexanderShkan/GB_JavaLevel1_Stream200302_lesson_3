package ru.gb.stream200302_lesson_3;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private  static final char DOT_HUMAN = 'X';
    private  static final char DOT_AI = '0';
    private  static final char DOT_EMPTY = '_';

    private static int fieldSizeX;
    private static int fieldSizeY;
    private static char [][] field;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static void initField() {

        //fieldSizeY=3;
        //fieldSizeX=3;

        fieldSizeY=5;
        fieldSizeX=5;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x <fieldSizeX ; x++) {
                field[y][x] = DOT_EMPTY;
            }

        }

    }

    private static void printField(){
        System.out.println("------");
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x <fieldSizeX ; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }

    private static boolean isValidCell (int x, int y) {
        return x >= 0  && x < fieldSizeX && y >=0 && y < fieldSizeY;
    }

    private static boolean isEmptyCell (int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private  static void humanTurn () {
        int x;
        int y;
        do {
            System.out.println("Введите координаты хода Х и Y (от 1 до 3) через пробел: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(x,y) || !isEmptyCell(x,y));
        field[y][x] = DOT_HUMAN;
    }

    private  static void aiTurn () {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x,y));
        field[y][x] = DOT_AI;
    }

    private  static boolean isFielFull () {
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x <fieldSizeX ; x++) {
                    if (field[y][x]==DOT_EMPTY) return false;
                }
            }
            return true;
        }
    private static boolean checkWin(char c) {
        int gorizontal = 0;
        for (int y_gor = 0; y_gor < 5; y_gor++) {
            for (int x_gor = 0; x_gor < 5; x_gor++) {
                if (field [y_gor][x_gor] == c) gorizontal++;
            }
            if (gorizontal==4)
                return true;
            else
                gorizontal=0;
        }
        int vertikal = 0;
        for (int x_vert = 0; x_vert < 5; x_vert++){
            for (int y_vert = 0; y_vert < 5; y_vert++) {
                if (field [y_vert][x_vert] == c) vertikal++;
            }
            if (vertikal==4)
                return true;
            else
                vertikal=0;
        }
        int diagonal = 0;
        int reversdiagonal = 0;
        for (int diag = 0; diag < 5; diag++) {
            if (field[diag][diag] == c) diagonal++;
            if (field[diag][4 - diag] == c) reversdiagonal++;
        }
        int diagonal1 = 0;
        int reversdiagonal1 = 0;
        int diagonal2 = 0;
        int reversdiagonal2 = 0;
        for (int diag = 0; diag < 4; diag++) {
            if (field [1+diag][diag] == c) diagonal1++;
            if (field[1+diag][4 - diag] == c) reversdiagonal1++;
            if (field [diag][1+diag] == c) diagonal2++;
            if (field[3-diag][diag] == c) reversdiagonal2++;

        }

        if (diagonal == 4 || reversdiagonal == 4 || diagonal1 == 4 || reversdiagonal1 == 4
                || diagonal2 == 4 || reversdiagonal2 == 4)
            return  true;

        return false;
    }


    /*private static boolean checkWin(char c) {
        int gorizontal = 0;
        for (int y_gor = 0; y_gor < 3; y_gor++) {
            for (int x_gor = 0; x_gor < 3; x_gor++) {
                if (field [y_gor][x_gor] == c) gorizontal++;
            }
            if (gorizontal==3)
                return true;
            else
                gorizontal=0;
         }
        int vertikal = 0;
        for (int x_vert = 0; x_vert < 3; x_vert++){
            for (int y_vert = 0; y_vert < 3; y_vert++) {
                if (field [y_vert][x_vert] == c) vertikal++;
            }
            if (vertikal==3)
                return true;
            else
                vertikal=0;
        }
        int diagonal = 0;
        int reversdiagonal = 0;
        for (int diag = 0; diag < 3; diag++) {
                if (field [diag][diag] == c) diagonal++;
                if (field [diag][2-diag] == c) reversdiagonal++;
        }
        if (diagonal == 3 || reversdiagonal == 3)
            return  true;

        return false;
    }*/

    public static void main(String[] args) {
        while (true) {
            playOneRound();
                System.out.println("Play again? y or n");
                if (SCANNER.next().equals("n"))
                    break;
        }
    }

    private static void playOneRound() {
            initField();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkWin(DOT_HUMAN)){
                    System.out.println("Выиграл человек!");
                    break;
                }
                if (isFielFull()) {
                    System.out.println("Ничья!");
                    break;
                }
                aiTurn();
                printField();
                if (checkWin(DOT_AI)){
                    System.out.println("Выиграл компьютер!");
                    break;
                }
                if (isFielFull()) {
                    System.out.println("Ничья!");
                    break;
                }
            }
        }

}
