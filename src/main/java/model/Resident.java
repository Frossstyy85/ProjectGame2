package model;

public class Resident extends Entity {
    Resident(String role, int health, int damage) {
        super(role, health, damage);
    }
    public Resident(int damage, int health){
        super(damage,health);
    }
}
