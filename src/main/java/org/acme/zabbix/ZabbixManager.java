package org.acme.zabbix;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.core.CentralConfiguration;
import org.acme.core.Service;
import org.acme.core.bean.Bean;

import com.google.common.base.Splitter;

@Named("zabbix")
@Singleton
public class ZabbixManager implements Service<Bean> {

	
	private final String FILE = "C:\\Users\\Luiz\\Documents\\zabbix.txt";
	
	@Inject
	CentralConfiguration controleSM;
	
	@Override
	public List<Bean> getBeans() {
		try {
			return Files.lines(Paths.get(FILE)).map(transform).collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				clearTheFile();
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void clearTheFile() throws Throwable {
		FileWriter fwOb = new FileWriter(FILE, false);
		PrintWriter pwOb = new PrintWriter(fwOb, false);
		pwOb.flush();
		pwOb.close();
		fwOb.close();
	}

	private Function<String, Zabbix> transform = new Function<String, Zabbix>() {

		public Zabbix apply(String line) {

			Zabbix registro = new Zabbix();

			List<String> pieces = Splitter.on("|").trimResults().omitEmptyStrings().splitToList(line);

			registro.setId(pieces.get(0));
			registro.setSistema(pieces.get(1));
			registro.setProblema(pieces.get(2));
			registro.setFila(pieces.get(3));
			registro.setNotifier(controleSM.getConfig().getCanalNotifier(registro.getFila()));

			return registro;
		}
	};
	
	class Zabbix extends Bean{

		private String sistema;
		private String problema;
		private String fila;

		@Override
		public String toString() {
			return "Sistema: " + sistema + " Problema: " + problema;
		}
		
		public String getSistema() {
			return sistema;
		}

		public void setSistema(String sistema) {
			this.sistema = sistema;
		}

		public String getProblema() {
			return problema;
		}

		public void setProblema(String problema) {
			this.problema = problema;
		}

		public String getFila() {
			return fila;
		}

		public void setFila(String fila) {
			this.fila = fila;
		}
	}

}