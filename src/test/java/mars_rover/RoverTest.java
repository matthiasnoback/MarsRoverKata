package mars_rover;

import javafx.geometry.Pos;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoverTest {
    @Test
    public void turning_right_means_changing_direction_clockwise_only() {
        Position initialPosition = Position.withCoordinates(1, 2);
        Rover rover = new Rover(initialPosition, Direction.NORTH);

        assertEquals(Direction.EAST, rover.turnRight());
        assertEquals(initialPosition, rover.currentPosition());

        assertEquals(Direction.SOUTH, rover.turnRight());
        assertEquals(initialPosition, rover.currentPosition());

        assertEquals(Direction.WEST, rover.turnRight());
        assertEquals(initialPosition, rover.currentPosition());

        assertEquals(Direction.NORTH, rover.turnRight());
        assertEquals(initialPosition, rover.currentPosition());
    }

    @Test
    public void turning_left_means_changing_direction_counter_clockwise_only() {
        Position initialPosition = Position.withCoordinates(1, 2);
        Rover rover = new Rover(initialPosition, Direction.NORTH);

        assertEquals(Direction.WEST, rover.turnLeft());
        assertEquals(initialPosition, rover.currentPosition());

        assertEquals(Direction.SOUTH, rover.turnLeft());
        assertEquals(initialPosition, rover.currentPosition());

        assertEquals(Direction.EAST, rover.turnLeft());
        assertEquals(initialPosition, rover.currentPosition());

        assertEquals(Direction.NORTH, rover.turnLeft());
        assertEquals(initialPosition, rover.currentPosition());
    }

    @Test
    public void moving_forward_means_going_to_the_next_position_in_the_current_direction() {
        Position initialPosition = Position.withCoordinates(1, 1);

        Rover rover = new Rover(initialPosition, Direction.NORTH);
        assertEquals(initialPosition.nextPosition(Direction.NORTH), rover.moveForward());

        rover = new Rover(initialPosition, Direction.WEST);
        assertEquals(initialPosition.nextPosition(Direction.WEST), rover.moveForward());

        rover = new Rover(initialPosition, Direction.SOUTH);
        assertEquals(initialPosition.nextPosition(Direction.SOUTH), rover.moveForward());

        rover = new Rover(initialPosition, Direction.EAST);
        assertEquals(initialPosition.nextPosition(Direction.EAST), rover.moveForward());
    }

    @Test
    public void moving_backward_means_going_to_the_previous_position_in_the_current_direction() {
        Position initialPosition = Position.withCoordinates(1, 1);

        Rover rover = new Rover(initialPosition, Direction.NORTH);
        assertEquals(initialPosition.previousPosition(Direction.NORTH), rover.moveBackward());

        rover = new Rover(initialPosition, Direction.WEST);
        assertEquals(initialPosition.previousPosition(Direction.WEST), rover.moveBackward());

        rover = new Rover(initialPosition, Direction.SOUTH);
        assertEquals(initialPosition.previousPosition(Direction.SOUTH), rover.moveBackward());

        rover = new Rover(initialPosition, Direction.EAST);
        assertEquals(initialPosition.previousPosition(Direction.EAST), rover.moveBackward());
    }

    @Test
    public void it_accepts_multiple_character_based_commands() {
        Rover rover = new Rover(Position.withCoordinates(1, 1), Direction.NORTH);
        rover.processCommands('f', 'r', 'f', 'f', 'l', 'l', 'b', 'f');
        assertEquals(Position.withCoordinates(3,0), rover.currentPosition());
        assertEquals(Direction.WEST, rover.currentDirection());
    }
}
