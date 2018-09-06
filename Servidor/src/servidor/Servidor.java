package servidor;

import java.net.*;
import java.io.*;
import java.util.*;

public class Servidor
{
       private ServerSocket servidor = null;
       public Servidor( ) throws IOException
       {
          // Creamos socket servidor escuchando en el mismo puerto donde se comunica el cliente
          // en este caso el puerto es el 4400
          servidor = new ServerSocket(5000);
          System.out.println( "Esperando recepcion de archivos..." ); 
       }
 
       public void iniciarServidor()
       {
          while( true )
          {
            try
            {
               // Se crea el socket que atenderá el servidor.
               Socket cliente = servidor.accept(); 
               // Se crea el flujo de entrada para leer los datos que envia el cliente. 
               DataInputStream dis = new DataInputStream( cliente.getInputStream() );
               // Se obtiene el nombre del archivo.
               String nombreArchivo = dis.readUTF().toString(); 
               // Se obtiene el tamaño del archivo.
               int tam = dis.readInt(); 
               System.out.println( "Recibiendo archivo "+nombreArchivo );
               // Se crea el flujo de salida, dicho flujo sirve para indicar se guardará el archivo.
               FileOutputStream fos = new FileOutputStream( "C:\\Users\\white\\Documents\\Telemática II\\"+nombreArchivo );
               BufferedOutputStream out = new BufferedOutputStream( fos );
               BufferedInputStream in = new BufferedInputStream( cliente.getInputStream() );
               // Se crea el array de bytes para leer los datos del archivo.
               byte[] buffer = new byte[ tam ];
               // Se obtiene el archivo mediante la lectura de bytes enviados.
               for( int i = 0; i < buffer.length; i++ )
               {
                  buffer[ i ] = ( byte )in.read( ); 
               }
 
               // Escribimos el archivo 
               out.write( buffer ); 
 
               // Cerramos flujos
               out.flush(); 
               in.close();
               out.close(); 
               cliente.close();
 
               System.out.println( "Archivo Recibido "+nombreArchivo );
        
           }
           catch( Exception e )
           {
              System.out.println( "Recibir: "+e.toString() ); 
           }
         } 
       }
       
       // Lanzamos el servidor para la recepción de archivos
       public static void main( String args[] ) throws IOException
       {
           new Servidor().iniciarServidor(); 
       }
}