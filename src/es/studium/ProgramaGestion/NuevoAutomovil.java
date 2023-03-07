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

public class NuevoAutomovil implements WindowListener, ActionListener
{
	Frame f = new Frame("Nuevo Automóvil");
	Dialog dlgMensaje = new Dialog(f, "Mensaje", true);
	Label lblAlta = new Label(" - Alta de Automóvil -");
	Label lblMensaje = new Label("Alta de Automóvil Correcta");
	Label lblFecha = new Label(" Fecha Fabricación (DD/MM/AA):");
	Label lblPotencia = new Label("Potencia (CV):");
	Label lblMatricula = new Label("Matrícula:");
	Label lblPrecio = new Label("Precio ($):");
	Label lblTransmision = new Label("Transmisión:");
	Label lblColor = new Label("Color:");
	Label lblModelo = new Label("Modelo:");
	TextField txtFecha = new TextField(15);
	TextField txtPotencia = new TextField(15);
	TextField txtMatricula = new TextField(15);
	TextField txtPrecio = new TextField(15);
	TextField txtTransmision = new TextField(15);
	TextField txtColor = new TextField(15);
	TextField txtModelo = new TextField(15);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Conexion c = new Conexion();
	
	NuevoAutomovil()
	{
		f.setSize(200, 500);
		f.setLayout(new FlowLayout());
		f.addWindowListener(this);
		f.add(lblAlta);
		f.add(lblModelo);
		f.add(txtModelo);
		f.add(lblPotencia);
		f.add(txtPotencia);
		f.add(lblPrecio);
		f.add(txtPrecio);
		f.add(lblTransmision);
		f.add(txtTransmision);
		f.add(lblColor);
		f.add(txtColor);
		f.add(lblFecha);
		f.add(txtFecha);
		f.add(lblMatricula);
		f.add(txtMatricula);
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
		String tabla[] = txtFecha.getText().split("/");
		if(e.getSource().equals(btnAceptar))
		{
			dlgMensaje.setSize(245, 100);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.addWindowListener(this);
			if(txtModelo.getText().length()==0||txtPotencia.getText().length()==0||txtPrecio.getText().length()==0||txtTransmision.getText().length()==0||txtColor.getText().length()==0||txtFecha.getText().length()==0||txtMatricula.getText().length()==0)
			{
				lblMensaje.setText("Algún campo no ha sido rellenado");
			}
			else if(txtMatricula.getText().length()!=8||txtPrecio.getText().length()>10)
			{
				lblMensaje.setText("La Matrícula o el Precio no son válidos");
			}
			else if(tabla[0].length()!=1||tabla[1].length()!=1||tabla[2].length()!=1)
			{
				lblMensaje.setText("La Fecha introducida no es válida");
			}
			else
			{
				String sentencia = "insert into automoviles values (null, '" + tabla[2] + "-" + tabla[1] + "-" + tabla[0] + "', " + txtPotencia.getText() + ", '" + txtMatricula.getText() + "', " + txtPrecio.getText() + ", '" + txtTransmision.getText() + "', '" + txtColor.getText() + "', '" + txtModelo.getText() + "');";
				int respuesta = c.altaAutomovil(sentencia);
				if(respuesta!=0)
				{
					lblMensaje.setText("Ha ocurrido un error");
				}
				else
				{
					lblMensaje.setText("Alta de Automóvil Correcta");
				}
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtModelo.setText("");
			txtPotencia.setText("");
			txtPrecio.setText("");
			txtTransmision.setText("");
			txtColor.setText("");
			txtFecha.setText("");
			txtMatricula.setText("");
			txtModelo.requestFocus();
			
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
