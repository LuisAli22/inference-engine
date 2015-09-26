package Engine;


import java.util.ArrayList;
import java.util.List;

public abstract class Rule  {	
	private String name;
	private List<String> nombrePremisas;
	private KnowledgeBase base;	
	private List<String> nombreConsecuentes;
	
	public Rule(String nameRule, KnowledgeBase base) {
		this.name = nameRule;
		this.nombrePremisas = new ArrayList<>();
		this.base = base;		
		nombreConsecuentes = new ArrayList<>();
	}
	
	public void agregarNombreConsecuente(String name) {
		nombreConsecuentes.add(name);
	}
	
	public void addPremiseName(String name) {
		nombrePremisas.add(name);
	}
	
	// precondicion: la base de conocimientos debe poseer todos las premisas 
	// que necesita la regla para ser disparada
	//
	// Si todas las premisas cumplen la condicion definida en la regla,
	// se agregará a la base de conocimientos un nuevo hecho ( premisa del consecuente)
	public abstract void disparar();
	
	
	// si todas las premisas que necesita la regla se encuentran en la base de conocimiento
	// se retorna 'true' caso contrario 'false'
	public boolean tienesTodasLasPremisas() {
		for(String nombrePremisa: nombrePremisas) {
			if(!base.existsFact(nombrePremisa))
				return false;
		}
		return true;
	}
	
	// retorna las premisas que faltan para que pueda ser disparada la regla
	public List<String> obtenerPremisasFaltantes() {
		List<String> premisas = new ArrayList<String>();
		
		for(String hecho: nombrePremisas) {
			if(!base.existsFact(hecho))
				premisas.add(hecho);
		}
		return premisas;
	}
	
	// retorna una cadena de string de las premisas que necesita la regla y que estan contenidas en la Base Conocim.
	public String obtenerListadoPremisasPresentes() {
		String result = "";		
		for(String hecho: nombrePremisas) {
			if(base.existsFact(hecho))
				result += hecho + ", ";
		}
		return result;
	}
	
	public String obtenerListadoPremisasNoPresentes() {
		String result = "";		
		for(String hecho: nombrePremisas) {
			if(!base.existsFact(hecho))
				result += hecho + ", ";
		}
		return result;
	}
 
	
	public List<String> getNombresConsecuentes() {
		return nombreConsecuentes;
	}
	
	public boolean contieneConsecuente(String name) {
		return nombreConsecuentes.contains(name);		
	}
	
	public String getNameRule() {
		return name;
	}
	
	
}
