package es.studium.ProgramaGestion;

import java.awt.Choice;
import java.awt.TextArea;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Conexion
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/fabricavehiculospr";
	String login = "userFabrica"; // Usuario MySQL
	String password = "Studium2023;"; // Clave correspondiente
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	Conexion()
	{
		connection = this.conectar();
	}
	
	public Connection conectar()
	{
		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD
			return(DriverManager.getConnection(url, login, password));
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-" + sqle.getMessage());
		}
		return null;
	}

	public int comprobarCredenciales(String u, String c)
	{
		String cadena = "select * from usuarios where nombreUsuario = '" + u + "' and claveUsuario = SHA2('" + c + "',256);";
		try
		{
			// Crear sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(cadena);
			if(rs.next())
			{
				String entrada = "Accede usuario '" + u + "'";
				apunteLog(u, entrada);
				return rs.getInt("tipoUsuario");
			}
			else
			{
				return -1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 3-" + sqle.getMessage());
		}
		return -1;
	}

	public int altaUsuario(String sentencia)
	{
		try
		{
			// Creación de la sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-" + sqle.getMessage());
			return 1;
		}
	}

	public void rellenarListadoUsuario(TextArea txaUsuarios)
	{
		String sentencia = "select idUsuario, nombreUsuario from usuarios;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			String entrada = "Entra en el listado";
			apunteLog(MenuPrincipal.nusuario, entrada);
			while (resultado.next())
			{
				txaUsuarios.append(resultado.getString("idUsuario") + "  ");
				txaUsuarios.append(resultado.getString("nombreUsuario") + "\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-" + sqle.getMessage());
		} 
	}

	public void rellenarChoiceUsers(Choice choUsers)
	{
		String sentencia = "select idUsuario, nombreUsuario from usuarios order by 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choUsers.add("Elige un usuario...");
			while (resultado.next())
			{
				choUsers.add(resultado.getString("idUsuario") + "-" + resultado.getString("nombreUsuario"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 6-" + sqle.getMessage());
		}
	}

	public int eliminarUsuario(String idUsuario)
	{
		String sentencia = "delete from usuarios where idUsuario = " + idUsuario;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-" + sqle.getMessage());
			return 1;
		}
	}

	public int altaEmpleado(String sentencia)
	{
		try
		{
			// Creación de la sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 8-" + sqle.getMessage());
			return 1;
		}
	}

	public int altaAutomovil(String sentencia)
	{
		try
		{
			// Creación de la sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 9-" + sqle.getMessage());
			return 1;
		}
	}

	public void rellenarListadoEmpleado(TextArea txaEmpleados)
	{
		String sentencia = "select idEmpleado, nombreEmpleado, apellidosEmpleado, correoElectronicoEmpleado from empleado;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			String entrada = "Entra en el listado";
			apunteLog(MenuPrincipal.nusuario, entrada);
			while (resultado.next())
			{
				txaEmpleados.append(resultado.getString("idEmpleado") + "  ");
				txaEmpleados.append(resultado.getString("nombreEmpleado") + "  ");
				txaEmpleados.append(resultado.getString("apellidosEmpleado") + "  ");
				txaEmpleados.append(resultado.getString("correoElectronicoEmpleado") + "\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 10-" + sqle.getMessage());
		}
	}

	public void rellenarListadoAutomovil(TextArea txaAutomovil)
	{
		String sentencia = "select idAutomovil, modeloAutomovil, precioAutomovil, potenciaAutomovil from automoviles;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			String entrada = "Entra en el listado";
			apunteLog(MenuPrincipal.nusuario, entrada);
			while (resultado.next())
			{
				txaAutomovil.append(resultado.getString("idAutomovil") + "  ");
				txaAutomovil.append(resultado.getString("modeloAutomovil") + "  ");
				txaAutomovil.append(resultado.getString("precioAutomovil") + "$  ");
				txaAutomovil.append(resultado.getString("potenciaAutomovil") + " CV" + "\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 11-" + sqle.getMessage());
		}
	}

	public void rellenarChoiceEmpleados(Choice choEmpleados)
	{
		String sentencia = "select idEmpleado, nombreEmpleado from empleado order by 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choEmpleados.add("Elige un empleado...");
			while (resultado.next())
			{
				choEmpleados.add(resultado.getString("idEmpleado") + "-" + resultado.getString("nombreEmpleado"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 12-" + sqle.getMessage());
		}
	}

	public int eliminarEmpleado(String idEmpleado)
	{
		String sentencia = "delete from empleado where idEmpleado = " + idEmpleado;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 13-" + sqle.getMessage());
			return 1;
		}
	}

	public void rellenarChoiceAutomoviles(Choice choAutomoviles)
	{
		String sentencia = "select idAutomovil, modeloAutomovil from automoviles order by 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choAutomoviles.add("Elige un automóvil...");
			while (resultado.next())
			{
				choAutomoviles.add(resultado.getString("idAutomovil") + "-" + resultado.getString("modeloAutomovil"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 14-" + sqle.getMessage());
		}
	}

	public int eliminarAutomovil(String idAutomovil)
	{
		String sentencia = "delete from automoviles where idAutomovil = " + idAutomovil;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 15-" + sqle.getMessage());
			return 1;
		}
	}

	public String getUserEditar(String idUsuario)
	{
		String resultado = "";
		String sentencia = "select * from usuarios where idUsuario = " + idUsuario;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idUsuario") + "-" + resultSet.getString("nombreUsuario") + "-" + resultSet.getString("claveUsuario"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 16-"+sqle.getMessage());
		}
		return resultado;
	}

	public int modificarUser(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 17-" + sqle.getMessage());
			return 1;
		}
	}

	public String getEmpleadoEditar(String idEmpleado)
	{
		String resultado = "";
		String sentencia = "select * from empleado where idEmpleado = " + idEmpleado;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idEmpleado") + "-" + resultSet.getString("nombreEmpleado") + "-" + resultSet.getString("telefonoEmpleado") + "-" + resultSet.getString("dniEmpleado") + "-" + resultSet.getString("correoElectronicoEmpleado") + "-" + resultSet.getString("apellidosEmpleado"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 18-" + sqle.getMessage());
		}
		return resultado;
	}

	public int modificarEmpleado(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 19-" + sqle.getMessage());
			return 1;
		}
	}

	public String getAutomovilesEditar(String idAutomovil)
	{
		String resultado = "";
		String sentencia = "select * from automoviles where idAutomovil = " + idAutomovil;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idAutomovil") + "-" + resultSet.getString("fechaFabricacionAutomovil") + "-" + resultSet.getString("potenciaAutomovil") + "-" + resultSet.getString("matriculaAutomovil") + "-" + resultSet.getString("precioAutomovil") + "-" + resultSet.getString("transmisionAutomovil") + "-" + resultSet.getString("colorAutomovil") + "-" + resultSet.getString("modeloAutomovil"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 20-" + sqle.getMessage());
		}
		return resultado;
	}

	public int modificarAutomovil(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 21-" + sqle.getMessage());
			return 1;
		}
	}
	
	public void apunteLog(String usuario, String sentencia)
	{	
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formato.format(fecha);
		try
		{
			// Abrir el fichero
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			// Gestionar el fichero
			salida.println("[" + fechaFormateada + "] [" + usuario + "] " + sentencia);
			System.out.println("Información Almacenada");
			// Cerrar el fichero
			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error en Fichero");
		}
	}
}
