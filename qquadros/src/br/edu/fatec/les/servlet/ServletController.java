package br.edu.fatec.les.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.les.command.AtualizarCommand;
import br.edu.fatec.les.command.ConsultarCommand;
import br.edu.fatec.les.command.DeletarCommand;
import br.edu.fatec.les.command.ICommand;
import br.edu.fatec.les.command.SalvarCommand;
import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.facade.Resultado;
import br.edu.fatec.les.viewHelper.ClienteVH;
import br.edu.fatec.les.viewHelper.EnderecoVH;
import br.edu.fatec.les.viewHelper.EstadoVH;
import br.edu.fatec.les.viewHelper.IViewHelper;
import br.edu.fatec.les.viewHelper.UsuarioVH;

@WebServlet(name = "servlet", urlPatterns = "/app")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Map<String, ICommand> commandMap;
    private static Map<String, IViewHelper> vhMap;
    private static String tarefa = null;
    private static IViewHelper vh;
	
    public ServletController() {
    	commandMap = new HashMap<String, ICommand>();
    	vhMap = new HashMap<String, IViewHelper>();
    	
    	// Usuario
        commandMap.put("loginUsuario", new ConsultarCommand());
        commandMap.put("logoutUsuario", new ConsultarCommand());
        commandMap.put("alterarSenha", new AtualizarCommand());
        vhMap.put("loginUsuario", new UsuarioVH());
        vhMap.put("logoutUsuario", new UsuarioVH());
        vhMap.put("alterarSenha", new UsuarioVH());

    	// Cliente
        commandMap.put("cadastrarCliente", new SalvarCommand());
        commandMap.put("editarCliente", new AtualizarCommand());
        commandMap.put("deletarCliente", new DeletarCommand());
        commandMap.put("consultarCliente", new ConsultarCommand());
        commandMap.put("atualizarCliente", new AtualizarCommand());
        vhMap.put("cadastrarCliente", new ClienteVH());
        vhMap.put("editarCliente", new ClienteVH());
        vhMap.put("deletarCliente", new ClienteVH());
        vhMap.put("consultarCliente", new ClienteVH());
        vhMap.put("atualizarCliente", new ClienteVH());
        
        // Estado
        commandMap.put("consultarEstado", new ConsultarCommand());
        vhMap.put("consultarCliente", new EstadoVH());
        
        // Endereco
        commandMap.put("cadastrarEndereco", new SalvarCommand());
        commandMap.put("editarEndereco", new AtualizarCommand());
        commandMap.put("deletarEndereco", new DeletarCommand());
        commandMap.put("consultarEndereco", new ConsultarCommand());
        commandMap.put("atualizarEndereco", new AtualizarCommand());
        vhMap.put("cadastrarEndereco", new EnderecoVH());
        vhMap.put("editarEndereco", new EnderecoVH());
        vhMap.put("deletarEndereco", new EnderecoVH());
        vhMap.put("consultarEndereco", new EnderecoVH());
        vhMap.put("atualizarEndereco", new EnderecoVH());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
    	resp.setContentType("txt/html;charset=UTF-8");
    	
    	try {
			tarefa = req.getParameter("tarefa");
			Resultado resultado;
			vh = vhMap.get(tarefa);
			AEntidade entidade = vh.getEntidade(req);
			ICommand command = commandMap.get(tarefa);
			resultado = command.execute(entidade);
			
			req.setAttribute("resultado", resultado);
			
			vh.setEntidade(req, resp);
		} catch (Exception e) {
			System.out.println(e);
			System.err.println(e.toString());
		}
    }
}
