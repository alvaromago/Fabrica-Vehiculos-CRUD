package es.studium.ProgramaGestion;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ListadoAutomovil implements WindowListener, ActionListener
{
	Frame f = new Frame("Listado Automóviles");
	TextArea txaAutomovil = new TextArea(6, 35);
	Button btnPdf = new Button("Exportar a PDF");
	Conexion c = new Conexion();
	
	ListadoAutomovil()
	{
		f.setSize(320, 200);
		f.setLayout(new FlowLayout());
		f.addWindowListener(this);
		c.rellenarListadoAutomovil(txaAutomovil);
		f.add(txaAutomovil);
		txaAutomovil.setEditable(false);
		f.add(btnPdf);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){}

	public void windowOpened(WindowEvent e){}

	public void windowClosing(WindowEvent e)
	{
		f.setVisible(false);
	}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
