package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Thief extends Character {
    private boolean guard;

    public Thief(String name, int hp, Weapon weapon) {
        super(name, hp, weapon);
        this.guard = false;
    }

    public void guard() {
        this.guard = true;
        System.out.println(getName() + "は身構えて防御の体勢に入った！");
    }

    public boolean isGuard() {
        return guard;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }

    @Override
    public void attack(Creature target) {
        Weapon weapon = getWeapon();
        int damage = weapon.getDamage() * 2;
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + "は素早く2回攻撃！" + weapon.getName() + weapon.attackMessage() +
                target.getName() + "に" + damage + "のダメージを与えた！");
    }

    @Override
    public void setHp(int hp) {
        if (guard) {
            System.out.println(getName() + "は攻撃をかわしてダメージを無効化した！");
            guard = false;
        } else {
            super.setHp(hp);
        }
    }
}
