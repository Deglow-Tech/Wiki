package Analisis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import Entities.Medicion;

public class Analisis {
	
	// Variables para pulsaciones de bebe
	static float PULSACIONES_BEBE_MAX = 160;
	static float PULSACIONES_BEBE_MAX_GRAVE = 180;
	static float PULSACIONES_BEBE_MIN = 120;
	static float PULSACIONES_BEBE_MIN_GRAVE = 100;
	static int CANT_PULSACIONES_MAX = 10;
	static int CANT_PULSACIONES_MIN = 15;
	static int PORCENTAJE_PULSACIONES_MAX = 4;
	static int PORCENTAJE_PULSACIONES_MIN = 4;
	static int CANT_PULSACIONES_SOBRE_MAX_MODERADO = 0;
	static int CANT_PULSACIONES_SOBRE_MIN_MODERADO = 0;
	static int CANT_PULSACIONES_SOBRE_MAX_GRAVE = 0;
	static int CANT_PULSACIONES_SOBRE_MIN_GRAVE = 0;
	
	static boolean ANALIZANDO_RIESGO = false;
	static int CANT_MEDICIONES = 0;
	
	// Variables para lectura de archivo
    static File archivo = null;
    static FileReader fr = null;
    static BufferedReader br = null;
	
	public static void main(String[] args) {
		try {
	         archivo = new File ("bradicardia.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         String linea;
	         int i = 1;
	         while((linea = br.readLine())!=null) {
	        	 Medicion medicion = new Medicion();
	        	 medicion.obtenerMedicion(linea);
	        	 medicion.imprimirMedicion(i);
	        	 if ((medicion.getFactor()).equals("Pulsacion")) {
	        		 detectarRiesgoPulsacionesBebe(medicion);
	        	 }
	        	 i++;
	         }
	 
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	
	public static void analizarMedicionDePulsacionesBebeConUmbrales(Medicion medicion) {
		if (medicion.getValor() > PULSACIONES_BEBE_MAX) {
			if (medicion.getValor() > PULSACIONES_BEBE_MAX_GRAVE) {
				CANT_PULSACIONES_SOBRE_MAX_GRAVE++;
			} else {
				CANT_PULSACIONES_SOBRE_MAX_MODERADO++;
			}
		}
		
		if (medicion.getValor() < PULSACIONES_BEBE_MIN) {
			if (medicion.getValor() < PULSACIONES_BEBE_MIN_GRAVE) {
				CANT_PULSACIONES_SOBRE_MIN_GRAVE++;
			} else {
				CANT_PULSACIONES_SOBRE_MIN_MODERADO++;
			}					
		}
	}
	
	public static void emitirAlertaPorPulsacionesBebe(Medicion medicion) {
		CANT_MEDICIONES++;
		analizarMedicionDePulsacionesBebeConUmbrales(medicion);
		medicion.setEvaluado(true);
		// Y actualizar en la bd
		
		if (CANT_MEDICIONES == CANT_PULSACIONES_MAX &&
				(CANT_PULSACIONES_SOBRE_MAX_GRAVE + CANT_PULSACIONES_SOBRE_MAX_MODERADO) > PORCENTAJE_PULSACIONES_MAX) {
			String print = ((CANT_PULSACIONES_SOBRE_MAX_MODERADO) > PORCENTAJE_PULSACIONES_MAX) ?
					"Alerta urgente (MAYOR A 180LPM): Envio de mail + mensaje de texto + notificacion en perfil medico web\n" :
						"Alerta moderada (ENTRE 160LPM Y 180LPM): Envio mail + notificacion en perfil medico web\n";
			System.out.println(print);
			ANALIZANDO_RIESGO = false;
			CANT_MEDICIONES = 0;
		}
		if (CANT_MEDICIONES == CANT_PULSACIONES_MIN) {
			if ((CANT_PULSACIONES_SOBRE_MIN_GRAVE + CANT_PULSACIONES_SOBRE_MIN_MODERADO) > PORCENTAJE_PULSACIONES_MIN) {
				String print = ((CANT_PULSACIONES_SOBRE_MIN_MODERADO) > PORCENTAJE_PULSACIONES_MIN) ?
						"Alerta urgente (MENOR A 100LPM): Envio de mail + mensaje de texto + notificacion en perfil medico web\n" :
							"Alerta moderada (ENTRE 100LPM Y 120LPM): Envio mail + notificacion en perfil medico web\n";
				System.out.println(print);
			}
			ANALIZANDO_RIESGO = false;
			CANT_MEDICIONES = 0;
		}
	}
	
	public static void detectarRiesgoPulsacionesBebe(Medicion medicion) {
		if (ANALIZANDO_RIESGO == false) {
			if (medicion.getValor() > PULSACIONES_BEBE_MAX || medicion.getValor() < PULSACIONES_BEBE_MIN) {
				ANALIZANDO_RIESGO = true;
				CANT_MEDICIONES = 1;
				
				analizarMedicionDePulsacionesBebeConUmbrales(medicion);
			}
		
			medicion.setEvaluado(true);
			// Y actualizar en la bd
		} else {
			if (CANT_MEDICIONES < CANT_PULSACIONES_MIN) {
				
				emitirAlertaPorPulsacionesBebe(medicion);
			}
		}
		}
}