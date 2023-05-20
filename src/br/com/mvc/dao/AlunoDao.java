package br.com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.mvc.model.*;
import br.com.mvc.util.ConnectionFactory;

public class AlunoDao {
	// CRUD
	
	private Aluno aluno;
	private Connection conn;				// Conecta com o banco de dados
	private java.sql.PreparedStatement ps;	// Permite executar querys
	private ResultSet rs;					// Tabela 
	
	
	
	public AlunoDao() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro "+ e.getMessage());
			}
		}
	public void salvar(Aluno aluno) throws Exception {
			try {
				String sql = "INSERT INTO alunotb(RGM_Aluno, Nome_Aluno, Dat_Nas_Aluno, CPF_Aluno, Email_Aluno, End_Aluno, Muni_Aluno, UF_Aluno, Cel_Aluno, Cur_Aluno, Cam_Aluno, Per_Aluno) "
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, aluno.getRGM_Aluno());
				ps.setString(2, aluno.getNome_Aluno());
				ps.setDate(3, new java.sql.Date(aluno.getDat_Nas_Aluno().getTime()));
				ps.setLong(4, aluno.getCPF_Aluno());
				ps.setString(5, aluno.getEmail_Aluno());
				ps.setString(6, aluno.getEnd_Aluno());
				ps.setString(7, aluno.getMuni_Aluno());
				ps.setString(8, aluno.getUF_Aluno());
				ps.setLong(9, aluno.getCel_Aluno());
				ps.setString(10, aluno.getCur_Aluno());
				ps.setString(11, aluno.getCam_Aluno());
				ps.setString(12, aluno.getPer_Aluno().name());		
				ps.executeUpdate();
			} catch(Exception e) {
				throw new Exception("Erro ao Salvar"+ e.getMessage());
			}
	}
	public List listarTodos() throws Exception {
		List<Aluno> lista = new ArrayList<Aluno>();
		try {
			ps = conn.prepareStatement("SELECT * FROM alunodb");
			rs = ps.executeQuery();
			while(rs.next()) {
				int RGM_Aluno = rs.getInt("RGM_Aluno");
				String Nome_Aluno = rs.getString("Nome_Aluno");
				
				aluno = new Aluno();
				lista.add(aluno);
			}
			
		} catch(Exception e) {
			throw new Exception("Erro listar "+ e.getMessage());
		}
		return lista;
	}
	public void alterar(Aluno aluno) throws Exception {
	    try {
	        String sql = "UPDATE alunotb SET Nome_Aluno=?, Dat_Nas_Aluno=?, CPF_Aluno=?, Email_Aluno=?, "
	                + "End_Aluno=?, Muni_Aluno=?, UF_Aluno=?, Cel_Aluno=?, Cur_Aluno=?, Cam_Aluno=?, "
	                + "Per_Aluno=? WHERE RGM_Aluno=?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, aluno.getNome_Aluno());
	        ps.setDate(2, new java.sql.Date(aluno.getDat_Nas_Aluno().getTime()));
	        ps.setLong(3, aluno.getCPF_Aluno());
	        ps.setString(4, aluno.getEmail_Aluno());
	        ps.setString(5, aluno.getEnd_Aluno());
	        ps.setString(6, aluno.getMuni_Aluno());
	        ps.setString(7, aluno.getUF_Aluno());
	        ps.setLong(8, aluno.getCel_Aluno());
	        ps.setString(9, aluno.getCur_Aluno());
	        ps.setString(10, aluno.getCam_Aluno());
	        ps.setString(11, aluno.getPer_Aluno().toString());
	        ps.setInt(12, aluno.getRGM_Aluno());
	        ps.executeUpdate();
	    } catch(Exception e) {
	        throw new Exception("Erro ao Alterar"+ e.getMessage());
	    }
	}

	public void excluir(int RGM_Aluno) throws Exception {
			try {
				String sql = "DELETE FROM alunotb "
						+ " WHERE RGM_Aluno=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, RGM_Aluno);
				ps.executeUpdate();
			} catch(Exception e) {
				throw new Exception("Erro ao Excluir"+ e.getMessage());
			}
			
}
	
	public Aluno consultar(int RGM_Aluno) throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM alunotb WHERE RGM_Aluno=?");
			ps.setInt(1, RGM_Aluno);
			rs = ps.executeQuery();
			if (rs.next()) {
	            aluno = new Aluno();
	            aluno.setRGM_Aluno(rs.getInt("RGM_Aluno"));
	            aluno.setNome_Aluno(rs.getString("Nome_Aluno"));
	            aluno.setDat_Nas_Aluno(rs.getDate("Dat_Nas_Aluno"));
	            aluno.setCPF_Aluno(rs.getLong("CPF_Aluno"));
	            aluno.setEmail_Aluno(rs.getString("Email_Aluno"));
	            aluno.setEnd_Aluno(rs.getString("End_Aluno"));
	            aluno.setMuni_Aluno(rs.getString("Muni_Aluno"));
	            aluno.setUF_Aluno(rs.getString("UF_Aluno"));
	            aluno.setCel_Aluno(rs.getLong("Cel_Aluno"));
	            aluno.setCur_Aluno(rs.getString("Cur_Aluno"));
	            aluno.setCam_Aluno(rs.getString("Cam_Aluno"));
	            aluno.setPer_Aluno(PeriodoAluno.valueOf(rs.getString("Per_Aluno")));

	        }
			
		} catch(Exception e) {
			throw new Exception("Erro listar "+ e.getMessage());
		}
		return aluno;
	}
}

