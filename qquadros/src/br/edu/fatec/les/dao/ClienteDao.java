package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.enums.Generos;
import br.edu.fatec.les.dominio.modelo.Cliente;
import br.edu.fatec.les.dominio.modelo.Endereco;
import br.edu.fatec.les.dominio.modelo.Usuario;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;
import br.edu.fatec.les.util.ConnectionFactory;

public class ClienteDao implements IDao{
	private Connection conn = null;
	private Mensagem msg;
	EnderecoDao enderecoDao = new EnderecoDao();
	UsuarioDao usuarioDao = new UsuarioDao();
	
	@Override
	public Mensagem salvar(AEntidade entidade) throws SQLException {
		Cliente cli = (Cliente) entidade;
		Cliente c = new Cliente();
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		
		PreparedStatement pstm = null;
		ResultSet rs;
		
		String sql = 
				"INSERT INTO tb_cliente "
				+ "(cli_nome, cli_cpf, cli_dtNascimento, cli_genero, cli_telefone, cli_usu_id, cli_ativo, cli_dtCadastro, cli_dtAtualizacao) "
				+ " VALUES (?, ?, ?, ?, ?, ?, true, NOW(), NOW())";

		try {
			msg = usuarioDao.salvar(cli.getUsuario());
			if (msg.getMsgStatus() == MensagemStatus.ERRO) {
				throw new  SQLException();
			}
			String idUsuario = msg.getMsg();
		
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, cli.getNome());
			pstm.setString(2, cli.getCpf());
			pstm.setObject(3, (LocalDate) cli.getDtNascimento());
			pstm.setString(4, cli.getGenero().toString());
			pstm.setString(5, cli.getTelefone());
			pstm.setLong(6, Long.parseLong(idUsuario));
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				c = new Cliente();
				c.setId(rs.getLong(1));
			
				for (Endereco end : cli.getEnderecos()) {
					end.setCliente(c);
					
					if (enderecoDao.salvar(end).getMsgStatus() == MensagemStatus.ERRO) {
						throw new SQLException();
					}
				}
			}
			msg = new Mensagem();
			msg.setMsg("Cliente cadastrado com sucesso!");
			msg.setMsgStatus(MensagemStatus.SUCESSO);
		} catch (SQLException e) {
			msg.setMsg("Ocorreu um erro durante a operação. Tente novamente.");
			msg.setMsgStatus(MensagemStatus.ERRO);
		} finally {
			ConnectionFactory.closeConnection(conn, pstm);
		}	
		return msg;
	}
	
	@Override
	public Mensagem atualizar(AEntidade entidade) throws SQLException {
		Cliente cli = (Cliente) entidade;
		Endereco end = new Endereco();
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		
		PreparedStatement pstm = null;
		String sql = 
				"UPDATE tb_cliente "
				+ "SET cli_nome = ?, cli_cpf = ?, cli_dtNascimento = ?, cli_genero = ?, cli_telefone = ?, cli_dtAtualizacao = NOW() "
				+ "WHERE cli_id = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cli.getNome());
			pstm.setString(2, cli.getCpf());
			pstm.setObject(3, (LocalDate) cli.getDtNascimento());
			pstm.setString(4, cli.getGenero().toString());
			pstm.setString(5, cli.getTelefone());
			pstm.setLong(6, cli.getId());
			
			if (usuarioDao.atualizar(cli.getUsuario()).getMsgStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			
			// endereco 
			List<AEntidade> endBanco = new ArrayList<AEntidade>();
			end = new Endereco();
			end.setCliente(cli);
			endBanco.addAll(enderecoDao.consultar(end));
			
			//add enderecos
			for (Endereco en : cli.getEnderecos()) {
				if(en.getId() == null) {
					if(enderecoDao.salvar(en).getMsgStatus() == MensagemStatus.ERRO) {
						throw new SQLException();
					}
				}
			}
			
			//excluindo enderecos
			for (AEntidade enB : endBanco) {
				enB = (Endereco) enB;
				boolean delete = false;
				for (Endereco enC : cli.getEnderecos()) {
					if (enC.getId() != null) {
						if (enC.getId() == enB.getId()) {
							break;
						} else {
							delete = true;
							continue;
						}	
					}
				}
				if (delete) {
					if(enderecoDao.deletar(enB).getMsgStatus() == MensagemStatus.ERRO) {
						throw new SQLException();
					}
				}
			}
			msg = new Mensagem();
			pstm.executeUpdate();
			msg.setMsg("Cliente atualizado com sucesso!");
			msg.setMsgStatus(MensagemStatus.SUCESSO);
		} catch (SQLException e) {
			msg.setMsg("Ocorreu um erro durante a operação. Tente novamente.");
			msg.setMsgStatus(MensagemStatus.ERRO);
		} finally {
			ConnectionFactory.closeConnection(conn, pstm);
		}
		return msg;
	}
	
	@Override
	public Mensagem deletar(AEntidade entidade) throws SQLException {
		Cliente cli = (Cliente) entidade;
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		Endereco end = new Endereco();
		end.setCliente(cli);
		
		PreparedStatement pstm = null;
		
		String sql =
				"UPDATE tb_cliente "
				+ "SET cli_ativo=false "
				+ "WHERE cli_id = " + cli.getId();
		
		try {
			pstm = conn.prepareStatement(sql);
			
			if (enderecoDao.deletar(end).getMsgStatus() == MensagemStatus.ERRO ||
					usuarioDao.deletar(cli.getUsuario()).getMsgStatus() == MensagemStatus.ERRO) {
				throw new SQLException();
			}
			
			pstm.executeUpdate();
			msg.setMsg("Cliente deletado com sucesso!");
			msg.setMsgStatus(MensagemStatus.SUCESSO);
		} catch (SQLException e) {
			msg.setMsg("Ocorreu um erro durante a operação. Tente novamente.");
			msg.setMsgStatus(MensagemStatus.ERRO);
		} finally {
			ConnectionFactory.closeConnection(conn, pstm);
		}
		return msg;
	}
	
	@Override
	public List<AEntidade> consultar(AEntidade entidade) throws SQLException {
		Cliente cli = (Cliente) entidade;
		conn = ConnectionFactory.getConnection();
		List<AEntidade> clientes = new ArrayList<AEntidade>();
		List<Endereco> endCliente = new ArrayList<Endereco>();
		List<AEntidade> endBanco = new ArrayList<AEntidade>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
				"SELECT "
				+ "cli_id, cli_nome, cli_cpf, cli_dtNascimento, cli_genero, cli_telefone, cli_usu_id, cli_dtCadastro, cli_dtAtualizacao "
				+ "FROM tb_cliente WHERE cli_ativo = 1";
		if (cli.getId() != null) {
			sql += " AND cli_id = " + cli.getId();
		}
		if (cli.getUsuario() != null && cli.getUsuario().getId() != null) {
			sql += " AND cli_usu_id = " + cli.getUsuario().getId();
		}
		
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Usuario u = new Usuario();
			Cliente c = new Cliente();
			Endereco en = new Endereco();
			
			while (rs.next()) {
				u = new Usuario();
				c = new Cliente();
				en = new Endereco();
				
				endCliente = new ArrayList<Endereco>();
				endBanco = new ArrayList<AEntidade>();
				
				c.setId(Long.parseLong(rs.getString("cli_id")));
				c.setNome(rs.getString("cli_nome"));
				c.setAtivo(rs.getBoolean("cli_ativo"));
				c.setCpf(rs.getString("cli_cpf"));
				c.setDtAtualizacao(rs.getObject("cli_dtAtualizacao", LocalDateTime.class));
				c.setDtCadastro(rs.getObject("cli_dtCadastro", LocalDateTime.class));
				c.setDtNascimento(rs.getObject("cli_dtNascimento", LocalDate.class));
				c.setGenero(Generos.valueOf(rs.getString("cli_genero")));
				c.setTelefone(rs.getString("cli_telefone"));
				
				en.setCliente(c);
				endBanco.addAll(enderecoDao.consultar(en));
				for (AEntidade e : endBanco) {
					endCliente.add((Endereco) e);
				}
				c.setEnderecos(endCliente);	
				
				u.setId(Long.parseLong(rs.getString("cli_usu_id")));
				c.setUsuario((Usuario) usuarioDao.consultar(u).get(0));
				
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, pstm, rs);
		}
		return clientes;
	}
}
