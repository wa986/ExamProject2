package creature.character;

import creature.Creature;
import weapon.Weapon;

public class SuperHero extends Hero {

    public SuperHero(Hero hero) {
        super(hero.getName(), hero.getHp(), hero.getWeapon());
        setHp(getHp() - 30);
    }

    public void attack(Creature target) {
        int damage = (int)(getWeapon().getDamage() * 2.5);
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + getWeapon().getName() + getWeapon().attackMessage() +
                target.getName() + "に" + damage + "のダメージを与えた！");
    }
}