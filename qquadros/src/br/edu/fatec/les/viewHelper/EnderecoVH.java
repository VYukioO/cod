package br.edu.fatec.les.viewHelper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.modelo.Cidade;
import br.edu.fatec.les.dominio.modelo.Cliente;
import br.edu.fatec.les.dominio.modelo.Endereco;
import br.edu.fatec.les.facade.Resultado;

public class EnderecoVH implements IViewHelper {

	public ArrayList<Endereco> getEntidades(HttpServletRequest request) {
		String tarefa = request.getParameter("tarefa");
		CidadeVH cidadeVH = new CidadeVH();
		Endereco end = new Endereco();
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

		if (request.getParameterValues("txtEndereco") == null) {
			return enderecos;
		} else {
			String[] enderecosForm = request.getParameterValues("txtEndereco");
			for (int i = 0; i < enderecosForm.length; i++) {
				end = new Endereco();

				if (tarefa.equals("atualizarCliente")) {
					if (request.getParameterValues("txtEnderecoId")[i] != "") {
						end.setId(Long.parseLong(request.getParameterValues("txtEnderecoId")[i]));
					} else {
						end.setId(null);
					}
				}

				end.setNome(request.getParameter("txtDescricaoEndereco"));
				end.setBairro(request.getParameter("txtBairro"));
				end.setCep(request.getParameter("txtCep"));
				end.setNumero((Integer.parseInt(request.getParameter("txtNumero"))));
				if (request.getParameter("txtComplemento") == "") {
					end.setComplemento(null);
				} else {
					end.setComplemento(request.getParameter("txtComplemento"));
				}
				if (request.getParameter("txtReferencia") == "") {
					end.setComplemento(null);
				} else {
					end.setComplemento(request.getParameter("txtReferencia"));
				}
				end.setLogradouro("txtLogradouro");
				end.setFavorito(Boolean.parseBoolean(request.getParameter("txtFavorito")));

				request.setAttribute("txtCidadeId", request.getParameter("txtCidadeId"));
				end.setCidade((Cidade) cidadeVH.getEntidade(request));

				enderecos.add(end);
			}
		}
		return enderecos;
	}

	@Override
	public AEntidade getEntidade(HttpServletRequest request) {
		Endereco end = new Endereco();
		String tarefa = request.getParameter("tarefa");

		if (tarefa.equals("adicionarEndereco")) {
			CidadeVH cidadeVH = new CidadeVH();
			ClienteVH clienteVH = new ClienteVH();

			end.setNome(request.getParameter("txtDescricaoEndereco"));
			end.setBairro(request.getParameter("txtBairro"));
			end.setCep(request.getParameter("txtCep"));
			end.setNumero((Integer.parseInt(request.getParameter("txtNumero"))));
			if (request.getParameter("txtComplemento") == "") {
				end.setComplemento(null);
			} else {
				end.setComplemento(request.getParameter("txtComplemento"));
			}
			if (request.getParameter("txtReferencia") == "") {
				end.setComplemento(null);
			} else {
				end.setComplemento(request.getParameter("txtReferencia"));
			}
			end.setLogradouro("txtLogradouro");
			end.setFavorito(Boolean.parseBoolean(request.getParameter("txtFavorito")));
			end.setCliente((Cliente) clienteVH.getEntidade(request));

			request.setAttribute("txtCidadeId", request.getParameter("txtCidadeId"));

			end.setCidade((Cidade) cidadeVH.getEntidade(request));
		} else if (tarefa.equals("consultarEndereco")) {
			ClienteVH clienteVH = new ClienteVH();
			end.setCliente((Cliente) clienteVH.getEntidade(request));
			if (request.getParameter("txtAtivo").equals("true")) {
				end.setAtivo(true);
			} else {
				end.setAtivo(false);
			}
		} else {
			end.setId(Long.parseLong(request.getParameter("txtEnderecoId")));
		}
		return end;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tarefa = req.getParameter("tarefa");

		if (tarefa.equals("consultarEndereco")) {
			Resultado resultado = new Resultado();

			String json = new Gson().toJson(resultado);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
		} else if (tarefa.equals("adicionarEnderecoLista") || tarefa.equals("removeEnderecoLista")) {
			req.getRequestDispatcher("alterarEndereco.jsp").forward(req, resp);
		}
	}

}
