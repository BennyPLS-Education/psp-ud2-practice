package obstacle;

import horse.Horse;

public class SpringTrap extends Obstacle {
    public SpringTrap() {
        super("Spring Trap", 0.5, "⏫");
    }
    
    @Override
    protected void effect(Horse horse) {
        horse.modVelocity(-(horse.getVelocity() * 4));
    }
}
