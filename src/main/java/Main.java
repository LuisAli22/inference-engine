import Engine.GeneratorRules;
import Engine.KnowledgeBase;
import Engine.MotorEncadenamientoHaciaAtras;
import Utils.Formulas;

import static Utils.Consts.*;

public class Main {

	public static void main(String[] args) {
	 
		KnowledgeBase base = new KnowledgeBase();
		GeneratorRules generatorRules = new GeneratorRules(base);
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
		base.addFact(CORTEULTIMO, 10.0);	
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
	
		/*
		 * Explicacion:
		 * 
		 *    ****** 
		 * Si todas las premisas que necesita una regla estan en la Base conocimiento (BC), entonces la regla
		 * puede ser disparada, aqui aparecen dos casos: 
		 * 1) Si los antecedentes cumplen la regla, despues que se dispare la regla se introduce 
		 *    un nuevo hecho en la BC 
		 * 2) Si los antecedentes No cumplen la regla despues que se dispare la regla 
		 *    NO se introduce ningun un nuevo hecho en la BC
		 *    
		 *    ******
		 * El motor de encadenamiento hacia atras necesita verificar si se cumple un objetivo o hipotesis (hecho final),
		 * como resultado la hipotesis inicial puede ser:
		 * "Verificada", es decir que se logro deducir un hecho, con dicho nombre, y se agrego a la BC
		 * o puede ser "No Verificada", no se pudo deducir...
		 * 
		 * 
		 * 	  *****
		 * Las reglas se encuentran encadenadas en base a la premisa que producen, por lo que forman un arbol
		 * El motor recorre recursivamente dicho arbol, realizando una busqueda en profundidad. 
		 * Si hay mas de dos reglas a disparar, el motor dispara las reglas segun su orden.  
		 * 
		 */
	}

}



 