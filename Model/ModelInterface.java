package Model;
import Model.*;
import java.util.ArrayList;

public class ModelInterface {
    public enum Colour {BLACK, YELLOW};
    TakBoard board;
    TakPlayer player1, player2;
    TakPlayer currentPlayer;

    public ModelInterface(int takBoardDimensions, TakPlayer player1, TakPlayer palyer2) {
        board = new TakBoard(takBoardDimensions);
        this.player1 = player1;
        this.player2 = player2;
    }

    public void executeMove() throws Errors.InvalidMoveException {
    }

    public String toString() {
        return board.toString();
    }
}
