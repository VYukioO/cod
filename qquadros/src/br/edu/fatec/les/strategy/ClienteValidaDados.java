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
			cli = (Cliente) entidade;
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
					msg.setMsg("G�nero");
				} else {
					msg.setMsg(msg.getMsg() + ", G�nero");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			
			if (msg.getMsgStatus() == MensagemStatus.ERRO) {
				msg.setMsg("Campo(s) " + msg.getMsg() + "n�o preenchido(s) !");
				return msg;
			}
			
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
					msg.setMsg("G�nero");
				} else {
					msg.setMsg(msg.getMsg() + ", G�nero");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			
			if (msg.getMsgStatus() == MensagemStatus.ERRO) {
				msg.setMsg("Campo(s) " + msg.getMsg() + "�/s�o obrigat�rio(s) !");
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
