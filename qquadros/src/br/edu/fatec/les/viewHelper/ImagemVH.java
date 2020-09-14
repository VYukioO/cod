package br.edu.fatec.les.viewHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import br.edu.fatec.les.dominio.AEntidade;
import br.edu.fatec.les.dominio.Imagem;

public class ImagemVH implements IViewHelper {

	@Override
	public AEntidade getEntidade(HttpServletRequest request) {
		Imagem ima = new Imagem();
		String tarefa = request.getParameter("tarefa");
		String base64String = request.getParameter("txtFile");
		System.out.println("imagemVH");
		System.out.println(tarefa);
		System.out.println("txtFile: " + base64String);
		
		if (tarefa.equals("atualizarCliente") || 
				tarefa.equals("deletarCliente") || 
				tarefa.equals("editarCliente")) {
			ima.setId(Long.parseLong(request.getParameter("txtImagemId")));
		}
		
		System.out.println("SETOU ID DA IMAGEM(se tiver)");
		if ((tarefa.equals("cadastrarCliente") || tarefa.equals("atualizarCliente") || tarefa.equals("cadastrarUsuario")) &&
				base64String != "") {
			String[] strings = base64String.split(",");
			String extension;
			
			System.out.println("IF CADASTRO");
			System.out.println("strigs   " + strings);
			
			switch (strings[0]) {
				case "data:image/jpeg;base64":
					extension = "jpeg";
					break;
				case "data:image/png;base64":
					extension = "png";
					break;
				case "data:image/svg+xml;base64":
					extension = "svg";
					break;
				default:
					extension = "jpeg";
					break;
			}
			
			String imagem = request.getParameter("txtCpf") + "." + extension;
			byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			String path = "C:/Users/Victor/Documents/GitHub/cod/qquadros/WebContent/img/" + imagem;
			File file = new File(path);
			
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ima.setFoto(imagem);
			ima.setDescricao("Imagem " + imagem);
			ima.setCaminho("./img/" + imagem);
			System.out.println("ATRIBUINDO IMAGE");
			System.out.println(ima.getCaminho());
			System.out.println(ima.getDescricao());
			System.out.println(ima.getFoto());
		}
		System.out.println("RETURN IMAGE");
		return ima;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
