package Model;
import Model.*;
import java.util.LinkedList;
import java.util.List;

public class TakStack {
    List<TakPiece> stack = new LinkedList<TakPiece>();
    Coordinate coordinate;

    public TakStack(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public TakPiece top() {
        return stack.get(0);
    }

    public TakPiece.PieceType topType() {
        return top().getType();
    }

    public int size() {
        return stack.size();
    }

    public boolean containsPiece() {
        return stack.size() != 0;
    }

    public void addPiece(TakPiece piece) throws Errors.InvalidMoveException {
        if ( size() == 0 )
            stack.add(piece);
        else
            throw new Errors.InvalidMoveException("cannot place piece at coordiante " + coordinate.toString() + ", piece already exists there");
    }

    public void cover(TakStack stackToBeCovered) throws Errors.InvalidMoveException {
        if ( stackToBeCovered.size() == 0 )
            validCover(stackToBeCovered);
        switch ( stackToBeCovered.topType()) {
            case CAP:
                throw new Errors.InvalidMoveException("cannot move move stack onto cap piece");
            case WALL:
                if ( topType() != TakPiece.PieceType.CAP )
                    throw new Errors.InvalidMoveException("cannot move move stack onto cap piece");
            case FLAT:
                validCover(stackToBeCovered);
                break;
            default:
                throw new Errors.InvalidMoveException("invalid piece type; something went wrong");
        }
    }

    private void validCover(TakStack stackToBeCovered) {
        stackToBeCovered.stack.addAll(0, stack);
        clearStack();
    }

    private List<TakPiece> getStack() {
        return stack;
    }

    private void clearStack() {
        stack = new LinkedList<TakPiece>();
    }

    public String toString() {
        String output = "";

        if ( !containsPiece())
            return output;

        for ( TakPiece piece : stack )
            output += piece.toString() + " ";
        return output.substring(0, output.length() - 1);
    }

    public TakStack splitStack(int sizeOfStackToRemove) throws Errors.InvalidMoveException {
        if ( sizeOfStackToRemove > stack.size())
            throw new Errors.InvalidMoveException("cannot remove more pieces than there exists in a stack!");
        TakStack newStack = new TakStack(coordinate);
        newStack.stack = stack.subList(0, sizeOfStackToRemove);
        stack = stack.subList(sizeOfStackToRemove, stack.size());
        return newStack;
    }
}
