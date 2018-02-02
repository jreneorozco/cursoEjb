import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class Utils {

	
	public static final String SHA_512="SHA-512"; 
	public static final String SHA="SHA"; 
	public static final String MD5="MD5"; 
	public static final String RUTA="/Users/reneorozco/Downloads/docScan/";
	
	
	    public static String muestraContenido(String archivo) throws FileNotFoundException, IOException {
	    		String cadena;
	        FileReader f = new FileReader(archivo);
	        BufferedReader b = new BufferedReader(f);
	        while((cadena = b.readLine())!=null) {
	            System.out.println(cadena);
	        }
	        b.close();
	        return cadena;
	        
	        
	        
	    }

	    public static void main(String[] args) throws IOException {
	       // muestraContenido("/home/mario/archivo.txt");
	        //String pdf=getContenidoPdf("/Users/reneorozco/Downloads/docScan/docA1.pdf");
	        //System.out.println("contenido"  +pdf);
//			       try {
//					getImagePdf("/Users/reneorozco/Downloads/docScan/docA1.pdf");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
	    //	String ruta="/Users/reneorozco/Downloads/docScan/docA1.pdf";
	    	//	String resultEncrypt=getEncript(ruta,MD5);
//	    		String[] sacarArchivo=ruta.split("\\/");
//	    		for(String f : sacarArchivo )
//	    		{
//	    			if(f.endsWith(".pdf"))
//	    			{
//	    				System.out.println("Archivo : " + f);
//	    			}
//	    		}
	    		File dir = new File(RUTA);
	    		
	    		String[] archivos=dir.list();
	    		
	    		for(String file: archivos)
	    		{
	    			
	    			if(file.endsWith(".pdf"))
	    			{
	    				String encrypt=getEncript(RUTA+file,SHA_512);
	    				System.out.println("File : " + file+ " SHA_512 " + encrypt);
	    			}
	    			
	    		}
	    }
	   
	
	    public static String pdfTika(String ruta)
	    {
	    		InputStream input=null;
	    		try {
	    			input = new FileInputStream(ruta);
	    			ContentHandler contentHandler= new BodyContentHandler();
	    			Metadata metadata= new Metadata();
	    			PDFParser pdfParser = new PDFParser();
	    			pdfParser.parse(input, contentHandler, metadata, new ParseContext());
	    			String salida=contentHandler.toString();
	    			System.out.println("Texto : " + salida);
	    			return salida;
	    		}catch(IOException e)
	    		
	    		{
	    			System.out.println("Error : " + e.getCause());
	    		} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TikaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		return null;
	    }
	    
	    public static String getContenidoPdf (String ruta) throws IOException {

	        //Loading an existing document
	        File file = new File(ruta);
	        PDDocument document = PDDocument.load(file);

	        //Instantiate PDFTextStripper class
	        PDFTextStripper pdfStripper = new PDFTextStripper();

	        //Retrieving text from PDF document
	        String text = pdfStripper.getText(document);
	        System.out.println(text);

	        //Closing the document
	        document.close();
	        return text;

	     }
	    
	    public static void getImagePdf(String ruta) throws Exception {

	        //Loading an existing PDF document
	        File file = new File(ruta);
	        PDDocument document = PDDocument.load(file);
	         
	        //Instantiating the PDFRenderer class
	        PDFRenderer renderer = new PDFRenderer(document);

	        //Rendering an image from the PDF document
	        BufferedImage image = renderer.renderImage(0);

	        //Writing the image to a file
	        ImageIO.write(image, "JPEG", new File("/Users/reneorozco/Downloads/docScan/docA1.jpg"));
	         
	        System.out.println("Image created");
	         
	        //Closing the document
	        document.close();

	     }
	    
	    public static String  getEncript(String ruta,String algorithm)
	    {
	    
	     //declarar funciones resumen
	       try{
	       //MessageDigest messageDigest = MessageDigest.getInstance("MD5"); // Inicializa MD5
	       MessageDigest messageDigest2 = MessageDigest.getInstance(algorithm); // Inicializa SHA-1
	       
	       //leer fichero byte a byte
	       
	          try{
	             InputStream archivo = new FileInputStream( ruta );
	          
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
	        
	             //Pasar los resumenes a hexadecimal
	        
//	             String s = "";
//	             for (int i = 0; i < resumen.length; i++)
//	             {
//	                s += Integer.toHexString((resumen[i] >> 4) & 0xf);
//	                s += Integer.toHexString(resumen[i] & 0xf);
//	             }
//	             System.out.println("Resumen MD5: " + s);
	        
	          
	             String m = "";
	             for (int i = 0; i < resumen2.length; i++)
	             {
	                m += Integer.toHexString((resumen2[i] >> 4) & 0xf);
	                m += Integer.toHexString(resumen2[i] & 0xf);
	             }
	            // System.out.println("Resumen "+algorithm+ " : " + m);
	           
	             return m;
	          
	          }
	          //lectura de los datos del fichero
	          catch(java.io.FileNotFoundException fnfe) {}
	          catch(java.io.IOException ioe) {}
	       
	       }   
	       //declarar funciones resumen
	       catch(java.security.NoSuchAlgorithmException nsae) {}
	       return null;
	    }
	   
}
