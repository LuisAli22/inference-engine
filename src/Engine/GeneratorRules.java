package Engine;

import static Engine.Consts.*;

public class GeneratorRules {
	private KnowledgeBase base;
	
	public GeneratorRules(KnowledgeBase base) {
		this.base = base;
	}
	
	public void cargarReglas() {		
		base.addRule(getRVerifAltura());
		base.addRule(getRNoVerifAltura());
		base.addRule(getRVerifLimiteSeccion());
		base.addRule(getRNoVerifLimiteSeccion());
		base.addRule(getRVerifSeccion());
		base.addRule(getRNoVerifSeccion());
		base.addRule(getRVerifCorte());
		base.addRule(getRNoVerifCorte());
		base.addRule(getRVerifFlexion());
		base.addRule(getRNoVerifFlexion());		
		base.addRule(getRVerifReqDisenio());
		base.addRule(getRNoVerifReqDisenio());
		base.addRule(getRefPerfil());
		base.addRule(getRefPlanchaAcero());
		base.addRule(getRefVigaReticulada());
		base.addRule(getRefEnchapeHormigon());
		base.addRule(getRefDobleViga());
		base.addRule(getRefFibra());
		base.addRule(getRefInviable());
		base.addRule(getRVerifReqDisConRefuerzoPlanchaAcero_EnchapeHormigon());
		base.addRule(getRNoVerifReqDisConRefuerzoPlanchaAcero_EnchapeHormigon());
		base.addRule(getRVerifReqDisConRefuerzoPerfil_VigaRet());
		base.addRule(getRNoVerifReqDisConRefuerzoPerfil_VigaRet());
		base.addRule(getRVerifReqDisConRefuerzoDobleViga());
		base.addRule(getRNoVerifReqDisConRefuerzoDobleViga());
		base.addRule(getRVerifReqDisConRefuerzoFibra());
		base.addRule(getRNoVerifReqDisConRefuerzoFibra());
	}
	
