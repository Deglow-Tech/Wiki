package movimientos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class AnalisisMovimientos {
	
	static LocalDateTime FECHA_HORA_PROX_ANALISIS_MOVS;
	static LocalDateTime FECHA_HORA_PROX_ANALISIS_MOVS_CON_RECO;
	static boolean RECO_EJECUTADA = false;
	static ArrayList<Medicion> movimientos = new ArrayList();
	static boolean ANALIZANDO = false;
	
	// Informacion para alerta
	static LocalDateTime FECHA_HORA_1RA_DETECCION;
	static LocalDateTime FECHA_HORA_2DA_DETECCION;
	static int MOVS_1RA_DETECCION = 0;
	static int MOVS_2DA_DETECCION = 0;
	
	//Simula cada movimiento nuevo que se reciba
	static Medicion movimiento = new Medicion();
	
	// Valores seteados por el médico de la paciente
	static int TIEMPO_ANALISIS_MOVS = 5;
	static int CANT_PATADAS_MIN = 2;
	static int TIEMPO_ANALISIS_MOVS_POSTERIOR_A_RECO = 3;
	static int CANT_PATADAS_MIN_POSTERIOR_A_RECO = 1;
	

	public static void main(String[] args) {
		
		// Hacemos el seteo inicial, que se hace apenas se detecte la conexion de la faja.
		FECHA_HORA_PROX_ANALISIS_MOVS = LocalDateTime.now().plusMinutes(TIEMPO_ANALISIS_MOVS); 
		
		// Este while simula el loop de analisis de la aplicación
		int i = 1;
		while(i == 1) {
			LocalDateTime now = LocalDateTime.now();
			
			if (FECHA_HORA_PROX_ANALISIS_MOVS.isBefore(now) && ANALIZANDO == false) {
				ANALIZANDO = true;
				// Se crea un hilo previo al analisis (Ya que se necesita ir guardando los movs que puedan darse durante el analisis)
				analizarExistenciaRiesgoPorMovs();
				FECHA_HORA_PROX_ANALISIS_MOVS = FECHA_HORA_PROX_ANALISIS_MOVS.plusMinutes(TIEMPO_ANALISIS_MOVS); 
			} // Este elseif simula la llegada de un nuevo movimiento, el cual debe guardarse temporalmente en la base local
			else if (movimiento != null) {
				// Guardado en base local
				movimientos.add(movimiento);
			}
		}

	}
	
	public static void analizarExistenciaRiesgoPorMovs() {
		for(Medicion mov : movimientos) {
			if (mov.getFechaHora().isBefore(FECHA_HORA_PROX_ANALISIS_MOVS)) {
				MOVS_1RA_DETECCION++;
				// Se manda la medicion para ser guardada en la bd hosteada
				movimientos.remove(mov);
			} else {
				break;
			}
		}
		
		if (MOVS_1RA_DETECCION < CANT_PATADAS_MIN) {
			// Emitir recomendacion
			FECHA_HORA_1RA_DETECCION = LocalDateTime.now();
			FECHA_HORA_PROX_ANALISIS_MOVS_CON_RECO = LocalDateTime.now().plusMinutes(TIEMPO_ANALISIS_MOVS_POSTERIOR_A_RECO);
			analizarPosibleRiesgoPorMovsConReco();
		} else {
			MOVS_1RA_DETECCION = 0;
			ANALIZANDO = false;
		}
	}
	
	public static void analizarPosibleRiesgoPorMovsConReco() {
		
		/* TENER EN CUENTA QUE LA RECO_EJECUTADA TOMARA EL VALOR EN TRUE CUANDO LA PACIENTE INDIQUE EN LA APP
		QUE EJECUTO UNA RECOMENDACION EMITIDA */

		LocalDateTime fechaHoraEjecucionReco = null;
		
		while (!(FECHA_HORA_PROX_ANALISIS_MOVS_CON_RECO.isAfter(LocalDateTime.now()))) {
			if (RECO_EJECUTADA == true) {
				fechaHoraEjecucionReco = LocalDateTime.now();
				FECHA_HORA_PROX_ANALISIS_MOVS_CON_RECO = LocalDateTime.now().plusMinutes(TIEMPO_ANALISIS_MOVS_POSTERIOR_A_RECO);
				RECO_EJECUTADA = false;
			}
			if (movimiento != null) {
				// Guardado en base local
				movimientos.add(movimiento);
			}
		}
		
		for(Medicion mov : movimientos) {
			if (mov.getFechaHora().isBefore(FECHA_HORA_PROX_ANALISIS_MOVS_CON_RECO)) {
				MOVS_2DA_DETECCION++;
				// Se manda la medicion para ser guardada en la bd hosteada
				movimientos.remove(mov);
			} else {
				break;
			}
		}
		
		if (MOVS_2DA_DETECCION < CANT_PATADAS_MIN_POSTERIOR_A_RECO) {
			FECHA_HORA_2DA_DETECCION = LocalDateTime.now();
			System.out.println("Emitir alerta por movimientos anomalos.");
			System.out.println("Fecha y hora del primer analisis: " + FECHA_HORA_1RA_DETECCION + " con " + MOVS_1RA_DETECCION + "movimientos.");
			System.out.println("Fecha y hora del segundo analisis: " + FECHA_HORA_2DA_DETECCION + " con " + MOVS_2DA_DETECCION + "movimientos.");
			System.out.println("Con recomendacion: " + (fechaHoraEjecucionReco != null ? "SI.\nFecha y hora de ejecución de recomendación: "
			+ fechaHoraEjecucionReco : "NO"));
		}
		
		MOVS_1RA_DETECCION = 0;
		MOVS_2DA_DETECCION = 0;
		ANALIZANDO = false;
		
	}

}
