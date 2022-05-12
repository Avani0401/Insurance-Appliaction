package com.insurance.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.entity.Dependents;
import com.insurance.helper.ErrorResponse;
import com.insurance.service.DependentsService;

// TODO: Auto-generated Javadoc
/**
 * The Class DependentsController.
 */
@RestController

public class DependentsController {
	
	/** The dependents service. */
	@Autowired
	private DependentsService dependentsService;
		

	/**
	 * Adds the dependents.
	 *
	 * @param policyholderId the policyholder id
	 * @param dependents the dependents
	 * @return the response entity
	 */
	@PostMapping("/{policyholderId}/addDependents")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<List<Dependents>> addDependents(@PathVariable ("policyholderId") int policyholderId,@RequestBody List<Dependents> dependents) {
		try {
			List<Dependents> dependent = this.dependentsService.addDependents(policyholderId,dependents);
			return ResponseEntity.of(Optional.of(dependent));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}



	/**
	 * Gets the dependents.
	 *
	 * @param policyholderId the policyholder id
	 * @return the dependents
	 */
	@GetMapping("/{policyholderId}/getDependents")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<?> getDependents(@PathVariable ("policyholderId") int policyholderId) {
		List<Dependents> dependent = this.dependentsService.getDependents(policyholderId);
		if(dependent.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setMessage("Record not found");
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dependent, HttpStatus.OK); 
	}
}

