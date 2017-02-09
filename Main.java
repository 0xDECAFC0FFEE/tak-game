import Model.*;

public class Main {
    public static void main(String args[]) {
        TakPlayer player1 = new TakPlayer(ModelInterface.Colour.BLACK);
        TakPlayer player2 = new TakPlayer(ModelInterface.Colour.YELLOW);
        ModelInterface modelInterface = new ModelInterface(5, player1, player2);

        modelInterface.setPlayer(player1);

        try{
            modelInterface.chooseInitialCoordinate(new Coordinate(0, 0));
            modelInterface.setTakPiece(new TakPiece(TakPiece.PieceType.FLAT, player1.getColour()));
            System.out.println(modelInterface.getCurrentMove());
            modelInterface.executeMove();
            System.out.println(modelInterface);

            modelInterface.chooseInitialCoordinate(new Coordinate(1, 0));
            modelInterface.setTakPiece(new TakPiece(TakPiece.PieceType.FLAT, player2.getColour()));
            System.out.println(modelInterface.getCurrentMove());
            modelInterface.executeMove();
            System.out.println(modelInterface);

            modelInterface.chooseInitialCoordinate(new Coordinate(0, 0));
            modelInterface.moveTo(new Coordinate(1, 0));
            modelInterface.grabStack(1);
            System.out.println(modelInterface.getCurrentMove());
            modelInterface.executeMove();
            modelInterface.completeMove();
            System.out.println(modelInterface);

            modelInterface.chooseInitialCoordinate(new Coordinate(0, 0));
            modelInterface.setTakPiece(new TakPiece(TakPiece.PieceType.FLAT, player2.getColour()));
            System.out.println(modelInterface.getCurrentMove());
            modelInterface.executeMove();
            System.out.println(modelInterface);

            modelInterface.chooseInitialCoordinate(new Coordinate(1, 0));
            modelInterface.moveTo(new Coordinate(0, 0));
            modelInterface.grabStack(2);
            System.out.println(modelInterface.getCurrentMove());
            modelInterface.executeMove();
            modelInterface.completeMove();
            System.out.println(modelInterface);

            modelInterface.chooseInitialCoordinate(new Coordinate(1, 0));
            modelInterface.setTakPiece(new TakPiece(TakPiece.PieceType.FLAT, player2.getColour()));
            System.out.println(modelInterface.getCurrentMove());
            modelInterface.executeMove();
            System.out.println(modelInterface);

            modelInterface.chooseInitialCoordinate(new Coordinate(0, 0));
            modelInterface.moveTo(new Coordinate(1, 0));
            modelInterface.grabStack(2);
            System.out.println(modelInterface.getCurrentMove());
            modelInterface.executeMove();
            System.out.println(modelInterface);

            modelInterface.moveTo(new Coordinate(2, 0));
            modelInterface.grabStack(1);
            System.out.println(modelInterface.getCurrentMove());
            modelInterface.executeMove();
            modelInterface.completeMove();
            System.out.println(modelInterface);
        } catch ( Errors.InvalidMoveException e ) {
            e.printStackTrace();
        }
        // try {
        //     modelInterface.addPiece(new Coordinate(1, 1), new TakPiece(TakPiece.PieceType.CAP, player1.getColour()));
        //     modelInterface.addPiece(new Coordinate(1, 2), new TakPiece(TakPiece.PieceType.FLAT, player1.getColour()));
        //
        //     Move nextMove = new Move(New Coordinate(1, 1));
        //     newMove.setStackSize(1);
        //     ArrayList<Coordinate> = move.getValidMoves();
        //     move.moveTo(Coordinate);
        //     move.execute();
        //
        //     System.out.println(modelInterface);
        // } catch ( Errors.InvalidMoveException e ) {
        //     System.out.println(e.getMessage());
        // }
    } /* main */
}
