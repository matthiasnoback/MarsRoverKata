package mars_rover;

import org.junit.Test;

import static org.junit.Assert.*;

public class ObstacleMapTest {
    @Test
    public void itRevealsObstacles() {
        ObstacleMap map = new ObstacleMap();
        map.registerObstacleAt(Position.withCoordinates(1, 2));
        assertTrue(map.hasObstacleAt(Position.withCoordinates(1, 2)));
        assertFalse(map.hasObstacleAt(Position.withCoordinates(2, 1)));
    }
}
