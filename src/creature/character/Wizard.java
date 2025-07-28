package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Wizard extends Character {
    private int mp;

    public Wizard(String name, int hp, Weapon weapon, int mp) {
        super(name, hp, weapon);
        this.mp = mp;
    }

    @Override
    public void attack(Creature target) {
        Weapon weapon = getWeapon();
        int damage = 3;
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + "は石を投げた！" + target.getName() + "に" + damage + "のダメージを与えた！");
    }

    public void magic(Creature target) {
        Weapon weapon = getWeapon();
        int cost = weapon.getCost();
        int damage = weapon.getDamage();

        if (mp < cost) {
            System.out.println(getName() + "はMPが足りず魔法が使えなかった！");
            return;
        }

        target.setHp(target.getHp() - damage);
        mp -= cost;
        System.out.println(getName() + "は" + weapon.getName() + weapon.attackMessage() +
                target.getName() + "に" + damage + "の魔法ダメージを与えた！（MP - " + cost + "）");
    }

    public void showStatus() {
        System.out.println(getName() + " HP:" + getHp() + " / MP:" + mp);
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = Math.max(mp, 0);
    }
}
