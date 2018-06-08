package com.mx.itce.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

public class CreaReporte {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	
	public String[] getListadoArchivos(String cadena)
	{
		String[] cedenaDatos= cadena.split("\\|");
		return cedenaDatos;
	}
	
	public void creaReportes(String ruta, String algotimo)
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "ReporteHash");
        String[] headers = new String[]{
            "Archivo",
            "Hash",
            "Algoritmo"
        };
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        headerStyle.setFont(font);
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		File dir = new File(ruta);
		EncriptaArchivo ea= new EncriptaArchivo();
		String[] archivos=dir.list();
		HSSFRow headerRow = sheet.createRow(0);
		File carpetaHash = new File(ruta+"HASH/");
		if(carpetaHash.exists())
		{
			if( carpetaHash.delete() )
			{
				System.out.println( "SE BORRO CORRETAMENTE LA CARPETA" );
			}else {
				System.out.println( "--ERROR --  NO SE  BORRO CORRETAMENTE LA CARPETA" );
			}
		}else {
			carpetaHash.mkdirs();
		}
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }
        
        List<String> listaFiltrada = new ArrayList<String>();
        
        
        for(int i=0; i <archivos.length;i++)
        {
	        	if( archivos[i].endsWith(".pdf") )
	        	{
	        		String file=archivos[i];
	        		listaFiltrada.add(file);
	        	}
        			
        }
        
        for(int i=0; i< listaFiltrada.size(); i++)
        {
        		String file=listaFiltrada.get(i);
        		HSSFRow dataRow = sheet.createRow(i + 1);
			String encrypt=ea.getEncript(ruta,file,algotimo);
			System.out.println("File : " + file+ "  "+algotimo +" "+ encrypt);
			dataRow.createCell(0).setCellValue(file);
            dataRow.createCell(1).setCellValue(encrypt);
            dataRow.createCell(2).setCellValue(algotimo);
        }
        HSSFRow dataRow = sheet.createRow(1 + archivos.length);
        HSSFCell total = dataRow.createCell(1);
        total.setCellType(Cell.CELL_TYPE_FORMULA);
        total.setCellStyle(style);
        total.setCellFormula(String.format("CONTARA(B2:B%d)", 1 + archivos.length));
        FileOutputStream file;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time=sdf.format(timestamp);
        PrintWriter pw =null;  
		try {
			
			File archivotxt= new File( ruta+"HASH/"+"ReporteHashITCE_"+time+".txt" );
			file = new FileOutputStream(ruta+"HASH/ReporteHashITCE_"+time+".xls");
			pw= new PrintWriter(archivotxt);
			for(int i=0; i <archivos.length;i++)
	        {
			String file2=archivos[i];
        		if(file2.endsWith(".pdf"))
    			{
    				String encrypt=ea.getEncript(ruta,file2,algotimo);
    				System.out.println("File : " + file2+ "  "+algotimo +" "+ encrypt);
    	            pw.println(encrypt  + " " + ruta+file2 );
    			}
				
	        }
		    workbook.write(file);
		    file.close();
		    pw.flush();
		    pw.close();
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
		
	}
}
