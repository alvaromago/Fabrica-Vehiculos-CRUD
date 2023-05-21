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

public class NuevoTrabajo implements WindowListener, ActionListener
{
	Frame f = new Frame("Nuevo Trabajo");
	Dialog dlgMensaje = new Dialog(f, "Mensaje", true);
	Label lblMensaje = new Label("Alta de Trabajo Correcta");
	Label lblAlta = new Label(" - Nuevo Trabajo -");
	Label lblEmpleado = new Label("Elige el empleado:");
	Label lblAutomovil = new Label("Elige el automóvil:");
	Choice choEmpleados = new Choice();
	Choice choAutomoviles = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Conexion c = new Conexion();

	NuevoTrabajo()
	{
		f.setSize(200, 230);
		f.setLayout(new FlowLayout());
		f.addWindowListener(this);
		f.add(lblAlta);
		f.add(lblEmpleado);
		c.rellenarChoiceEmpleados(choEmpleados);
		f.add(choEmpleados);
		f.add(lblAutomovil);
		c.rellenarChoiceAutomoviles(choAutomoviles);
		f.add(choAutomoviles);
		f.add(btnAceptar);
		f.add(btnCancelar);
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
		f.add(btnAceptar);
		f.add(btnCancelar);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnAceptar))
		{
			dlgMensaje.setSize(225, 100);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.addWindowListener(this);
			if(choEmpleados.getSelectedIndex()==0 || choAutomoviles.getSelectedIndex()==0)
			{
				lblMensaje.setText("Por favor seleccione los dos campos");
			}
			else
			{
				// Registrar trabajo
				String sentencia = "";
				int respuesta = c.altaTrabajo(sentencia);
				if(respuesta!=0)
				{
					// Mensaje de error
					lblMensaje.setText("Ha ocurrido un error");
				}
				else
				{
					lblMensaje.setText("Alta de Trabajo Correcta");
				}
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			f.setVisible(false);
		}
	}

	public void windowClosing(WindowEvent e)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
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
