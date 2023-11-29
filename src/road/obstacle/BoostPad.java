package road.obstacle;

import horse.Horse;
import road.Road;

public class BoostPad extends Obstacle {
    public BoostPad() {
        super("Boost Pad", 1, "⏩");
    }
    
    @Override
    protected void effect(Horse horse, Road road) {
        horse.setVelocityModifier(horse.getVelocityModifierBase() + 1);
        horse.setAdditionalVelocity(horse.getAdditionalVelocity() + 10);
    }
}
