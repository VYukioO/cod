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

public class EstadoDao implements IDao{
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
		Estado estado = (Estado) entidade;
		CidadeDao cidadeDao = new CidadeDao();
		conexao = ConnectionFactory.getConnection();

		List<AEntidade> entidadeEstados = new ArrayList<AEntidade>();
		List<AEntidade> entidadeCidades = new ArrayList<AEntidade>();
		List<Cidade> cidades = new ArrayList<Cidade>();
		List<Estado> estados = new ArrayList<Estado>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
				"SELECT est_id, est_nome, est_sigla, est_isAtivo, est_dtCadastro, est_dtAtualizacao"
				+ "FROM tb_estado"
				+ "WHERE est_ativo = 1";
		if(estado.getId() != null){
			sql += "AND est_id = " + estado.getId();
		}
		
		try {
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			Cidade cid = new Cidade();
			Estado est = new Estado();
			
			while(rs.next()) {
				est = new Estado();

				est.setId(Long.parseLong(rs.getString("est_id")));
				est.setNome(rs.getString("est_nome"));
				est.setSigla(rs.getString("est_sigla"));
				est.setAtivo(rs.getBoolean("est_isAtivo"));
				est.setDtCadastro(rs.getObject("est_dtCadastro", LocalDateTime.class));
				est.setDtAtualizacao(rs.getObject("est_dtAtualizacao", LocalDateTime.class));
			
				if(estado.getId() == null) {
					cid = new Cidade();
					
					entidadeCidades = new ArrayList<AEntidade>();
					cidades = new ArrayList<Cidade>();
					
					cid.setEstado(est);
					
					entidadeCidades.addAll(cidadeDao.consultar(cid));
					
					for(AEntidade cidade : entidadeCidades) {
						cidades.add((Cidade) cidade);
					}
					
					est.setCidades(cidades);
				}
				estados.add(est);
			}
			entidadeEstados.addAll(estados);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conexao, pstm, rs);
		}
		return entidadeEstados;
	}

}
