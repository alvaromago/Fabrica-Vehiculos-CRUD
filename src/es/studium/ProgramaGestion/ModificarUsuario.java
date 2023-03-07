package es.studium.ProgramaGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModificarUsuario implements WindowListener, ActionListener
{
	Frame f = new Frame("Editar Usuario");
	Dialog dlgEditar = new Dialog(f, "Edición", true);
	Dialog dlgMensaje = new Dialog(f, "Mensaje", true);
	Label lblElegir = new Label(" Elige el usuario a editar:");
	Choice choUsers = new Choice();
	Button btnMod = new Button("Editar");
	Conexion c = new Conexion();
	Label lblMod = new Label(" - Modificación de Usuario -");
	Label lblNombre = new Label("Nombre:");
	Label lblClave = new Label("Clave:");
	Label lblClave2 = new Label("Repetir Clave:");
	Label lblMensaje = new Label("Modificación de Usuario Correcta");
	TextField txtNombre = new TextField(15);
	TextField txtClave = new TextField(15);
	TextField txtClave2 = new TextField(15);
	Button btnMod2 = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");
	String idUsuario = "";

	ModificarUsuario()
	{
		f.setLayout(new FlowLayout());
		f.setSize(200,200);
		f.addWindowListener(this);
		f.add(lblElegir);
		// Rellenar el Choice
		c.rellenarChoiceUsers(choUsers);
		f.add(choUsers);
		btnMod.addActionListener(this);
		f.add(btnMod);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnMod))
		{
			if(choUsers.getSelectedIndex()!=0)
			{
				dlgEditar.setLayout(new FlowLayout());
				dlgEditar.setSize(170,280);
				dlgEditar.addWindowListener(this);
				btnMod2.addActionListener(this);
				dlgEditar.add(lblElegir);
				dlgEditar.add(lblNombre);
				dlgEditar.add(txtNombre);
				dlgEditar.add(lblClave);
				txtClave.setEchoChar('*');
				dlgEditar.add(txtClave);
				dlgEditar.add(lblClave2);
				txtClave2.setEchoChar('*');
				dlgEditar.add(txtClave2);				
				btnMod.addActionListener(this);
				btnCancelar.addActionListener(this);
				dlgEditar.add(btnMod2);
				dlgEditar.add(btnCancelar);
				dlgEditar.setResizable(false);
				dlgEditar.setLocationRelativeTo(null);
				String tabla[] = choUsers.getSelectedItem().split("-");
				String resultado = c.getUserEditar(tabla[0]);
				String datos[] = resultado.split("-");
				idUsuario = datos[0];
				txtNombre.setText(datos[1]);
				txtClave.setText("");
				txtClave2.setText("");
				dlgEditar.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnMod2))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(220,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtNombre.getText().length()==0||txtClave.getText().length()==0||txtClave2.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			// Comprobar las claves
			else if(!txtClave.getText().equals(txtClave2.getText()))
			{
				lblMensaje.setText("Las claves no coinciden");
			}
			else
			{
				// Modificar
				String sentencia = "update usuarios set nombreUsuario='"+txtNombre.getText()+"', claveUsuario = SHA2('"+txtClave.getText()+"', 256) where idUsuario = " + idUsuario + ";";
				int respuesta = c.modificarUser(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error al Modificar");
				}
				else
				{
					lblMensaje.setText("Modificación de Usuario Correcta");
				}
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if (e.getSource().equals(btnCancelar))
		{
			dlgEditar.setVisible(false);
		}
	}
	
	public void windowClosing(WindowEvent e)
	{
		if(dlgEditar.isActive())
		{
			dlgEditar.setVisible(false);
			f.setVisible(false);
		}
		else if (dlgMensaje.isActive())
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
