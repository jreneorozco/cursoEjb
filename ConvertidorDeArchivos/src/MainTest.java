import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MainTest {

	public static void main(String[] args) {
		
		MessageDigest md = null;
		String password = "password D:";
		
		copia("/Users/reneorozco/Downloads/docScan/doc05727320171222122016_A1.pdf","/Users/reneorozco/Downloads/docScan/docA1.pdf");
		
		  try {
			  password=	muestraContenido("/Users/reneorozco/Downloads/docScan/doc05727320171222122016_A1.pdf");
			  //System.out.println(password);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	        try {
	            
	            //SHA-512
	            md= MessageDigest.getInstance("SHA-512");
	            md.update(password.getBytes());
	            byte[] mb = md.digest();
	            System.out.println(Hex.encodeHex(mb));
	            
	            //SHA-1
	            md= MessageDigest.getInstance("SHA-1");
	            md.update(password.getBytes());
	            mb = md.digest();
	            System.out.println(Hex.encodeHex(mb));
	            
	            //MD5
	            md= MessageDigest.getInstance("MD5");
	            md.update(password.getBytes());
	            mb = md.digest();
	            System.out.println(Hex.encodeHex(mb));
	            
	        } catch (NoSuchAlgorithmException e) {
	            //Error
	        }

	      
	}
	

	    
	    public static String muestraContenido(String archivo) throws FileNotFoundException, IOException {
	        String cadena;
	        String contenido="";
	        FileReader f = new FileReader(archivo);
	        BufferedReader b = new BufferedReader(f);
	        while((cadena = b.readLine())!=null) {
	           // System.out.println(cadena);
	            contenido+=cadena+"\n";
	        }
	        b.close();
	        //System.out.println(contenido);
	        return contenido;
	    }
	    
	    
	    public static void copia (String ficheroOriginal, String ficheroCopia)
		{
			try
			{
	                        // Se abre el fichero original para lectura
				FileInputStream fileInput = new FileInputStream(ficheroOriginal);
				BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
				
				// Se abre el fichero donde se hará la copia
				FileOutputStream fileOutput = new FileOutputStream (ficheroCopia);
				BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
				
				// Bucle para leer de un fichero y escribir en el otro.
				byte [] array = new byte[1000];
				int leidos = bufferedInput.read(array);
				while (leidos > 0)
				{
					bufferedOutput.write(array,0,leidos);
					leidos=bufferedInput.read(array);
				}

				// Cierre de los ficheros
				bufferedInput.close();
				bufferedOutput.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	    
	    

}
