package Model;
import Model.*;
import java.util.LinkedList;

public class Move {
    // defines a move
    // should not check if the move is valid; that should be done within the board

    enum Direction {NORTH, EAST, SOUTH, WEST, ALL, NONE};

    Coordinate prevCoordinate;
    TakBoard board;
    TakPlayer player;

    public Move(TakBoard board, Coordinate prevCoordinate, TakPlayer player) {
        this.prevCoordinate = prevCoordinate;
        this.board = board;
        this.player = player;
    }

    public Coordinate getPrevCoordinate() throws Errors.InvalidMoveException {
        if ( prevCoordinate == null )
            throw new Errors.InvalidMoveException("coordinate to move from not selected yet");
        return prevCoordinate;
    }

    public TakBoard getBoard() {
        return board;
    }

    public TakPlayer getPlayer() {
        return player;
    }

    public static boolean correctMoveDirection(Move.Direction direction, Coordinate currentCoordinate, Coordinate newCoordinate) {
        switch ( direction ) {
            case ALL:
                return true;
            case NORTH:
                return currentCoordinate.x == newCoordinate.x && currentCoordinate.y == newCoordinate.y + 1;
            case SOUTH:
                return currentCoordinate.x == newCoordinate.x && currentCoordinate.y == newCoordinate.y - 1;
            case EAST:
                return currentCoordinate.x == newCoordinate.x + 1 && currentCoordinate.y == newCoordinate.y;
            case WEST:
                return currentCoordinate.x == newCoordinate.x - 1 && currentCoordinate.y == newCoordinate.y;
            default:
                return false;
        }
    }

    public static Move.Direction directionFromCoordinate(Coordinate currentCoordinate, Coordinate newCoordinate) {
        if ( currentCoordinate.x == newCoordinate.x ) {
            return currentCoordinate.y > newCoordinate.y ? Move.Direction.NORTH : Move.Direction.SOUTH;
        } else if ( currentCoordinate.y == newCoordinate.y ) {
            return currentCoordinate.x > newCoordinate.x ? Move.Direction.EAST : Move.Direction.WEST;
        } else {
            return Move.Direction.NONE;
        }
    }

    public Move executeMove() throws Errors.InvalidMoveException {
        throw new Errors.InvalidMoveException("please choose a coordinate to move to");
    }

    public boolean isCoverAction() {
        return false;
    }

    public boolean isPlaceAction() {
        return false;
    }

    public String toString() {
        return "Move";
    }
}
