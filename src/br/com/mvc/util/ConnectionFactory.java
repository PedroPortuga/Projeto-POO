package br.com.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	public static Connection getConnection() throws Exception {
		// método getconnection - não irá tratar erros
		try {
			// indica o DB mysql e aponta para o driver
			Class.forName("com.mysql.jdbc.Driver");
			// conexão com DB
			String login = "root";
			String senha = "root";
			String url = "jdbc:mysql://localhost:3306/MVCDB";
			return DriverManager.getConnection(url,login,senha);
			} catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			JOptionPane.showMessageDialog(null, "Banco de Dados Conectado");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}


