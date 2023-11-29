package road.obstacle;

import horse.Horse;
import road.Road;

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
            new SpringTrap(),
            new BlueShell()
        );
        
        return obstacles.get(random.nextInt(obstacles.size()));
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
    
    public void affect(Horse horse, Road road) {
        if (random.nextDouble() < getProbability()) {
            effect(horse, road);
        }
    }
    
    protected abstract void effect(Horse horse, Road road);
}
