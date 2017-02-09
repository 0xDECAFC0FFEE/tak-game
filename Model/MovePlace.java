package Model;
import Model.*;
import java.util.LinkedList;

public class MovePlace extends Move {
    TakPiece newTakPiece;
    public MovePlace(TakBoard board, Coordinate prevCoordinate, TakPlayer player) {
        super(board, prevCoordinate, player);
    }

    public void setTakPiece(TakPiece newTakPiece) {
        this.newTakPiece = newTakPiece;
    }

    @Override
    public Move executeMove() throws Errors.InvalidMoveException {
        if ( newTakPiece == null )
            throw new Errors.InvalidMoveException("please define piece type before executing move");
        board.placePiece(newTakPiece, prevCoordinate);
        return null;
    }

    @Override
    public boolean isCoverAction() {
        return false;
    }

    @Override
    public boolean isPlaceAction() {
        return true;
    }

    @Override
    public String toString() {
        return "placing takPiece " + newTakPiece + " at " + prevCoordinate;
    }
}
