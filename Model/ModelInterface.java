package Model;
import Model.*;
import java.util.ArrayList;

public class ModelInterface{
	public enum Colour { BLACK, YELLOW };
	TakBoard board;

	public ModelInterface(int takBoardDimensions){
		board = new TakBoard(takBoardDimensions);
	}


	public void addPiece(Coordinate input, TakPiece piece) throws Errors.InvalidMoveException{
		board.get(input).addPiece(piece);
	}

	public TakStack get(Coordinate input){
		return board.get(input);
	}
	public String toString(){
		return board.toString();
	}
}
