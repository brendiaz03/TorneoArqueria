package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Torneo {
	
	private String nombre;
	private List<Arquero> arqueros;

	public Torneo(String nombre) {
		this.nombre=nombre;
		this.arqueros= new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void ingresarParticipante(Arquero arquero) throws ArqueroRepetidoException{
			if(existeArquero(arquero.getNumeroParticipante())) {
				throw new ArqueroRepetidoException("Arquero ya ingresado anteriormente");
			}else {
				arqueros.add(arquero);
			}
	}
	
	public Boolean existeArquero(Integer numeroArquero) {
		for(Arquero a : arqueros) {
			if(a.getNumeroParticipante().equals(numeroArquero)) {
				return true;
			}
		}
		return false;
	}

	public Arquero getArquero(Integer numeroArquero) {
		for(Arquero a: arqueros) {
			if(a.getNumeroParticipante().equals(numeroArquero)) {
				return a;
			}
		}
		return null;
	}

	public TreeMap<Integer, Arquero> podioDeAruqeros() throws ParticipanteDescalificadoException {
		TreeMap <Integer, Arquero> arqueross = new TreeMap<>(Comparator.reverseOrder());
		for(Arquero a : arqueros) {
			arqueross.put(a.puntajeTotal(), a);
		}
		return arqueross;
	}


}
