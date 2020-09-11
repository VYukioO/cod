package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.Imagem;
import br.edu.fatec.les.dominio.modelo.Usuario;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;
import br.edu.fatec.les.util.ConnectionFactory;

public class UsuarioDao implements IDao {
	private Connection conn = null;
	private Mensagem msg;
	ImagemDao imagemDao = new ImagemDao();
	
	@Override
	public Mensagem salvar(AEntidade entidade) throws SQLException {
		Usuario usu = (Usuario) entidade;
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		
		PreparedStatement pstm = null;
		ResultSet rs;
		
		String sql =
				"INSERT INTO tb_usuario "
				+ "(usu_email, usu_senha, usu_ima_id, usu_ativo, usu_admin, usu_dtCadastro, usu_dtAtualizacao) "
				+ "VALUES (?, ?, ?, true, false, NOW(), NOW() ";
		
		
		try {
			String idImagem = imagemDao.salvar(usu.getImagem()).getMsg();
			
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, usu.getEmail());
			pstm.setString(2, usu.getSenha());
			System.out.println(idImagem);
			pstm.setLong(3, Long.parseLong(idImagem));
			System.out.println("set imagem");
			System.out.println("atribuiu valores a sql");
			pstm.executeUpdate();
			
			rs = pstm.getGeneratedKeys();
			if(rs.next()) {
				msg.setMsg(Integer.toString(rs.getInt(1)));
				msg.setMsgStatus(MensagemStatus.OPERACAO);
				return msg;
			}
		} catch (SQLException e) {
			System.out.println("erro aqui ");
			System.out.println(e);
			msg.setMsg("Ocorreu um erro durante a operação. Tente novamente.");
			msg.setMsgStatus(MensagemStatus.ERRO);
		} finally {
			ConnectionFactory.closeConnection(conn, pstm);
		}
		return msg;
	}
	
	@Override
	public Mensagem atualizar(AEntidade entidade) throws SQLException {
		Usuario usu = (Usuario) entidade;
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		
		PreparedStatement pstm = null;
		
		try {
			if(usu.getSenha() != null) {
				String sql =
						"UPDATE tb_usuario "
						+ "SET usu_email = ? "
						+ "WHERE usu_id = ?";
				
				if(usu.getImagem().getId() == null) {
					msg = imagemDao.atualizar(usu.getImagem());
					if (msg.getMsgStatus() == MensagemStatus.ERRO) {
						return msg;
					}
				}
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, usu.getEmail());
				pstm.setLong(2, usu.getId());
				pstm.executeUpdate();
				msg.setMsg("Usuário atualizado com sucesso!");
				msg.setMsgStatus(MensagemStatus.SUCESSO);
			}
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
		Usuario usu = (Usuario) entidade;
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		
		String sql =
				"UPDATE tb_usuario "
				+ "SET usu_ativo = false "
				+ "WHERE usu_id = " + usu.getId() 
				+ "AND usu_id != 1";
		
		PreparedStatement pstm = null;
		
		try {
			if (imagemDao.deletar(usu.getImagem()) == null) {
				return null;
			}
			
			pstm = conn.prepareStatement(sql);
			pstm.execute();
			msg.setMsg("Usuário deletado com sucesso!");
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
		Usuario usu = (Usuario) entidade;
		conn = ConnectionFactory.getConnection();
		List<AEntidade> usuarios = new ArrayList<AEntidade>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
				"SELECT "
				+ "usu_id, usu_email, usu_senha, usu_ima_id, usu_ativo, usu_admin, usu_dtCadastro, usu_dtAtualizacao "
				+ "FROM tb_usuario "
				+ "WHERE usu_ativo = 1";
		
		if(usu.getId() != null) {
			sql += " AND usu_id = " + usu.getId();
		}
		if(usu.getEmail() != null) {
			sql += " AND usu_email = '" + usu.getEmail() + "'";
		}
		if(usu.getSenha() != null) {
			sql += " AND usu_senha = '" + usu.getSenha() + "'";
		}
			
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Usuario u = new Usuario();
			Imagem i = new Imagem();
			
			while (rs.next()) {
				u = new Usuario();
				i = new Imagem();
				
				u.setId(Long.parseLong(rs.getString("usu_id")));
				u.setEmail(rs.getString("usu_email"));
				u.setSenha(rs.getString("usu_senha"));
				u.setAdmin(rs.getBoolean("usu_admin"));
				i.setId(rs.getLong("usu_ima_id"));
				u.setImagem((Imagem) imagemDao.consultar(i).get(0));
				u.setAtivo(rs.getBoolean("usu_ativo"));
				u.setDtCadastro(rs.getObject("usu_dtCadastro", LocalDateTime.class));
				u.setDtAtualizacao(rs.getObject("usu_dtAtualizacao", LocalDateTime.class));

				usuarios.add(u);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(conn, pstm, rs);
		}
		return usuarios;
	}

}
