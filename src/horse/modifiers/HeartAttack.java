package horse.modifiers;

import horse.Horse;

public class HeartAttack extends Modifier {
    public HeartAttack() {
        super("Heart Attack", 0.001);
    }
    
    @Override
    protected void modify(Horse horse) {
        horse.kill();
    }
}
