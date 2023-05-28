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
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class ListadoTrabajo implements WindowListener, ActionListener
{
	Frame f = new Frame("Listado Trabajos");
	TextArea txaTrabajos = new TextArea(6, 35);
	Button btnPdf = new Button("Exportar a PDF");
	Conexion c = new Conexion();
	
	ListadoTrabajo()
	{
		f.setSize(300, 200);
		f.setLayout(new FlowLayout());
		f.addWindowListener(this);
		// Rellenar textarea
		c.rellenarListadoTrabajo(txaTrabajos);
		f.add(txaTrabajos);
		txaTrabajos.setEditable(false);
		btnPdf.addActionListener(this);
		f.add(btnPdf);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnPdf))
		{
			String dest = "ListadoTrabajos.pdf";
			
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
				PdfFont fontDatos = PdfFontFactory.createFont(StandardFonts.HELVETICA);

				// Crear tabla y configurar anchos de columna
				Table tabla = new Table(UnitValue.createPercentArray(new float[]
				{ 1, 1, 1, 1}));
				tabla.setWidth(UnitValue.createPercentValue(100));

				// Agregar encabezados a la tabla
				tabla.addHeaderCell(new Cell().add(new Paragraph("ID").setFont(fontHeader).setBold()));
				tabla.addHeaderCell(new Cell().add(new Paragraph("Empleado").setFont(fontHeader).setBold()));
				tabla.addHeaderCell(new Cell().add(new Paragraph("DNI").setFont(fontHeader).setBold()));
				tabla.addHeaderCell(new Cell().add(new Paragraph("Automóvil").setFont(fontHeader).setBold()));

				// Agregar datos a la tabla desde el TextArea
				String[] lineas = txaTrabajos.getText().split("\n");
				for (String linea : lineas)
				{
					String[] datos = linea.split(" - ");
					for (String dato : datos)
					{
						tabla.addCell(new Cell().add(new Paragraph(dato).setFont(fontDatos)));
					}
				}

				// Agregar la tabla al documento
				document.add(tabla);

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

	public void windowClosing(WindowEvent e)
	{
		f.setVisible(false);
	}
	
	public void windowOpened(WindowEvent e){}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
