package Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MotorEncadenamientoHaciaAtras {
	private List<String> history;
	private KnowledgeBase base;
	private String initialHyposthesis;	
	
	public MotorEncadenamientoHaciaAtras(KnowledgeBase base) {
		history = new ArrayList<String>();
		this.base = base;
	}
	
	public void addInitialHypothesis(String hypothesis) {
		//stackHypothesis.push(hypothesis);
		initialHyposthesis = hypothesis;
	}
	
	public void generateResult() {
		if(initialHyposthesis != null) {		 
			execute(initialHyposthesis);
			Iterator<String> iter = history.iterator();
			Collections.reverse(history);
			while(iter.hasNext()) {
				System.out.println(iter.next());
			}
		}			 
		else 
			System.out.println("No se ingreso una hipotesis inicial");
	}
	
		
	// retorna true si la hipotesi fue verificada, es decir fueron agregada a la BC
	// caso contrario retorna false
	public boolean execute(String hypothesis)  {
		StringBuffer result = new StringBuffer("\n ******Hipotesis: \"" + hypothesis + "\" ******\n ");
		
		// si la hipotesis esta en la base de conoc. se finaliza la ejecucion
		if(base.existsFact(hypothesis)) {
			result.append(" fue verificada.");
			history.add(result.toString());
			return true;
		} else {
			List<Rule> filteredRules = getFilteredRules(hypothesis);
			// como BC no contiene la HP, se filtran todas las reglas que producen la hipotesis (consecuente)
			
			if(filteredRules.size() > 0) {
				result.append("\n\tReglas a aplicar: ");
				for(Rule rule: filteredRules) {
					result.append((filteredRules.indexOf(rule) + 1) + "- " + rule.getNameRule() + ", ");
				}
				for(Rule rule: filteredRules) {
					result.append("\n\n\t\t -> Regla evaluada: " + (filteredRules.indexOf(rule) + 1) + "- " + 
							rule.getNameRule());
							//"\n\t\t\tPremisas completas: " + rule.obtenerListadoPremisasPresentes() + 
							//"\n\t\t\tPremisas Incompletas: " + rule.obtenerListadoPremisasNoPresentes());
					
					//  la decision es analizar cada regla de acuerdo a su orden
					// si la regla contiene todas sus premisas, entonces se dispara (se PUEDE producir un nuevo hecho)
					// pero no todas las reglas que contienen todas sus reglas resultar verdadero, con lo cual es necesario disparar todas
					if(rule.tienesTodasLasPremisas()) {
						rule.disparar();
						if(base.existsFact(hypothesis)) {
							// si el hecho existe entonces la regla se disparo correctamente y ya se obtuvo un resultado
							// evitando ejecutarse las posibles reglas restantes
							result.append("\n\t\t    Esta regla se disparo y se agrego a la BC el hecho: " + 
									hypothesis + "\": " + base.getFact(hypothesis) + 
									"\n Por lo tanto Hipotesis \"" + hypothesis + "\" fue verificada");
							history.add(result.toString());
							return true;
						} else {
							result.append("\n\t\t    Esta regla se disparo, pero NO se agrego ningun hecho a la BC.");									
						}
					} else {
						// como la regla no puede ser disparada, se generarán nuevas hipotesis a partir de las premisas faltantes
						List<String> newHypothesis = rule.obtenerPremisasFaltantes();
						
						boolean todasOK = true; //se considera que se puedo hallar todas las premisas
						
						// eleccion de premisas: se evaluan segun su orden
						for(String hip: newHypothesis) {
							if(!execute(hip)) {
								todasOK = false;
							}							
						}						
						if(todasOK) { // como se encontraron todas las premisas se verifica la regla
							rule.disparar();
							if(base.existsFact(hypothesis)) {						
								result.append("\n\t\t" + "    Esta regla se disparo y se agrego a la BC el hecho: " + 
										hypothesis + "\": " + base.getFact(hypothesis) + 
										"\n Por lo tanto Hipotesis \"" + hypothesis + "\" fue verificada");
								history.add(result.toString());
								return true;
							} else {
								result.append("\n\t\t    Esta regla se disparo, pero NO se agrego ningun hecho a la BC.");									
							}
						} else {
							result.append("\n\t\t    Esta regla No se disparo porque faltan premisas.");
						}						
					}
				}
			} else {
				result.append("\n La Hipotesis " + hypothesis +
						" no tiene reglas que la produzcan ni se encuentra en la BC.");
			}
		
			
		}
		result.append("\n --> Hipotesis " + hypothesis + " no Verificada\n");
		history.add(result.toString());
		return false; /////
	}
	
	
	
	// retorna las reglas que contienen un consecuente igual a la hipotesis
	private List<Rule> getFilteredRules(String hypothesis) {
		List<Rule> filteredRules = new ArrayList<Rule>();
		
		for(Rule rule: base.getRules()) {
			if(rule.contieneConsecuente(hypothesis)){
				filteredRules.add(rule);
			}
		}
		return filteredRules;
	}
	
}
