package mars_rover;

import java.util.HashMap;

class ObstacleMap {
    private HashMap<Position, Boolean> obstacles = new HashMap<>();

    void registerObstacleAt(Position position) {
        this.obstacles.put(position, true);
    }

    boolean hasObstacleAt(Position position) {
        return this.obstacles.getOrDefault(position, false);
    }
}
