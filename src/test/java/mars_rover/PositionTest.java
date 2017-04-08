package mars_rover;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    private int maxX = 5;
    private int maxY = 10;

    @Test
    public void it_can_be_created_with_coordinates() {
        int x = 1;
        int y = 2;
        final Position position = Position.withCoordinates(x, y);
        assertEquals(x, position.x());
        assertEquals(y, position.y());
    }

    @Test
    public void hash_code_is_equal_if_coordinates_are_equal() {
        assertEquals(Position.withCoordinates(1, 2).hashCode(), Position.withCoordinates(1, 2).hashCode());
    }

    @Test
    public void hash_code_is_unequal_if_coordinates_are_unequal() {
        assertNotEquals(Position.withCoordinates(2, 1).hashCode(), Position.withCoordinates(1, 2).hashCode());
    }

    @Test
    public void next_position_in_northern_direction() {
        assertEquals(
                Position.withCoordinates(1, 0),
                Position.withCoordinates(1, 1).nextPosition(Direction.NORTH, this.maxX, this.maxY)
        );

        // continue on the other side of the grid
        assertEquals(
                Position.withCoordinates(1, this.maxY),
                Position.withCoordinates(1, 0).nextPosition(Direction.NORTH, this.maxX, this.maxY)
        );
    }

    @Test
    public void next_position_in_eastern_direction() {
        assertEquals(
                Position.withCoordinates(2, 1),
                Position.withCoordinates(1, 1).nextPosition(Direction.EAST, this.maxX, this.maxY)
        );

        // continue on the other side of the grid
        assertEquals(
                Position.withCoordinates(0, 1),
                Position.withCoordinates(this.maxX, 1).nextPosition(Direction.EAST, this.maxX, this.maxY)
        );
    }
    @Test
    public void next_position_in_southern_direction() {
        assertEquals(
                Position.withCoordinates(1, 2),
                Position.withCoordinates(1, 1).nextPosition(Direction.SOUTH, this.maxX, this.maxY)
        );

        // continue on the other side of the grid
        assertEquals(
                Position.withCoordinates(1, 0),
                Position.withCoordinates(1, this.maxY).nextPosition(Direction.SOUTH, this.maxX, this.maxY)
        );
    }
    @Test
    public void next_position_in_western_direction() {
        assertEquals(
                Position.withCoordinates(0, 1),
                Position.withCoordinates(1, 1).nextPosition(Direction.WEST, this.maxX, this.maxY)
        );

        // continue on the other side of the grid
        assertEquals(
                Position.withCoordinates(this.maxX, 1),
                Position.withCoordinates(0, 1).nextPosition(Direction.WEST, this.maxX, this.maxY)
        );
    }

    @Test
    public void previous_position_in_northern_direction() {
        assertEquals(
                Position.withCoordinates(1, 2),
                Position.withCoordinates(1, 1).previousPosition(Direction.NORTH, this.maxX, this.maxY)
        );

        // continue on the other side of the grid
        assertEquals(
                Position.withCoordinates(1, 0),
                Position.withCoordinates(1, this.maxY).previousPosition(Direction.NORTH, this.maxX, this.maxY)
        );
    }

    @Test
    public void previous_position_in_eastern_direction() {
        assertEquals(
                Position.withCoordinates(0, 1),
                Position.withCoordinates(1, 1).previousPosition(Direction.EAST, this.maxX, this.maxY)
        );

        // continue on the other side of the grid
        assertEquals(
                Position.withCoordinates(this.maxX, 1),
                Position.withCoordinates(0, 1).previousPosition(Direction.EAST, this.maxX, this.maxY)
        );
    }

    @Test
    public void previous_position_in_southern_direction() {
        assertEquals(
                Position.withCoordinates(1, 0),
                Position.withCoordinates(1, 1).previousPosition(Direction.SOUTH, this.maxX, this.maxY)
        );

        // continue on the other side of the grid
        assertEquals(
                Position.withCoordinates(1, this.maxY),
                Position.withCoordinates(1, 0).previousPosition(Direction.SOUTH, this.maxX, this.maxY)
        );
    }

    @Test
    public void previous_position_in_western_direction() {
        assertEquals(
                Position.withCoordinates(2, 1),
                Position.withCoordinates(1, 1).previousPosition(Direction.WEST, this.maxX, this.maxY)
        );

        // continue on the other side of the grid
        assertEquals(
                Position.withCoordinates(0, 1),
                Position.withCoordinates(this.maxX, 1).previousPosition(Direction.WEST, this.maxX, this.maxY)
        );
    }
}
