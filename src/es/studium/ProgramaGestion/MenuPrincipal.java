package es.studium.ProgramaGestion;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal implements WindowListener, ActionListener
{
	Frame v = new Frame("Menú Principal");
	MenuBar menuBar = new MenuBar();
	Menu mnuUsuarios = new Menu("Usuarios");
	MenuItem mniNuevoUsuario = new MenuItem("Nuevo");
	MenuItem mniListadoUsuario = new MenuItem("Listado");
	MenuItem mniBajaUsuario = new MenuItem("Baja");
	MenuItem mniModificarUsuario = new MenuItem("Modificar");
	Menu mnuEmpleados = new Menu("Empleados");
	MenuItem mniNuevoEmpleado = new MenuItem("Nuevo");
	MenuItem mniListadoEmpleado = new MenuItem("Listado");
	MenuItem mniBajaEmpleado = new MenuItem("Baja");
	MenuItem mniModificarEmpleado = new MenuItem("Modificar");
	Menu mnuAutomoviles = new Menu("Automoviles");
	MenuItem mniNuevoAutomovil = new MenuItem("Nuevo");
	MenuItem mniListadoAutomovil = new MenuItem("Listado");
	MenuItem mniBajaAutomovil = new MenuItem("Baja");
	MenuItem mniModificarAutomovil = new MenuItem("Modificar");
	Menu mnuTrabajos = new Menu("Trabajos");
	MenuItem mniNuevoTrabajo = new MenuItem("Nuevo");
	MenuItem mniListadoTrabajo = new MenuItem("Listado");
	Conexion c = new Conexion();
	static String nusuario;
	int tipoUsuario;
	
	MenuPrincipal(String usuario, int t)
	{
		nusuario = usuario;
		tipoUsuario = t;
		v.setLayout(new FlowLayout());
		v.setSize(350, 250);
		v.addWindowListener(this);
		mniNuevoUsuario.addActionListener(this);
		mniListadoUsuario.addActionListener(this);
		mniBajaUsuario.addActionListener(this);
		mniModificarUsuario.addActionListener(this);
		mnuUsuarios.add(mniNuevoUsuario);
		if(tipoUsuario==0)
		{			
			mnuUsuarios.add(mniListadoUsuario);
			mnuUsuarios.add(mniBajaUsuario);
			mnuUsuarios.add(mniModificarUsuario);
		}
		mniNuevoEmpleado.addActionListener(this);
		mniListadoEmpleado.addActionListener(this);
		mniBajaEmpleado.addActionListener(this);
		mniModificarEmpleado.addActionListener(this);
		mnuEmpleados.add(mniNuevoEmpleado);
		if(tipoUsuario==0)
		{			
			mnuEmpleados.add(mniListadoEmpleado);
			mnuEmpleados.add(mniBajaEmpleado);
			mnuEmpleados.add(mniModificarEmpleado);
		}
		mniNuevoAutomovil.addActionListener(this);
		mniListadoAutomovil.addActionListener(this);
		mniBajaAutomovil.addActionListener(this);
		mniModificarAutomovil.addActionListener(this);
		mnuAutomoviles.add(mniNuevoAutomovil);
		if(tipoUsuario==0)
		{			
			mnuAutomoviles.add(mniListadoAutomovil);
			mnuAutomoviles.add(mniBajaAutomovil);
			mnuAutomoviles.add(mniModificarAutomovil);
		}
		mniNuevoTrabajo.addActionListener(this);
		mniListadoTrabajo.addActionListener(this);
		mnuTrabajos.add(mniNuevoTrabajo);
		mnuTrabajos.add(mniListadoTrabajo);
		menuBar.add(mnuUsuarios);
		menuBar.add(mnuEmpleados);
		menuBar.add(mnuAutomoviles);
		menuBar.add(mnuTrabajos);
		v.setMenuBar(menuBar);
		v.setLocationRelativeTo(null);
		v.setResizable(false);
		v.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		// Dar de alta un usuario
		if(e.getSource().equals(mniNuevoUsuario))
		{
			new NuevoUsuario();
		}
		// Listado de los usuarios
		else if(e.getSource().equals(mniListadoUsuario))
		{
			new ListadoUsuario();
		}
		// Dar de baja un usuario
		else if(e.getSource().equals(mniBajaUsuario))
		{
			new BajaUsuario();
		}
		// Modificar un usuario
		else if(e.getSource().equals(mniModificarUsuario))
		{
			new ModificarUsuario();
		}
		// Dar de alta un empleado
		else if(e.getSource().equals(mniNuevoEmpleado))
		{
			new NuevoEmpleado();
		}
		// Listado de los empleados
		else if(e.getSource().equals(mniListadoEmpleado))
		{
			new ListadoEmpleado();
		}
		// Dar de baja un empleado
		else if(e.getSource().equals(mniBajaEmpleado))
		{
			new BajaEmpleado();
		}
		// Modificar un empleado
		else if(e.getSource().equals(mniModificarEmpleado))
		{
			new ModificarEmpleado();
		}
		// Dar de alta un automóvil
		else if(e.getSource().equals(mniNuevoAutomovil))
		{
			new NuevoAutomovil();
		}
		// Listado de los automóviles
		else if(e.getSource().equals(mniListadoAutomovil))
		{
			new ListadoAutomovil();
		}
		// Dar de baja un automóvil
		else if(e.getSource().equals(mniBajaAutomovil))
		{
			new BajaAutomovil();
		}
		// Modificar un automóvil
		else if(e.getSource().equals(mniModificarAutomovil))
		{
			new ModificarAutomovil();
		}
		// Registrar nuevo trabajo
		else if(e.getSource().equals(mniNuevoTrabajo))
		{
			new NuevoTrabajo();
		}
		else if(e.getSource().equals(mniListadoTrabajo))
		{
			new ListadoTrabajo();
		}
	}

	public void windowClosing(WindowEvent e)
	{
		String salida = "Sale usuario '" + nusuario + "'";
		c.apunteLog(nusuario, salida);
		System.exit(0);
	}
	
	public void windowOpened(WindowEvent e){}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
	
}
