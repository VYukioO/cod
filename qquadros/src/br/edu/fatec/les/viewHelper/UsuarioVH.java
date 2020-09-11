package br.edu.fatec.les.viewHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.les.dao.ClienteDao;
import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.Imagem;
import br.edu.fatec.les.dominio.modelo.Cliente;
import br.edu.fatec.les.dominio.modelo.Usuario;
import br.edu.fatec.les.facade.Mensagem;
import br.edu.fatec.les.facade.MensagemStatus;
import br.edu.fatec.les.facade.Resultado;

public class UsuarioVH implements IViewHelper {

	@Override
	public AEntidade getEntidade(HttpServletRequest request) {
		Usuario usu = new Usuario();
		ImagemVH imaVH = new ImagemVH();
		String tarefa = request.getParameter("tarefa");
		
		if (tarefa.equals("atualizarCliente") ||
				tarefa.equals("deletarCliente") ||
				tarefa.equals("editarCliente")) {
			usu.setId(Long.parseLong(request.getParameter("txtUsuarioId")));
		}
		
		usu.setAdmin(false);
		usu.setEmail(request.getParameter("txtEmail"));
		usu.setSenha(request.getParameter("txtSenha"));
		usu.setImagem((Imagem) imaVH.getEntidade(request));
		
		return usu;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tarefa = req.getParameter("tarefa");
		if (tarefa.equals("alterarSenha")) {
			req.getRequestDispatcher("edtSenha.jsp").forward(req, resp);
		} else {
			Resultado resultado = new Resultado();
			resultado = (Resultado) req.getAttribute("resultado");
			if (resultado.getEntidade().isEmpty() || resultado.getEntidade() == null) {
				Mensagem msg = new Mensagem();
				msg.setMsg("Login e/ou senha inválido(s)!");
				msg.setMsgStatus(MensagemStatus.ERRO);
				
				ArrayList<Mensagem> msgs = new ArrayList<Mensagem>();
				msgs.add(msg);
				
				resultado = new Resultado();
				resultado.setMsg(msgs);
				
				req.setAttribute("resultado", resultado);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {
				Usuario usu = (Usuario) resultado.getEntidade().get(0);
				
				HttpSession session = req.getSession();
				session.invalidate();
				session = req.getSession();
				session.setMaxInactiveInterval(15*60);
				
				if (!usu.isAdmin()) {
					try {
						ClienteDao clienteDao = new ClienteDao();
						Cliente cli = new Cliente();
						cli.setUsuario(usu);
						cli = (Cliente) clienteDao.consultar(cli).get(0);
						session.setAttribute("cliente", cli);
					} catch (SQLException e) {
						// TODO: handle exception
					}
				}
				
				session.setAttribute("status", "on");
				session.setAttribute("usuario", usu);
				
				resp.sendRedirect("index.jsp");
			}
		}
	}
}
