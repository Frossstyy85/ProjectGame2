package Abstract;

public class Entity {

    protected String role;
    protected int health;
    protected int damage;

    Entity(String role, int health, int damage){
        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    public Entity(int damage, int health){
        this.damage = damage;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getRole() {
        return role;
    }

    public void punch(Entity toPunch){
        toPunch.takeHit(this.damage);
    }

    private void takeHit(int damage){
        this.health -= damage;
    }

    public Boolean isConscious(){
        return this.health > 0;
    }

    public void increaseDamage(int damage){
        this.damage += damage;
    }

    public static Resident createResident(){
        return new Resident("Resident",12,3);
    }
    public static Burglar createBurglar(){
        return new Burglar("Intruder",12,4);
    }






}
