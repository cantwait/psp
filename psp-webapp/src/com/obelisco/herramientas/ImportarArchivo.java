package com.obelisco.herramientas;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.zkoss.zkplus.spring.SpringUtil;

import com.csvreader.CsvReader;
import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.model.UnidadMedida;
import com.pdvsa.psp.model.Item.DATA_TYPE;
import com.pdvsa.psp.model.Item.ITEM_TYPE;
import com.pdvsa.psp.service.IUnidadMedidaService;

public class ImportarArchivo {

	Resource rutaFormato;

	public String getUrl() {
		String salida = null;
		try {
			if (rutaFormato.getFile().exists()) {
				salida = rutaFormato.getFile().getAbsolutePath();
			} else {
				salida = "";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salida;
	}

	/**
	 * ESTA ME ESTA DANDO ERRORES...
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	// public List<OpcInfoRegister> getOpcList(byte[] data) throws Exception {
	// final String TEST_STRING =
	// "hostModbusSlave,tagOpc,tagName,stationId,reference,portModbusSlave,regType\n"
	// +

	// "localhost,T3,tag1,R8\n" +
	//
	// "localhost,T4,tag2,R3\n" +
	// "rcadenas,T5,tag3,R8";
	//		
	// ColumnPositionMappingStrategy<OpcInfoRegister> a = new
	// ColumnPositionMappingStrategy<OpcInfoRegister>();
	// a.setType(OpcInfoRegister.class);
	// String[] cols = new
	// String[]{"hostModbusSlave","tagOpc","tagName","regType"};
	// a.setColumnMapping(cols);
	//		
	//		
	//		
	// List<OpcInfoRegister> lista = new ArrayList<OpcInfoRegister>();
	// InputStream input = new ByteArrayInputStream(data);
	// InputStreamReader in = new InputStreamReader(input);
	// HeaderColumnNameMappingStrategy<OpcInfoRegister> strat = new
	// HeaderColumnNameMappingStrategy<OpcInfoRegister>();
	// strat.setType(OpcInfoRegister.class);
	// CsvToBean<OpcInfoRegister> csv = new CsvToBean<OpcInfoRegister>();
	// // System.out.println(TEST_STRING);
	// // System.out.println(convertStreamToString(input));
	// // CSVReader reader = new CSVReader(new StringReader(new String(data)),
	// // c);
	// CSVReader reader = new CSVReader(new StringReader(TEST_STRING), ',');
	// // CSVReader reader = new CSVReader(in, ';', ' ',' ');
	// // System.out.println(new String(data));
	// final String contenido = convertStreamToString(input);
	// System.out.println("\n" + contenido);
	// lista = csv.parse(a, new StringReader(TEST_STRING));
	// System.out.println(lista.size());
	// return lista;
	// }

	public List<Item> getListaVariables(byte[] data) throws Exception {
		CsvReader reader = null;
		IUnidadMedidaService servicioUnidad = (IUnidadMedidaService) SpringUtil
				.getBean("unidadMedidaService");
		UnidadMedida u = new UnidadMedida();
		try { 
			List<Item> lista = new ArrayList<Item>();
			InputStream input = new ByteArrayInputStream(data);
			final String contenido = convertStreamToString(input);
			reader = new CsvReader(new StringReader(contenido), ',');
			reader.getHeaders();
			int contador = 1;
			while (reader.readRecord()) {
				System.out.println("Filas: " + contador);

				if (contador > 1) {
					System.out.println("nombre: " + reader.get(0));
					System.out.println("descripcion: " + reader.get(1));
					System.out.println("hda: " + reader.get(2));
					System.out.println("itemOpc: " + reader.get(3));
					System.out.println("tipoDato: " + reader.get(4));
					System.out.println("tipoItem: " + reader.get(5));
					System.out.println("activo: " + reader.get(6));
					System.out.println("Unidad: " + reader.get(7));
					System.out.println("\n");
					
					u = servicioUnidad.getUnidadMedidaByNombre(reader.get(7));
					
					lista.add(new Item(Boolean.valueOf(reader.get(6)), reader
							.get(1), reader.get(0), Boolean.valueOf(reader
							.get(2)), reader.get(3), DATA_TYPE.valueOf(reader
							.get(4)), ITEM_TYPE.valueOf(reader.get(5)), new UnidadMedida(u.getId(),u.getNombre(),u.getDescripcion())));
				}
				contador++;
			}
			return lista;
		} catch (Exception e) {
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	protected static String convertStreamToString(InputStream is)
			throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		is.close();
		return sb.toString();
	}

	public String extensionDocumento(String nombreArchivo) {
		String ext = "";

		if (StringUtils.isNotBlank(nombreArchivo)) {
			ext = StringUtils.substring(nombreArchivo, StringUtils.indexOf(
					nombreArchivo, '.'));
		}

		return ext;
	}

	public Resource getRutaFormato() {
		return rutaFormato;
	}

	public void setRutaFormato(Resource rutaFormato) {
		this.rutaFormato = rutaFormato;
	}

}
