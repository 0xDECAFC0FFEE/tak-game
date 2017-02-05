package Model;
import Model.*;
import java.util.LinkedList;

public class Move {
	enum Direction{NORTH, EAST, SOUTH, WEST, ALL};

	Coordinate coordinate;
	TakBoard board;
	TakPlayer player;
	LinkedList<Coordinate> adjacentCoordinates;
	Direction direction = Direction.ALL;;
	TakStack stackInMovement;
	int maxStackSize = 100;

	public Move(TakBoard board, Coordinate coordinate, TakPlayer player, Direction direction, int maxStackSize){
		this.direction = direction;
		this.maxStackSize = maxStackSize;
		setValues(coordinate, board, player);
	}

	public Move(TakBoard board, Coordinate coordinate, TakPlayer player){
		setValues(coordinate, board, player);
	}

	private void setValues(Coordinate coordinate, TakBoard board, TakPlayer player){
		this.coordinate = coordinate;
		this.board = board;
		this.player = player;
		addAdjacentCoordinate(new Coordinate(coordinate.x, coordinate.y+1));
		addAdjacentCoordinate(new Coordinate(coordinate.x+1, coordinate.y));
		addAdjacentCoordinate(new Coordinate(coordinate.x, coordinate.y-1));
		addAdjacentCoordinate(new Coordinate(coordinate.x-1, coordinate.y));
	}

	private void addAdjacentCoordinate(Coordinate coordinate){
		if (   coordinate.x >= 0
			&& coordinate.x <= board.getTakBoardDimensions()
			&& coordinate.y >= 0
			&& coordinate.y <= board.getTakBoardDimensions())
			adjacentCoordinates.add(coordinate);
	}

	public void grabStack(int stackInMovementSize) throws Errors.InvalidMoveException {
		if (stackInMovement != null)
			stackInMovement.cover(board.get(coordinate));
		if (stackInMovementSize <= maxStackSize){
			TakStack originalStartingLocationStack = board.get(coordinate);
			stackInMovement = originalStartingLocationStack.splitStack(stackInMovementSize);
		} else {
			throw new Errors.InvalidMoveException("when splaying pieces, must only pick up pieces previously splayed");
		}
	}

	public LinkedList<Coordinate> getValidMoves() throws Errors.InvalidMoveException{
		if (stackInMovement == NULL)
			throw new InvalidMoveException("please select a stack size first");
		if (!board.containsPiece(coordinate)){
			LinkedList<Coordinate> output = new LinkedList<Coordinate>();
			output.add(coordinate);
			return output;
		} else if (player.getColour() != board.get(coordinate).top().getColour()) { // im picking up a stack
			throw new Errors.InvalidMoveException("cannot move stack not owned by yourself");
		} else { // there exists a stack and my stack's top is a piece of the same colour
			LinkedList<Coordinate> output = new LinkedList<Coordinate>();
			for (Coordinate adjacentCoordinate : adjacentCoordinates){
				if ((!board.containsPiece(adjacentCoordinate)
				|| board.get(adjacentCoordinate).topType() == TakPiece.PieceType.FLAT
				|| board.get(adjacentCoordinate).topType() == TakPiece.PieceType.WALL && board.get(coordinate).topType() == TakPiece.PieceType.CAP)) {
					if (correctMoveDirection(this.direction, this.coordinate, adjacentCoordinate))
						output.add(adjacentCoordinate);
				}
			}
			return output;
		}
	}

	private boolean correctMoveDirection(Direction direction, Coordinate currentCoordinate, Coordinate newCoordinate){
		switch (direction){
			case ALL: return true;
			case NORTH: if (currentCoordinate.x == newCoordinate.x && currentCoordinate.y == newCoordinate.y+1) return true; else return false;
			case SOUTH: if (currentCoordinate.x == newCoordinate.x && currentCoordinate.y == newCoordinate.y-1) return true; else return false;
			case EAST: if (currentCoordinate.x == newCoordinate.x+1 && currentCoordinate.y == newCoordinate.y) return true; else return false;
			case WEST: if (currentCoordinate.x == newCoordinate.x-1 && currentCoordinate.y == newCoordinate.y) return true; else return false;
			default: return false;
		}
	}

	public boolean moveTo(Coordinate newCoordinate) throws Errors.InvalidMoveException{
		LinkedList<Coordinate> validMoves = getValidMoves();
		if (validMoves.contains(newCoordinate)){

		}
	}
}
