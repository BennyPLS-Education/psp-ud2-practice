package road.obstacle;

import horse.Horse;
import road.Road;

public class SpringTrap extends Obstacle {
    public SpringTrap() {
        super("Spring Trap", 0.5, "⏫");
    }
    
    @Override
    protected void effect(Horse horse, Road road) {
        horse.modVelocity(-(horse.getVelocity() * 3));
    }
}
