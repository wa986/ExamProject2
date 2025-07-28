package creature.character;

import creature.Creature;
import creature.Character;
import weapon.Sword;

public class Hero extends Character {

    public Hero(String name, int hp) {
        super(name, hp,new Sword());
    }

    @Override
    public void attack(Creature target) {
        System.out.println(getName() + "は" + getWeapon().getName() + getWeapon().attackMessage() + target.getName() + "に" + getWeapon().getDamage() + "のダメージを与えた！");
        target.setHp(target.getHp() - getWeapon().getDamage());
    }
}