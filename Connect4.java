import java.util.Scanner;

public class Connect4 {
  static final int ROWS = 6;
  static final int COLUMNS = 7;
  static final char BLANK = ' ';
  static final char PLAYER_1 = 'X';
  static final char PLAYER_2 = 'O';

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    char[][] board = new char[ROWS][COLUMNS];
    char currentPlayer = PLAYER_1;

    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        board[i][j] = BLANK;
      }
    }

    while (true) {
      printBoard(board);
      System.out.println("Player " + currentPlayer + ", enter a column number:");
      int column = scan.nextInt();
      int row = placeDisc(board, column - 1, currentPlayer);
      if (row == -1) {
        System.out.println("This column is full. Try another one.");
      } else if (isWinningMove(board, row, column - 1)) {
        printBoard(board);
        System.out.println("Player " + currentPlayer + " wins!");
        break;
      } else {
        currentPlayer = currentPlayer == PLAYER_1 ? PLAYER_2 : PLAYER_1;
      }
    }
    scan.close();
  }

  public static void printBoard(char[][] board) {
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        System.out.print("|" + board[i][j]);
      }
      System.out.println("|");
    }
    System.out.println("---------------");
  }

  public static int placeDisc(char[][] board, int column, char disc) {
    for (int i = ROWS - 1; i >= 0; i--) {
      if (board[i][column] == BLANK) {
        board[i][column] = disc;
        return i;
      }
    }
    return -1;
  }

  public static boolean isWinningMove(char[][] board, int row, int column) {
    // Check horizontal
    int count = 0;
    for (int j = column - 3; j <= column + 3; j++) {
      if (j >= 0 && j < COLUMNS && board[row][j] == board[row][column]) {
        count++;
        if (count == 4) {
          return true;
        }
      } else {
        count = 0;
      }
    }

    // Check vertical
    count = 0;
    for (int i = row - 3; i <= row + 3; i++) {
      if (i >= 0 && i < ROWS && board[i][column] == board[row][column]) {
        count++;
        if (count == 4) {
          return true;
        }
    }
}
    return false;
}
}