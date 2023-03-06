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

public class NuevoUsuario implements WindowListener, ActionListener
{
	Frame f = new Frame("Nuevo Usuario");
	Dialog dlgMensaje = new Dialog(f, "Mensaje", true);
	Label lblAlta = new Label(" - Alta de Usuario -");
	Label lblNombre = new Label("Nombre:");
	Label lblClave = new Label("Clave:");
	Label lblClave2 = new Label("Repetir Clave:");
	Label lblMensaje = new Label("Alta de Usuario Correcta");
	TextField txtNombre = new TextField(15);
	TextField txtClave = new TextField(15);
	TextField txtClave2 = new TextField(15);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Conexion c = new Conexion();
	
	NuevoUsuario()
	{
		f.setSize(200, 275);
		f.setLayout(new FlowLayout());
		f.addWindowListener(this);
		f.add(lblAlta);
		f.add(lblNombre);
		f.add(txtNombre);
		f.add(lblClave);
		f.add(txtClave);
		txtClave.setEchoChar('*');
		f.add(lblClave2);
		f.add(txtClave2);
		txtClave2.setEchoChar('*');
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
			if(txtNombre.getText().length()==0||txtClave.getText().length()==0||txtClave2.getText().length()==0)
			{
				lblMensaje.setText("Algún campo no ha sido rellenado");
			}
			else if(!txtClave.getText().equals(txtClave2.getText()))
			{
				lblMensaje.setText("Las claves no coinciden");
			}
			else
			{
				// Dar de alta
				String sentencia = "insert into usuarios values (null, '" + txtNombre.getText() + "', SHA2('" + txtClave.getText() + "', 256), 1);";
				int respuesta = c.altaUsuario(sentencia);
				if(respuesta!=0)
				{
					// Mensaje de error
					lblMensaje.setText("Ha ocurrido un error");
				}
				else
				{
					lblMensaje.setText("Alta de Usuario Correcta");
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
			txtClave.setText("");
			txtClave2.setText("");
			txtNombre.requestFocus();
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
