package br.atm.location.service.client.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import br.atm.location.controller.dto.AtmDto;
import br.atm.location.service.client.AtmClientService;

/**
 * This class make the communication with the ING location services to get all
 * atm locatio in nederlands
 *
 * @author Wanessa Nascimento
 */
@Service
public class AtmClientServiceImp implements AtmClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AtmClientServiceImp.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${url.api.atm.ing.localtion.nl}")
	private String urlAtmIngLocation;

	@Override
	public List<AtmDto> getAtmLocation() {
		LOGGER.info("Calling web service getAtmLocation");
		return getObject(restTemplate.getForObject(urlAtmIngLocation, String.class));
	}

	private List<AtmDto> getObject(String atmsReturn) {
		Gson gson = new GsonBuilder().create();
		try {
			return gson.fromJson(atmsReturn, new TypeToken<List<AtmDto>>() {
			}.getType());
		} catch (JsonSyntaxException e) {
			String returnRplaced = atmsReturn.replace(atmsReturn.split("\\[")[0], "");
			return gson.fromJson(returnRplaced, new TypeToken<List<AtmDto>>() {
			}.getType());
		}
	}
}
