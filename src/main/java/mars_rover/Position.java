package mars_rover;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    private Position(int x, int y) {
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

    Position nextPosition(Direction direction, int maxX, int maxY) {
        switch (direction) {
            case NORTH:
                return this.withY(this.y > 0 ? this.y - 1 : maxY);
            case EAST:
                return this.withX(this.x < maxX ? this.x + 1 : 0);
            case SOUTH:
                return this.withY(this.y < maxY ? this.y + 1 : 0);
            case WEST:
                return this.withX(this.x > 0 ? this.x - 1 : maxX);
            default:
                throw new IllegalArgumentException();
        }
    }

    Position previousPosition(Direction direction, int maxX, int maxY) {
        return this.nextPosition(direction.opposite(), maxX, maxY);
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
