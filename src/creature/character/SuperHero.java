package creature.character;

import creature.Creature;
import weapon.Weapon;

public class SuperHero extends Hero {
    public SuperHero(Hero hero) {
        super(hero.getName(), hero.getHp());
        setWeapon(hero.getWeapon());
        setHp(getHp() - 30);
        System.out.println(hero.getName() + "はスーパーヒーローになった！");
    }

    @Override
    public void attack(Creature target) {
        int damage = (int) (getWeapon().getDamage() * 2.5);
        System.out.println(getName() + "は" + getWeapon().getName() + getWeapon().attackMessage() + target.getName() + "に" + damage + "のダメージを与えた！");
        target.setHp(target.getHp() - damage);
    }
}