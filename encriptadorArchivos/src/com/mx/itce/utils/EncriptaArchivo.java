package com.mx.itce.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class EncriptaArchivo {

	
	
	//public static final String PATH="/home/" 
	
	
	
	public static String  getEncript(String ruta,String file,String algorithm)
    {
    
     //declarar funciones resumen
       try{
       //MessageDigest messageDigest = MessageDigest.getInstance("MD5"); // Inicializa MD5
       MessageDigest messageDigest2 = MessageDigest.getInstance(algorithm); // Inicializa SHA-1
       
       //leer fichero byte a byte
       
          try{
             InputStream archivo = new FileInputStream( ruta+file );
          
             byte[] buffer = new byte[1];
             int fin_archivo = -1;
             int caracter;
        
             caracter = archivo.read(buffer);
        
             while( caracter != fin_archivo ) {
          
              //  messageDigest.update(buffer); // Pasa texto claro a la función resumen
                messageDigest2.update(buffer);
                caracter = archivo.read(buffer);
             }   
        
             archivo.close();
             //byte[] resumen = messageDigest.digest(); // Genera el resumen MD5
             byte[] resumen2 = messageDigest2.digest(); // Genera el resumen SHA-1
        
          
             String m = "";
             for (int i = 0; i < resumen2.length; i++)
             {
                m += Integer.toHexString((resumen2[i] >> 4) & 0xf);
                m += Integer.toHexString(resumen2[i] & 0xf);
             }
           
             return m;
          
          }
          catch(java.io.FileNotFoundException fnfe) {}
          catch(java.io.IOException ioe) {}
       
       }   
       catch(java.security.NoSuchAlgorithmException nsae) {}
       return null;
    }
	 
}
