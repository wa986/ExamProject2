package creature;

public abstract class Monster implements Creature {
    private String name;
    private int hp;
    private char suffix;

    public Monster(String name, char suffix, int hp) {
        if (hp < 0) {
            throw new IllegalArgumentException("初期設定に誤りがあるため、キャラクターを作成できませんでした");
        }
        this.name = name;
        this.hp = hp;
        this.suffix = suffix;
    }

    public final boolean isAlive() {
        return this.hp > 0;
    }

    public void showStatus() {
        System.out.println(this.name + this.suffix + "：HP " + this.hp);
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public char getSuffix() {
        return this.suffix;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    // 逃げるメソッド
    public void run() {
        System.out.println(getName() + getSuffix() + "は逃げ出した！");
    }

    // 倒れた時のメソッド
    public void die() {
        System.out.println(this.name + this.suffix + "は倒れた！");
    }
}
