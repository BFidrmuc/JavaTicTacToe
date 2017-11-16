/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bfidr
 */
public class Reflection {

    public static String IspisReflectiona(String nazivKlase) {
        String tekstZaUpis = nazivKlase + "\n";
        try {
            Class<?> klasa = Class.forName("BL.Polje");

            Field[] poljeVarijabli = klasa.getDeclaredFields();

            tekstZaUpis += "Popis privatnih varijabli je sljedeći: " + "\n";

            for (Field varijabla : poljeVarijabli) {
                if (Modifier.isPrivate(varijabla.getModifiers())) {
                    tekstZaUpis += varijabla.getName() + "\n";
                }
            }

            tekstZaUpis += "Popis javnih metoda je sljedeći: " + "\n";

            Method[] metode = klasa.getMethods();

            for (Method metoda : metode) {
                if (Modifier.isPublic(metoda.getModifiers())) {
                    tekstZaUpis += metoda.getName();
                    if (metoda.getParameters().length > 0) {
                        tekstZaUpis += "Metoda prima: " + "\n";
                        Parameter[] parameters = metoda.getParameters();
                        for (Parameter param : parameters) {
                            tekstZaUpis += param.getType() + " " + param.getName() + "\n";
                        }
                    } else {
                        tekstZaUpis += "Metoda ne prima ništa!" + "\n";
                    }

                    tekstZaUpis += "Metoda vraća: " + "\n";
                    tekstZaUpis += metoda.getReturnType() + "\n";

                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Reflection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tekstZaUpis;
    }

}
