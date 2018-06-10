
import javax.swing.JOptionPane;

public class RgException extends Exception{
	
	JOptionPane jop = new JOptionPane();
	
	public void impRg(){
		jop.showMessageDialog(null,"O RG ddeve estar entre 11 e 49","Erro",jop.PLAIN_MESSAGE);
	}


}