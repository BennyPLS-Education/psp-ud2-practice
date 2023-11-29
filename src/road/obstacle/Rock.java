package road.obstacle;

import horse.Horse;
import road.Road;

public class Rock extends Obstacle {
    public Rock() {
        super("Rock", 0.7, "\uD83D\uDDFF");
    }
    
    @Override
    protected void effect(Horse horse, Road road) {
        horse.modVelocity(-20);
        
        if (random.nextDouble() < 0.01) {
            horse.kill();
        }
    }
}
