package obstacle;

import horse.Horse;

import java.util.List;
import java.util.Random;

public abstract class Obstacle {
    protected final static Random random = new Random();
    private final String name;
    private final double probability;
    private final String symbol;
    
    protected Obstacle(String name, double probability, String symbol) {
        this.name = name;
        this.probability = probability;
        this.symbol = symbol;
    }
    
    public static Obstacle getRandomObstacle() {
        var obstacles = List.of(
            new Banana(),
            new BoostPad(),
            new Rock(),
            new SpringTrap()
        );
        
        return obstacles.get(random.nextInt(obstacles.size()));
    }
    
    public String getName() {
        return name;
    }
    
    public double getProbability() {
        return probability;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public void affect(Horse horse) {
        if (random.nextDouble(0, 1) < getProbability()) {
            effect(horse);
        }
    }
    
    protected abstract void effect(Horse horse);
}
