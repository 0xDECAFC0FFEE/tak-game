package Model;
import Model.*;
import java.util.LinkedList;
import java.util.ArrayList;

public class TakBoard {
    ArrayList<ArrayList<TakStack> > board = new ArrayList<ArrayList<TakStack> >();

    public TakBoard(int takBoardDimensions) {
        for ( int i = 0; i < takBoardDimensions; i++ ) {
            ArrayList<TakStack> newRow = new ArrayList<TakStack>();
            for ( int j = 0; j < takBoardDimensions; j++ ) {
                newRow.add(new TakStack(new Coordinate(i, j)));
            }
            board.add(newRow);
        }
    }

    public void placePiece(TakPiece piece, Coordinate coordinate) throws Errors.InvalidMoveException {
        if ( !coordinateInBoard(coordinate))
            throw new Errors.InvalidMoveException("piece coordinates out of bounds");
        if ( !containsPiece(coordinate))
            board.get(coordinate.x).get(coordinate.y).addPiece(piece);
        else
            throw new Errors.InvalidMoveException("cannot place piece onto square already occupied");
    }

    public void coverStack(TakStack stack, Coordinate coordinate) throws Errors.InvalidMoveException {
        board.get(coordinate.x).get(coordinate.y).getCoveredBy(stack);
    }

    public LinkedList<Coordinate> getValidMoves(Move move) throws Errors.InvalidMoveException {
        if ( !containsPiece(move.getPrevCoordinate())) {
            LinkedList<Coordinate> output = new LinkedList<Coordinate>();
            output.add(move.getPrevCoordinate());
            return output;
        } else if ( move.getPlayer().getColour() != get(move.getPrevCoordinate()).top().getColour()) { // im picking up a stack
            throw new Errors.InvalidMoveException("cannot move stack not owned by yourself");
        } else { // there exists a stack and my stack's top is a piece of the same colour
            LinkedList<Coordinate> output = new LinkedList<Coordinate>();
            LinkedList<Coordinate> adjacentCoordinates = constructAdjacentCoordinates(move.getPrevCoordinate());
            for ( Coordinate adjacentCoordinate : adjacentCoordinates ) {
                if ( canMoveOntoSquare(move, adjacentCoordinate) && Move.correctMoveDirection(((MoveCover) move).getDirection(), move.getPrevCoordinate(), adjacentCoordinate))
                    output.add(adjacentCoordinate);
            }
            return output;
        }
    }

    public boolean canMoveOntoSquare(Move move, Coordinate adjacentCoordinate) throws Errors.InvalidMoveException {
        return !containsPiece(adjacentCoordinate) ||
               get(adjacentCoordinate).topType() == TakPiece.PieceType.FLAT ||
               get(adjacentCoordinate).topType() == TakPiece.PieceType.WALL && get(move.getPrevCoordinate()).topType() == TakPiece.PieceType.CAP;
    }

    private LinkedList<Coordinate> constructAdjacentCoordinates(Coordinate coordinate) {
        LinkedList<Coordinate> possibleCoordinates = new LinkedList<Coordinate>();
        addAdjacentCoordinate(new Coordinate(coordinate.x, coordinate.y + 1), possibleCoordinates);
        addAdjacentCoordinate(new Coordinate(coordinate.x + 1, coordinate.y), possibleCoordinates);
        addAdjacentCoordinate(new Coordinate(coordinate.x, coordinate.y - 1), possibleCoordinates);
        addAdjacentCoordinate(new Coordinate(coordinate.x - 1, coordinate.y), possibleCoordinates);
        return possibleCoordinates;
    }

    private void addAdjacentCoordinate(Coordinate coordinate, LinkedList<Coordinate> adjacentCoordinates) {
        if ( coordinateInBoard(coordinate))
            adjacentCoordinates.add(coordinate);
    }

    public boolean coordinateInBoard(Coordinate coordinate) {
        return coordinate.x >= 0 &&
               coordinate.x < getTakBoardDimensions() &&
               coordinate.y >= 0 &&
               coordinate.y < getTakBoardDimensions();
    }

    public TakPiece top(Coordinate input) {
        TakPiece topPiece = get(input).top();

        return topPiece;
    }

    public boolean containsPiece(Coordinate input) {
        return board.get(input.x).get(input.y).containsPiece();
    }

    public TakStack get(Coordinate input) {
        return board.get(input.x).get(input.y);
    }

    public TakStack splitStack(Coordinate input, int size) throws Errors.InvalidMoveException {
        return get(input).splitStack(size);
    }

    public int getTakBoardDimensions() {
        return board.size();
    }

    public String toString() {
        String output = "";

        for ( int i = 0; i < board.size(); i++ ) {
            for ( int j = 0; j < board.get(i).size(); j++ ) {
                output += "[" + board.get(i).get(j) + "]\t";
            }
            output += "\n";
        }
        return output;
    }
}
