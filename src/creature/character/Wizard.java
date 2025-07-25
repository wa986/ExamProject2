package creature.character;

import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Wizard extends Character {
    private int mp;
    private int hp;
    public Wizard(String name, int hp, Weapon weapon, int mp) {
        super(name, hp,weapon);
        this.mp = mp;
    }

    public void magic(Creature target) {
        //自分のmpをweapon.cost減らす
        mp =getWeapon().getCost();

        //自分のmpが0の場合「MPが足りない！」と表示し、処理を中断する
        if (mp <= 0) {
            System.out.println(getName() + "はMPが足りない！");
            //対象 (target) のhpをweapon.damage減らす
            hp = target.getHp() - getWeapon().getDamage();
            //表示内容をattackMessage()で受け取る
            getWeapon().attackMessage();
        } else {
            System.out.println(getName() + "は火の玉を放った！" + target.getName() + "に3のダメージを与えた！");
            target.setHp(target.getHp() - 3);
            this.setMp(this.getMp() - 1);
        }
    }
    public void attack(Creature target){
        target.setHp(target.getHp() - 3);
    }
    public void showStatus() {
        System.out.println(getName() + "HP:" + hp + "/ MP:" + mp);
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