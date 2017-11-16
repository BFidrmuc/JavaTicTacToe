/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.IgracaPloca;

/**
 *
 * @author bfidr
 */
public interface IRepo {

    void SaveGame(IgracaPloca a);

    IgracaPloca LoadGame();
}
