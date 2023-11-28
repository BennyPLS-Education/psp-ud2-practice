package road.obstacle;

import horse.Horse;

public class Rock extends Obstacle {
    public Rock() {
        super("Rock", 0.7, "\uD83D\uDDFF");
    }
    
    @Override
    protected void effect(Horse horse) {
        horse.modVelocity(-20);
    }
}
