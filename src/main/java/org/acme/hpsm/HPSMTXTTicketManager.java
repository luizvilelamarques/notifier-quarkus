package org.acme.hpsm;

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

@Named("hpsmtxt")
@Singleton
public class HPSMTXTTicketManager implements Service<Bean> {

	private final String FILE = "C:\\Users\\Luiz\\Documents\\tickets.txt";
	
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

	private Function<String, Ticket> transform = new Function<String, Ticket>() {

		public Ticket apply(String line) {

			Ticket ticket = new Ticket();

			List<String> pieces = Splitter.on("|").trimResults().omitEmptyStrings().splitToList(line);

			ticket.setId(pieces.get(0));
			ticket.setDescricao(pieces.get(1));
			ticket.setInicio(pieces.get(2));
			ticket.setFim(pieces.get(3));
			ticket.setFila(pieces.get(4));
			ticket.setNotifier(controleSM.getConfig().getCanalNotifier(ticket.getFila()));

			return ticket;
		}
	};
	
	class Ticket extends Bean{

		private String id;
		private String inicio;
		private String fim;
		private String descricao;
		private String fila;

		@Override
		public String toString() {
			return "Ticket: " + id + " Descrição: " + descricao + " Início: " + inicio + " Fim: " + fim;
		}

		public String getFila() {
			return fila;
		}

		public void setFila(String fila) {
			this.fila = fila;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getInicio() {
			return inicio;
		}

		public void setInicio(String inicio) {
			this.inicio = inicio;
		}

		public String getFim() {
			return fim;
		}

		public void setFim(String fim) {
			this.fim = fim;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

	}

}
