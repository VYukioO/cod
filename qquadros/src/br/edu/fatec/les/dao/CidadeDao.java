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
import br.edu.fatec.les.dominio.modelo.Estado;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.util.ConnectionFactory;

public class CidadeDao implements IDao{
	private Connection conexao = null;

	@Override
	public Mensagem salvar(AEntidade entidade) throws SQLException {
		throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem atualizar(AEntidade entidade) throws SQLException {
		throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public Mensagem deletar(AEntidade entidade) throws SQLException {
		throw new UnsupportedOperationException("Operação não suportada.");
	}

	@Override
	public List<AEntidade> consultar(AEntidade entidade) throws SQLException {
		Cidade cidade = (Cidade) entidade;
		conexao = ConnectionFactory.getConnection();
		List<AEntidade> cidades = new ArrayList<AEntidade>();
		EstadoDao estadoDao = new EstadoDao();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
				"SELECT cid_id, cid_nome, cid_est_id, cid_ativo, cid_dtCadastro, cid_dtAtualizacao"
				+ "FROM tb_cidade"
				+ "WHERE cid_ativo = 1";
		if(cidade.getId() != null) {
			sql += " AND cid_id = " + cidade.getId();
		}
		if(cidade.getEstado() != null) {
			sql += " AND cid_est_id = " + cidade.getEstado().getId();
		}
		
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Cidade cid = new Cidade();
			Estado est = new Estado();
			
			while (rs.next()) {
				cid = new Cidade();
				est = new Estado();
				
				cid.setId(Long.parseLong(rs.getString("cid_id")));
				cid.setNome(rs.getString("cid_nome"));
				cid.setAtivo(rs.getBoolean("cid_isAtivo"));
				cid.setDtCadastro(rs.getObject("cid_dtCadastro", LocalDateTime.class));
				cid.setDtAtualizacao(rs.getObject("cid_dtAtualizacao", LocalDateTime.class));
				
				if(cidade.getId() != null) {
					est.setId(Long.parseLong(rs.getString("cid_est_id")));
					cid.setEstado((Estado) estadoDao.consultar(est).get(0));
				}else if (cidade.getEstado() != null) {
					est.setId(cidade.getEstado().getId());
					cid.setEstado(est);
				}else {
					throw new RuntimeException();
				}
				cidades.add(cid);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(conexao, pstm, rs);
		}
		return cidades;
	}
	
	

}
