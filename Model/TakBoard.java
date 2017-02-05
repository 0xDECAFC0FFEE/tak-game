package Model;
import Model.*;
import java.util.ArrayList;

public class TakBoard{
	ArrayList<ArrayList<TakStack>> board = new ArrayList<ArrayList<TakStack>>();
	TakStack stackInMotion;

	public TakBoard(int takBoardDimensions){
		for (int i = 0; i < takBoardDimensions; i++){
			ArrayList<TakStack> newRow = new ArrayList<TakStack>();
			for (int j = 0; j < takBoardDimensions; j++){
				newRow.add(new TakStack(new Coordinate(i, j)));
			}
			board.add(newRow);
		}
	}

	public TakPiece top(Coordinate input){
		TakPiece topPiece = get(input).top();
		return topPiece;
	}

	public boolean containsPiece(Coordinate input){
		return board.get(input.x).get(input.y).containsPiece();
	}
	public TakStack get(Coordinate input){
		return board.get(input.x).get(input.y);
	}
	public TakStack splitStack(Coordinate input, int size) throws Errors.InvalidMoveException{
		if (stackInMotion != null || )
		stackInMotion = get(input).splitStack(size);
	}
	public TakStack getStackInMotion(){

	}
	public int getTakBoardDimensions(){
		return board.size();
	}
	public String toString(){
		String output = "";
		for(int i = 0; i < board.size(); i++){
			for (int j = 0; j < board.get(i).size(); j++){
				output+="["+board.get(i).get(j)+"]\t";
			}
			output += "\n";
		}
		return output;
	}
}
