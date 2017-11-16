/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.io.Serializable;

/**
 *
 * @author bfidr
 */
public class Polje implements Serializable {

    private Oznaka oznaka;
    private int x;
    private int y;

    public Polje(Oznaka oznaka) {
        this.oznaka = oznaka;
    }

    public Oznaka getOznaka() {
        return oznaka;
    }

    public void setOznaka(Oznaka oznaka) {
        this.oznaka = oznaka;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Polje(Oznaka oznaka, int x, int y) {
        this.oznaka = oznaka;
        this.x = x;
        this.y = y;
    }

    public Polje(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
