package com.obelisco.modelo.data.seguridad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

//import org.apache.jackrabbit.extractor.TextExtractor;


import com.obelisco.modelo.data.DataGenerica; //import javax.activation.DataHandler;
//import javax.activation.*;
//import javax.mail.internet.MimePartDataSource;
import java.io.ByteArrayInputStream; //import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.io.FileInputStream;

public class Archivo extends DataGenerica {

	private byte[] contenido = new byte[] {};
	private String nombre = "";
	private String tipo = "";
	private Long tamano;

	public Archivo() {
	}

	public Archivo(File archivo) {
		try {
			byte[] bytes = Archivo.toByteArray(archivo);
			this.contenido = bytes;
			this.nombre = archivo.getName();
			this.tipo = Archivo.determinarTipo(archivo);
		} catch (Exception ex) {
			// Seguramente un Error de Entrada y Salida
		}
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.setTamano(new Long(contenido.length));
		this.contenido = contenido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/** Don't invoke this. Used by Hibernate only. */
	// public void setBlob(Blob blob) {
	// this.contenido = this.toByteArray(blob);
	// }
	//
	// public Blob getBlob() {
	// return Hibernate.createBlob(this.contenido);
	// }

	public static String determinarTipo(byte[] bytes) {
		String t = "";

		/*
		 * try { ByteArrayInputStream inputStream = new
		 * ByteArrayInputStream(bytes); MimeBodyPart bodyPart = new
		 * MimeBodyPart(inputStream); DataSource source = new
		 * MimePartDataSource(bodyPart); DataHandler dh = new
		 * DataHandler(source); t = dh.getContentType(); } catch (Exception ex)
		 * { // Capturar el Tipo de la Foto es importante pero no necesaria, el
		 * manejo de la exception // no tiene trascendencia }
		 */

		return t;
	}

	public static String determinarTipo(File archivo) {
		String t = "";

		/*
		 * try { DataSource source = new FileDataSource(archivo); DataHandler dh
		 * = new DataHandler(source); t = dh.getContentType(); } catch
		 * (Exception ex) { // Capturar el Tipo de la Foto es importante pero no
		 * necesaria, el manejo de la exception // no tiene trascendencia }
		 */

		return t;
	}

	// public static byte[] toByteArray(Blob fromBlob) {
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// try {
	// return toByteArrayImpl(fromBlob, baos);
	// }
	// catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	// catch (IOException e) {
	// throw new RuntimeException(e);
	// }
	// finally {
	// if (baos != null) {
	// try {
	// baos.close();
	// }
	// catch (IOException ex) {}
	// }
	// }
	// }
	//
	// private static byte[] toByteArrayImpl(Blob fromBlob,
	// ByteArrayOutputStream baos) throws
	// SQLException, IOException {
	// byte[] buf = new byte[4000];
	// InputStream is = fromBlob.getBinaryStream();
	// try {
	// for (; ; ) {
	// int dataSize = is.read(buf);
	// if (dataSize == -1) {
	// break;
	// }
	// baos.write(buf, 0, dataSize);
	// }
	// }
	// finally {
	// if (is != null) {
	// try {
	// is.close();
	// }
	// catch (IOException
	// ex) {}
	// }
	// }
	// return baos.toByteArray();
	// }

	public InputStream getInputStreamContenido() {
		ByteArrayInputStream input;

		input = new ByteArrayInputStream(contenido);

		return input;
	}

	public static byte[] toByteArray(InputStream is) throws SQLException,
			IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buf = new byte[4000];
		try {
			for (;;) {
				int dataSize = is.read(buf);
				if (dataSize == -1) {
					break;
				}
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}
		return baos.toByteArray();
	}

	public static byte[] toByteArray(File archivo) throws IOException {

		FileInputStream fileInput = new FileInputStream(archivo);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buf = new byte[4000];
		InputStream is = fileInput;
		try {
			for (;;) {
				int dataSize = is.read(buf);
				if (dataSize == -1) {
					break;
				}
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}

		return baos.toByteArray();
	}

	public Long getTamano() {
		return tamano;
	}

	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}

}