	private Rule getRVerifAltura() {
		Rule rule = new Rule(RVERIFALTURA, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(ALTURAMINIMA) <= base.getFactDouble(ALTURA)) {
					base.addFact(VERIFICAALTURA, true);
				}				
			}
		};
		rule.addPremiseName(ALTURA);
		rule.addPremiseName(ALTURAMINIMA);
		rule.agregarNombreConsecuente(VERIFICAALTURA);
		
		return rule;
	}
	
	private Rule getRNoVerifAltura() {
		Rule rule = new Rule(RNOVERIFALTURA, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(ALTURAMINIMA) > base.getFactDouble(ALTURA)) {
					base.addFact(VERIFICAALTURA, false);
				}				
			}
		};
		rule.addPremiseName(ALTURA);
		rule.addPremiseName(ALTURAMINIMA);
		rule.agregarNombreConsecuente(VERIFICAALTURA);
		
		return rule;
	}
	
	private Rule getRVerifLimiteSeccion() {
		Rule rule = new Rule(RVERIFLIMITESECCION, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(SECCIONMINIMA) >= base.getFactDouble(LIMITESECCIONMINIMA)) {
					base.addFact(VERIFICALIMITESECCIONMINIMA, true);
				}				
			}
		};
		rule.addPremiseName(SECCIONMINIMA);
		rule.addPremiseName(LIMITESECCIONMINIMA);
		rule.agregarNombreConsecuente(VERIFICALIMITESECCIONMINIMA);
		
		return rule;
	}
	
	private Rule getRNoVerifLimiteSeccion() {
		Rule rule = new Rule(RNOVERIFLIMITESECCION, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(SECCIONMINIMA) < base.getFactDouble(LIMITESECCIONMINIMA)) {
					base.addFact(VERIFICALIMITESECCIONMINIMA, false);
				}				
			}
		};
		rule.addPremiseName(SECCIONMINIMA);
		rule.addPremiseName(LIMITESECCIONMINIMA);
		rule.agregarNombreConsecuente(VERIFICALIMITESECCIONMINIMA);
		
		return rule;
	}
	
	private Rule getRVerifSeccion() {
		Rule rule = new Rule(RVERIFSECCION, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(SECCIONMINIMA) <= base.getFactDouble(SECCION) &&
						base.getFactBoolean(VERIFICALIMITESECCIONMINIMA)) {
					base.addFact(VERIFICASECCION, true);
				}				
			}
		};
		rule.addPremiseName(SECCIONMINIMA);
		rule.addPremiseName(SECCION);
		rule.addPremiseName(VERIFICALIMITESECCIONMINIMA);
		rule.agregarNombreConsecuente(VERIFICASECCION);
		
		return rule;
	}
	
	private Rule getRNoVerifSeccion() {
		Rule rule = new Rule(RNOVERIFSECCION, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(SECCIONMINIMA) > base.getFactDouble(SECCION) ||
						!base.getFactBoolean(VERIFICALIMITESECCIONMINIMA)) {
					base.addFact(VERIFICASECCION, false);
				}				
			}
		};
		rule.addPremiseName(SECCIONMINIMA);
		rule.addPremiseName(SECCION);
		rule.addPremiseName(VERIFICALIMITESECCIONMINIMA);
		rule.agregarNombreConsecuente(VERIFICASECCION);
		
		return rule;
	}
	
	private Rule getRVerifCorte() {
		Rule rule = new Rule(RVERIFCORTE, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(CAPACIDADCORTE) >= base.getFactDouble(CORTEULTIMO)) {
					base.addFact(VERIFICACORTE, true);
				}				
			}
		};
		rule.addPremiseName(CAPACIDADCORTE);
		rule.addPremiseName(CORTEULTIMO);
		rule.agregarNombreConsecuente(VERIFICACORTE);
		
		return rule;
	}
	
	private Rule getRNoVerifCorte() {
		Rule rule = new Rule(RNOVERIFCORTE, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(CAPACIDADCORTE) < base.getFactDouble(CORTEULTIMO)) {
					base.addFact(VERIFICACORTE, false);
				}				
			}
		};
		rule.addPremiseName(CAPACIDADCORTE);
		rule.addPremiseName(CORTEULTIMO);
		rule.agregarNombreConsecuente(VERIFICACORTE);
		
		return rule;
	}
	
	private Rule getRVerifFlexion() {
		Rule rule = new Rule(RVERIFFLEXION, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(CAPACIDADFLEXION) >= base.getFactDouble(MOMENTOULTIMO)) {
					base.addFact(VERIFICAFLEXION, true);
				}				
			}
		};
		rule.addPremiseName(CAPACIDADFLEXION);
		rule.addPremiseName(MOMENTOULTIMO);
		rule.agregarNombreConsecuente(VERIFICAFLEXION);
		
		return rule;
	}
	
	private Rule getRNoVerifFlexion() {
		Rule rule = new Rule(RNOVERIFFLEXION, base) {			
			@Override
			public void disparar() {
				if(base.getFactDouble(CAPACIDADFLEXION) <base.getFactDouble(MOMENTOULTIMO)) {
					base.addFact(VERIFICAFLEXION, false);
				}				
			}
		};
		rule.addPremiseName(CAPACIDADFLEXION);
		rule.addPremiseName(MOMENTOULTIMO);
		rule.agregarNombreConsecuente(VERIFICAFLEXION);
		
		return rule;
	}
	
	private Rule getRVerifReqDisenio() {
		Rule rule = new Rule(RVERIFREQUISITOSDISEÑO, base) {			
			@Override
			public void disparar() {
				if(base.getFactBoolean(VERIFICAALTURA)  && base.getFactBoolean(VERIFICASECCION) &&
						base.getFactBoolean(VERIFICACORTE) && base.getFactBoolean(VERIFICAFLEXION)) {
					base.addFact(VIGABIENDIMENSIONADA, true);
					base.addFact(REFUERZO, NOREQUIEREREFUERZO);
				}				
			}			
		};
		rule.addPremiseName(VERIFICAALTURA);
		rule.addPremiseName(VERIFICASECCION);
		rule.addPremiseName(VERIFICACORTE);
		rule.addPremiseName(VERIFICAFLEXION);
		rule.agregarNombreConsecuente(VIGABIENDIMENSIONADA);
		rule.agregarNombreConsecuente(REFUERZO);
		
		return rule;
	}
	
	private Rule getRNoVerifReqDisenio() {
		Rule rule = new Rule(RNOVERIFREQUISITOSDISEÑO, base) {			
			@Override
			public void disparar() {
				if(!base.getFactBoolean(VERIFICAALTURA) || !base.getFactBoolean(VERIFICASECCION) ||
						!base.getFactBoolean(VERIFICACORTE) || !base.getFactBoolean(VERIFICAFLEXION)) {
					base.addFact(VIGABIENDIMENSIONADA, false);					
				}				
			}			
		};
		rule.addPremiseName(VERIFICAALTURA);
		rule.addPremiseName(VERIFICASECCION);
		rule.addPremiseName(VERIFICACORTE);
		rule.addPremiseName(VERIFICAFLEXION);
		rule.agregarNombreConsecuente(VIGABIENDIMENSIONADA);
		
		return rule;
	}
	
	private Rule getRefPerfil() {
		Rule rule = new Rule(RREFPERFIL, base) {			
			@Override
			public void disparar() {
				if(!base.getFactBoolean(VIGABIENDIMENSIONADA) && 
						base.getFactString(TIEMPO).equals(TIEMPOMUYRAPIDO) &&
						base.getFactString(COSTO).equals(COSTOCARO) &&
						base.getFactString(ESPACIO).equals(ESPACIOMUCHO) &&
						base.getFactString(CARGA).equals(CARGAMUCHA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONMUYDIFICIL))	{
					base.addFact(REFUERZO, PERFIL);
				}
			}
		};
		rule.addPremiseName(VIGABIENDIMENSIONADA);
		rule.addPremiseName(TIEMPO);
		rule.addPremiseName(COSTO);
		rule.addPremiseName(ESPACIO);
		rule.addPremiseName(CARGA);
		rule.addPremiseName(ADQUISICION);
		rule.agregarNombreConsecuente(REFUERZO);
		
		return rule;
	}
	
	private Rule getRefPlanchaAcero() {
		Rule rule= new Rule(RREFPLANCHAACERO, base) {			
			@Override
			public void disparar() {
				if(!base.getFactBoolean(VIGABIENDIMENSIONADA) && 
						base.getFactString(TIEMPO).equals(TIEMPOMUYRAPIDO) &&
						base.getFactString(COSTO).equals(COSTOCARO) &&
						base.getFactString(ESPACIO).equals(ESPACIOSUFICIENTE) &&
						base.getFactString(CARGA).equals(CARGAMODERADA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONDIFICIL))	{
					base.addFact(REFUERZO, PLANCHAACERO);
				}
			}
		};
		rule.addPremiseName(VIGABIENDIMENSIONADA);
		rule.addPremiseName(TIEMPO);
		rule.addPremiseName(COSTO);
		rule.addPremiseName(ESPACIO);
		rule.addPremiseName(CARGA);
		rule.addPremiseName(ADQUISICION);
		rule.agregarNombreConsecuente(REFUERZO);
		
		return rule;
	}
	
	private Rule getRefVigaReticulada() {
		Rule rule= new Rule(RREFVIGARETICULADA, base) {			
			@Override
			public void disparar() {
				if(!base.getFactBoolean(VIGABIENDIMENSIONADA) && 
						base.getFactString(TIEMPO).equals(TIEMPORAPIDO) &&
						base.getFactString(COSTO).equals(COSTOMUYCARO) &&
						base.getFactString(ESPACIO).equals(ESPACIOMUCHO) &&
						base.getFactString(CARGA).equals(CARGASUFICIENTE) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONDIFICIL))	{
					base.addFact(REFUERZO, VIGARETICULADA);
				}
			}
		};
		rule.addPremiseName(VIGABIENDIMENSIONADA);
		rule.addPremiseName(TIEMPO);
		rule.addPremiseName(COSTO);
		rule.addPremiseName(ESPACIO);
		rule.addPremiseName(CARGA);
		rule.addPremiseName(ADQUISICION);
		rule.agregarNombreConsecuente(REFUERZO);
		
		return rule;
	}
	

	private Rule getRefEnchapeHormigon() {
		Rule rule= new Rule(RREFENCHAPEHORMIGON, base) {			
			@Override
			public void disparar() {
				if(!base.getFactBoolean(VIGABIENDIMENSIONADA) && 
						base.getFactString(TIEMPO).equals(TIEMPOMUYLENTO) &&
						base.getFactString(COSTO).equals(COSTOMUYBARATO) &&
						base.getFactString(ESPACIO).equals(ESPACIOSUFICIENTE) &&
						base.getFactString(CARGA).equals(CARGAPOCA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONFACIL))	{
					base.addFact(REFUERZO, ENCHAPEHORMIGON);
				}
			}
		};
		rule.addPremiseName(VIGABIENDIMENSIONADA);
		rule.addPremiseName(TIEMPO);
		rule.addPremiseName(COSTO);
		rule.addPremiseName(ESPACIO);
		rule.addPremiseName(CARGA);
		rule.addPremiseName(ADQUISICION);
		rule.agregarNombreConsecuente(REFUERZO);
		
		return rule;
	}
	

	private Rule getRefDobleViga() {
		Rule rule= new Rule(RREFDOBLEVIGA, base) {			
			@Override
			public void disparar() {
				if(!base.getFactBoolean(VIGABIENDIMENSIONADA) && 
						base.getFactString(TIEMPO).equals(TIEMPOLENTO) &&
						base.getFactString(COSTO).equals(COSTOBARATO) &&
						base.getFactString(ESPACIO).equals(ESPACIOMUCHO) &&
						base.getFactString(CARGA).equals(CARGAMUCHA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONMUYFACIL))	{
					base.addFact(REFUERZO, DOBLEVIGA);
				}
			}
		};
		rule.addPremiseName(VIGABIENDIMENSIONADA);
		rule.addPremiseName(TIEMPO);
		rule.addPremiseName(COSTO);
		rule.addPremiseName(ESPACIO);
		rule.addPremiseName(CARGA);
		rule.addPremiseName(ADQUISICION);
		rule.agregarNombreConsecuente(REFUERZO);
		
		return rule;
	}
	
	private Rule getRefFibra() {
		Rule rule= new Rule(RREFFIBRA, base) {			
			@Override
			public void disparar() {
				if(!base.getFactBoolean(VIGABIENDIMENSIONADA) && 
						base.getFactString(TIEMPO).equals(TIEMPOLENTO) &&
						base.getFactString(COSTO).equals(COSTOMUYCARO) &&
						base.getFactString(ESPACIO).equals(ESPACIOPOCO) &&
						base.getFactString(CARGA).equals(CARGAPOCA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONMUYDIFICIL))	{
					base.addFact(REFUERZO, FIBRA);
				}
			}
		};
		rule.addPremiseName(VIGABIENDIMENSIONADA);
		rule.addPremiseName(TIEMPO);
		rule.addPremiseName(COSTO);
		rule.addPremiseName(ESPACIO);
		rule.addPremiseName(CARGA);
		rule.addPremiseName(ADQUISICION);
		rule.agregarNombreConsecuente(REFUERZO);
		
		return rule;
	}


	private Rule getRefInviable() {
		Rule rule = new Rule(RREFINVIABLE, base) {			
			@Override
			public void disparar() {
				if(!base.getFactBoolean(VIGABIENDIMENSIONADA) && 
						!(base.getFactString(TIEMPO).equals(TIEMPOMUYRAPIDO) &&
						base.getFactString(COSTO).equals(COSTOCARO) &&
						base.getFactString(ESPACIO).equals(ESPACIOMUCHO) &&
						base.getFactString(CARGA).equals(CARGAMUCHA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONMUYDIFICIL)) &&						
						!(base.getFactString(TIEMPO).equals(TIEMPOMUYRAPIDO) &&
						base.getFactString(COSTO).equals(COSTOCARO) &&
						base.getFactString(ESPACIO).equals(ESPACIOSUFICIENTE) &&
						base.getFactString(CARGA).equals(CARGAMODERADA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONDIFICIL)) &&
						!(base.getFactString(TIEMPO).equals(TIEMPORAPIDO) &&
						base.getFactString(COSTO).equals(COSTOMUYCARO) &&
						base.getFactString(ESPACIO).equals(ESPACIOMUCHO) &&
						base.getFactString(CARGA).equals(CARGASUFICIENTE) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONDIFICIL)) &&
						!(base.getFactString(TIEMPO).equals(TIEMPOMUYLENTO) &&
						base.getFactString(COSTO).equals(COSTOMUYBARATO) &&
						base.getFactString(ESPACIO).equals(ESPACIOSUFICIENTE) &&
						base.getFactString(CARGA).equals(CARGAPOCA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONFACIL)) &&
						!(base.getFactString(TIEMPO).equals(TIEMPOLENTO) &&
						base.getFactString(COSTO).equals(COSTOBARATO) &&
						base.getFactString(ESPACIO).equals(ESPACIOMUCHO) &&
						base.getFactString(CARGA).equals(CARGAMUCHA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONMUYFACIL)) &&
						!(base.getFactString(TIEMPO).equals(TIEMPOLENTO) &&
						base.getFactString(COSTO).equals(COSTOMUYCARO) &&
						base.getFactString(ESPACIO).equals(ESPACIOPOCO) &&
						base.getFactString(CARGA).equals(CARGAPOCA) &&
						base.getFactString(ADQUISICION).equals(ADQUISICIONMUYDIFICIL))	) {
					base.addFact(REFUERZO, NOREQUIEREREFUERZO);
				}
			}
		};
		rule.addPremiseName(VIGABIENDIMENSIONADA);
		rule.addPremiseName(TIEMPO);
		rule.addPremiseName(COSTO);
		rule.addPremiseName(ESPACIO);
		rule.addPremiseName(CARGA);
		rule.addPremiseName(ADQUISICION);
		rule.agregarNombreConsecuente(REFUERZO);

		return rule;
	}
	
	private Rule getRVerifReqDisConRefuerzoPlanchaAcero_EnchapeHormigon() {
		Rule rule = new Rule(RVERIFREQDISREF_PLANCHAACERO_ENCHAPEHORMIGON, base) {			
			@Override
			public void disparar() {
				String refuerzo = base.getFactString(REFUERZO);
				if( (refuerzo.equals(PLANCHAACERO) || refuerzo.equals(ENCHAPEHORMIGON)) &&
						((base.getFactDouble(CAPACIDADCORTE) / base.getFactDouble(CORTEULTIMO)*0.75) >= 1.0) &&
						((base.getFactDouble(CAPACIDADFLEXION) / 0.75*base.getFactDouble(MOMENTOULTIMO)) >= 1.0) &&
						(1.5*base.getFactDouble(SECCION) >= base.getFactDouble(SECCIONMINIMA))
						) {
					base.addFact(VERIFICAREQFINAL, true);
				}
							
			}
		};
		rule.addPremiseName(REFUERZO);
		rule.addPremiseName(CAPACIDADCORTE);
		rule.addPremiseName(CORTEULTIMO);
		rule.addPremiseName(CAPACIDADFLEXION);
		rule.addPremiseName(MOMENTOULTIMO);
		rule.addPremiseName(SECCION);
		rule.addPremiseName(SECCIONMINIMA);
		rule.agregarNombreConsecuente(VERIFICAREQFINAL);
		
		return rule;
	}


	private Rule getRNoVerifReqDisConRefuerzoPlanchaAcero_EnchapeHormigon() {
		Rule rule = new Rule(RNOVERIFREQDISREF_PLANCHAACERO_ENCHAPEHORMIGON, base) {			
			@Override
			public void disparar() {
				String refuerzo = base.getFactString(REFUERZO);
				if( (refuerzo.equals(PLANCHAACERO) || refuerzo.equals(ENCHAPEHORMIGON)) &&
						( ((base.getFactDouble(CAPACIDADCORTE) / base.getFactDouble(CORTEULTIMO)*0.75) < 1.0) ||
						((base.getFactDouble(CAPACIDADFLEXION) / 0.75*base.getFactDouble(MOMENTOULTIMO)) < 1.0) ||
						(1.5*base.getFactDouble(SECCION) < base.getFactDouble(SECCIONMINIMA)))					
						) {
					base.addFact(VERIFICAREQFINAL, false);
				}
							
			}
		};
		rule.addPremiseName(REFUERZO);		
		rule.addPremiseName(CAPACIDADCORTE);
		rule.addPremiseName(CORTEULTIMO);
		rule.addPremiseName(CAPACIDADFLEXION);
		rule.addPremiseName(MOMENTOULTIMO);
		rule.addPremiseName(SECCION);
		rule.addPremiseName(SECCIONMINIMA);
		rule.agregarNombreConsecuente(VERIFICAREQFINAL);
		
		return rule;
	}
	

	private Rule getRVerifReqDisConRefuerzoPerfil_VigaRet() {
		Rule rule = new Rule(RVERIFREQDISREF_PERFIL_VIGARETIC, base) {			
			@Override
			public void disparar() {
				String refuerzo = base.getFactString(REFUERZO);
				if( (refuerzo.equals(PERFIL) || refuerzo.equals(VIGARETICULADA)) &&
						((base.getFactDouble(CAPACIDADCORTE) / base.getFactDouble(CORTEULTIMO)*0.5) >= 1.0) &&
						((base.getFactDouble(CAPACIDADFLEXION) / 0.5*base.getFactDouble(MOMENTOULTIMO)) >= 1.0) &&
						(10*base.getFactDouble(SECCION) >= base.getFactDouble(SECCIONMINIMA))
						) {
					base.addFact(VERIFICAREQFINAL, true);
				}
							
			}
		};
		rule.addPremiseName(REFUERZO);		
		rule.addPremiseName(CAPACIDADCORTE);
		rule.addPremiseName(CORTEULTIMO);
		rule.addPremiseName(CAPACIDADFLEXION);
		rule.addPremiseName(MOMENTOULTIMO);
		rule.addPremiseName(SECCION);
		rule.addPremiseName(SECCIONMINIMA);
		rule.agregarNombreConsecuente(VERIFICAREQFINAL);
		
		return rule;
	}


	private Rule getRNoVerifReqDisConRefuerzoPerfil_VigaRet() {
		Rule rule = new Rule(RNOVERIFREQDISREF_PERFIL_VIGARETIC, base) {			
			@Override
			public void disparar() {
				String refuerzo = base.getFactString(REFUERZO);
				if( (refuerzo.equals(PERFIL) || refuerzo.equals(VIGARETICULADA)) &&
						( ((base.getFactDouble(CAPACIDADCORTE) / base.getFactDouble(CORTEULTIMO)*0.5) < 1.0) ||
						((base.getFactDouble(CAPACIDADFLEXION) / 0.5*base.getFactDouble(MOMENTOULTIMO)) < 1.0) ||
						(10*base.getFactDouble(SECCION) < base.getFactDouble(SECCIONMINIMA)))
						) {
					base.addFact(VERIFICAREQFINAL, false);
				}
							
			}
		};
		rule.addPremiseName(REFUERZO);		
		rule.addPremiseName(CAPACIDADCORTE);
		rule.addPremiseName(CORTEULTIMO);
		rule.addPremiseName(CAPACIDADFLEXION);
		rule.addPremiseName(MOMENTOULTIMO);
		rule.addPremiseName(SECCION);
		rule.addPremiseName(SECCIONMINIMA);
		rule.agregarNombreConsecuente(VERIFICAREQFINAL);
		
		return rule;
	}
	

	private Rule getRVerifReqDisConRefuerzoDobleViga() {
		Rule rule = new Rule(RVERIFREQDISREF_DOBLEVIGA, base) {			
			@Override
			public void disparar() {				
				if( base.getFactString(REFUERZO).equals(DOBLEVIGA) &&
						((base.getFactDouble(CAPACIDADCORTE) / base.getFactDouble(CORTEULTIMO)*0.5) >= 1.0) &&
						((base.getFactDouble(CAPACIDADFLEXION) / 0.5*base.getFactDouble(MOMENTOULTIMO)) >= 1.0) &&
						(2*base.getFactDouble(SECCION) >= base.getFactDouble(SECCIONMINIMA))
						) {
					base.addFact(VERIFICAREQFINAL, true);
				}
							
			}
		};
		rule.addPremiseName(REFUERZO); 		
		rule.addPremiseName(CAPACIDADCORTE); 
		rule.addPremiseName(CORTEULTIMO); 
		rule.addPremiseName(CAPACIDADFLEXION); 
		rule.addPremiseName(MOMENTOULTIMO); 
		rule.addPremiseName(SECCION); 
		rule.addPremiseName(SECCIONMINIMA); 
		rule.agregarNombreConsecuente(VERIFICAREQFINAL);
		
		return rule;
	}
	
	private Rule getRNoVerifReqDisConRefuerzoDobleViga() {
		Rule rule = new Rule(RNOVERIFREQDISREF_DOBLEVIGA, base) {			
			@Override
			public void disparar() {				
				if( base.getFactString(REFUERZO).equals(DOBLEVIGA) &&
						( ((base.getFactDouble(CAPACIDADCORTE) / base.getFactDouble(CORTEULTIMO)*0.5) < 1.0) ||
						((base.getFactDouble(CAPACIDADFLEXION) / 0.5*base.getFactDouble(MOMENTOULTIMO)) < 1.0) ||
						(2*base.getFactDouble(SECCION) < base.getFactDouble(SECCIONMINIMA)))
						) {
					base.addFact(VERIFICAREQFINAL, false);
				}
							
			}
		};
		rule.addPremiseName(REFUERZO); 		
		rule.addPremiseName(CAPACIDADCORTE); 
		rule.addPremiseName(CORTEULTIMO); 
		rule.addPremiseName(CAPACIDADFLEXION); 
		rule.addPremiseName(MOMENTOULTIMO); 
		rule.addPremiseName(SECCION); 
		rule.addPremiseName(SECCIONMINIMA); 
		rule.agregarNombreConsecuente(VERIFICAREQFINAL);
		
		return rule;
	}

	private Rule getRVerifReqDisConRefuerzoFibra() {
		Rule rule = new Rule(RVERIFREQDISREF_FIBRA, base) {			
			@Override
			public void disparar() {				
				if( base.getFactString(REFUERZO).equals(FIBRA) &&
						((base.getFactDouble(CAPACIDADCORTE) / base.getFactDouble(CORTEULTIMO)*0.9) >= 1.0) &&
						((base.getFactDouble(CAPACIDADFLEXION) / 0.9*base.getFactDouble(MOMENTOULTIMO)) >= 1.0) &&
						(1.2*base.getFactDouble(SECCION) >= base.getFactDouble(SECCIONMINIMA))
						) {
					base.addFact(VERIFICAREQFINAL, true);
				}
							
			}
		};
		rule.addPremiseName(REFUERZO); 		
		rule.addPremiseName(CAPACIDADCORTE); 
		rule.addPremiseName(CORTEULTIMO); 
		rule.addPremiseName(CAPACIDADFLEXION); 
		rule.addPremiseName(MOMENTOULTIMO); 
		rule.addPremiseName(SECCION); 
		rule.addPremiseName(SECCIONMINIMA); 
		rule.agregarNombreConsecuente(VERIFICAREQFINAL);
		
		return rule;
	}
	
	private Rule getRNoVerifReqDisConRefuerzoFibra() {
		Rule rule = new Rule(RNOVERIFREQDISREF_FIBRA, base) {			
			@Override
			public void disparar() {				
				if( base.getFactString(REFUERZO).equals(FIBRA) &&
						( ((base.getFactDouble(CAPACIDADCORTE) / base.getFactDouble(CORTEULTIMO)*0.9) < 1.0) ||
						((base.getFactDouble(CAPACIDADFLEXION) / 0.9*base.getFactDouble(MOMENTOULTIMO)) < 1.0) ||
						(1.2*base.getFactDouble(SECCION) < base.getFactDouble(SECCIONMINIMA)))
						) {
					base.addFact(VERIFICAREQFINAL, false);
				}
							
			}
		};
		rule.addPremiseName(REFUERZO); 		
		rule.addPremiseName(CAPACIDADCORTE); 
		rule.addPremiseName(CORTEULTIMO); 
		rule.addPremiseName(CAPACIDADFLEXION); 
		rule.addPremiseName(MOMENTOULTIMO); 
		rule.addPremiseName(SECCION); 
		rule.addPremiseName(SECCIONMINIMA); 
		rule.agregarNombreConsecuente(VERIFICAREQFINAL);
		
		return rule;
	}
}










