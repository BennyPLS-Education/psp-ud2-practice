package horse.modifiers;

import horse.Horse;

public class FartModifier extends Modifier {
    public FartModifier() {
        super("Fart", 0.01);
    }
    
    @Override
    protected void modify(Horse horse) {
        horse.modVelocity(horse.getVelocity() * 0.1);
    }
}
