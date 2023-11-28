package obstacle;

import horse.Horse;

public class Banana extends Obstacle {
    public Banana() {
        super("Banana", 0.7, "\uD83C\uDF4C");
    }
    
    @Override
    protected void effect(Horse horse) {
        horse.setVelocityModifier(horse.getVelocityModifier() - 0.1);
    }
}
