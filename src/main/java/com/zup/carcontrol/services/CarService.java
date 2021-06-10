package com.zup.carcontrol.services;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.zup.carcontrol.classes.CarInfo;
import com.zup.carcontrol.classes.DefaultFipeObject;
import com.zup.carcontrol.classes.ModelosObject;
import com.zup.carcontrol.dto.CarDto;
import com.zup.carcontrol.dto.CarInsertDto;
import com.zup.carcontrol.entities.Car;
import com.zup.carcontrol.entities.enums.DiaRodizio;
import com.zup.carcontrol.repositories.CarRepository;
import com.zup.carcontrol.services.exceptions.CodigoNotFoundException;

import reactor.core.publisher.Flux;

@Service
public class CarService {
	
	String fipeBaseUrl = "https://parallelum.com.br/fipe/api/v1/carros";
	WebClient webClient = WebClient.builder().baseUrl(fipeBaseUrl).build();

	@Autowired
	private CarRepository repository;
	
	@Transactional
	public CarDto insert(CarInsertDto dto) {
		Car entity = new Car();
		entity = fillEntity(entity, dto);
		entity = repository.save(entity);
		
		CarDto responseDto = new CarDto(entity);
		return responseDto;
	}
	
	@Transactional
	private Car fillEntity(Car entity, CarInsertDto dto) {
		entity.setAno(dto.getAno());
		entity.setMarca(dto.getMarca());
		entity.setModelo(dto.getModelo());
		entity.setUser(dto.getUser());
		entity.setValor(getValorVeiculo(dto));
		entity.setDiaRodizio(getDiaRodizio(dto.getAno()));
		entity.setIsDiaRodizio(isDiaRodizio(getDiaRodizio(dto.getAno())));
		
		return entity;
	}
	
	// Erro está aqui, porque o ano passado é nulo.
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
	
	public boolean isDiaRodizio(DiaRodizio diaRodizio) {
		DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
			
		// Se o dia da semana de hoje é o mesmo dia da semana do rodízio
		if(dayOfWeek.getValue() == (diaRodizio.ordinal() + 1)) { 
			return true;
		}
			
		return false;
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
			.orElseThrow(() -> new CodigoNotFoundException("Marca não encontrada"));
		
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
		
		throw new CodigoNotFoundException("Modelo não encontrado");
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
				 .orElseThrow(() -> new CodigoNotFoundException("Ano não encontrado"));
		
		return anoObjeto.getCodigo();
	}
	
	private String getValorVeiculo(CarInsertDto dto) {
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
