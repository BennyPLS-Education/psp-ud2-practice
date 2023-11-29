package road.obstacle;

import horse.Horse;
import road.Road;

public class Banana extends Obstacle {
    public Banana() {
        super("Banana", 0.7, "\uD83C\uDF4C");
    }
    
    @Override
    protected void effect(Horse horse, Road road) {
        horse.setVelocityModifier(horse.getVelocityModifier() - 0.2);
    }
}
