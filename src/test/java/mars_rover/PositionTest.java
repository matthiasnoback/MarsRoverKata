package mars_rover;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
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
                Position.withCoordinates(1, 1).nextPosition(Direction.NORTH)
        );
    }

    @Test
    public void next_position_in_eastern_direction() {
        assertEquals(
                Position.withCoordinates(2, 1),
                Position.withCoordinates(1, 1).nextPosition(Direction.EAST)
        );
    }
    @Test
    public void next_position_in_southern_direction() {
        assertEquals(
                Position.withCoordinates(1, 2),
                Position.withCoordinates(1, 1).nextPosition(Direction.SOUTH)
        );
    }
    @Test
    public void next_position_in_western_direction() {
        assertEquals(
                Position.withCoordinates(0, 1),
                Position.withCoordinates(1, 1).nextPosition(Direction.WEST)
        );
    }

    @Test
    public void previous_position_in_northern_direction() {
        assertEquals(
                Position.withCoordinates(1, 2),
                Position.withCoordinates(1, 1).previousPosition(Direction.NORTH)
        );
    }

    @Test
    public void previous_position_in_eastern_direction() {
        assertEquals(
                Position.withCoordinates(0, 1),
                Position.withCoordinates(1, 1).previousPosition(Direction.EAST)
        );
    }

    @Test
    public void previous_position_in_southern_direction() {
        assertEquals(
                Position.withCoordinates(1, 0),
                Position.withCoordinates(1, 1).previousPosition(Direction.SOUTH)
        );
    }

    @Test
    public void previous_position_in_western_direction() {
        assertEquals(
                Position.withCoordinates(2, 1),
                Position.withCoordinates(1, 1).previousPosition(Direction.WEST)
        );
    }
}
