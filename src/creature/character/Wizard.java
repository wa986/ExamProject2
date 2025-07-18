package creature.character;

import creature.Character;
import creature.Creature;

public class Wizard extends Character {
    private int mp;

    public Wizard(String name, int hp, int mp) {
        super(name, hp);
        this.mp = mp;
    }

    public void attack(Creature target) {
        if (mp <= 0) {
            System.out.println(getName() + "はMPが足りないため、攻撃できなかった！");
        } else {
            System.out.println(getName() + "は火の玉を放った！" + target.getName() + "に3のダメージを与えた！");
            target.setHp(target.getHp() - 3);
            this.setMp(this.getMp() - 1);
        }
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        if (mp < 0) {
            this.mp = 0;
        } else {
            this.mp = mp;
        }
    }
}