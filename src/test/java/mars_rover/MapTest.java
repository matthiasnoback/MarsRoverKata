package mars_rover;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    @Test
    public void itRevealsObstacles() {
        Map map = new Map();
        map.registerObstacleAt(Position.withCoordinates(1, 2));
        assertTrue(map.hasObstacleAt(Position.withCoordinates(1, 2)));
        assertFalse(map.hasObstacleAt(Position.withCoordinates(2, 1)));
    }
}
