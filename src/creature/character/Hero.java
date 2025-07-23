package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Hero extends Character {

    public Hero(String name, int hp, Weapon weapon) {
        super(name, hp, weapon);
    }

    public void attack(Creature target) {
        int damage = getWeapon().getDamage();
        target.setHp(target.getHp() - damage);
        System.out.println(this.getName() + "は" + this.getWeapon().getName() + getWeapon().attackMessage() + target.getName() + "に" + damage + "のダメージを与えた！");
    }
}