package br.com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.mvc.model.*;
import br.com.mvc.util.ConnectionFactory;

public class Not_FalDao {
	// CRUD
	
	private Not_Fal notfal;
	private Connection conn;				// Conecta com o banco de dados
	private java.sql.PreparedStatement ps;	// Permite executar querys
	private ResultSet rs;					// Tabela 
	
	
	
	public Not_FalDao() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getMessage());
			}
		}
	public void salvar(Not_Fal notfal) throws Exception {
			try {
				String sql = "INSERT INTO notefaltb(RGM_NotFal, Dis_NotFal, Sem_NotFal, Not_NotFal, Fal_NotFal) "
						+ "values (?, ?, ?, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, notfal.getRGM_NotFal());
				ps.setString(2, notfal.getDis_NotFal());
				ps.setString(3, notfal.getSem_NotFal());
				ps.setString(4, notfal.getNot_NotFal());
				ps.setString(5, notfal.getFal_NotFal());
				ps.executeUpdate();
			} catch(Exception e) {
				throw new Exception("Erro ao Salvar"+ e.getMessage());
			}
	}

/*	public List listarTodos() throws Exception {
		List<Not_Fal> lista = new ArrayList<Not_Fal>();
		try {
			ps = conn.prepareStatement("SELECT * FROM alunodb");
			rs = ps.executeQuery();
			while(rs.next()) {
				int RGM_Aluno = rs.getInt("RGM_Aluno");
				String Nome_Aluno = rs.getString("Nome_Aluno");
				
				notfal = new Notfal();
				lista.add(notfal);
			}
			
		} catch(Exception e) {
			throw new Exception("Erro listar "+ e.getMessage());
		}
		return lista;
	}
*/	public void alterar(Not_Fal notfal) throws Exception {
	    try {
	        String sql = "UPDATE notefaltb SET Dis_NotFal=?, Sem_NotFal=?, Not_NotFal=?, Fal_NotFal=?"
	                + "WHERE RGM_NotFal=?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, notfal.getDis_NotFal());
	        ps.setString(2, notfal.getSem_NotFal());
	        ps.setString(3, notfal.getNot_NotFal());
	        ps.setString(4, notfal.getFal_NotFal());
	        ps.setInt(5, notfal.getRGM_NotFal());
	        ps.executeUpdate();
	    } catch(Exception e) {
	        throw new Exception("Erro ao Alterar"+ e.getMessage());
	    }
	}


public void excluir(int RGM_NotFal) throws Exception {
    try {
        String sql = "DELETE FROM notefaltb "
                    + "WHERE RGM_NotFal=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, RGM_NotFal);
        ps.executeUpdate();
    } catch(Exception e) {
        throw new Exception("Erro ao Excluir"+ e.getMessage());
    }
}


	public Not_Fal consultar(int RGM_NotFal) throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM notefaltb WHERE RGM_NotFal=?");
			ps.setInt(1, RGM_NotFal);
			rs = ps.executeQuery();
			if (rs.next()) {
	            notfal = new Not_Fal();
	            notfal.setRGM_NotFal(rs.getInt("RGM_NotFal"));
	            notfal.setDis_NotFal(rs.getString("Dis_NotFal"));
	            notfal.setSem_NotFal(rs.getString("Sem_NotFal"));
	            notfal.setNot_NotFal(rs.getString("Not_NotFal"));
	            notfal.setFal_NotFal(rs.getString("Fal_NotFal"));
	           
	        }
			
		} catch(Exception e) {
			throw new Exception("Erro listar "+ e.getMessage());
		}
		return notfal;
	
}

}
