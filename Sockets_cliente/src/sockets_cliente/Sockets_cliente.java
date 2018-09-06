package sockets_cliente;

import java.net.*;
import java.io.*;
public class Sockets_cliente
{
        private String nombreArchivo = "";
        private String nombreArchivo2 = "";
        public Sockets_cliente( String nombreArchivo, String nombreArchivo2 )
        {
              this.nombreArchivo = nombreArchivo;
              this.nombreArchivo2 = nombreArchivo2;
         }
         
         public void enviarArchivo( )
        {
          try
          {
         
            // Creamos la direccion IP de la maquina que recibira el archivo
            InetAddress direccion = InetAddress.getByName( "172.30.10.214" );
         
            // Creamos el Socket con la direccion y elpuerto de comunicacion
            Socket socket = new Socket( direccion, 5000);
            socket.setSoTimeout( 2000 );
            socket.setKeepAlive( true );
         
            // Creamos el archivo que vamos a enviar
            File archivo = new File( nombreArchivo );
            File archivo2 = new File( nombreArchivo2 );
         
            // Obtenemos el tamaño del archivo
            int tamañoArchivo = ( int )archivo.length();
            int tamañoArchivo2 = ( int )archivo2.length();
            // Creamos el flujo de salida, este tipo de flujo nos permite 
            // hacer la escritura de diferentes tipos de datos tales como
            // Strings, boolean, caracteres y la familia de enteros, etc.
            DataOutputStream dos = new DataOutputStream( socket.getOutputStream() );
            DataOutputStream dos2 = new DataOutputStream( socket.getOutputStream() );
         
            System.out.println( "Enviando archivo: "+archivo.getName() );
            System.out.println( "Enviando llave: "+archivo2.getName() );
            // Enviamos el nombre del archivo 
            dos.writeUTF( archivo.getName() );
            dos2.writeUTF( archivo2.getName() );
         
            // Enviamos el tamaño del archivo
            dos.writeInt( tamañoArchivo );
            dos2.writeInt( tamañoArchivo2 );
            // Creamos flujo de entrada para realizar la lectura del archivo en bytes
            FileInputStream fis = new FileInputStream( nombreArchivo );
            FileInputStream fis2 = new FileInputStream( nombreArchivo2 );
            BufferedInputStream bis = new BufferedInputStream( fis );
            BufferedInputStream bis2 = new BufferedInputStream( fis2 );
         
            // Creamos el flujo de salida para enviar los datos del archivo en bytes
            BufferedOutputStream bos = new BufferedOutputStream( socket.getOutputStream()          );
            BufferedOutputStream bos2 = new BufferedOutputStream( socket.getOutputStream()          );
            // Creamos un array de tipo byte con el tamaño del archivo 
            byte[] buffer = new byte[ tamañoArchivo ];
            byte[] buffer2 = new byte[ tamañoArchivo2 ];
         
            // Leemos el archivo y lo introducimos en el array de bytes 
            bis.read( buffer ); 
            bis2.read( buffer );
            // Realizamos el envio de los bytes que conforman el archivo
            for( int i = 0; i < buffer2.length; i++ )
            {
                bos2.write( buffer2[ i ] ); 
            } 
            for( int i = 0; i < buffer.length; i++ )
            {
                bos.write( buffer[ i ] ); 
            } 
            System.out.println( "Llave enviada: "+archivo2.getName() );
            System.out.println( "Archivo enviado: "+archivo.getName() );
            // Cerramos socket y flujos
            bis.close();
            bis2.close();
            bos2.close();
            bos.close();
            socket.close(); 
          }
          catch( Exception e )
          {
            System.out.println( e.toString() );
          }
         
         }
         
         // Lanzamos nuestro cliente para realizar el envio del archivo calc.exe
         // ubicado en C:\Windows\calc.exe
         public static void main( String args[] )
         {
            Sockets_cliente ea = new Sockets_cliente( "C:\\Users\\james\\Documents\\TELEMÁTICA II\\PRIMER CORTE\\LABORATORIO 2\\texto.txt" ,"C:\\Users\\james\\Documents\\TELEMÁTICA II\\PRIMER CORTE\\LABORATORIO 2\\llave.txt");
            ea.enviarArchivo();
         }
         
}