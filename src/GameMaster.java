import creature.Monster;
import creature.character.Hero;
import creature.character.SuperHero;
import creature.character.Thief;
import creature.character.Wizard;
import creature.Character;
import creature.monster.Goblin;
import creature.monster.Matango;
import creature.monster.Slime;

import java.io.*;
import java.util.*;
public class GameMaster {
    public static void main(String[] args){
        GameMaster game = new GameMaster();
        game.startGame();
    }

    public void startGame() {
        ArrayList<Character> party = new ArrayList<>();
        ArrayList<Monster> monsters = new ArrayList<>();
        Random rand = new Random();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Hero hero = new Hero("勇者", 100);
            Wizard wizard = new Wizard("魔法使い", 60, 30);
            Thief thief = new Thief("盗賊", 70);

            party.add(hero);
            party.add(wizard);
            party.add(thief);

            int matangoCount = 0;
            int goblinCount = 0;
            int slimeCount = 0;

            for (int i = 0; i < 5; i++) {
                int monsterType = rand.nextInt(3); // 0:マタンゴ, 1:ゴブリン, 2:スライム
                char suffix;
                int hp;

                switch (monsterType) {
                    case 0:
                        suffix = (char) ('A' + matangoCount++);
                        hp = 45;
                        monsters.add(new Matango(suffix, hp));
                        break;
                    case 1:
                        suffix = (char) ('A' + goblinCount++);
                        hp = 50;
                        monsters.add(new Goblin(suffix, hp));
                        break;
                    case 2:
                        suffix = (char) ('A' + slimeCount++);
                        hp = 40;
                        monsters.add(new Slime(suffix, hp));
                        break;
                }
            }

            System.out.println("---味方パーティ---");
            for (Character member : party) {
                member.showStatus();
            }
            System.out.println("---敵グループ---");
            for (Monster monster : monsters) {
                monster.showStatus();
            }

            while (!party.isEmpty() && !monsters.isEmpty()) {
                System.out.println("\n---味方の総攻撃！---");
                Iterator<Character> partyIterator = party.iterator();
                while (partyIterator.hasNext()) {
                    Character attacker = partyIterator.next();
                    if (!attacker.isAlive()) {
                        attacker.die();
                        partyIterator.remove();
                        continue;
                    }

                    if (monsters.isEmpty()) {
                        break;
                    }

                    System.out.println("\n" + attacker.getName() + "の行動を選択してください:");
                    if (attacker instanceof Hero) {
                        Hero currentHero = (Hero) attacker;
                        if (currentHero instanceof SuperHero) {
                            System.out.println("  1. 攻撃");
                        } else {
                            System.out.println("  1. 攻撃");
                            System.out.println("  2. SuperHeroになる");
                        }
                    } else if (attacker instanceof Wizard) {
                        System.out.println("  1. 攻撃 (石を投げる)");
                        System.out.println("  2. 魔法攻撃");
                    } else if (attacker instanceof Thief) {
                        System.out.println("  1. 攻撃");
                        System.out.println("  2. 守る (ガード)");
                    } else {
                        System.out.println("  1. 攻撃");
                    }

                    int actionChoice = -1;
                    while (true) {
                        try {
                            System.out.print("選択: ");
                            actionChoice = Integer.parseInt(br.readLine());
                            if ((attacker instanceof Hero && !(attacker instanceof SuperHero) && (actionChoice == 1 || actionChoice == 2)) ||
                                    (attacker instanceof SuperHero && actionChoice == 1) ||
                                    (attacker instanceof Wizard && (actionChoice == 1 || actionChoice == 2)) ||
                                    (attacker instanceof Thief && (actionChoice == 1 || actionChoice == 2)) ||
                                    (!(attacker instanceof Hero || attacker instanceof Wizard || attacker instanceof Thief) && actionChoice == 1)) {
                                break;
                            } else {
                                System.out.println("無効な選択です。もう一度入力してください。");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("数字で入力してください。");
                        }
                    }

                    Monster targetMonster = null;
                    if (actionChoice == 1 || (attacker instanceof Wizard && actionChoice == 2)) { // 攻撃または魔法攻撃の場合
                        if (monsters.isEmpty()) {
                            System.out.println("攻撃する敵がいません！");
                            continue;
                        }
                        System.out.println("誰を攻撃しますか？");
                        for (int i = 0; i < monsters.size(); i++) {
                            System.out.println("  " + (i + 1) + ". " + monsters.get(i).getName() + monsters.get(i).getSuffix() + " (HP: " + monsters.get(i).getHp() + ")");
                        }

                        int targetChoice = -1;
                        while (true) {
                            try {
                                System.out.print("選択: ");
                                targetChoice = Integer.parseInt(br.readLine());
                                if (targetChoice >= 1 && targetChoice <= monsters.size()) {
                                    targetMonster = monsters.get(targetChoice - 1);
                                    break;
                                } else {
                                    System.out.println("無効な選択です。もう一度入力してください。");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("数字で入力してください。");
                            }
                        }
                    }

                    if (attacker instanceof Hero) {
                        Hero currentHero = (Hero) attacker;
                        if (actionChoice == 1) {
                            currentHero.attack(targetMonster);
                        } else if (actionChoice == 2 && !(currentHero instanceof SuperHero)) {
                            SuperHero superHero = new SuperHero(currentHero);
                            if (!superHero.isAlive()) {
                                superHero.die();
                                partyIterator.remove();
                                System.out.println(currentHero.getName() + "はスーパーヒーローになろうとしたが、力尽きてしまった！");
                            } else {
                                int index = party.indexOf(currentHero);
                                party.set(index, superHero);
                                System.out.println(currentHero.getName() + "はスーパーヒーローに進化した！");
                            }
                        }
                    } else if (attacker instanceof Wizard) {
                        Wizard currentWizard = (Wizard) attacker;
                        if (actionChoice == 1) {
                            currentWizard.attack(targetMonster);
                        } else if (actionChoice == 2) {
                            currentWizard.magic(targetMonster);
                        }
                    } else if (attacker instanceof Thief) {
                        Thief currentThief = (Thief) attacker;
                        if (actionChoice == 1) {
                            currentThief.attack(targetMonster);
                        } else if (actionChoice == 2) {
                            currentThief.guard();
                        }
                    }

                    if (targetMonster != null) {
                        if (!targetMonster.isAlive()) {
                            targetMonster.die();
                            monsters.remove(targetMonster);
                        } else if (targetMonster.getHp() <= 5) {
                            targetMonster.run();
                            monsters.remove(targetMonster);
                        }
                    }
                    if (monsters.isEmpty()) {
                        break;
                    }
                }

                if (monsters.isEmpty()) {
                    System.out.println("\n敵を全て倒した" + hero.getName() + "達は勝利した!");
                    break;
                }

                System.out.println("\n---敵の総攻撃！---");
                for (Monster attackerM : new ArrayList<>(monsters)) {
                    if (!attackerM.isAlive()) {
                        continue;
                    }
                    if (party.isEmpty()) {
                        break;
                    }

                    Character targetCharacter = party.get(rand.nextInt(party.size()));
                    attackerM.attack(targetCharacter);

                    if (!targetCharacter.isAlive()) {
                        targetCharacter.die();
                        party.remove(targetCharacter);
                    }
                }

                if (party.isEmpty()) {
                    System.out.println("\n味方パーティは全滅してしまった…");
                    break;
                }

                System.out.println("\n---現在のステータス---");
                for (Character member : party) {
                    member.showStatus();
                }
                for (Monster monster : monsters) {
                    monster.showStatus();
                }
            }

            System.out.println("\n---味方パーティ最終ステータス---");
            for (Character member : party) {
                member.showStatus();
                if (member.isAlive()) {
                    System.out.println(member.getName() + "：生存");
                } else {
                    System.out.println(member.getName() + "：死亡");
                }
            }

            System.out.println("\n---敵グループ最終ステータス---");
            for (Monster monster : monsters) {
                monster.showStatus();
                if (monster.isAlive()) {
                    System.out.println(monster.getName() + "：生存");
                } else {
                    System.out.println(monster.getName() + monster.getSuffix() + "：討伐済み");
                }
            }
        } catch (IOException e) {
            System.err.println("入力エラーが発生しました: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.out.println("エラーが発生しました。\nゲームを開始できません。");
        }
    }
}