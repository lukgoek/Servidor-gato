/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.EventListener;

/**
 *
 * @author Humberto Lugo
 */


interface AvisaServer extends EventListener{
    
    
    //Metodos a  heredar en servidos
    public void setPlayers();
    public void getPlayers();
    
    public void onClientTurn(String turn);
    public void onClientClick(String nickname, String posicion);
    
}


