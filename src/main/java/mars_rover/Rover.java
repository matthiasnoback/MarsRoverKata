package mars_rover;

public class Rover {
    private Position position;
    private Direction direction;

    public Rover(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Direction turnRight() {
        this.direction = direction.clockwiseRight();

        return this.direction;
    }

    public Direction turnLeft() {
        this.direction = direction.clockwiseLeft();

        return this.direction;
    }

    public Position moveForward() {
        this.position = this.position.nextPosition(this.direction);

        return this.position;
    }

    public Position moveBackward() {
        this.position = this.position.previousPosition(this.direction);

        return this.position;
    }

    public Position currentPosition() {
        return position;
    }

    public Direction currentDirection() {
        return direction;
    }

    public void processCommands(char... commands) {
        for (char command : commands) {
            switch (command) {
                case 'f':
                    this.moveForward();
                    break;
                case 'b':
                    this.moveBackward();
                    break;
                case 'l':
                    this.turnLeft();
                    break;
                case 'r':
                    this.turnRight();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown command: " + command);
            }
        }
    }
}
