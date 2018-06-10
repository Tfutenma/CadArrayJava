
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.lang.NumberFormatException;



public class Principal implements ActionListener{
	
	private static JOptionPane jop = new JOptionPane();


	private static JFrame janela = new JFrame(); 
	private static JLabel lbl_rg = new JLabel(); 
	private static JTextField tf_rg = new JTextField(21); 
	private static JLabel lbl_nome = new JLabel(); 
	private static JTextField tf_nome = new JTextField(19); 
	private static JLabel lbl_rua = new JLabel(); 
	private static JTextField tf_rua = new JTextField(20); 
	private static JLabel lbl_numero = new JLabel(); 	
	private static JTextField tf_numero = new JTextField(15); 
	private static JButton btn_inserir = new JButton("Inserir");
	private static JButton btn_consultar = new JButton("Consultar");
	private static JButton btn_alterar = new JButton("Alterar");
	private static JButton btn_deletar = new JButton("Deletar");
	private static JButton btn_limpar = new JButton("Limpar");
	private static JButton btn_sair = new JButton("Sair");

	private static Principal principal= new Principal();

	private BDpes bd = new BDpes(); 
	
	public static void main(String args[]){

		
		int larg = 300, alt =210; 
		janela.setSize(larg,alt); 
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		janela.setLocationRelativeTo(null);
		janela.setTitle("Cadastro de Pessoas");

		
		lbl_rg.setText("RG :"); 
		janela.add(lbl_rg);		
		janela.add(tf_rg);
		tf_rg.setText("");

		
		lbl_nome.setText("Nome: "); 
		janela.add(lbl_nome);		
		janela.add(tf_nome);
		tf_nome.requestFocus();
		tf_nome.setText("");
		
		lbl_rua.setText("Rua :"); 
		janela.add(lbl_rua);
		janela.add(tf_rua);
		tf_rua.setText("");

		
		lbl_numero.setText("Numero: "); 
		janela.add(lbl_numero);		
		janela.add(tf_numero);		
		tf_numero.setText("");
		
		janela.add(btn_inserir);		
		janela.add(btn_consultar);		
		janela.add(btn_alterar);		
		janela.add(btn_deletar);		
		janela.add(btn_limpar);		
		janela.add(btn_sair);

		janela.setLayout(new FlowLayout()); 
		janela.setVisible(true);

		btn_inserir.addActionListener(principal);
		btn_consultar.addActionListener(principal);
		btn_alterar.addActionListener(principal);
		btn_deletar.addActionListener(principal);
		btn_limpar.addActionListener(principal);
		btn_sair.addActionListener(principal);

		

	}


