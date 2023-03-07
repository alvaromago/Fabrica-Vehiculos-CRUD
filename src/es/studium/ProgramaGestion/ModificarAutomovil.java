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

public class ModificarAutomovil implements WindowListener, ActionListener
{
	Frame f = new Frame("Editar Automóvil");
	Dialog dlgEditar = new Dialog(f, "Edición", true);
	Dialog dlgMensaje = new Dialog(f, "Mensaje", true);
	Label lblElegir = new Label(" Elige el automóvil a editar:");
	Choice choAutomoviles = new Choice();
	Button btnMod = new Button("Editar");
	Conexion c = new Conexion();
	Label lblMod = new Label(" - Modificación de Automóvil -");
	Label lblFecha = new Label(" Fecha Fabricación (DD/MM/AA):");
	Label lblPotencia = new Label("Potencia (CV):");
	Label lblMatricula = new Label("Matrícula:");
	Label lblPrecio = new Label("Precio ($):");
	Label lblTransmision = new Label("Transmisión:");
	Label lblColor = new Label("Color:");
	Label lblModelo = new Label("Modelo:");
	Label lblMensaje = new Label("Modificación de Automóvil Correcta");
	TextField txtFecha = new TextField(15);
	TextField txtPotencia = new TextField(15);
	TextField txtMatricula = new TextField(15);
	TextField txtPrecio = new TextField(15);
	TextField txtTransmision = new TextField(15);
	TextField txtColor = new TextField(15);
	TextField txtModelo = new TextField(15);
	Button btnMod2 = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");
	String idAutomovil = "";

	ModificarAutomovil()
	{
		f.setLayout(new FlowLayout());
		f.setSize(200,170);
		f.addWindowListener(this);
		f.add(lblElegir);
		// Rellenar el Choice
		c.rellenarChoiceAutomoviles(choAutomoviles);
		f.add(choAutomoviles);
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
			if(choAutomoviles.getSelectedIndex()!=0)
			{
				dlgEditar.setLayout(new FlowLayout());
				dlgEditar.setSize(200,500);
				dlgEditar.addWindowListener(this);
				btnMod2.addActionListener(this);
				dlgEditar.add(lblElegir);
				dlgEditar.add(lblModelo);
				dlgEditar.add(txtModelo);
				dlgEditar.add(lblPotencia);
				dlgEditar.add(txtPotencia);
				dlgEditar.add(lblPrecio);
				dlgEditar.add(txtPrecio);
				dlgEditar.add(lblTransmision);
				dlgEditar.add(txtTransmision);
				dlgEditar.add(lblColor);
				dlgEditar.add(txtColor);
				dlgEditar.add(lblFecha);
				dlgEditar.add(txtFecha);
				dlgEditar.add(lblMatricula);
				dlgEditar.add(txtMatricula);
				btnMod.addActionListener(this);
				btnCancelar.addActionListener(this);
				dlgEditar.add(btnMod2);
				dlgEditar.add(btnCancelar);
				dlgEditar.setResizable(false);
				dlgEditar.setLocationRelativeTo(null);
				String tabla[] = choAutomoviles.getSelectedItem().split("-");
				String resultado = c.getAutomovilesEditar(tabla[0]);
				String datos[] = resultado.split("-");
				idAutomovil = datos[0];
				txtFecha.setText(datos[3] + "/" + datos[2] + "/" + datos[1]);
				txtPotencia.setText(datos[4]);
				txtMatricula.setText(datos[5]);
				txtPrecio.setText(datos[6]);
				txtTransmision.setText(datos[7]);
				txtColor.setText(datos[8]);
				txtModelo.setText(datos[9]);
				
				dlgEditar.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnMod2))
		{
			String fecha[] = txtFecha.getText().split("/");
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(230,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtModelo.getText().length()==0||txtFecha.getText().length()==0||txtPotencia.getText().length()==0||txtPrecio.getText().length()==0||txtTransmision.getText().length()==0||txtColor.getText().length()==0||txtMatricula.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			else if(txtMatricula.getText().length()!=8||txtPrecio.getText().length()>10)
			{
				lblMensaje.setText("La Matrícula o el Precio no son válidos");
			}
			else
			{
				// Modificar
				String sentencia = "update automoviles set modeloAutomovil='" + txtModelo.getText() + "', fechaFabricacionAutomovil = '" + fecha[2] + "-" + fecha[1] + "-" + fecha[0] + "', potenciaAutomovil = '" + txtPotencia.getText() + "', precioAutomovil = '" + txtPrecio.getText() + "', transmisionAutomovil = '" + txtTransmision.getText() + "', colorAutomovil = '" + txtColor.getText() + "', matriculaAutomovil = '" + txtMatricula.getText() + "'  where idAutomovil = " + idAutomovil + ";";
				int respuesta = c.modificarUser(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error al Modificar");
				}
				else
				{
					lblMensaje.setText("Modificación de Automóvil Correcta");
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
