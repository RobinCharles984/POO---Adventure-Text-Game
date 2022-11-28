package Character;

import java.awt.*;

public class Character {
    //Atributos
    public String name;
    private int hp;//Vida
    private int def;//Defesa
    private int dmg;//Dano
    protected float xp;
    public Image image;

    //Getter/Setter
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}
