package Model;
import Model.*;
import java.util.LinkedList;

public class MoveCover extends Move {
    int stackSize = -1, maxStackSize = -1;
    Direction direction = Direction.ALL;
    Coordinate nextCoordinate;

    public MoveCover(TakBoard board, Coordinate prevCoordinate, TakPlayer player, Direction direction, int maxStackSize) {
        super(board, prevCoordinate, player);
        this.direction = direction;
        this.maxStackSize = maxStackSize;
    }

    public MoveCover(TakBoard board, Coordinate prevCoordinate, TakPlayer player) {
        super(board, prevCoordinate, player);
        this.direction = Direction.ALL;
        this.maxStackSize = board.getTakBoardDimensions();
    }

    public void moveTo(Coordinate nextCoordinate) throws Errors.InvalidMoveException {
        if ( !prevCoordinate.isAdjacent(nextCoordinate))
            throw new Errors.InvalidMoveException("next coordinate selected is not adjacent to current coordinate");
        if ( !board.canMoveOntoSquare(this, nextCoordinate))
            throw new Errors.InvalidMoveException("current stack cannot cover new selected stack");
        if ( correctMoveDirection(this.direction, getPrevCoordinate(), nextCoordinate))
            this.nextCoordinate = nextCoordinate;
    }

    public void grabStack(int stackInMovementSize) throws Errors.InvalidMoveException {
        if ( stackInMovementSize <= maxStackSize ) {
            this.stackSize = stackInMovementSize;
        } else {
            throw new Errors.InvalidMoveException("while splaying pieces, can only pick up pieces previously splayed");
        }
    }

    public int getStackSize() throws Errors.InvalidMoveException {
        return stackSize;
    }

    public Direction getDirection() throws Errors.InvalidMoveException {
        return direction;
    }

    public int getMaxStackSize() throws Errors.InvalidMoveException {
        if ( maxStackSize == -1 )
            throw new Errors.InvalidMoveException("max stack size not set yet");
        return maxStackSize;
    }

    @Override
    public Move executeMove() throws Errors.InvalidMoveException {
        if ( stackSize == -1 )
            throw new Errors.InvalidMoveException("stack size not chosen yet");
        TakStack stackInMotion = board.splitStack(prevCoordinate, stackSize);

        board.coverStack(stackInMotion, nextCoordinate);
        return new MoveCover(board, nextCoordinate, player, Move.directionFromCoordinate(prevCoordinate, nextCoordinate), stackSize - 1);
    }

    @Override
    public boolean isPlaceAction() {
        return false;
    }

    @Override
    public boolean isCoverAction() {
        return true;
    }

    public Coordinate getNextCoordinate() throws Errors.InvalidMoveException {
        if ( nextCoordinate == null )
            throw new Errors.InvalidMoveException("coordinate to move to not selected yet");
        return nextCoordinate;
    }

    @Override
    public String toString() {
        return "moving top " + stackSize + " takPieces from stack at " + prevCoordinate + " to " + nextCoordinate;
    }
}
