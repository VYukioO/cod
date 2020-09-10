package br.edu.fatec.les.viewHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.modelo.Cidade;

public class CidadeVH implements IViewHelper {

	@Override
	public AEntidade getEntidade(HttpServletRequest request) {
		Cidade cid = new Cidade();
		
		cid.setId(Long.parseLong((String) request.getAttribute("txtCidadeId")));
		
		return cid;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	

}
