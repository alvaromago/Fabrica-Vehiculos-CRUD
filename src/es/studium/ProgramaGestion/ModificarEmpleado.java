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

public class ModificarEmpleado implements WindowListener, ActionListener
{
	Frame f = new Frame("Editar Empleado");
	Dialog dlgEditar = new Dialog(f, "Edición", true);
	Dialog dlgMensaje = new Dialog(f, "Mensaje", true);
	Label lblElegir = new Label(" Elige el empleado a editar:");
	Choice choEmpleados = new Choice();
	Button btnMod = new Button("Editar");
	Conexion c = new Conexion();
	Label lblMod = new Label(" - Modificación de Empleado -");
	Label lblNombre = new Label("Nombre:");
	Label lblApellidos = new Label("Apellidos:");
	Label lblDni = new Label("DNI / NIF:");
	Label lblMovil = new Label("Teléfono");
	Label lblCorreo = new Label("Correo:");
	Label lblMensaje = new Label("Modificación de Empleado Correcta");
	TextField txtNombre = new TextField(15);
	TextField txtApellidos = new TextField(15);
	TextField txtDni = new TextField(15);
	TextField txtMovil = new TextField(15);
	TextField txtCorreo = new TextField(15);
	Button btnMod2 = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");
	String idEmpleado = "";

	ModificarEmpleado()
	{
		f.setLayout(new FlowLayout());
		f.setSize(200,170);
		f.addWindowListener(this);
		f.add(lblElegir);
		// Rellenar el Choice
		c.rellenarChoiceEmpleados(choEmpleados);
		f.add(choEmpleados);
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
			if(choEmpleados.getSelectedIndex()!=0)
			{
				dlgEditar.setLayout(new FlowLayout());
				dlgEditar.setSize(180,390);
				dlgEditar.addWindowListener(this);
				btnMod2.addActionListener(this);
				dlgEditar.add(lblElegir);
				dlgEditar.add(lblNombre);
				dlgEditar.add(txtNombre);
				dlgEditar.add(lblApellidos);
				dlgEditar.add(txtApellidos);
				dlgEditar.add(lblDni);
				dlgEditar.add(txtDni);
				dlgEditar.add(lblMovil);
				dlgEditar.add(txtMovil);
				dlgEditar.add(lblCorreo);
				dlgEditar.add(txtCorreo);
				btnMod.addActionListener(this);
				btnCancelar.addActionListener(this);
				dlgEditar.add(btnMod2);
				dlgEditar.add(btnCancelar);
				dlgEditar.setResizable(false);
				dlgEditar.setLocationRelativeTo(null);
				String tabla[] = choEmpleados.getSelectedItem().split("-");
				String resultado = c.getEmpleadoEditar(tabla[0]);
				String datos[] = resultado.split("-");
				idEmpleado = datos[0];
				txtNombre.setText(datos[1]);
				txtApellidos.setText(datos[5]);
				txtDni.setText(datos[3]);
				txtMovil.setText(datos[2]);
				txtCorreo.setText(datos[4]);
				dlgEditar.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnMod2))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(230,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtNombre.getText().length()==0||txtApellidos.getText().length()==0||txtDni.getText().length()==0||txtMovil.getText().length()==0||txtCorreo.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			else if(txtDni.getText().length()!=9||txtMovil.getText().length()!=9)
			{
				lblMensaje.setText("El DNI o Teléfono no son válidos");
			}
			else
			{
				// Modificar
				String sentencia = "update empleado set nombreEmpleado='" + txtNombre.getText() + "', telefonoEmpleado = '" + txtMovil.getText() + "', dniEmpleado = '" + txtDni.getText() + "', correoElectronicoEmpleado = '" + txtCorreo.getText() + "', apellidosEmpleado = '" + txtApellidos.getText() + "' where idEmpleado = " + idEmpleado + ";";
				int respuesta = c.modificarEmpleado(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error al Modificar");
				}
				else
				{
					lblMensaje.setText("Modificación de Empleado Correcta");
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
