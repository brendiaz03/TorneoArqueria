package ar.edu.unlam.pb2;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TestTorneoArqueria {
	
	@Test
	public void queSePuedanRegistrarLosArqueros() throws ArqueroRepetidoException {
		Arquero arquero1= new Arquero("Brenda", 42156466, 25, false);
		Arquero arquero2= new Arquero("Brend", 42156467, 24, false);
		Arquero arquero3= new Arquero("Bren", 42156468, 23, false);
		Arquero arquero4= new Arquero("Bre", 42156469, 22, false);
		Arquero arquero5= new Arquero("Br", 42156470, 21, false);
		
		Torneo torneo = new Torneo("Torneo De Bren");
		
		torneo.ingresarParticipante(arquero1);
		torneo.ingresarParticipante(arquero2);
		torneo.ingresarParticipante(arquero3);
		torneo.ingresarParticipante(arquero4);
		torneo.ingresarParticipante(arquero5);
		
		assertEquals(arquero1, torneo.getArquero(42156466));
		assertEquals(arquero2, torneo.getArquero(42156467));
		assertEquals(arquero3, torneo.getArquero(42156468));
		assertEquals(arquero4, torneo.getArquero(42156469));
		assertEquals(arquero5, torneo.getArquero(42156470));
	}
	
	@Test (expected = ArqueroRepetidoException.class)
	public void queAlIngresarUnArqueroRepetidoSeLanceUnaException() throws ArqueroRepetidoException {
		Arquero arquero= new Arquero("Brenda", 42156466, 25, false);
		
		Torneo torneo = new Torneo("Torneo De Bren");
		
		torneo.ingresarParticipante(arquero);
		torneo.ingresarParticipante(arquero);
	}
	
	@Test
	public void queSePuedanRegistrarDisparosDeUnArqueroConSusPuntos() throws ArqueroRepetidoException {
		Torneo torneo = new Torneo("Torneo De Bren");
		
		Arquero arquero= new Arquero("Brenda", 42156466, 25, false);
		
		Disparo disparo1 = new Disparo(0.0,0.1); //1000 puntos
		Disparo disparo2 = new Disparo(0.2,0.0); //500 puntos
		Disparo disparo3 = new Disparo(0.2,0.1); //200 puntos
		Disparo disparo4 = new Disparo(0.3,0.1); //100 puntos	
		Disparo disparo5 = new Disparo(0.2,0.4); //50 puntos
				
		torneo.ingresarParticipante(arquero);
		
		torneo.getArquero(42156466).registrarDisparo(1, disparo1);
		torneo.getArquero(42156466).registrarDisparo(2, disparo2);
		torneo.getArquero(42156466).registrarDisparo(3, disparo3);
		torneo.getArquero(42156466).registrarDisparo(4, disparo4);
		torneo.getArquero(42156466).registrarDisparo(5, disparo5);
		
		assertEquals(arquero, torneo.getArquero(42156466));
		assertEquals(1000, torneo.getArquero(42156466).getDisparo(1).getPuntos(), 0);	
		assertEquals(500, torneo.getArquero(42156466).getDisparo(2).getPuntos(), 0);	
		assertEquals(200, torneo.getArquero(42156466).getDisparo(3).getPuntos(), 0);	
		assertEquals(100, torneo.getArquero(42156466).getDisparo(4).getPuntos(), 0);	
		assertEquals(50, torneo.getArquero(42156466).getDisparo(5).getPuntos(), 0);	
	}
	
	@Test
	public void queSiElParticipanteHaceUnTiroInvalidoSeSumen0Puntos() throws ArqueroRepetidoException {
		Torneo torneo = new Torneo("Torneo De Bren");
		
		Arquero arquero= new Arquero("Brenda", 42156466, 25, false);
		
		Disparo disparo1 = new Disparo(1.0,1.1); //Invalido
		
		torneo.ingresarParticipante(arquero);
		torneo.getArquero(42156466).registrarDisparo(1, disparo1);
		assertEquals(0, torneo.getArquero(42156466).getDisparo(1).getPuntos(),0);
	}
	
	@Test (expected = ParticipanteDescalificadoException.class)
	public void queSiElParticipanteNoTiene5TirosValidosSeLanceUnaExceptionYSeDescalifiqueCuandoSePideElPuntaje() throws ArqueroRepetidoException, ParticipanteDescalificadoException {
		Torneo torneo = new Torneo("Torneo De Bren");
		
		Arquero arquero= new Arquero("Brenda", 42156466, 25, false);
		
		Disparo disparo1 = new Disparo(1.0,0.1); //Invalido
		Disparo disparo2 = new Disparo(0.2,0.0); //500 puntos
		Disparo disparo3 = new Disparo(0.2,0.1); //200 puntos
		Disparo disparo4 = new Disparo(0.3,0.1); //100 puntos	
		Disparo disparo5 = new Disparo(0.2,0.4); //50 puntos
				
		torneo.ingresarParticipante(arquero);
		
		torneo.getArquero(42156466).registrarDisparo(1, disparo1);
		torneo.getArquero(42156466).registrarDisparo(2, disparo2);
		torneo.getArquero(42156466).registrarDisparo(3, disparo3);
		torneo.getArquero(42156466).registrarDisparo(4, disparo4);
		torneo.getArquero(42156466).registrarDisparo(5, disparo5);
		
		torneo.getArquero(42156466).puntajeTotal();
	}
	
	@Test
	public void queSePuedanVerEnElPodioLosPrimeros3MayoresPuntajesOrdenados() throws ArqueroRepetidoException, ParticipanteDescalificadoException {
		Torneo torneo = new Torneo("Torneo De Bren");
		
		Arquero arquero1= new Arquero("Brenda", 42156466, 25, false);
		Arquero arquero2= new Arquero("Brend", 42156467, 24, false);
		Arquero arquero3= new Arquero("Bren", 42156468, 23, false);
		
		
		Disparo disparo1 = new Disparo(0.0,0.1); //1000 puntos
		Disparo disparo2 = new Disparo(0.2,0.0); //500 puntos
		Disparo disparo3 = new Disparo(0.2,0.1); //200 puntos
		Disparo disparo4 = new Disparo(0.3,0.1); //100 puntos	
		Disparo disparo5 = new Disparo(0.2,0.4); //50 puntos
		
		torneo.ingresarParticipante(arquero1);
		torneo.ingresarParticipante(arquero2);
		torneo.ingresarParticipante(arquero3);
		
		torneo.getArquero(42156466).registrarDisparo(1, disparo1);
		torneo.getArquero(42156466).registrarDisparo(5, disparo5);//1050 puntos
		
		torneo.getArquero(42156468).registrarDisparo(3, disparo3);
		torneo.getArquero(42156468).registrarDisparo(3, disparo3);//400 puntos
		
		torneo.getArquero(42156467).registrarDisparo(2, disparo2);
		torneo.getArquero(42156467).registrarDisparo(2, disparo2);//1000 puntos
		
		TreeMap<Integer, Arquero> podioDeArqueros =  new TreeMap<>(Comparator.reverseOrder());
		podioDeArqueros.put(arquero1.puntajeTotal(), arquero1);
		podioDeArqueros.put(arquero2.puntajeTotal(), arquero2);
		podioDeArqueros.put(arquero3.puntajeTotal(), arquero3);
		
		assertEquals(podioDeArqueros, torneo.podioDeAruqeros());
	}
	
}
