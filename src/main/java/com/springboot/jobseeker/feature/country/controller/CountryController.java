package com.springboot.jobseeker.feature.country.controller;

import com.springboot.jobseeker.feature.country.dto.CountriesResponse;
import com.springboot.jobseeker.feature.country.dto.CreateCountryRequest;
import com.springboot.jobseeker.feature.country.dto.UpdateCountryRequest;
import com.springboot.jobseeker.feature.country.service.CountryService;
import com.springboot.jobseeker.shared.data.dto.ApiResponse;
import com.springboot.jobseeker.shared.data.dto.ErrorResponse;
import com.springboot.jobseeker.shared.data.repository.jpa.CountryJpaRepository;
import com.springboot.jobseeker.shared.exception.BadRequestException;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/countries")
@RestController
@AllArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<?> createCountry(
            @RequestBody CreateCountryRequest request
    ){
        ApiResponse apiResponse;
        try {
            apiResponse = ApiResponse.builder()
                    .status(201)
                    .data(
                            countryService.createCountry(request)
                    )
                    .message("Country Created Successfully.")
                    .build();
        }catch (BadRequestException e){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()
                    )
            );
        }
        return ResponseEntity.ok().body(
                apiResponse
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(
            @PathVariable Long id,
            @RequestBody UpdateCountryRequest request
    ){
        ApiResponse apiResponse;
        try{apiResponse = ApiResponse.builder()
                .status(201)
                .data(
                        countryService.updateCountry(id, request)
                )
                .message("Country Updated Successfully.")
                .build();
        }catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()
                    )
            );
        }
        return ResponseEntity.ok().body(
                apiResponse
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> retrieveAllCountries(){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .status(200)
                        .data(countryService.retrieveAll())
                        .message("Countries Retrieved Successfully.")
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveOneCountry(@PathVariable Long id){
        ApiResponse apiResponse;
        try {
            CountriesResponse countriesResponse = countryService.retrieveOne(id);
            apiResponse = ApiResponse.builder()
                    .status(200)
                    .data(countriesResponse)
                    .message("Country Retrieved Successfully.")
                    .build();
        }  catch(NotFoundException e2) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND.value(),
                            e2.getMessage()
                    )
            );
        }catch (BadRequestException e){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()
                    )
            );
        }
        return ResponseEntity.ok().body(
                apiResponse
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id){
        try {
            countryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.ok()
                    .body(
                            ApiResponse.builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .data(false)
                                    .message(e.getMessage())
                                    .build()
                    );
        } catch (BadRequestException e){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getMessage()
                    )
            );
        }
    }
}
