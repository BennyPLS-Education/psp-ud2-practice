package obstacle;

import horse.Horse;

public class BoostPad extends Obstacle {
    public BoostPad() {
        super("Boost Pad", 1, "⏩");
    }
    
    @Override
    protected void effect(Horse horse) {
        horse.setVelocityModifier(horse.getVelocityModifierBase() + 1);
        horse.modVelocity(10);
    }
}
