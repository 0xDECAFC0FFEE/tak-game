package Model;
import Model.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ModelInterface {
    public enum Colour {BLACK, YELLOW};
    TakBoard board;
    TakPlayer player1, player2, currentPlayer;
    Move currentMove;

    public ModelInterface(int takBoardDimensions, TakPlayer player1, TakPlayer palyer2) {
        board = new TakBoard(takBoardDimensions);
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setPlayer(TakPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void chooseInitialCoordinate(Coordinate coordinate) throws Errors.InvalidMoveException {
        if ( !board.coordinateInBoard(coordinate))
            throw new Errors.InvalidMoveException("coordinate selected is not in board");
        if ( currentMove != null && currentMove.isCoverAction() )
            throw new Errors.InvalidMoveException("please continue splaying");
        if ( board.containsPiece(coordinate)) {
            if ( board.get(coordinate).topColour() != currentPlayer.getColour() )
                throw new Errors.InvalidMoveException("please move stacks of your own colour");
            currentMove = new MoveCover(board, coordinate, currentPlayer);
        } else {
            currentMove = new MovePlace(board, coordinate, currentPlayer);
        }
    }

    public void setTakPiece(TakPiece newTakPiece) throws Errors.InvalidMoveException {
        checkIsPlace();
        ((MovePlace) currentMove).setTakPiece(newTakPiece);
    }

    public void moveTo(Coordinate nextCoordinate) throws Errors.InvalidMoveException {
        checkIsCover();
        ((MoveCover) currentMove).moveTo(nextCoordinate);
    }

    public void grabStack(int stackInMovementSize) throws Errors.InvalidMoveException {
        checkIsCover();
        ((MoveCover) currentMove).grabStack(stackInMovementSize);
    }

    public void executeMove() throws Errors.InvalidMoveException {
        if ( currentMove == null )
            throw new Errors.InvalidMoveException("please finish defining move before executing");
        currentMove = currentMove.executeMove();
        if ( currentMove == null ) {
            currentPlayer = (player1 == currentPlayer ? player2 : player1);
        }
    }

    public void completeMove() throws Errors.InvalidMoveException {
        currentMove = null;
        currentPlayer = (player1 == currentPlayer ? player2 : player1);
    }

    public LinkedList<Coordinate> getPossibleMoves(Move move) throws Errors.InvalidMoveException {
        return board.getValidMoves(move);
    }

    private void checkIsCover() throws Errors.InvalidMoveException {
        if ( currentMove == null )
            throw new Errors.InvalidMoveException("please select a location first");
        if ( currentMove.isPlaceAction())
            throw new Errors.InvalidMoveException("not in cover mode");
    }

    private void checkIsPlace() throws Errors.InvalidMoveException {
        if ( currentMove == null )
            throw new Errors.InvalidMoveException("please select a location first");
        if ( currentMove.isCoverAction())
            throw new Errors.InvalidMoveException("not in place mode");
    }

    public Move getCurrentMove() {
        return currentMove;
    }

    public String toString() {
        return board.toString();
    }
}
