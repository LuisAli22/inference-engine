package src.main.java.Engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KnowledgeBase {	
	private HashMap <String, String> hechosString;
	private HashMap <String, Double> hechosDouble;
	private HashMap <String, Boolean> hechosBoolean;
	private List<Rule> reglas;
	
	public KnowledgeBase() {
		reglas = new ArrayList<>();
		hechosDouble = new HashMap<>();
		hechosString = new HashMap<>();
		hechosBoolean = new HashMap<>();
	}
	
	public void addRule(Rule rule) {
		reglas.add(rule);
	}
	
	public void addFact(String name, Boolean value) {
		if(hechosDouble.containsKey(name)) {
			hechosDouble.remove(name);
		}
		if(hechosString.containsKey(name)) {
			hechosBoolean.remove(name);
		}
		hechosBoolean.put(name, value);
	}
	
	public void addFact(String name, String value) {
		if(hechosDouble.containsKey(name)) {
			hechosDouble.remove(name);
		}
		if(hechosBoolean.containsKey(name)) {
			hechosBoolean.remove(name);
		}
		hechosString.put(name, value);
	}
	
	public void addFact(String name, Double value) {
		if(hechosString.containsKey(name)) {
			hechosString.remove(name);
		}
		if(hechosBoolean.containsKey(name)) {
			hechosBoolean.remove(name);
		}
		hechosDouble.put(name, value);
	}
	
	public Double getFactDouble(String key) {
		return hechosDouble.get(key);
	}
	
	public String getFactString(String key) {
		return hechosString.get(key);
	}
		
	public Boolean getFactBoolean(String key) {
		return hechosBoolean.get(key);
	}
	
	public String getFact(String key) {
		if(hechosDouble.containsKey(key))
			return hechosDouble.get(key).toString();
		if(hechosBoolean.containsKey(key))
			return hechosBoolean.get(key).toString();
		
		return hechosString.get(key);
	}
	
	public List<Rule> getRules() {
		return reglas;
	}
	
	public boolean existsFact(String name) {
		if(hechosString.keySet().contains(name ) || hechosDouble.keySet().contains(name)
				|| hechosBoolean.keySet().contains(name)) {
			return true;
		}
		return false;
	}
	
	public void imprimirHechos() {		
		for(String s: hechosDouble.keySet()) {
			System.out.println("hecho: " + s + ", valor: " + hechosDouble.get(s));
		}
		
		for(String s: hechosString.keySet()) {
			System.out.println("hecho: " + s + ", valor: " + hechosDouble.get(s));
		}
		
		for(String s: hechosBoolean.keySet()) {
			System.out.println("hecho: " + s + ", valor: " + hechosDouble.get(s));
		}
	}
}
