package src.main.java;

import src.main.java.Engine.RuleBuilder;
import src.main.java.Engine.KnowledgeBase;
import src.main.java.Engine.MotorEncadenamientoHaciaAtras;
import src.main.java.Utils.Formulas;
import static src.main.java.Utils.Consts.*;

public class Main {

	public static void main(String[] args) {
	 
		KnowledgeBase base = new KnowledgeBase();
		RuleBuilder generatorRules = new RuleBuilder(base);
		generatorRules.cargarReglas();
		
		// hechos iniciales
		base.addFact(ALTURA, 25.0);
		base.addFact(CANTPUNTOSAPOYO, 5.0);
		base.addFact(DIAMBARRALONG, 5.0);
		base.addFact(DIAMBARRAESTRIBO, 3.0);
		base.addFact(RECUBHORMIGONFILOARM, 1.0);
		base.addFact(CALIDADACERO, 1.2);
		base.addFact(CALIDADHORMIGON, 40.3);
		base.addFact(ANCHO, 5.5);
		base.addFact(CORTENOMINAL, 2.3);
		base.addFact(MOMENTONOMINAL, 2.5);
		base.addFact(SECCION, 300.0);
		
		// Si corte ultimo 10.0 => vigaBienDimensionada: false
		// Si corte ultimo 1.0 => vigaBienDimensionada: true
		base.addFact(CORTEULTIMO, 1.0);	
		base.addFact(MOMENTOULTIMO, 1.3);
		base.addFact(TIEMPO, TIEMPOLENTO);
		base.addFact(COSTO, COSTOMUYCARO);
		base.addFact(ESPACIO, ESPACIOPOCO);
		base.addFact(CARGA, CARGAPOCA);
		base.addFact(ADQUISICION, ADQUISICIONMUYDIFICIL);
		
		Formulas formulas = new Formulas();
		formulas.cargarNuevosHechos(base);
		
		MotorEncadenamientoHaciaAtras motor = new MotorEncadenamientoHaciaAtras(base);
		
		/*
		 * Las hipotesis a ingresar son los nombres de los hechos que producen todas las reglas, como:
		 	VERIFICAALTURA, VERIFICALIMITESECCIONMINIMA, VERIFICASECCION	
			VERIFICACORTE, VERIFICAFLEXION, VIGABIENDIMENSIONADA, REFUERZO, VERIFICAREQFINAL
		 */
		motor.addInitialHypothesis(REFUERZO);
		motor.generateResult();
	
	 
	}

}



 