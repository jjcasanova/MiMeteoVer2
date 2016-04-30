
public class Sensores {
	private double temperaturaAgua, temperaturaAire, humedadRelativa;
	private int presionAtmosferica;
	protected Sensores(){
		
	}
	protected double getTemperaturaAgua() {
		return temperaturaAgua;
	}
	protected void setTemperaturaAgua(double temperaturaAgua) {
		this.temperaturaAgua = temperaturaAgua;
	}
	protected double getTemperaturaAire() {
		return temperaturaAire;
	}
	protected void setTemperaturaAire(double temperaturaAire) {
		this.temperaturaAire = temperaturaAire;
	}
	protected double getHumedadRelativa() {
		return humedadRelativa;
	}
	protected void setHumedadRelativa(double humedadRelativa) {
		this.humedadRelativa = humedadRelativa;
	}
	protected int getPresionAtmosferica() {
		return presionAtmosferica;
	}
	protected void setPresionAtmosferica(int presionAtmosferica) {
		this.presionAtmosferica = presionAtmosferica;
	}
	
}
