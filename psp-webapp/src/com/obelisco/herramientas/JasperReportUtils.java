package com.obelisco.herramientas;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.obelisco.exception.ObeliscoException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

public class JasperReportUtils {

	public static final String PDF = "pdf";
	public static final String PDF_EXT = ".pdf";
	public static final String PDF_MIME_TYPE = "application/pdf";
	
	public static final String XML = "xml";
	public static final String XML_EXT = ".xml";
	public static final String XML_MIME_TYPE = "text/xml";
	
	public static final String HTML = "html";
	public static final String HTML_EXT = ".html";	
	public static final String HTML_MIME_TYPE = "text/html";
	
	public static final String RTF = "rtf";
	public static final String RTF_EXT = ".rtf";	
	public static final String RTF_MIME_TYPE = "application/rtf";
	
	public static final String XLS = "xls";
	public static final String XLS_EXT = ".xls";	
	public static final String XLS_MIME_TYPE = "application/vnd.ms-excel";
	
	public static final String JXL = "jxl";
	public static final String JXL_EXT = ".jxl";	
	public static final String JXL_MIME_TYPE = "application/vnd.ms-excel";
	
	public static final String CSV = "csv";
	public static final String CSV_EXT = ".csv";	
	public static final String CSV_MIME_TYPE = "text/csv";
	
	public static final String ODT = "odt";
	public static final String ODT_EXT = ".odt";	
	public static final String ODT_MIME_TYPE = "application/vnd.oasis.opendocument.text";

	public static byte[] generar(String path, Map parametros,
			JRDataSource source, String formato) throws JRException, IOException {
		byte[] resultado = {};
		
			InputStream is = new FileInputStream(path);
			
//			InputStream is = new ByteArrayInputStream();
			

			JasperPrint jasperPrint = JasperFillManager.fillReport(is,
					parametros, source != null ? source
							: new JREmptyDataSource());
			
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			JRExporter exporter = null;
			// export one type of report
			if (PDF.equals(formato)) {
				
				exporter = new JRPdfExporter();
				
			} else if (XML.equals(formato)) {
				
				exporter = new JRXmlExporter();
				
			} else if (HTML.equals(formato)) {
				
				exporter = new JRHtmlExporter();

			} else if (RTF.equals(formato)) {
				
				exporter = new JRRtfExporter();

			} else if (XLS.equals(formato)) {

				exporter = new JExcelApiExporter();
				//exporter = new JRXlsExporter();
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,	Boolean.TRUE);

			} else if (JXL.equals(formato)) {

				exporter = new JExcelApiExporter();
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,	Boolean.TRUE);
				
			} else if (CSV.equals(formato)) {

				exporter = new JRCsvExporter();
				
			} else if (ODT.equals(formato)) {

				exporter = new JROdtExporter();

			} else {
				throw new ObeliscoException("Type: " + formato
						+ " is not supported in JasperReports.");
			}

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
			exporter.exportReport();
			
			resultado = arrayOutputStream.toByteArray();
			arrayOutputStream.close();
			

		

		return resultado;
	}

	public static byte[] generar(String path, Map parametros, Collection data,
			String formato) throws JRException, IOException {
		JRDataSource source = null;

		if (data != null && data.size() > 0) {
			source = new JRBeanCollectionDataSource(data);
		}

		return generar(path, parametros, source, formato);
	}

	public static byte[] generar(String path, Map parametros, Object objeto,String formato) throws JRException, IOException {

		ArrayList data = new ArrayList();
		data.add(objeto);

		return generar(path, parametros, data,
				formato);
	}

}