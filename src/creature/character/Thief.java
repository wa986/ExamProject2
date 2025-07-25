package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Thief extends Character {
    private boolean guard;

    public Thief(String name,int hp,Weapon weapon) {
        super(name, hp,weapon);
        this.guard = false;
    }

    public boolean isGuard() {
        return guard;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }

    public void attack(Creature target) {
        Weapon weapon = getWeapon();
        if (weapon == null) {
            System.out.println("武器を装備していません！");
            return;
        }
        int damage = weapon.getDamage() * 2;
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + "は素早く2回攻撃した！" + getWeapon().getName() + getWeapon().attackMessage() +
                target.getName() + "に" + damage + "のダメージを与えた！");
    }

    public void guard() {
        this.guard = true;
    }

    public void setHp(int hp) {
        if (guard) {
            System.out.println("しかし、" + getName() + "は攻撃を回避し、ダメージが入らなかった！");
            guard = false;
        } else {
            super.setHp(hp);
        }
    }
}
