
public class Pessoa{
	

	private int rg;
	private String nome;
	private Endereco endereco = new Endereco();

	public int getRg(){
		return rg;
	}

	public String getNome(){
		return nome;
	}

	public Endereco getEndereco(){
		return endereco;
	}

	public void setRg(int rg) throws RgException{
		
		if(rg>10&&rg<50) this.rg = rg;
		else throw new RgException();


	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public void setEndereco(Endereco endereco){
		this.endereco = endereco;
	}


}