package model;

public class Burglar extends Entity {

    Burglar(String role, int health, int damage) {
        super(role, health, damage);
    }
    public Burglar(int damage, int health){
        super(damage,health);
    }
}
