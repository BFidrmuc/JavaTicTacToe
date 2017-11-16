/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author bfidr
 */
public class RepoFactory {

    public static IRepo getRepo() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("KojiRepo.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.equals("1")) {
                    return new RepoDat();
                } else {
                    return null;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Neuspjesno citanje iz datoteke.");
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

}
