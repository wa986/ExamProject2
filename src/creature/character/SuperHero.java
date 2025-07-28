package creature.character;

import creature.Creature;
import weapon.Weapon;

public class SuperHero extends Hero {

    public SuperHero(Hero hero) {
        super(hero.getName(), hero.getHp() - 30, hero.getWeapon());
    }

    @Override
    public void attack(Creature target) {
        Weapon weapon = getWeapon();
        int damage = (int)(weapon.getDamage() * 2.5);
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + "は超パワーで攻撃！" + weapon.getName() + weapon.attackMessage() +
                target.getName() + "に" + damage + "のダメージを与えた！");
    }
}
