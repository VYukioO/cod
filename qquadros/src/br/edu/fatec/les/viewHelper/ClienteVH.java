package br.edu.fatec.les.viewHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.enums.Generos;
import br.edu.fatec.les.dominio.modelo.Cliente;
import br.edu.fatec.les.dominio.modelo.Endereco;
import br.edu.fatec.les.dominio.modelo.Usuario;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;
import br.edu.fatec.les.facade.Resultado;

public class ClienteVH implements IViewHelper {
	@Override
	public AEntidade getEntidade(HttpServletRequest request) {
		String tarefa = request.getParameter("tarefa");
		Cliente cli = new Cliente();
		EnderecoVH enderecoVH = new EnderecoVH();
		UsuarioVH usuarioVH = new UsuarioVH();
		
		if (tarefa.equals("cadastrarCliente") || tarefa.equals("atualizarCliente")) {
			if (request.getParameter("txtClienteId") != null) {
				cli.setId(Long.parseLong(request.getParameter("txtClienteId")));
			} else {
				cli.setId(null);
			}
			
			cli.setNome(request.getParameter("txtNome"));
			cli.setGenero(Generos.valueOf(request.getParameter("txtGenero")));
			cli.setDtNascimento(LocalDateTime.parse(request.getParameter("txtDtNascimento")));
			cli.setCpf(request.getParameter("txtCpf"));
			cli.setTelefone(request.getParameter("txtTelefone"));
			cli.setUsuario((Usuario) usuarioVH.getEntidade(request));
			cli.setEnderecos(enderecoVH.getEntidades(request));
			
			for (Endereco en : cli.getEnderecos()) {
				en.setCliente(cli);
			}
		} else {
			if (request.getParameter("txtClienteId") != null) {
				cli.setId(Long.parseLong(request.getParameter("txtClienteId")));
			}
			
			if (request.getParameter("txtUsuarioId") != null) {
				cli.setUsuario((Usuario) usuarioVH.getEntidade(request));
			}
		}
		return cli;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tarefa = req.getParameter("tarefa");
		Resultado resultado = new Resultado();
		
		if (tarefa.equals("consultarCliente")) {
			resultado = new Resultado();
			resultado = (Resultado) req.getAttribute("resultado");
			
			String json = new Gson().toJson(resultado);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
			
		} else if (tarefa.equals("editarCliente")) {
			List<Cliente> clientes = new ArrayList<Cliente>();
			resultado = new Resultado();
			resultado = (Resultado) req.getAttribute("resultados");
			
			for (AEntidade c : resultado.getEntidade()) {
				Cliente cl = (Cliente) c;
				clientes.add(cl);
			}
			
			Cliente cli = clientes.get(0);
			
			req.setAttribute("cliente", cli);
			req.getRequestDispatcher("clienteEditar.jsp").forward(req, resp);
		} else if (tarefa.equals("atualizarCliente") || tarefa.equals("cadastrarCliente")) {
			resultado = new Resultado();
			resultado = (Resultado) req.getAttribute("resultado");
			boolean erro = false;
			
			for (Mensagem msg : resultado.getMsgs()) {
				if (msg.getMsgStatus() == MensagemStatus.ERRO) {
					erro = true;
				}
			}
			if (erro) {
				if (tarefa.equals("atualizarCliente")) {
					req.getRequestDispatcher("clienteLista.jsp").forward(req, resp);
				} else {
					req.getRequestDispatcher("usuarioLogin.jsp").forward(req, resp);
				}
			} else {
				req.getRequestDispatcher("usuarioLogin.jsp").forward(req, resp);
			}
		} else if (tarefa.equals("deletarCliente")) {
			req.getRequestDispatcher("clienteLista.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("index.html");
		}
	}
}
