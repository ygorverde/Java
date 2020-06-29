
public class Paciente {

	private String Id;
	private String CPF;
	private String Nome;
	private String Sobrenome;
	private String Sexo;
	private String Dataadmissao;
	private String Pais;
	private String Diagnostico;

	public Paciente(String id, String cpf, String nome, String sobrenome, /* String email, */ String sexo,
			String dataadmissao, String pais, String diagnostico) {
		super();
		Id = id;
		CPF = cpf;
		Nome = nome;
		Sobrenome = sobrenome;
		// Email = email;
		Sexo = sexo;
		Dataadmissao = dataadmissao;
		Pais = pais;
		Diagnostico = diagnostico;
	}

	public String getId() {
		return Id;
	}

	public String getCPF() {
		return CPF;
	}

	public String getNome() {
		return Nome;
	}

	public String getSobrenome() {
		return Sobrenome;
	}

	/*
	 * public String getEmail() { return Email; }
	 *
	 * public void setEmail(String email) { Email = email; }
	 */

	public String getSexo() {
		return Sexo;
	}

	public String getDataadmissao() {
		return Dataadmissao;
	}

	public String getPais() {
		return Pais;
	}

	public String getDiagnostico() {
		return Diagnostico;
	}


}
