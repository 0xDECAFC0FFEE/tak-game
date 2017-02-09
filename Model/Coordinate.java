package Model;
import Model.*;

public class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean isAdjacent(Coordinate otherCoordinate) {
        return Math.abs((otherCoordinate.x - x) + (otherCoordinate.y - y)) == 1;
    }
}
