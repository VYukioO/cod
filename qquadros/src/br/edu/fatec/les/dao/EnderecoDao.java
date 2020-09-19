package br.edu.fatec.les.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.modelo.Cidade;
import br.edu.fatec.les.dominio.modelo.Cliente;
import br.edu.fatec.les.dominio.modelo.Endereco;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;
import br.edu.fatec.les.util.ConnectionFactory;

public class EnderecoDao implements IDao {
	private Connection conn = null;
	private Mensagem msg;
	CidadeDao cidadeDao = new CidadeDao();
	
	@Override
	public Mensagem salvar(AEntidade entidade) throws SQLException {
		Endereco end = (Endereco) entidade;
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		
		String sql =
				"INSERT INTO tb_endereco "
				+ "(end_logradouro, end_numero, end_bairro, end_cid_id, end_complemento, end_referencia, end_cep, end_favorito, end_cli_id, end_nome, end_ativo, end_dtCadastro, end_dtAtualizacao) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true, NOW(), NOW()) ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, end.getLogradouro());
			pstm.setInt(2, end.getNumero());
			pstm.setString(3, end.getBairro());
			pstm.setLong(4, end.getCidade().getId());
			pstm.setString(5, end.getComplemento());
			pstm.setString(6, end.getReferencia());
			pstm.setString(7, end.getCep());
			pstm.setBoolean(8, end.isFavorito());
			pstm.setLong(9, end.getCliente().getId());
			pstm.setString(10, end.getNome());
			pstm.executeUpdate();
			
			msg.setMsg("Endereco cadastrado com sucesso!");
			msg.setMsgStatus(MensagemStatus.OPERACAO);
			
			return msg;
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
		throw new UnsupportedOperationException("Operação não suportada.");
	}
	
	@Override
	public Mensagem deletar(AEntidade entidade) throws SQLException {
		Endereco end = (Endereco) entidade;
		conn = ConnectionFactory.getConnection();
		msg = new Mensagem();
		
		String sql = 
				"UPDATE tb_endereco "
				+ "SET end_ativo = false, end_dtAtualizacao = NOW() "
				+ "WHERE ";
		if (end.getId() != null) {
			sql += "end_id = " + end.getId();
		} else {
			sql += "end_cli_id = " + end.getCliente().getId();
		}
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			msg.setMsg("Endereço deletado com sucesso!");
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
		Endereco end = (Endereco) entidade;
		conn = ConnectionFactory.getConnection();
		Endereco en = new Endereco();
		Cidade cid = new Cidade();
		Cliente cli = new Cliente();
		List<AEntidade> enderecos = new ArrayList<AEntidade>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
				"SELECT "
				+ "end_id, end_logradouro, end_numero, end_bairro, end_cid_id, end_complemento, end_referencia, end_cep, end_favorito, end_cli_id, cid_nome, end_ativo, end_dtCadastro, end_dtAtualizacao "
				+ "FROM tb_endereco ";
		if (end.isAtivo()) {
			sql += "WHERE end_ativo = 1 ";
		} else {
			sql += "WHERE (end_ativo = 1 OR end_ativo = 0) ";
		}
		if (end.getId() != null) {
			sql += "AND end_id = " + end.getId();
		}
		if (end.getCliente() != null && end.getCliente().getId() != null) {
			sql += "AND end_cli_id = " + end.getCliente().getId();
		}
		
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				en = new Endereco();
				cid = new Cidade();
				cli = new Cliente();
				
				cid.setId(Long.parseLong(rs.getString("end_cid_id")));
				cli.setId(Long.parseLong(rs.getString("end_cli_id")));
				
				en.setId(Long.parseLong(rs.getString("end_id")));
				en.setLogradouro(rs.getString("end_logradouro"));
				en.setBairro(rs.getString("end_bairro"));
				en.setCep(rs.getString("end_cep"));
				en.setNumero(Integer.parseInt(rs.getString("end_numero")));
				en.setComplemento(rs.getString("end_complemento"));
				en.setReferencia(rs.getString("end_referencia"));
				en.setFavorito(rs.getBoolean("end_favorito"));
				en.setNome(rs.getString("end_nome"));
				en.setAtivo(rs.getBoolean("end_ativo"));
				en.setDtCadastro(rs.getObject("end_dtCadastro", LocalDateTime.class));
				en.setDtAtualizacao(rs.getObject("end_dtAtualizacao", LocalDateTime.class));
				
				enderecos.add(en);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, pstm, rs);
		}
		return enderecos;
	}
}
