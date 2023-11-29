package road.obstacle;

import horse.Horse;
import road.Road;

public class BlueShell extends Obstacle {
    public BlueShell() {
        super("Blue Shell", 0.05, "\uD83E\uDD4F");
    }
    
    @Override
    protected void effect(Horse horse, Road road) {
        road.getHorsesByPosition().getFirst().kill();
    }
}
