package mars_rover;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoverTest {

    private int maxX;
    private int maxY;
    private ObstacleMap obstacleMap;

    @Before
    public void setUp() throws Exception {
        obstacleMap = new ObstacleMap();
    }

    @Test
    public void turning_right_means_changing_direction_clockwise_only() {
        Position initialPosition = Position.withCoordinates(1, 2);
        Rover rover = new Rover(initialPosition, Direction.NORTH, 1, 1, obstacleMap);

        rover.turnRight();
        assertEquals(Direction.EAST, rover.currentDirection());
        assertEquals(initialPosition, rover.currentPosition());

        rover.turnRight();
        assertEquals(Direction.SOUTH, rover.currentDirection());
        assertEquals(initialPosition, rover.currentPosition());

        rover.turnRight();
        assertEquals(Direction.WEST, rover.currentDirection());
        assertEquals(initialPosition, rover.currentPosition());

        rover.turnRight();
        assertEquals(Direction.NORTH, rover.currentDirection());
        assertEquals(initialPosition, rover.currentPosition());
    }

    @Test
    public void turning_left_means_changing_direction_counter_clockwise_only() {
        Position initialPosition = Position.withCoordinates(1, 2);
        Rover rover = new Rover(initialPosition, Direction.NORTH, 1, 1, obstacleMap);

        rover.turnLeft();
        assertEquals(Direction.WEST, rover.currentDirection());
        assertEquals(initialPosition, rover.currentPosition());

        rover.turnLeft();
        assertEquals(Direction.SOUTH, rover.currentDirection());
        assertEquals(initialPosition, rover.currentPosition());

        rover.turnLeft();
        assertEquals(Direction.EAST, rover.currentDirection());
        assertEquals(initialPosition, rover.currentPosition());

        rover.turnLeft();
        assertEquals(Direction.NORTH, rover.currentDirection());
        assertEquals(initialPosition, rover.currentPosition());
    }

    @Test
    public void moving_forward_means_going_to_the_next_position_in_the_current_direction() {
        Position initialPosition = Position.withCoordinates(1, 1);

        maxX = 1;
        maxY = 1;
        Rover rover = new Rover(initialPosition, Direction.NORTH, this.maxX, this.maxY, obstacleMap);
        rover.moveForward();
        assertEquals(initialPosition.nextPosition(Direction.NORTH, this.maxX, this.maxY), rover.currentPosition());

        rover = new Rover(initialPosition, Direction.WEST, 1, 1, obstacleMap);
        rover.moveForward();
        assertEquals(initialPosition.nextPosition(Direction.WEST, this.maxX, this.maxY), rover.currentPosition());

        rover = new Rover(initialPosition, Direction.SOUTH, this.maxX, this.maxY, obstacleMap);
        rover.moveForward();
        assertEquals(initialPosition.nextPosition(Direction.SOUTH, this.maxX, this.maxY), rover.currentPosition());

        rover = new Rover(initialPosition, Direction.EAST, this.maxX, this.maxY, obstacleMap);
        rover.moveForward();
        assertEquals(initialPosition.nextPosition(Direction.EAST, this.maxX, this.maxY), rover.currentPosition());
    }

    @Test
    public void moving_backward_means_going_to_the_previous_position_in_the_current_direction() {
        Position initialPosition = Position.withCoordinates(1, 1);

        Rover rover = new Rover(initialPosition, Direction.NORTH, this.maxX, this.maxY, obstacleMap);
        rover.moveBackward();
        assertEquals(initialPosition.previousPosition(Direction.NORTH, this.maxX, this.maxY), rover.currentPosition());

        rover = new Rover(initialPosition, Direction.WEST, this.maxX, this.maxY, obstacleMap);
        rover.moveBackward();
        assertEquals(initialPosition.previousPosition(Direction.WEST, this.maxX, this.maxY), rover.currentPosition());

        rover = new Rover(initialPosition, Direction.SOUTH, this.maxX, this.maxY, obstacleMap);
        rover.moveBackward();
        assertEquals(initialPosition.previousPosition(Direction.SOUTH, this.maxX, this.maxY), rover.currentPosition());

        rover = new Rover(initialPosition, Direction.EAST, this.maxX, this.maxY, obstacleMap);
        rover.moveBackward();
        assertEquals(initialPosition.previousPosition(Direction.EAST, this.maxX, this.maxY), rover.currentPosition());
    }

    @Test
    public void it_accepts_multiple_character_based_commands() {
        Rover rover = new Rover(Position.withCoordinates(1, 2), Direction.NORTH, 5, 5, obstacleMap);
        rover.processCommands('f', 'r', 'f', 'f', 'l', 'l', 'b', 'f');
        assertEquals(Position.withCoordinates(3, 1), rover.currentPosition());
        assertEquals(Direction.WEST, rover.currentDirection());
    }

    @Test
    public void when_driving_off_the_grid_it_continues_on_the_opposite_side() {
        // move north, end up at the far south end
        int xDoesNotChange, yDoesNotChange;
        int minX = 0;
        int minY = 0;
        int maxX = 5;
        int maxY = 10;

        // move north, end up at the far south end
        xDoesNotChange = 5;
        Rover rover = new Rover(Position.withCoordinates(xDoesNotChange, minY), Direction.NORTH, maxX, maxY, obstacleMap);
        rover.moveForward();
        assertEquals(Position.withCoordinates(xDoesNotChange, maxY), rover.currentPosition());

        // move east, end up at far west end
        yDoesNotChange = 5;
        rover = new Rover(Position.withCoordinates(maxX, yDoesNotChange), Direction.EAST, maxX, maxY, obstacleMap);
        rover.moveForward();
        assertEquals(Position.withCoordinates(minX, yDoesNotChange), rover.currentPosition());

        // move south, end up at far north end
        xDoesNotChange = 5;
        rover = new Rover(Position.withCoordinates(xDoesNotChange, maxY), Direction.SOUTH, maxX, maxY, obstacleMap);
        rover.moveForward();
        assertEquals(Position.withCoordinates(xDoesNotChange, minY), rover.currentPosition());

        // move west, end up at far east end
        yDoesNotChange = 5;
        rover = new Rover(Position.withCoordinates(minX, yDoesNotChange), Direction.WEST, maxX, maxY, obstacleMap);
        rover.moveForward();
        assertEquals(Position.withCoordinates(maxX, yDoesNotChange), rover.currentPosition());
    }

    @Test(expected = CanNotMoveToPosition.class)
    public void it_fails_to_move_forward_if_an_obstacle_is_in_the_way() {
        ObstacleMap obstacleMap = new ObstacleMap();
        obstacleMap.registerObstacleAt(Position.withCoordinates(3, 2));
        Rover rover = new Rover(Position.withCoordinates(2, 2), Direction.EAST, 10, 10, obstacleMap);

        rover.moveForward();
    }

    @Test(expected = CanNotMoveToPosition.class)
    public void it_fails_to_move_backward_if_an_obstacle_is_in_the_way() {
        ObstacleMap obstacleMap = new ObstacleMap();
        obstacleMap.registerObstacleAt(Position.withCoordinates(1, 2));
        Rover rover = new Rover(Position.withCoordinates(2, 2), Direction.EAST, 10, 10, obstacleMap);

        rover.moveBackward();
    }
}
