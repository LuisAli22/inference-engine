package Utils;

import Engine.KnowledgeBase;

import static Utils.Consts.*;

public class Formulas {
	
	public void cargarNuevosHechos(KnowledgeBase base) {
		double altura = base.getFactDouble(ALTURA);
		double ptosApoyo = base.getFactDouble(CANTPUNTOSAPOYO);
		double diamBarraLong = base.getFactDouble(DIAMBARRALONG);
		double diamBarraEstribos = base.getFactDouble(DIAMBARRAESTRIBO);
		double recubrHormigon = base.getFactDouble(RECUBHORMIGONFILOARM);
		double calidadAcero = base.getFactDouble(CALIDADACERO);
		double calidadHormigon = base.getFactDouble(CALIDADHORMIGON);
		double ancho = base.getFactDouble(ANCHO);
		double corteNominal = base.getFactDouble(CORTENOMINAL);
		double momentoNominal = base.getFactDouble(MOMENTONOMINAL);
		
		double alturaUtil = getAlturaUtil(altura, diamBarraLong, diamBarraEstribos, recubrHormigon);
		
		base.addFact(ALTURAMINIMA, getAlturaMinima(altura, ptosApoyo));		
		base.addFact(ALTURAUTIL, alturaUtil);
		base.addFact(SECCIONMINIMA, getSeccionMinima(alturaUtil, calidadAcero, calidadHormigon, ancho));
		base.addFact(LIMITESECCIONMINIMA, getLimiteSeccionMinima(alturaUtil, calidadAcero, ancho));
		base.addFact(CAPACIDADCORTE, getCapacidadCorte(corteNominal));
		base.addFact(CAPACIDADFLEXION, getCapacidadFlexion(momentoNominal));
	}
	
	public double getAlturaMinima(double altura, double ptosApoyo) {
		if(ptosApoyo == 1)
			return altura/8;
		if(ptosApoyo == 2)
			return altura/16;
		if(ptosApoyo == 3)
			return altura/18.5;
		return altura/21;		
	}
	
	public double getAlturaUtil(double altura, double diamBarraLong, double diamBarraEstribos, 
			double recubrHormigon) {
		return altura - (diamBarraLong/2) - diamBarraEstribos - recubrHormigon;
	}
	
	public double getSeccionMinima(double alturaUtil, double calidadAcero, double calidadHormigon,
			double ancho) {
		return (Math.sqrt(calidadHormigon)/(4*calidadAcero))*ancho*alturaUtil;
	}
	
	public double getLimiteSeccionMinima(double alturaUtil, double calidadAcero, double ancho) {
		return (1.4/calidadAcero)*ancho*alturaUtil;
	}
	
	public double getCapacidadCorte(double corteNominal) {
		return 0.75*corteNominal;
	}
	
	public double getCapacidadFlexion(double momentoNominal) {
		return 0.9*momentoNominal;
	}
}
