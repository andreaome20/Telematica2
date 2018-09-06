package ecb_des;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
public class ECB_DES {
  public static void main(String[] args) {
    String strPlaintext = new String();
    String strCipherText = new String();
    String strDecipher = new String();	
    try{
    // Crea llave DES usando un generador de llaves.
      KeyGenerator LlaveCreada = KeyGenerator.getInstance("DES");
      SecretKey LlaveOculta = LlaveCreada.generateKey();		
      Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
    // Inicializa el cifrado para cifrar.		
      c.init(Cipher.ENCRYPT_MODE,LlaveOculta);
      byte[] bPlaintext = readBytesFromFile("C:\\Users\\james\\Documents\\TELEMÁTICA II\\PRIMER CORTE\\LABORATORIO 2\\texto.txt");
    // Cifrar los bytes empleando el método doFinal.
      byte[] bCipherText = c.doFinal(bPlaintext); 
      strCipherText = new BASE64Encoder().encode(bCipherText);
      System.out.println("El texto cifrado es: \n" +strCipherText);
    // Descifrar el texto.
      c.init(Cipher.DECRYPT_MODE,LlaveOculta,c.getParameters());
    // Descifrar los bytes empleando el método doFinal.
      byte[] Decipher= c.doFinal(bCipherText);
      strDecipher = new String(Decipher);
      System.out.println("\n");
      System.out.println("El texto descifrado es: \n" +strDecipher);
    }	
    catch (NoSuchAlgorithmException noAlgoritmo)
      { System.out.println(" Algoritmo no existe. " + noAlgoritmo); }
          catch (NoSuchPaddingException noRelleno)
            { System.out.println(" Relleno no existe. " + noRelleno); }
              catch (InvalidKeyException errorLlave)
                { System.out.println(" Error de llave. " + errorLlave); }		
       		  catch (BadPaddingException errorRelleno)
                    { System.out.println(" Error relleno. " + errorRelleno); }	
                      catch (IllegalBlockSizeException bloqueIlegal)
                        { System.out.println(" Tamaño de bloque ilegal. " + bloqueIlegal); }	
                          catch (InvalidAlgorithmParameterException errorParametro)
                            { System.out.println(" Error parámetro. " + errorParametro); }
  }
    
  private static byte[] readBytesFromFile(String filePath) {
    FileInputStream fileInputStream = null;
    byte[] bytesArray = null;
    try {
      File file = new File(filePath);
      bytesArray = new byte[(int) file.length()];
    // Leer el archivo en bytes [].
      fileInputStream = new FileInputStream(file);
      fileInputStream.read(bytesArray);
    } catch (IOException e) {
          e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
        }
}