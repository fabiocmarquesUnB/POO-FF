package br.unb.cic.poo.MiniHaskell;

import br.unb.cic.poo.MiniHaskell.visitors.Visitor;

public class InserirNaLista extends Expressao {
	Expressao listaExp;
	Expressao valorInserido;
	
	public InserirNaLista(Expressao valor, Expressao listaExp) {
		this.listaExp = listaExp;
		this.valorInserido = valor;
	}
	
	@Override
	public Valor avaliar() {
		Expressao lista = listaExp.avaliar();
		if(lista instanceof Lista){
			Lista novaLista = new ListaValorada(valorInserido.avaliar(), (Lista)lista);
			if(novaLista.checarTipo() == false){
				throw new RuntimeException("Erro ao inserir na lista");
			}
			return novaLista;
		}
		else{
			throw new RuntimeException("Argumento de criação de lista inválido");
		}
	}

	@Override
	public boolean checarTipo() {
		Expressao lista = listaExp.avaliar();
		if(lista.tipo().equals(Tipo.LISTA)){
			return lista.checarTipo() &&
					(((Lista)lista).IsEmptyList() || (valorInserido.avaliar().tipo().equals(((ListaValorada)lista).valor())));
		}
		else{
			return false;
		}
	}

	@Override
	public Tipo tipo() {
		return checarTipo() ? Tipo.LISTA:Tipo.ERROR;
	}
	
	public void aceitar(Visitor v) {
		v.visitar(this);
	}
	
	public Expressao getListaExp(){
		return listaExp;
	}
	
	public Expressao getValorInserido(){
		return valorInserido;
	}
}
