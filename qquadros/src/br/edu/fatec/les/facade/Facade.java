package br.edu.fatec.les.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.les.dao.ClienteDao;
import br.edu.fatec.les.dao.EnderecoDao;
import br.edu.fatec.les.dao.EstadoDao;
import br.edu.fatec.les.dao.IDao;
import br.edu.fatec.les.dao.UsuarioDao;
import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.EntidadeDominio;
import br.edu.fatec.les.dominio.modelo.Cliente;
import br.edu.fatec.les.dominio.modelo.Endereco;
import br.edu.fatec.les.dominio.modelo.Estado;
import br.edu.fatec.les.dominio.modelo.Usuario;
import br.edu.fatec.les.strategy.ClienteValidaDados;
import br.edu.fatec.les.strategy.IStrategy;
import br.edu.fatec.les.strategy.UsuarioValidaDados;

public class Facade implements IFacade {
	private Map<String, IDao> daoMap;
	private Map<String, ArrayList<IStrategy>> rnMap;
	private Resultado resultado;
	private Mensagem msg;
	ArrayList<Mensagem> msgs = new ArrayList<Mensagem>();
	
	public Facade() {
		daoMap = new HashMap<String, IDao>();
		rnMap = new HashMap<String, ArrayList<IStrategy>>();
		
		UsuarioDao usuarioDao = new UsuarioDao();
		ClienteDao clienteDao = new ClienteDao();
		EstadoDao estadoDao = new EstadoDao();
		EnderecoDao enderecoDao = new EnderecoDao();
		
		daoMap.put(Usuario.class.getName(), usuarioDao);
		daoMap.put(Cliente.class.getName(), clienteDao);
		daoMap.put(Estado.class.getName(), estadoDao);
		daoMap.put(Endereco.class.getName(), enderecoDao);
		
		IStrategy usuarioValidaDados = new UsuarioValidaDados();
		ArrayList<IStrategy> rnsUsuario = new ArrayList<IStrategy>();
		rnsUsuario.add(usuarioValidaDados);
		
		IStrategy clienteValidaDados = new ClienteValidaDados();
		ArrayList<IStrategy> rnsCliente = new ArrayList<IStrategy>();
		rnsCliente.add(clienteValidaDados);
		rnsCliente.addAll(rnsUsuario);
		
		rnMap.put(Usuario.class.getName(), rnsUsuario);
		rnMap.put(Cliente.class.getName(), rnsCliente);
	}
	
	@Override
	public Resultado salvar(EntidadeDominio entidadeDominio) {
		resultado = new Resultado();
		msg = new Mensagem();
		msgs = new ArrayList<Mensagem>();
		System.out.println("facade");
		
		ArrayList<IStrategy> strategies = rnMap.get(entidadeDominio.getClass().getName());
	
		if(strategies != null) {
			for(IStrategy s : strategies) {
				msg = s.execute(entidadeDominio);
				if(msg.getMsgStatus() == MensagemStatus.ERRO) {
					msgs.add(msg);
				}else {
					continue;
				}
			}
		}
		System.out.println("fez strategies");
		if(!msgs.isEmpty()) {
			System.out.println("TEM MENSAGEM");
			resultado.setMsg(msgs);
			return resultado;
		}
		System.out.println("VIU MENSAGENS FACADE");
		IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());
		
		try {
			msg = daoEntidade.salvar(entidadeDominio);
			msgs.add(msg);
			resultado.setMsg(msgs);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("fez dao");
		return resultado;
	}
	
	@Override
	public Resultado atualizar(EntidadeDominio entidadeDominio) {
		resultado = new Resultado();
		msg = new Mensagem();
		msgs = new ArrayList<Mensagem>();
		
		IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());
		
		try {
			msg = daoEntidade.atualizar(entidadeDominio);
			msgs.add(msg);
			resultado.setMsg(msgs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	@Override
	public Resultado deletar(EntidadeDominio entidadeDominio) {
		resultado = new Resultado();
		msg = new Mensagem();
		msgs = new ArrayList<Mensagem>();
		
		IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());
		
		try {
			msg = daoEntidade.deletar(entidadeDominio);
			msgs.add(msg);
			resultado.setMsg(msgs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	@Override
	public Resultado consultar(EntidadeDominio entidadeDominio) {
		resultado = new Resultado();
		msg = new Mensagem();
		msgs = new ArrayList<Mensagem>();
		
		IDao daoEntidade = daoMap.get(entidadeDominio.getClass().getName());
		AEntidade aEntidade = (AEntidade) entidadeDominio;
		
		try {
			List<AEntidade> listaEntidades = daoEntidade.consultar(aEntidade);
			resultado.setEntidade(listaEntidades);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
