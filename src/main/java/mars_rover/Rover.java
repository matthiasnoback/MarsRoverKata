package mars_rover;

public class Rover {
    private Position position;
    private Direction direction;
    private final int maxX;
    private final int maxY;
    private ObstacleMap obstacleMap;

    public Rover(Position position, Direction direction, int maxX, int maxY, ObstacleMap obstacleMap) {
        this.position = position;
        this.direction = direction;
        this.maxX = maxX;
        this.maxY = maxY;
        this.obstacleMap = obstacleMap;
    }

    public void turnRight() {
        this.direction = direction.clockwiseRight();
    }

    public void turnLeft() {
        this.direction = direction.clockwiseLeft();
    }

    public void moveForward() {
        Position nextPosition = this.position.nextPosition(this.direction, this.maxX, this.maxY);

        if (this.obstacleMap.hasObstacleAt(nextPosition)) {
            throw CanNotMoveToPosition.obstacleEncountered(nextPosition);
        }

        this.position = nextPosition;
    }

    public void moveBackward() {
        Position previousPosition = this.position.previousPosition(this.direction, this.maxX, this.maxY);

        if (this.obstacleMap.hasObstacleAt(previousPosition)) {
            throw CanNotMoveToPosition.obstacleEncountered(previousPosition);
        }

        this.position = previousPosition;
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
