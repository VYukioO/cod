package br.edu.fatec.les.strategy;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.modelo.Usuario;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;

public class UsuarioValidaDados implements IStrategy {
	Usuario usu = new Usuario();
	Mensagem msg = new Mensagem();
	
	@Override
	public Mensagem execute(AEntidade entidade) {
		if (entidade instanceof Usuario) {
			System.out.println("Validando dados de usuario");
			
			if (usu.getEmail() == null) {
				if (msg.getMsg() == null) {
					msg.setMsg("E-mail");
				} else {
					msg.setMsg(msg.getMsg() + ", E-mail");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);				
			}
			if (usu.getSenha() == null) {
				if (msg.getMsg() == null) {
					msg.setMsg("Senha");
				} else {
					msg.setMsg(msg.getMsg() + ", Senha");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if (msg.getMsgStatus() == MensagemStatus.ERRO) {
				msg.setMsg("Campo(s) " + msg.getMsg() + " não preenchido(s) !");
				return msg;
			}
			
			if (usu.getEmail().trim().equals("")) {
				if (msg.getMsg() == null) {
					msg.setMsg("E-mail");
				} else {
					msg.setMsg(msg.getMsg() + ", E-mail");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if (usu.getSenha().trim().equals("")) {
				if (msg.getMsg() == null) {
					msg.setMsg("Senha");
				} else {
					msg.setMsg(msg.getMsg() + ", Senha");
				}
				msg.setMsgStatus(MensagemStatus.ERRO);
			}
			if (msg.getMsgStatus() == MensagemStatus.ERRO) {
				msg.setMsg("Campo(s) " + msg.getMsg() + " são obrigatorio(s) !");
				return msg;
			}
		} else {
			msg.setMsg("Deve ser registrado um usuário.");
			msg.setMsgStatus(MensagemStatus.ERRO);
		}
		
		return msg;
	}

}
