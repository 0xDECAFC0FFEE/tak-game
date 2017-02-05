import Model.*;

public class Main{
	public static void main(String args[]){
		ModelInterface modelInterface = new ModelInterface(5);
		TakPlayer player1 = new TakPlayer(ModelInterface.Colour.BLACK);
		TakPlayer player2 = new TakPlayer(ModelInterface.Colour.YELLOW);
		try {
			modelInterface.addPiece(new Coordinate(1,1), new TakPiece(TakPiece.PieceType.CAP, player1.getColour()));
			modelInterface.addPiece(new Coordinate(1,2), new TakPiece(TakPiece.PieceType.FLAT, player1.getColour()));

			Move nextMove = new Move(New Coordinate(1,1));
			newMove.setStackSize(1);
			ArrayList<Coordinate> = move.getValidMoves();
			move.moveTo(Coordinate);
			move.execute();

			System.out.println(modelInterface);
		} catch(Errors.InvalidMoveException e) {
			System.out.println(e.getMessage());
		}
	}
}
