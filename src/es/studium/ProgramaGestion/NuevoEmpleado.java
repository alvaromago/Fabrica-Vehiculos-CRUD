package es.studium.ProgramaGestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoEmpleado implements WindowListener, ActionListener
{
	Frame f = new Frame("Nuevo Empleado");
	Dialog dlgMensaje = new Dialog(f, "Mensaje", true);
	Label lblAlta = new Label(" - Alta de Empleado -");
	Label lblNombre = new Label("Nombre:");
	Label lblApellidos = new Label("Apellidos:");
	Label lblDni = new Label("DNI / NIF:");
	Label lblMovil = new Label("Teléfono:");
	Label lblCorreo = new Label("Correo:");
	Label lblMensaje = new Label("Alta de Empleado Correcta");
	TextField txtNombre = new TextField(15);
	TextField txtApellidos = new TextField(15);
	TextField txtDni = new TextField(15);
	TextField txtMovil = new TextField(15);
	TextField txtCorreo = new TextField(15);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Conexion c = new Conexion();
	
	NuevoEmpleado()
	{
		f.setSize(200, 390);
		f.setLayout(new FlowLayout());
		f.addWindowListener(this);
		f.add(lblAlta);
		f.add(lblNombre);
		f.add(txtNombre);
		f.add(lblApellidos);
		f.add(txtApellidos);
		f.add(lblDni);
		f.add(txtDni);
		f.add(lblMovil);
		f.add(txtMovil);
		f.add(lblCorreo);
		f.add(txtCorreo);
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
			if(txtNombre.getText().length()==0||txtApellidos.getText().length()==0||txtDni.getText().length()==0||txtMovil.getText().length()==0||txtCorreo.getText().length()==0)
			{
				lblMensaje.setText("Algún campo no ha sido rellenado");
			}
			else if(txtDni.getText().length()!=9||txtMovil.getText().length()!=9)
			{
				lblMensaje.setText("El DNI o Teléfono no son válidos");
			}
			else
			{
				String sentencia = "insert into empleado values (null, '" + txtNombre.getText() + "', '" + txtMovil.getText() + "', '" + txtDni.getText() + "', '" + txtCorreo.getText() + "', '" + txtApellidos.getText() + "');";
				int respuesta = c.altaEmpleado(sentencia);
				if(respuesta!=0)
				{
					lblMensaje.setText("Ha ocurrido un error");
				}
				else
				{
					lblMensaje.setText("Alta de Empleado Correcta");
				}
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtNombre.setText("");
			txtApellidos.setText("");
			txtDni.setText("");
			txtMovil.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
		}
	}

	public void windowOpened(WindowEvent e){}

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

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
