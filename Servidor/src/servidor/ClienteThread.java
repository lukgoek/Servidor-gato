

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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Humberto Lugo
 */
public class ClienteThread   {
    
    private Socket socket;
    
    public String datos = "", nickname = "", turn ="";
        
    private OutputStream outputStream;
    private InputStream inputStream;
    
    private DataOutputStream salidaDatos;
    private DataInputStream entradaDatos;
    
    //variable de instancia "LISTENER"
    public AvisaServer avisaServer;
    
    private boolean conexionActiva;
    
    
    
/*******************************************************************************/ 
    
    
    public ClienteThread(Socket socket){
        this.socket=socket;
        
        conexionActiva=true;
        Thread hiloServer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(conexionActiva){
                    recibirDatos();
                    
                    
                }
                cerrarConexion();
            }
        });
        hiloServer.start();   
    }
    
    
    
 /*******************************************************************************/   
    
    
    
    /**
     * @param avisaServer*********/
    public void addListener(AvisaServer avisaServer){
        
        this.avisaServer = avisaServer;
        
    }
    
    
    
 /*******************************************************************************/
    
    
    
    public void recibirDatos(){
        try{
            inputStream = socket.getInputStream();
            entradaDatos = new DataInputStream(inputStream);
            
            
            
            String comandos[] = entradaDatos.readUTF().split("/");
            
            System.out.println("1 :"+comandos[0]);
            System.out.println("2 :"+comandos[1]);
            
            /**********  GUARDA EL NICKNAME *********/
            
            if(comandos[0].equals("nick")){
                this.nickname = comandos[1];
                System.out.println("NICK NAME" +this.nickname);
                
            }
            
            /*
            if(this.avisaServer != null){
            
                this.avisaServer.onClientReceive(entradaDatos.readUTF());    
            }*/
            
            
            
           
            
         
            
            
            
            /******** TURNO so se ejecuta una vez ********/
            if(comandos[0].equals(this.nickname)){
            
                this.avisaServer.onClientTurn(comandos[0]); 
                
            }
            
            /***** CLICK ******/
            if(comandos[1].equals("click")){
            
                this.avisaServer.onClientClick(comandos[0], comandos[2]);    
            }
            
            
            //System.out.println("RECIBIRDATOS CLIENTETHREAD"+entradaDatos.readUTF());
        }catch(Exception ex){
            ex.printStackTrace();
            conexionActiva=false;
        }
    }
    
    
    
 /*******************************************************************************/   
    
    
    
    public void enviarDatos(String datos){
        try{
            outputStream = socket.getOutputStream();
            salidaDatos = new DataOutputStream(outputStream);
            salidaDatos.writeUTF(datos);
            salidaDatos.flush();
            
            System.out.println("ENVIADO! "+datos);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
/*******************************************************************************/
    
    public void cerrarConexion(){
        try{
            salidaDatos.close();
            entradaDatos.close();
            socket.close();
        }catch(Exception ex){
          //  ex.printStackTrace();
        }
    }

    
    
    
}