package org.acme.core;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import org.acme.core.bean.Config;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * converte arquivo de config do json para Entity - POJO
 * @author Luiz
 *
 */
@Singleton
public class JsonConvert {

	Config config;

	public JsonConvert() {
		this.config = build();
	}

	public Config getConfig() {
		return config;
	}

	private Config build() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ClassLoader classLoader = JsonConvert.class.getClassLoader();
			File file = new File(classLoader.getResource("config.json").getFile());
			Config sm = mapper.readValue(file, Config.class);
			return sm;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
