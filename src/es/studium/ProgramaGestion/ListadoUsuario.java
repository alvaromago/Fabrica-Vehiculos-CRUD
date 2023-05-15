package es.studium.ProgramaGestion;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class ListadoUsuario implements WindowListener, ActionListener
{
	Frame f = new Frame("Listado Usuarios");
	TextArea txaUsuarios = new TextArea(6, 20);
	Button btnPdf = new Button("Exportar a PDF");
	Conexion c = new Conexion();
		
	ListadoUsuario()
	{
		f.setSize(210, 200);
		f.setLayout(new FlowLayout());
		f.addWindowListener(this);
		c.rellenarListadoUsuario(txaUsuarios);
		f.add(txaUsuarios);
		txaUsuarios.setEditable(false);
		f.add(btnPdf);
		btnPdf.addActionListener(this);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnPdf))
		{
			String dest = "ListadoUsuarios.pdf";

			try
			{
				// Inicializar PDF writer
				PdfWriter writer = new PdfWriter(dest);

				// Inicializar PDF document
				PdfDocument pdf = new PdfDocument(writer);

				// Inicializar documento
				Document document = new Document(pdf);

				// Crear fuente para el encabezado
				PdfFont fontHeader = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

				// Crear fuente para los datos
				PdfFont fontData = PdfFontFactory.createFont(StandardFonts.HELVETICA);

				// Crear tabla y configurar anchos de columna
				Table table = new Table(UnitValue.createPercentArray(new float[]
				{ 1, 1 }));
				table.setWidth(UnitValue.createPercentValue(100));

				// Agregar encabezados a la tabla
				table.addHeaderCell(new Cell().add(new Paragraph("ID").setFont(fontHeader).setBold()));
				table.addHeaderCell(new Cell().add(new Paragraph("Nombre").setFont(fontHeader).setBold()));

				// Agregar datos a la tabla desde el TextArea
				String[] lines = txaUsuarios.getText().split("\n");
				for (String line : lines)
				{
					String[] data = line.split(" - ");
					for (String item : data)
					{
						table.addCell(new Cell().add(new Paragraph(item).setFont(fontData)));
					}
				}

				// Agregar la tabla al documento
				document.add(table);

				// Cerrar documento
				document.close();

				// Abrir el PDF generado
				Desktop.getDesktop().open(new File(dest));
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	public void windowOpened(WindowEvent e){}

	public void windowClosing(WindowEvent e)
	{
		f.setVisible(false);
	}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
