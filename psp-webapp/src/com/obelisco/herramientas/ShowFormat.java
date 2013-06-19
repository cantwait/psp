package com.obelisco.herramientas;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.zul.Filedownload;

public class ShowFormat {

	public static void execute(String path, Object dato, String formato,
			String extension, String mimeType, String nombre) {

		if (path != null && dato != null) {
			byte[] content;
			try {

				content = JasperReportUtils.generar(path, new HashMap(), dato,
						formato);

				Filedownload.save(content, mimeType, nombre + extension);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void execute(String path, Collection datos, String formato,
			String extension, String mimeType, String nombre) {

		if (path != null && datos != null && datos.size() > 0) {
			byte[] content;
			try {

				content = JasperReportUtils.generar(path, new HashMap(), datos,
						formato);

				Filedownload.save(content, mimeType, nombre + extension);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void execute(String path, Collection datos, String nombre) {
		execute(path, datos, JasperReportUtils.PDF, JasperReportUtils.PDF_EXT,
				JasperReportUtils.PDF_MIME_TYPE, nombre);
	}

	public static void execute(String path, Object dato, String nombre) {
		execute(path, dato, JasperReportUtils.PDF, JasperReportUtils.PDF_EXT,
				JasperReportUtils.PDF_MIME_TYPE, nombre);
	}

	public static void executeExcel(String path, Collection datos, String nombre) {
		execute(path, datos, JasperReportUtils.XLS, JasperReportUtils.XLS_EXT,
				JasperReportUtils.XLS_MIME_TYPE, nombre);
	}

	public static void executeExcel(String path, Object dato, String nombre) {
		execute(path, dato, JasperReportUtils.XLS, JasperReportUtils.XLS_EXT,
				JasperReportUtils.XLS_MIME_TYPE, nombre);
	}

}