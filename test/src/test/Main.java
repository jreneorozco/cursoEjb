package test;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		
		List<String> listaNombres=getHolaMundo();
		for(String nombre: listaNombres)
		{
			if(nombre.equals("Ana"))
			{
			System.out.println("El nombre es  "+nombre);
			}
			else if(nombre.equals("Rene")){
				System.out.println("El nombre es  "+nombre);
				
			}
		}
	}
	
	
	public static List<String> getHolaMundo()
	{
//		String mensaje="Hola mundo mi nombre es :"+nombre; 
		List<String> listaNombre=new ArrayList<>();
		listaNombre.add("Luis");
		listaNombre.add("Rene");
		listaNombre.add("Ana");
		
		return listaNombre;
	}

}
