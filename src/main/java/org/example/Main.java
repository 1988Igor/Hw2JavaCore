package org.example;

public class Main {

    private static final int WIN_COUNT = 5;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '•';
    private static final java.util.Scanner SCANNER = new java.util.Scanner(System.in);
    private static final java.util.Random RANDOM = new java.util.Random();
    private static char[][] field;
    private static int fieldSizeX = 5;
    private static int fieldSizeY = 5;


    public static void main(String[] args) {
        initializeGame();
        printField();

        while (true) {
            humanTurn();
            printField();
            if (gameCheck(DOT_HUMAN, "Human win!"))
                break;
            if (isDraw()) {
                System.out.println("Draw!");
                break;
            }
            aiTurn();
            printField();
            if (gameCheck(DOT_AI, "AI win!"))
                break;
            if (isDraw()) {
                System.out.println("Draw!");
                break;
            }
        }


    }

    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellValid(x, y));
        field[y][x] = DOT_AI;
    }

    private static boolean isDraw() {
        boolean result = true;
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++)
                if (field[i][j] == DOT_EMPTY) {
                    result = false;
                    break;
                }
            if (!result) {
                break;
            }
        }
        return result;
    }

    private static boolean gameCheck(char dotHuman, String s) {
        boolean result = false;
        if (checkWin(dotHuman)) {
            System.out.println(s);
            result = true;
        }
        return result;
    }

    private static boolean checkWin(char dotHuman) {
        boolean result = false;
        if (checkWinDiagonals(dotHuman) || checkWinLines(dotHuman)) {
            result = true;
        }
        return result;
    }

    private static boolean checkWinLines(char dotHuman) {
        boolean result = false;
        for (int i = 0; i < fieldSizeY; i++) {
            int line = 0;
            int column = 0;
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == dotHuman) {
                    line++;
                }
                if (field[j][i] == dotHuman) {
                    column++;
                }
            }
            if (line == WIN_COUNT || column == WIN_COUNT) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static boolean checkWinDiagonals(char dotHuman) {
        boolean result = false;
        int mainDiagonal = 0;
        int sideDiagonal = 0;
        for (int i = 0; i < fieldSizeX; i++) {
            if (field[i][i] == dotHuman) {
                mainDiagonal++;
            }
            if (field[i][fieldSizeX - 1 - i] == dotHuman) {
                sideDiagonal++;
            }
        }
        if (mainDiagonal == WIN_COUNT || sideDiagonal == WIN_COUNT) {
            result = true;
        }
        return result;
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты X и Y (от 1 до 5) через пробел >>>");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y));
        field[y][x] = DOT_HUMAN;
    }

    private static boolean isCellValid(int x, int y) {
        boolean result = true;
        if (x < 0 || x >= fieldSizeX || y < 0 || y >= fieldSizeY) {
            result = false;
        }
        if (field[y][x] != DOT_EMPTY) {
            result = false;
        }
        return result;
    }

    private static void printField() {
        printFieldHeader();
        printFieldBody();
        System.out.println();

    }

    private static void printFieldBody() {
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < fieldSizeX; j++)
                System.out.print(field[i][j] + " ");
            System.out.println();
        }
    }

    private static void printFieldHeader() {
        System.out.print("  ");
        for (int i = 0; i < fieldSizeX; i++)
            System.out.print(i + 1 + " ");
        System.out.println();
    }

    private static void initializeGame() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++)
                field[y][x] = DOT_EMPTY;
        }
    }

}