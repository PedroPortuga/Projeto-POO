package br.com.mvc.model;

import java.util.Date;


public class Aluno {
	// Atributos
	private int RGM_Aluno;
	private String Nome_Aluno;
	private Date Dat_Nas_Aluno;
	private long CPF_Aluno;
	private String Email_Aluno;
	private String End_Aluno;
	private String Muni_Aluno;
	private String UF_Aluno;
	private long Cel_Aluno;
	private String Cur_Aluno;
	private String Cam_Aluno;
	private PeriodoAluno Per_Aluno;

	
	
	//Construtores
		public Aluno () {
			
		}



	public Aluno(int rGM_Aluno, String nome_Aluno, Date dat_Nas_Aluno, long cPF_Aluno, String email_Aluno,
			String end_Aluno, String muni_Aluno, String uF_Aluno, long cel_Aluno, String cur_Aluno, String cam_Aluno,
			PeriodoAluno per_Aluno) {
		super();
		RGM_Aluno = rGM_Aluno;
		Nome_Aluno = nome_Aluno;
		Dat_Nas_Aluno = dat_Nas_Aluno;
		CPF_Aluno = cPF_Aluno;
		Email_Aluno = email_Aluno;
		End_Aluno = end_Aluno;
		Muni_Aluno = muni_Aluno;
		UF_Aluno = uF_Aluno;
		Cel_Aluno = cel_Aluno;
		Cur_Aluno = cur_Aluno;
		Cam_Aluno = cam_Aluno;
		Per_Aluno = per_Aluno;
	}



	public int getRGM_Aluno() {
		return RGM_Aluno;
	}



	public void setRGM_Aluno(int rGM_Aluno) {
		RGM_Aluno = rGM_Aluno;
	}



	public String getNome_Aluno() {
		return Nome_Aluno;
	}



	public void setNome_Aluno(String nome_Aluno) {
		Nome_Aluno = nome_Aluno;
	}



	public Date getDat_Nas_Aluno() {
		return Dat_Nas_Aluno;
	}



	public void setDat_Nas_Aluno(Date dat_Nas_Aluno) {
		Dat_Nas_Aluno = dat_Nas_Aluno;
	}



	public long getCPF_Aluno() {
		return CPF_Aluno;
	}



	public void setCPF_Aluno(long cPF_Aluno) {
		CPF_Aluno = cPF_Aluno;
	}



	public String getEmail_Aluno() {
		return Email_Aluno;
	}



	public void setEmail_Aluno(String email_Aluno) {
		Email_Aluno = email_Aluno;
	}



	public String getEnd_Aluno() {
		return End_Aluno;
	}



	public void setEnd_Aluno(String end_Aluno) {
		End_Aluno = end_Aluno;
	}



	public String getMuni_Aluno() {
		return Muni_Aluno;
	}



	public void setMuni_Aluno(String muni_Aluno) {
		Muni_Aluno = muni_Aluno;
	}



	public String getUF_Aluno() {
		return UF_Aluno;
	}



	public void setUF_Aluno(String uF_Aluno) {
		UF_Aluno = uF_Aluno;
	}



	public long getCel_Aluno() {
		return Cel_Aluno;
	}



	public void setCel_Aluno(long cel_Aluno) {
		Cel_Aluno = cel_Aluno;
	}



	public String getCur_Aluno() {
		return Cur_Aluno;
	}



	public void setCur_Aluno(String cur_Aluno) {
		Cur_Aluno = cur_Aluno;
	}



	public String getCam_Aluno() {
		return Cam_Aluno;
	}



	public void setCam_Aluno(String cam_Aluno) {
		Cam_Aluno = cam_Aluno;
	}



	public PeriodoAluno getPer_Aluno() {
		return Per_Aluno;
	}



	public void setPer_Aluno(PeriodoAluno per_Aluno) {
		Per_Aluno = per_Aluno;
	}

		

		
		
		
		
}