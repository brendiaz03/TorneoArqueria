package ar.edu.unlam.pb2;

public class Disparo {

	private Double x;
	private Double y;
	private Boolean isValido;
	private Double distanciaAlCentro;
	private Integer puntaje;
	
	public Disparo(Double x, Double y) {
		this.x=x;
		this.y=y;
		this.distanciaAlCentro = Math.sqrt(x * x + y * y);
		getPuntos();
	}

	public Double getDistanciaAlCentro() {
		return distanciaAlCentro;
	}

	public void setDistanciaAlCentro(Double distanciaAlCentro) {
		this.distanciaAlCentro = distanciaAlCentro;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Boolean getIsValido() {
		Integer puntos = getPuntos();
		if(puntos==0) {
			setIsValido(false);
			return false;
		}
		return true;
	}

	public void setIsValido(Boolean isValido) {
		this.isValido = isValido;
	}
	
	public Integer getPuntos() {
        if (getDistanciaAlCentro() <= 0.1) {
        	setPuntaje(1000);
            return getPuntaje();
        } else if (getDistanciaAlCentro() <= 0.2) {
        	setPuntaje(500);
            return getPuntaje();
        } else if (getDistanciaAlCentro() <= 0.3) {
        	setPuntaje(200);
            return getPuntaje();
        } else if (getDistanciaAlCentro() <= 0.4) {
        	setPuntaje(100);
            return getPuntaje();
        } else if (getDistanciaAlCentro() <= 0.5) {
        	setPuntaje(50);
            return getPuntaje();
        } else {
        	setPuntaje(0);
        	return 0;
        }
    }

	public Integer getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}


}
