
import javax.swing.JOptionPane;

public class ExistRgException extends Exception{
	
	JOptionPane jop = new JOptionPane();
	
	public void impRg(){
		jop.showMessageDialog(null,"O Rg ja existe", "ExistRgException" ,jop.PLAIN_MESSAGE);
	}


}