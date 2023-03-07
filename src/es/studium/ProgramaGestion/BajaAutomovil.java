package es.studium.ProgramaGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BajaAutomovil implements WindowListener, ActionListener
{
	Frame f = new Frame("Baja Autom�viles");
	Dialog dlgSeguro = new Dialog(f, "�Segur@?", true);
	Dialog dlgMensaje = new Dialog(f, "Mensaje", true);
	Label lblElegir = new Label("Elegir el autom�vil a eliminar:");
	Label lblSeguro = new Label("�Seguro de eliminar ");
	Label lblMensaje = new Label("Autom�vil Eliminado");
	Choice choAutomoviles = new Choice();
	Button btnEliminar = new Button("Eliminar");
	Button btnSi = new Button("S�");
	Button btnNo = new Button("No");
	Conexion c = new Conexion();

	BajaAutomovil()
	{
		f.setSize(200, 220);
		f.setLayout(new FlowLayout());
		f.addWindowListener(this);
		f.add(lblElegir);
		c.rellenarChoiceAutomoviles(choAutomoviles);
		f.add(choAutomoviles);
		btnEliminar.addActionListener(this);
		f.add(btnEliminar);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String tabla[] = choAutomoviles.getSelectedItem().split("-");

		if (e.getSource().equals(btnEliminar))
		{
			if (choAutomoviles.getSelectedIndex() != 0)
			{
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.setSize(320,100);
				dlgSeguro.addWindowListener(this);
				lblSeguro.setText("�Seguro de eliminar al autom�vil " + tabla[1] + "?");
				dlgSeguro.add(lblSeguro);
				btnSi.addActionListener(this);
				dlgSeguro.add(btnSi);
				btnNo.addActionListener(this);
				dlgSeguro.add(btnNo);
				dlgSeguro.setResizable(false);
				dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.setVisible(true);
			}
		}
		else if (e.getSource().equals(btnNo))
		{
			dlgSeguro.setVisible(false);
		}
		else if (e.getSource().equals(btnSi))
		{
			int respuesta = c.eliminarAutomovil(tabla[0]);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(170,100);
			dlgMensaje.addWindowListener(this);
			if(respuesta==0)
			{
				lblMensaje.setText("Autom�vil Eliminado");
			}
			else
			{
				lblMensaje.setText("Error al eliminar");
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		
	}
	
	public void windowClosing(WindowEvent e)
	{
		if(dlgSeguro.isActive())
		{
			dlgSeguro.setVisible(false);
		}
		else if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
			dlgSeguro.setVisible(false);
			f.setVisible(false);
		}
		else
		{
			f.setVisible(false);
		}
	}

	public void windowOpened(WindowEvent e){}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
