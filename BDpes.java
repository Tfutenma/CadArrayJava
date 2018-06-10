

import javax.swing.JOptionPane;

public class BDpes{
	
	
	private Pessoa[] vetPes = new Pessoa[5];
	private int indice = 0;


	JOptionPane jop = new JOptionPane();

	public Pessoa consPesRg(Pessoa pes)throws ExistRgException{
		if(indice !=0){
			for(int i=0;i<indice;i++){  
				if(vetPes[i].getRg()==pes.getRg()){                     
					return vetPes[i];                 
				}  
        	}
		}else{
			return null;
		}      
		return null;    
	}

	public int consEndRg(Pessoa pes){
		int end =-1;
		for(int i=0;i<indice;i++){  
			if(vetPes[i].getRg()==pes.getRg()){                     
				return i;                 
			}  
        }             
            return -1; 
	}

	public Pessoa insPes(Pessoa pes) throws ExistRgException{
		
		if(consPesRg(pes)!=null){
			int op = jop.showConfirmDialog(null,"Esse Rg já existe, deseja sobrepor?","Sobrepor",jop.YES_NO_OPTION);
			if(op==0){		

				try{
					int end=consEndRg(pes);			
					vetPes[end].setRg(pes.getRg());
					vetPes[end].setNome(pes.getNome());
					vetPes[end].setEndereco(pes.getEndereco());
					jop.showMessageDialog(null,"Pessoa Inserida com sucesso!","Inserido!",jop.PLAIN_MESSAGE);
					return consPesRg(pes);
				}catch(RgException re){
					re.impRg();
				}		
								
			}else{
				return null;
			}

		} else{
			if(indice<vetPes.length){
				try{
					vetPes[indice] = new Pessoa();
					vetPes[indice].setRg(pes.getRg());
					vetPes[indice].setNome(pes.getNome());
					vetPes[indice].setEndereco(pes.getEndereco());
					indice++;
					jop.showMessageDialog(null,"Pessoa Inserida com sucesso!","Inserido!",jop.PLAIN_MESSAGE);
					return consPesRg(pes);

				} catch(RgException re){
					re.impRg();
				}				
			} else{
				jop.showMessageDialog(null,"Tamanho do vetor excedido","Erro",jop.PLAIN_MESSAGE);
				return null;
			}
		}
		return null;
	}


	public Pessoa altPesRg(Pessoa pes) throws ExistRgException{

		int end = consEndRg(pes);
		if(consPesRg(pes)!=null){
			int op = jop.showConfirmDialog(null,"Deseja alterar?","Alterar",jop.YES_NO_OPTION);
			if(op==0){
				return vetPes[end];
			} else{
				return null;
			}
		} else{
			jop.showMessageDialog(null,"Nao existe pessoa com esse RG","Erro",jop.PLAIN_MESSAGE);
			return null;
		}

	}

	public Pessoa delPes(Pessoa pes) throws ExistRgException{

		int end = consEndRg(pes);
		if(consPesRg(pes)!=null){
			int op = jop.showConfirmDialog(null,"Deseja deletar?","Deletar",jop.YES_NO_OPTION);
			if(op==0){
				
				for(int i=end;i<indice-1;i++){
					vetPes[i] = vetPes[i+1];

				}
				indice--;
				vetPes[indice]=null;
				return null;

			} else{
				return null;
			}
		} else{

			jop.showMessageDialog(null,"Nao existe pessoa com esse RG","Erro",jop.PLAIN_MESSAGE);
			return null;
			
		}

	}




}