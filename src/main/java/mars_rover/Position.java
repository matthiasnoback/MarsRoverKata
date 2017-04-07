package mars_rover;

import java.util.Objects;

import static mars_rover.Direction.*;
import static mars_rover.Direction.SOUTH;

public class Position {
    private final int x;
    private final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    static Position withCoordinates(int x, int y) {
        return new Position(x, y);
    }

    int x() {
        return this.x;
    }

    int y() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass())
                && ((Position) obj).x == this.x
                && ((Position) obj).y == this.y;
    }

    public Position nextPosition(Direction direction) {
        switch (direction) {
            case NORTH:
                return this.withY(this.y - 1);
            case EAST:
                return this.withX(this.x + 1);
            case SOUTH:
                return this.withY(this.y + 1);
            case WEST:
                return this.withX(this.x - 1);
            default:
                throw new IllegalArgumentException();
        }
    }

    public Position previousPosition(Direction direction) {
        switch (direction) {
            case NORTH:
                return this.withY(this.y + 1);
            case EAST:
                return this.withX(this.x - 1);
            case SOUTH:
                return this.withY(this.y - 1);
            case WEST:
                return this.withX(this.x + 1);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    private Position withX(int x) {
        return Position.withCoordinates(x, this.y);
    }

    private Position withY(int y) {
        return Position.withCoordinates(this.x, y);
    }
}
