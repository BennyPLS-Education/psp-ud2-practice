package horse.modifiers;

import horse.Horse;

public class PooModifier extends Modifier {
    
    public PooModifier() {
        super("Poo", 0.01);
    }
    
    @Override
    protected void modify(Horse horse) {
        horse.modVelocity(-horse.getVelocity());
    }
}