	public void actionPerformed(ActionEvent evt){ 
		Object obj = evt.getSource();
		if(obj.equals(btn_inserir)){ 

			Pessoa pes = new Pessoa(); 
			Pessoa resp = new Pessoa();
			
			if(tf_rg.getText().trim().equals("") || tf_nome.getText().trim().equals("") || tf_rua.getText().trim().equals("") || tf_numero.getText().trim().equals("")){

				jop.showMessageDialog(null, "Esta faltando informacao", "Erro", jop.PLAIN_MESSAGE);	
					
			}else{

				try{
					try{
						pes.setRg(Integer.parseInt(tf_rg.getText()));
						pes.setNome(tf_nome.getText());
						pes.getEndereco().setRua(tf_rua.getText());
						pes.getEndereco().setNumero(Integer.parseInt(tf_numero.getText()));

						resp = bd.insPes(pes);

						if(resp==null){
							tf_nome.setText(null);
							tf_rg.setText(null);
							tf_rua.setText(null);
							tf_numero.setText(null);
						} else{							
							tf_nome.setText(resp.getNome());
							tf_rg.setText(Integer.toString(resp.getRg()));
							tf_rua.setText(resp.getEndereco().getRua());
							tf_numero.setText(Integer.toString(resp.getEndereco().getNumero()));
						}

					}catch(RgException re){
						re.impRg();
					}catch(NumberFormatException nfe){
						jop.showMessageDialog(null,"O RG e o Numero devem ser inteiros","Erro",jop.PLAIN_MESSAGE);
						
					}
				} catch(ExistRgException ere){
					ere.impRg();
				}				

			}
		}


		if(obj.equals(btn_consultar)){

			Pessoa pes = new Pessoa(); 
			Pessoa resp = new Pessoa();
			
			if(tf_rg.getText().trim().equals("") ){
				jop.showMessageDialog(null,"Digite o RG","Erro",jop.PLAIN_MESSAGE);
			} else{

				try{
					try{
						pes.setRg(Integer.parseInt(tf_rg.getText()));
						resp = bd.consPesRg(pes);

						if(resp!=null){
							tf_nome.setText(resp.getNome());
							tf_rg.setText(Integer.toString(resp.getRg()));
							tf_rua.setText(resp.getEndereco().getRua());
							tf_numero.setText(Integer.toString(resp.getEndereco().getNumero()));	
							jop.showMessageDialog(null,"Encontrado!","Sucesso",jop.PLAIN_MESSAGE);							
						} else{
							tf_nome.setText(null);
							tf_rg.setText(null);
							tf_rua.setText(null);
							tf_numero.setText(null);
							jop.showMessageDialog(null,"RG nao cadastrado","Erro",jop.PLAIN_MESSAGE);
						}
					}catch(RgException re){
						re.impRg();
					}
				}catch(ExistRgException ere){
					ere.impRg();
				}
			}
		}


		if(obj.equals(btn_alterar)){
			Pessoa pes = new Pessoa(); 
			Pessoa resp = new Pessoa();

			if(tf_rg.getText().trim().equals("")){
				jop.showMessageDialog(null,"Digite o RG","Erro",jop.PLAIN_MESSAGE);
			}else{
				try{
					try{
						pes.setRg(Integer.parseInt(tf_rg.getText()));
						resp = bd.altPesRg(pes);

						if(resp!=null){
							tf_nome.setText(resp.getNome());
							tf_rg.setText(Integer.toString(resp.getRg()));
							tf_rua.setText(resp.getEndereco().getRua());
							tf_numero.setText(Integer.toString(resp.getEndereco().getNumero()));
							tf_rg.requestFocus();							
						} else{
							tf_nome.setText(null);
							tf_rg.setText(null);
							tf_rua.setText(null);
							tf_numero.setText(null);								
						}
					}catch(RgException re){
						re.impRg();
					}
				}catch(ExistRgException ere){
					ere.impRg();
				}
			}
		}


		

		if(obj.equals(btn_deletar)){
		
			Pessoa pes = new Pessoa(); 
			Pessoa resp = new Pessoa();

			if(tf_rg.getText().trim().equals("")){
				jop.showMessageDialog(null,"Digite o RG","Erro",jop.PLAIN_MESSAGE);
			}else{
				try{
					try{
						pes.setRg(Integer.parseInt(tf_rg.getText()));
						resp = bd.delPes(pes);

						if(resp!=null){
							tf_nome.setText(resp.getNome());
							tf_rg.setText(Integer.toString(resp.getRg()));
							tf_rua.setText(resp.getEndereco().getRua());
							tf_numero.setText(Integer.toString(resp.getEndereco().getNumero()));
							tf_rg.requestFocus();							
						} else{
							tf_nome.setText(null);
							tf_rg.setText(null);
							tf_rua.setText(null);
							tf_numero.setText(null);
							tf_rg.requestFocus();								
						}
					}catch(RgException re){
						re.impRg();
					}
				}catch(ExistRgException ere){
					ere.impRg();
				}
			}

		}

		if(obj.equals(btn_limpar)){
			tf_nome.setText(null);
							tf_rg.setText(null);
							tf_rua.setText(null);
							tf_numero.setText(null);
							tf_rg.requestFocus();	
		}

		if(obj.equals(btn_sair)){
			int i = jop.showConfirmDialog(null,"Deseja mesmo sair?","Sair",jop.YES_NO_OPTION);
			if(i==0) System.exit(0);
		}


	}
}