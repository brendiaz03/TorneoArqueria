package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arquero {
	
	private String nombre;
	private Integer numeroParticipante;
	private Integer edad;
	private Map<Integer, Disparo> disparos;
	private Boolean isDescalificado;

	public Arquero(String nombre, Integer numeroParticipante, Integer edad, Boolean descalificado) {
		this.nombre=nombre;
		this.numeroParticipante=numeroParticipante;
		this.edad=edad;		
		this.setIsDescalificado(descalificado);
		this.disparos= new HashMap<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroParticipante() {
		return numeroParticipante;
	}

	public void setNumeroParticipante(Integer numeroParticipante) {
		this.numeroParticipante = numeroParticipante;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public void registrarDisparo(Integer numero, Disparo disparo) {
			disparos.put(numero, disparo);
	}

	public Disparo getDisparo(Integer numeroClave) {
		for(Map.Entry<Integer, Disparo> entry : disparos.entrySet()) {
			Integer numero = entry.getKey();
			Disparo d = entry.getValue();
			if(numero == numeroClave) {
				return d;
			}
		}
		return null;
	}
	
	public Integer cantidadDeDisparosInvalidos() {
		Integer contador = 0;
		for(Map.Entry<Integer, Disparo> entry : disparos.entrySet()) {
			Disparo d = entry.getValue();
			if(d.getIsValido() == false) {
				contador++;
			}
		}
		return contador;
	}
	
	public Integer cantidadDeDisparos() {
		Integer contador = 0;
		for(Map.Entry<Integer, Disparo> entry : disparos.entrySet()) {
				contador++;
		}
		return contador;
	}
	
	public Integer puntajeTotal() throws ParticipanteDescalificadoException{
		Integer contadorPuntaje = 0;
		for(Map.Entry<Integer, Disparo> entry : disparos.entrySet()) {
			Disparo d = entry.getValue();
			if(cantidadDeDisparos() == 5  && cantidadDeDisparosInvalidos()>=1) {
				setIsDescalificado(true);
				throw new ParticipanteDescalificadoException("Participante descalificado");
			}else {
				contadorPuntaje = contadorPuntaje + d.getPuntaje();
			}
		}
		return contadorPuntaje;
	}

	public Boolean getIsDescalificado() {
		return isDescalificado;
	}

	public void setIsDescalificado(Boolean isDescalificado) {
		this.isDescalificado = isDescalificado;
	}

}
