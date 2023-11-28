package horse.modifiers;

import horse.Horse;

import java.util.Random;

public abstract class Modifier {
    protected final static Random random = new Random();
    private final String name;
    private final double probability;
    
    protected Modifier(String name, double probability) {
        this.name = name;
        this.probability = probability;
    }
    
    public String getName() {
        return name;
    }
    
    public double getProbability() {
        return probability;
    }
    
    public void affect(Horse horse) {
        if (random.nextDouble() < getProbability()) {
            modify(horse);
        }
    }
    
    
    protected abstract void modify(Horse horse);
    
}
