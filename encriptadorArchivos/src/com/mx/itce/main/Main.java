package com.mx.itce.main;

import java.io.File;
import java.net.URL;

import com.mx.itce.utils.CreaReporte;

public class Main {

	 URL path_ejecucion=getClass().getProtectionDomain().getCodeSource().getLocation();
	
	public static void main(String[] args) {
		//String ruta =args[0];
//		String algoritmo =args[1];
		File carpeta = new File(".");
		String ruta= carpeta.getAbsolutePath().substring( 0,carpeta.getAbsolutePath().length()-1 );
		String algoritmo="";
		
		if(args.length==0)
		{
			algoritmo="MD5";
		}else
		{
			for(int i=0; i< args.length; i++)
			{
				System.out.println(args[i]);
				if(args[i].toUpperCase().equals("MD5"))
				{
					algoritmo=args[i].toUpperCase();
				}
				else if(args[i].toUpperCase().equals("SHA-1"))
				{
					algoritmo=args[i].toUpperCase();
				}
				else if(args[i].toUpperCase().equals("SHA-256"))
				{
					algoritmo=args[i].toUpperCase();
				}
				else if(args[i].toUpperCase().equals("SHA-384"))
				{
					algoritmo=args[i].toUpperCase();
				}
				else if(args[i].toUpperCase().equals("SHA-512"))
				{
					algoritmo=args[i].toUpperCase();
				}else {
					algoritmo="MD5";
				}
				
			}
		}
		
		
//		String ruta="/Users/reneorozco/Downloads/docScan/";
		//String algoritmo="SHA-256";
		CreaReporte creaReporte= new CreaReporte();
		creaReporte.creaReportes(ruta, algoritmo);

	}

}
