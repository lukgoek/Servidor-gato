/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author fimaz
 */
public class Servidor {

   
    
    public static void main(String[] args) {
        Server obj = new Server();
        obj.conexion(5555);
    }
    
}
