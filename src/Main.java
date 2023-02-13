import com.sun.source.tree.BreakTree;

import java.util.*;

public class Main {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {{'_', '|', '_', '|', '_'},
                {'_', '|', '_', '|', '_'},
                {' ', '|', ' ', '|', ' '}};


        printGameBoard(gameBoard);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your placement(1-9): ");
            int playPosition = scanner.nextInt();
            while (playerPositions.contains(playPosition) || cpuPositions.contains(playPosition)){
                System.out.println("position is taken, please enter a correct position");
                playPosition = scanner.nextInt();
            }

            placePiece(gameBoard, playPosition, "player");
            String result = checkWinner();
            if (result.length()>0){
                System.out.println(result);
                break;
            }
            Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1;
            while(cpuPositions.contains(cpuPosition) || playerPositions.contains(cpuPosition)){
                cpuPosition = random.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPosition, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length()>0){
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String user) {
        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }
        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[1][0] = symbol;
                break;
            case 5:
                gameBoard[1][2] = symbol;
                break;
            case 6:
                gameBoard[1][4] = symbol;
                break;
            case 7:
                gameBoard[2][0] = symbol;
                break;
            case 8:
                gameBoard[2][2] = symbol;
                break;
            case 9:
                gameBoard[2][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> middleRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftColumn = Arrays.asList(1, 4, 7);
        List<Integer> middleColumn = Arrays.asList(2, 5, 8);
        List<Integer> rightColumn = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftColumn);
        winning.add(middleColumn);
        winning.add(rightColumn);
        winning.add(cross1);
        winning.add(cross2);
        for (List list : winning) {
            if (playerPositions.containsAll(list)) {
                return "Congratulations! You Win :) ";
            } else if (cpuPositions.containsAll(list)) {
                return "Sorry! CPU Wins :( ";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT!";
            }
        }
        return "";
    }
}

