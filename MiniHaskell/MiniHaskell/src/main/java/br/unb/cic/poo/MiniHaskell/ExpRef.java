package br.unb.cic.poo.MiniHaskell;

import br.unb.cic.poo.MiniHaskell.visitors.Visitor;

public class ExpRef extends Expressao {
	private String id;
	
	public ExpRef(String id) {
		this.id = id;
	}
	
	public String getId(){
		return id;
	}

	public Valor avaliar() {
		if(!checarTipo()){
			throw new RuntimeException("Variável " + id + " não declarada");
		}
		Expressao exp = AmbienteExecucao.getInstance().consultaReferencia(id);
		
		return exp.avaliar();
	}

	public boolean checarTipo() {
		return !tipo().equals(Tipo.ERROR);
	}

	public Tipo tipo() {
		Expressao exp = AmbienteExecucao.getInstance().consultaReferencia(id);
		if(exp == null) {
			return Tipo.ERROR;
		}
		return exp.tipo();
	}

	public void aceitar(Visitor v) {
		v.visitar(this);
	} 

}
