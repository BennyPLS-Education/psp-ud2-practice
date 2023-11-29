package road.obstacle;

import horse.Horse;
import road.Road;

public class BlueShell extends Obstacle {
    public BlueShell() {
        super("Blue Shell", 0.1, "\uD83E\uDD4F");
    }
    
    @Override
    protected void effect(Horse horse, Road road) {
        for (Horse other : road.getHorsesByPosition()) {
            if (other != horse && !(other.isFinished() || other.isDead())) {
                other.kill();
                break;
            }
        }
    }
}
