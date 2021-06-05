package com.zup.carcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.entities.CarInfo;
import com.zup.carcontrol.entities.DefaultFipeObject;
import com.zup.carcontrol.entities.ModelosObject;
import com.zup.carcontrol.entities.enums.DiaRodizio;
import com.zup.carcontrol.repositories.CarRepository;

import reactor.core.publisher.Flux;

@Service
public class CarService {
	
	String fipeBaseUrl = "https://parallelum.com.br/fipe/api/v1/carros";
	WebClient webClient = WebClient.builder().baseUrl(fipeBaseUrl).build();

	@Autowired
	private CarRepository repository;
	
	@Transactional
	public Car insert(Car car) {
		Car entity = new Car();
		entity = fillEntity(entity, car);
		entity = repository.save(entity);
		return entity;
	}
	
	private Car fillEntity(Car entity, Car dto) {
		entity.setAno(dto.getAno());
		entity.setMarca(dto.getMarca());
		entity.setModelo(dto.getModelo());
		entity.setUser(dto.getUser());
		entity.setDiaRodizio(getDiaRodizio(dto.getAno()));
		entity.setValor(getValorVeiculos(dto));
		
		return entity;
	}
	
	private DiaRodizio getDiaRodizio(String ano) {
		DiaRodizio diaRodizio = null;
		Integer anoInteiro = Integer.valueOf(ano);
		int ultimoDigito = anoInteiro % 10;
		
		switch(ultimoDigito) {
		case 0: 
		case 1:
			diaRodizio = DiaRodizio.SEGUNDA_FEIRA;
			break;
		case 2: 
		case 3:
			diaRodizio = DiaRodizio.TERCA_FEIRA;
			break;
		case 4: 
		case 5:
			diaRodizio = DiaRodizio.QUARTA_FEIRA;
			break;
		case 6: 
		case 7:
			diaRodizio = DiaRodizio.QUINTA_FEIRA;
			break;
		case 8: 
		case 9:
			diaRodizio = DiaRodizio.SEXTA_FEIRA;
			break;
		}
		
		return diaRodizio;
	}
	
	private String getCodigoMarca(String marca) {
		String codigo = "";
		
		Flux<DefaultFipeObject> marcas = webClient.get()
									.uri("/marcas")
									.retrieve()
									.bodyToFlux(DefaultFipeObject.class);
		
		DefaultFipeObject marcaObj = marcas.toStream()
			.filter(m -> m.getNome().equalsIgnoreCase(marca))
			.findFirst()
			.orElse(null);
		
		codigo = marcaObj.getCodigo();
		
		return codigo;
	}
	
	private String getCodigoModelo(String codigoMarca, String nomeModelo) {
		
		ModelosObject objetoModelos = webClient.get()
												.uri("/marcas/{codigoMarca}/modelos", codigoMarca)
												.retrieve()
												.bodyToMono(ModelosObject.class)
												.block();
		
		DefaultFipeObject[] arrayModelos = objetoModelos.getModelos();
		
		for(DefaultFipeObject modelo: arrayModelos) {
			if(modelo.getNome().equalsIgnoreCase(nomeModelo)) {
				return modelo.getCodigo();
			}
		}
		
		return null;
	}
	
	private String getCodigoAno(String codigoMarca, String codigoModelo, String ano) {
		
		Flux<DefaultFipeObject> arrayAnos = webClient.get()
										.uri("/marcas/{codigoMarca}/modelos/{codigoModelo}/anos", 
											codigoMarca, codigoModelo)
										.retrieve()
										.bodyToFlux(DefaultFipeObject.class);
		
		DefaultFipeObject anoObjeto = arrayAnos.toStream()
				 .filter(a -> a.getNome().split(" ")[0].equalsIgnoreCase(ano))
				 .findFirst()
				 .orElse(null);
		
		return anoObjeto.getCodigo();
	}
	
	private String getValorVeiculos(Car dto) {
		String codigoMarca = getCodigoMarca(dto.getMarca());
		String codigoModelo = getCodigoModelo(codigoMarca, dto.getModelo());
		String codigoAno = getCodigoAno(codigoMarca, codigoModelo, dto.getAno());
		
		CarInfo carInfo = webClient.get()
					.uri("/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{codigoAno}", 
							codigoMarca, codigoModelo, codigoAno)
					.retrieve()
					.bodyToMono(CarInfo.class)
					.block();
		
		return carInfo.getValor();
	}
}
