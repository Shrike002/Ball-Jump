package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PlatformTest {

    @Test
    void testPlatformInitialization() {
        Platform platform = new Platform(100, 200, 60, 10, PlatformType.NORMAL);
        assertEquals(100, platform.getX());
        assertEquals(200, platform.getY());
        assertEquals(PlatformType.NORMAL, platform.getType());
    }

    @Test
    void testDeactivatePlatform() {
        Platform platform = new Platform(100, 200, 60, 10, PlatformType.PURPLE);
        platform.deactivate();
        assertFalse(platform.isActive(), "Platform should be inactive after deactivation");
    }

    @Test
    void testUpdatePosition() {
        Platform platform = new Platform(100, 200, 60, 10, PlatformType.NORMAL);
        platform.update(50);
        assertEquals(250, platform.getY(), "Platform Y position should update with offset");
    }
}
