package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Hero extends Character {

    public Hero(String name, int hp, Weapon weapon) {
        super(name, hp, weapon);
    }

    @Override
    public void attack(Creature target) {
        Weapon weapon = getWeapon();
        int damage = weapon.getDamage();
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + "は" + weapon.getName() + weapon.attackMessage() +
                target.getName() + "に" + damage + "のダメージを与えた！");
    }
}