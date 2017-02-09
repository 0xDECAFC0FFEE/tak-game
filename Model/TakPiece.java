package Model;
import Model.*;

public class TakPiece {
    public enum PieceType {CAP, FLAT, WALL};
    private PieceType type;
    private ModelInterface.Colour colour;

    public TakPiece(PieceType type, ModelInterface.Colour colour) {
        this.type = type;
        this.colour = colour;
    }

    public PieceType getType() {
        return type;
    }

    public ModelInterface.Colour getColour() {
        return colour;
    }

    public String toString() {
        String output = "(";

        switch ( colour ) {
            case BLACK: output += "B";
                break;
            case YELLOW: output += "Y";
                break;
            default: output += "UNKNOWN COLOUR";
        }
        switch ( type ) {
            case CAP: output += "C)";
                break;
            case WALL: output += "W)";
                break;
            case FLAT: output += "F)";
                break;
            default: output += "UNKNOWN PIECE TYPE)";
        }
        return output;
    }
}
