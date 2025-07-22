package weapon;

public abstract class Weapon {
    private String name;
    private int damage;
    private int cost = 0;

    public Weapon(String name, int damage, int cost) {
        this.name = name;
        this.damage = damage;
    }
    public String  attackMessage(){

    }
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getCost() {
        return cost;
    }
}
