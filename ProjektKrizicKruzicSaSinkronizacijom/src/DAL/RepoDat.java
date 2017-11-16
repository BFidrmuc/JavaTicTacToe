/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.IgracaPloca;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author bfidr
 */
public class RepoDat implements IRepo {

    @Override
    public void SaveGame(IgracaPloca igracaPloca) {

        try (ObjectOutputStream repozitorij = new ObjectOutputStream(
                new FileOutputStream("igra.dat"))) {

            repozitorij.writeObject(igracaPloca);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RepoDat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RepoDat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public IgracaPloca LoadGame() {

        IgracaPloca ploca = new IgracaPloca();

        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream("igra.dat"))) {

            while (true) {
                Object procitanaIgra = reader.readObject();

                if (procitanaIgra instanceof IgracaPloca) {
                    IgracaPloca procitana = (IgracaPloca) procitanaIgra;
                    ploca = procitana;

                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RepoDat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) {
            System.out.println("Došli smo do kraja!");
        } catch (IOException ex) {
            Logger.getLogger(RepoDat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RepoDat.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ploca;
    }
}
