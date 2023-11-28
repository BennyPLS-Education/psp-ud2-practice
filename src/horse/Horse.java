package horse;

import horse.modifiers.FartModifier;
import horse.modifiers.Modifier;
import horse.modifiers.PooModifier;
import race.Race;
import race.Road;
import ui.ASCII;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static utils.ThreadUtility.waitTime;

public class Horse extends Thread {
    
    private static final Random random = new Random();
    private static final List<Modifier> horseModifiersAfter = List.of(
        new PooModifier(),
        new FartModifier()
    );
    private static final List<Modifier> horseModifiersBefore = List.of(
    
    );
    
    public ASCII color;
    
    /* The road where the horse is running */
    private final Road road;
    /* The race he is running */
    private final Race race;
    
    private long finishedAt = -1;
    
    public boolean isWaiting = false;
    
    /* The velocity is represented in km per hour */
    private static final int MAXIMUM_VELOCITY = 70;
    private static final int MINIMUM_VELOCITY = 15;
    
    private double lastVariation = 0;
    private double velocity = 50;
    private double velocityModifier = 1;
    private double velocityModifierBase = 1;
    
    public Horse(String name, Road road, Race race, ASCII color) {
        super(name);
        this.road = road;
        this.race = race;
        this.color = color;
        road.add(this);
    }
    
    public void run() {
        
        waitWrapper();
        
        while (true) {
            road.update(this);
            velocityModifier = velocityModifierBase;
            
            if (isInterrupted()) {
                break;
            }
            
            waitWrapper();
        }
        
        finishedAt = System.currentTimeMillis();
    }
    
    /**
     * This method is used to wrap the wait() method for the race object.
     * <p><div>
     * It synchronizes on the race object and then calls the wait() method,
     * waits until race thread notifies it
     */
    private void waitWrapper() {
        try {
            isWaiting = true;
            synchronized (race) {
                race.wait();
            }
            isWaiting = false;
        } catch (InterruptedException ignored) {}
    }
    
    public void modVelocity(double velocity) {
        this.velocity += velocity;
    }
    
    public void velocityVariation() {
        double stdSpeedVariation = random.nextDouble(-5.0, 6.0);
        
        horseModifiersBefore.forEach(modifier -> modifier.affect(this));
        
        var speedVariation = stdSpeedVariation * velocityModifier;
        velocity += speedVariation;
        lastVariation = speedVariation;
        
        restrictVelocity();
        
        horseModifiersAfter.forEach(modifier -> modifier.affect(this));
    }
    
    private void restrictVelocity() {
        if (velocity > MAXIMUM_VELOCITY) {
            velocity = MAXIMUM_VELOCITY;
        }
        
        if (velocity < MINIMUM_VELOCITY) {
            velocity = MINIMUM_VELOCITY;
        }
    }
    
    public boolean isStopped() {
        return velocity == 0;
    }
    
    public double getVelocity() {
        return velocity;
    }
    
    public double getKmPerSecond() {
        return velocity / 3600.0;
    }
    
    public boolean isFinished() {
        return finishedAt != -1;
    }
    
    public Date getFinishedAt() {
        return new Date(finishedAt);
    }
    
    public double getLastVariation() {
        return lastVariation;
    }
    
    @Override
    public String toString() {
        return getName();
    }
    
    
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    
    public double getVelocityModifier() {
        return velocityModifier;
    }
    
    public void setVelocityModifier(double velocityModifier) {
        this.velocityModifier = velocityModifier;
    }
    
    public double getVelocityModifierBase() {
        return velocityModifierBase;
    }
    
    public void setVelocityModifierBase(double velocityModifierBase) {
        this.velocityModifierBase = velocityModifierBase;
    }
}