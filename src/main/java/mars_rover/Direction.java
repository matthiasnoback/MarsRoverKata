package mars_rover;

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Direction clockwiseRight() {
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
        }

        throw new IllegalArgumentException();
    }

    public Direction clockwiseLeft() {
        switch (this) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
        }

        throw new IllegalArgumentException();
    }
}
