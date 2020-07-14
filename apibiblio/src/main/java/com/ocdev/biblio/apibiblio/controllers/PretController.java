package com.ocdev.biblio.apibiblio.controllers;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ocdev.biblio.apibiblio.dto.PretDto;
import com.ocdev.biblio.apibiblio.entities.Pret;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.DelayLoanException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.errors.NotEnoughCopiesException;
import com.ocdev.biblio.apibiblio.services.PretService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/*")
@Validated
public class PretController
{
	@Autowired PretService pretService;
	
	@ApiOperation(value = "Création d'un prêt", notes = "Création d'un nouveau prêt")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Le prêt est correctement créé"),
			@ApiResponse(code = 409, message = "Un prêt en cours existe déjà pour cet ouvrage et cet abonné")
			})
	@PutMapping(value = "/prets/abonne/{abonneId}/ouvrage/{ouvrageId}")
	public ResponseEntity<Pret> pret(@ApiParam(value = "ID de l'abonné", required = true, example = "1") @PathVariable @Min(1) final Long abonneId, 
			@ApiParam(value = "ID de l'ouvrage", required = true, example = "1") @PathVariable @Min(1) final Long ouvrageId)
					throws AlreadyExistsException, EntityNotFoundException, NotEnoughCopiesException
	{
		Pret result = pretService.creer(abonneId, ouvrageId);
		return new ResponseEntity<Pret>(result, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Retour d'un prêt", notes = "Retour d'un prêt")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le retour du prêt est enregistré"),
			@ApiResponse(code = 404, message = "Le prêt n'existe pas"),
			@ApiResponse(code = 409, message = "Le prêt est déjà retourné")
			})
	@PutMapping(value ="/prets") // TODO /prets/retour/{id} sans Body
	public ResponseEntity<String> retourPret(@Valid @RequestBody final PretDto pretDto) throws EntityNotFoundException
	{
		pretService.retournerOuvrage(pretDto.getId());
		return new ResponseEntity<String>("Retour OK", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Liste des prêts", notes = "Obtenir la liste des prêts pour un abonné")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des prêts est retourné dans le corps de la réponse")
			})
	@GetMapping(value = "/prets/{abonneId}")
	public ResponseEntity<Collection<Pret>> ListeMesPrets(@ApiParam(value = "ID de l'abonné", required = true, example = "1")
			@PathVariable @Min(1) final Long abonneId) throws EntityNotFoundException
	{
		Collection<Pret> results = pretService.listerSesPrets(abonneId);
		return new ResponseEntity<Collection<Pret>>(results, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Prolongation d'un prêt", notes = "Prolongation d'un prêt pour une nouvelle période")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La prolongation du prêt est enregistrée"),
			@ApiResponse(code = 404, message = "Le prêt n'existe pas"),
			@ApiResponse(code = 409, message = "Le prêt ne peut plus être prolongé")
			})
	@PutMapping(value ="/prets/{pretId}") // TODO /prets/prolonge/{pretId}/abonne/{abonneId}
	public ResponseEntity<Pret> prolongePret(@ApiParam(value = "ID du prêt", required = true, example = "1")
			@PathVariable @Min(1) final Long pretId) throws EntityNotFoundException, DelayLoanException
	{
		Pret result = pretService.prolonger(pretId);
		return new ResponseEntity<Pret>(result, HttpStatus.OK);
	}
}
