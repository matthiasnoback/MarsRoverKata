package mars_rover;

class CanNotMoveToPosition extends RuntimeException {
    CanNotMoveToPosition(String message) {
        super(message);
    }

    static CanNotMoveToPosition obstacleEncountered(Position position) {
        return new CanNotMoveToPosition("Can't move to position " + position + ": an obstacle is in the way");
    }
}
