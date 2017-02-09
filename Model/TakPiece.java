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
        String output = "";

        switch ( colour ) {
            case BLACK: output += "\033[1;34m";
                break;
            case YELLOW: output += "\033[1;33m";
                break;
            default: output += "UNKNOWN COLOUR ";
        }
        switch ( type ) {
            case CAP: output += "C\033[0m";
                break;
            case WALL: output += "W\033[0m";
                break;
            case FLAT: output += "F\033[0m";
                break;
            default: output += "UNKNOWN PIECE TYPE";
        }
        return output;
    }
}
