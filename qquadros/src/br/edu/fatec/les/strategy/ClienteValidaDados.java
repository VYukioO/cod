package br.edu.fatec.les.strategy;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.modelo.Cliente;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;

public class ClienteValidaDados implements IStrategy {
	Cliente cli = new Cliente();
	Mensagem msg = new Mensagem();

	@Override
	public Mensagem execute(AEntidade entidade) {
		if (entidade instanceof Cliente) {
			System.out.println("Validando dados de cliente");
			
			if(cli.getNome() == null) {
				msg.setMsg("Nome");
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if(cli.getDtNascimento() == null) {
				if (msg.getMsg() == null) {
					msg.setMsg("Data de Nascimento");
				} else {
					msg.setMsg(msg.getMsg() + ", Data de Nascimento");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if(cli.getCpf() == null) {
				if (msg.getMsg() == null) {
					msg.setMsg("CPF");
				} else {
					msg.setMsg(msg.getMsg() + ", CPF");
				}
			}
			if(cli.getTelefone() == null) {
				if (msg.getMsg() == null) {
					msg.setMsg("Telefone");
				} else {
					msg.setMsg(msg.getMsg() + ", Telefone");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if(cli.getGenero() == null) {
				if (msg.getMsg() == null) {
					msg.setMsg("Gênero");
				} else {
					msg.setMsg(msg.getMsg() + ", Gênero");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			
			if (msg.getMsgStatus() == MensagemStatus.ERRO) {
				msg.setMsg("Campo(s) " + msg.getMsg() + "não preenchido(s) !");
				return msg;
			}
			// e-mail, senha, endereço residencial
			
			if(cli.getNome().trim().equals("")) {
				if (msg.getMsg() == null) {
					msg.setMsg("Nome");
				} else {
					msg.setMsg(msg.getMsg() + ", Nome");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if(cli.getDtNascimento().equals("00/00/0000")) {
				if (msg.getMsg() == null) {
					msg.setMsg("Data de Nascimento");
				} else {
					msg.setMsg(msg.getMsg() + ", Data de Nascimento");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if(cli.getCpf().trim().equals("")) {
				if (msg.getMsg() == null) {
					msg.setMsg("CPF");
				} else {
					msg.setMsg(msg.getMsg() + ", CPF");
				}
			}
			if(cli.getTelefone().trim().equals("")) {
				if (msg.getMsg() == null) {
					msg.setMsg("Telefone");
				} else {
					msg.setMsg(msg.getMsg() + ", Telefone");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if(cli.getGenero().equals("")) {
				if (msg.getMsg() == null) {
					msg.setMsg("Gênero");
				} else {
					msg.setMsg(msg.getMsg() + ", Gênero");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			
			if (msg.getMsgStatus() == MensagemStatus.ERRO) {
				msg.setMsg("Campo(s) " + msg.getMsg() + "é/são obrigatório(s) !");
				return msg;
			}
		} else {
			msg.setMsg("Deve ser registrado um cliente.");
			msg.setMsgStatus(MensagemStatus.ERRO);
		}

		return msg;
		// pq nao retorna status sucesso?
	}

}
