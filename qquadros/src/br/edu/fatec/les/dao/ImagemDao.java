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
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;
import br.edu.fatec.les.util.ConnectionFactory;

public class ImagemDao implements IDao{
	private Connection conn = null;
	private Mensagem msg;
	
	@Override
	public Mensagem salvar(AEntidade entidade) throws SQLException {
		Imagem ima = (Imagem) entidade;
		conn = ConnectionFactory.getConnection();
		ResultSet rs;
		msg = new Mensagem();
		
		String sql =
				"INSERT INTO tb_imagem "
				+ "(ima_nome, ima_descricao, ima_caminho, ima_ativo, ima_dtCadastro, ima_dtAtualizacao) "
				+ "VALUES (?, ?, ?, true, NOW(), NOW())";
	
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, ima.getFoto());
			pstm.setString(2, ima.getDescricao());
			pstm.setString(3, ima.getCaminho());
			pstm.execute();
			
			rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				msg.setMsg(Integer.toString(rs.getInt(1)));
				msg.setMsgStatus(MensagemStatus.OPERACAO);
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
	public Mensagem atualizar(AEntidade entidade) throws SQLException {
		Imagem ima = (Imagem) entidade;
		msg = new Mensagem();
		conn = ConnectionFactory.getConnection();
		
		String sql = 
				"UPDATE tb_imagem "
				+ "ima_nome = ?, ima_descricao = ?, ima_caminho = ?, ima_dtAtualizacao = NOW() "
				+ "WHERE ima_id = ? "
				+ "AND ima_ativo = 1";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, ima.getFoto());
			pstm.setString(2, ima.getDescricao());
			pstm.setString(3, ima.getCaminho());
			pstm.setLong(4, ima.getId());
			pstm.executeUpdate();
			msg.setMsg("Imagem atualizada com sucesso!");
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
		Imagem ima = (Imagem) entidade;
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		
		String sql =
				"UPDATE tb_imagem "
				+ "SET ima_ativo = false, ima_dtAtualizacao = NOW() "
				+ "WHERE ima_id = " + ima.getId();
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			msg.setMsg("Imagem deletada com sucesso!");
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
		Imagem ima = (Imagem) entidade;
		conn = ConnectionFactory.getConnection();
		List<AEntidade> imagens = new ArrayList<AEntidade>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
				"SELECT "
				+ "ima_id, ima_nome, ima_descricao, ima_caminho, ima_ativo, ima_dtCadastro, ima_dtAtualizacao "
				+ "FROM tb_imagem "
				+ "WHERE ima_ativo = 1 ";
		if(ima.getId() != null) {
			sql += "AND ima_id = " + ima.getId();
		}
		
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Imagem i = new Imagem();
			
			while(rs.next()) {
				i = new Imagem();
				
				i.setId(Long.parseLong(rs.getString("ima_id")));
				i.setFoto(rs.getString("ima_nome"));
				i.setDescricao(rs.getString("ima_descricao"));
				i.setCaminho(rs.getString("ima_caminho"));
				i.setAtivo(rs.getBoolean("ima_ativo"));
				i.setDtCadastro(rs.getObject("ima_dtCadastro", LocalDateTime.class));
				i.setDtAtualizacao(rs.getObject("ima_dtAtualizacao", LocalDateTime.class));
				
				imagens.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, pstm, rs);
		}
		return imagens;
	}
	

}
