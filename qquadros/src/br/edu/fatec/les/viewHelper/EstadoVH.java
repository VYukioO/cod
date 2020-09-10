package br.edu.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.modelo.Estado;
import br.edu.fatec.les.facade.Resultado;

public class EstadoVH implements IViewHelper {

	@Override
	public AEntidade getEntidade(HttpServletRequest request) {
		Estado est = new Estado();
		return est;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Estado est = new Estado();
		List<Estado> estados = new ArrayList<Estado>();
		Resultado resultado = new Resultado();
		resultado = (Resultado) req.getAttribute("resultado");
		
		for (AEntidade es : resultado.getEntidade()) {
			est = (Estado) es;
			estados.add(est);
		}
		
		String json = new Gson().toJson(estados);
		 
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}

}
