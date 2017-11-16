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
public class IgracaPloca implements Serializable {

    private Polje[][] igracaploca;
    private String igracPrvi;
    private String igracDrugi;
    private int counter;

    public Polje[][] getIgracaploca() {
        return igracaploca;
    }

    public void setIgracaploca(Polje[][] igracaploca) {
        this.igracaploca = igracaploca;
    }

    public String getIgracPrvi() {
        return igracPrvi;
    }

    public void setIgracPrvi(String igracPrvi) {
        this.igracPrvi = igracPrvi;
    }

    public String getIgracDrugi() {
        return igracDrugi;
    }

    public void setIgracDrugi(String igracDrugi) {
        this.igracDrugi = igracDrugi;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public IgracaPloca() {
    }

    public IgracaPloca(Polje[][] igracaploca, String igracPrvi, String igracDrugi, int counter) {
        this.igracaploca = igracaploca;
        this.igracPrvi = igracPrvi;
        this.igracDrugi = igracDrugi;
        this.counter = counter;
    }

}
