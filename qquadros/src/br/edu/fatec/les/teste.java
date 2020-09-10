package br.edu.fatec.les;

import java.time.LocalDateTime;

import br.edu.fatec.les.dominio.enums.Generos;
import br.edu.fatec.les.dominio.modelo.Cliente;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;
import javafx.util.converter.LocalDateTimeStringConverter;

public class teste {

	public static void main(String[] args) {
		Mensagem msg = new Mensagem();
		Cliente cli = new Cliente();
		
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
		
		String data = new String();
		data = String.valueOf(dt);
		System.out.println(data);
		dt = LocalDateTime.parse(data);
		System.out.println(dt);
		
		cli.setNome("         ");
		cli.setDtNascimento(dt);
		cli.setCpf("  ");
		cli.setGenero(Generos.MASCULINO);
		cli.setTelefone("");
		
		if(cli.getNome() == null) {
			if (msg.getMsg() == null) {
				msg.setMsg("Nome");
			} else {
				msg.setMsg(msg.getMsg() + ", Nome");
			}
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
		
		if (msg.getMsg() != null) {
			msg.setMsg("Campo(s) " + msg.getMsg() + " n�o preenchido(s) !");
			System.out.println(msg.getMsg());
			System.out.println(msg.getMsgStatus());
		}
		// e-mail, senha, endere�o residencial
		if(cli.getNome().trim().equals("")) {
			if (msg.getMsg() == null) {
				msg.setMsg("Nome");
			} else {
				msg.setMsg(msg.getMsg() + ", Nome");
			}
			msg.setMsgStatus(MensagemStatus.ERRO);
		}
		if(cli.getDtNascimento().equals("")) {
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
			msg.setMsgStatus(MensagemStatus.ERRO);
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
			msg.setMsg("Campo(s) " + msg.getMsg() + " �/s�o obrigat�rio(s) !");
		}
		System.out.println(msg.getMsg());
		System.out.println(msg.getMsgStatus());
	}

}
