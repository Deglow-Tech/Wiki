package Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Medicion {
	private float valor;
	private String sujeto;
	private String factor;
	private String unidad;
	private boolean evaluado;
	private LocalDateTime fechaHora;
	
	public Medicion() {
		// TODO Auto-generated constructor stub
	}
	
	public Medicion(float valor, String sujeto, String factor, String unidad, boolean evaluado, LocalDateTime fechaHora) {
		this.valor = valor;
		this.sujeto = sujeto;
		this.factor = factor;
		this.unidad = unidad;
		this.evaluado = evaluado;
		this.fechaHora = fechaHora;
	}

	// Suponiendo el formato: valor,sujeto,factor,unidad,evaluado
	public void obtenerMedicion(String cadenaMedicion) throws ParseException {
		String[] arr = cadenaMedicion.split(",");
		
		this.valor = Float.parseFloat(arr[0]);
		this.sujeto = arr[1];
		this.factor = arr[2];
		this.unidad = arr[3];
		this.evaluado = Boolean.valueOf(arr[4]);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fechaHora = LocalDateTime.parse(arr[5], formatter);
	}
	
	public void setEvaluado(boolean evaluado) {
		this.evaluado = evaluado;
	}
	
	public float getValor() {
		return this.valor;
	}
	
	public String getFactor() {
		return this.factor;
	}
	
	public void imprimirMedicion(int i) {
		System.out.println("MEDICION " + i + ":\nSujeto: " + this.sujeto
				+ "\nFactor: " + this.factor
				+ "\nEvaluado: " + (this.evaluado == true ? "SI" : "NO")
				+ "\nValor: " + this.valor + " " + this.unidad
				+ "\nFecha y hora: " + this.fechaHora.getDayOfMonth() + "/" + this.fechaHora.getMonthValue() + "/"
				+ this.fechaHora.getYear() + " " + this.fechaHora.getHour() + ":" + this.fechaHora.getMinute()
				+ "\n\n");
		
	}
}