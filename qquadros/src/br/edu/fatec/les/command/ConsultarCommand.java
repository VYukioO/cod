package br.edu.fatec.les.command;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.facade.Resultado;

public class ConsultarCommand extends AbstractCommand {
	@Override
	public Resultado execute(AEntidade aEntidade) {
		return facade.consultar((EntidadeDominio) aEntidade);
	}
}
