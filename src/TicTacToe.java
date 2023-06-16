import java.util.Scanner;

public class TicTacToe {
    private char[][] board;         // Двовимірний масив для представлення ігрової дошки
    private char currentPlayer;    // Змінна для визначення поточного гравця

    public TicTacToe() {
        board = new char[3][3];    // Ініціалізація дошки розміром 3x3
        currentPlayer = 'X';       // Початковий гравець 'X'
        initializeBoard();         // Ініціалізація початкового стану дошки
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';   // Заповнення всіх клітинок дошки значенням '-'
            }
        }
    }

    private void printBoard() {
        System.out.println("---------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");    // Виведення значень клітинок дошки
            }
            System.out.println("\n---------");
        }
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;    // Перевірка, чи дошка ще не заповнена повністю
                }
            }
        }
        return true;
    }

    private boolean hasWon(char player) {
        // Перевірка рядків та стовпців
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;  // Перевірка рядка
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;  // Перевірка стовпця
            }
        }

        // Перевірка діагоналей
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;    // Перевірка діагоналей
        }

        return false;
    }


    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;   // Зробити хід у вказану клітинку
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';   // Змінити поточного гравця
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        System.out.println("Гра почалась!");

        while (!gameOver) {
            printBoard();    // Вивести поточний стан дошки

            System.out.println("Гравець " + currentPlayer + ", введіть рядок (0-2): ");
            int row = scanner.nextInt();     // Введення рядка від гравця

            System.out.println("Гравець " + currentPlayer + ", введіть стовпець (0-2): ");
            int col = scanner.nextInt();     // Введення стовпця від гравця

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                makeMove(row, col);   // Зробити хід, якщо введені координати дійсні та клітинка пуста

                if (hasWon(currentPlayer)) {
                    printBoard();
                    System.out.println("Гравець " + currentPlayer + " переміг!");
                    gameOver = true;   // Гра закінчена, якщо гравець переміг
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("Нічия.");
                    gameOver = true;   // Гра закінчена, якщо дошка заповнена повністю і немає переможця
                }
            } else {
                System.out.println("Неправильнuй хід, спробуйте ще раз.");
            }
        }

        scanner.close();   // Закрити Scanner
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}
