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

public class Login implements WindowListener, ActionListener
{
	Frame login = new Frame("Login");
	Dialog dlgMensaje = new Dialog(login, "Error", true);
	Label lblUser = new Label("Usuario:");
	Label lblClave = new Label("Clave:");
	Label lblMensaje = new Label("Credenciales incorrectas");
	TextField txtUser = new TextField(15);
	TextField txtClave = new TextField(15);
	Button btnAcceder = new Button("Acceder");
	Conexion c = new Conexion();
	int tipoUsuario;
	
	Login()
	{
		login.setLayout(new FlowLayout());
		login.setSize(200,190);
		login.addWindowListener(this);
		login.add(lblUser);
		login.add(txtUser);
		login.add(lblClave);
		login.add(txtClave);
		txtClave.setEchoChar('*');
		btnAcceder.addActionListener(this);
		login.add(btnAcceder);
		login.setResizable(false);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnAcceder))
		{
			String usuario = txtUser.getText();
			String clave = txtClave.getText();
			// Credenciales correctas
			tipoUsuario = c.comprobarCredenciales(usuario, clave);
			if(tipoUsuario!=-1)
			{
				new MenuPrincipal(tipoUsuario);
				login.setVisible(false);
			}
			// Credenciales incorrectas
			else
			{
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(lblMensaje);
				dlgMensaje.setSize(200, 80);
				dlgMensaje.setResizable(false);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
			}
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
			System.exit(0);
		}
	}
	
	public void windowOpened(WindowEvent e){}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}

	public static void main(String[] args)
	{
		new Login();
	}
}
