package com.eleos.app.controller;

import com.eleos.app.db.Country;
import com.eleos.app.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {

    @Autowired
    CountryService countryService;


    /**
     * This method is used to create a country
     */
    @PostMapping()
    ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country createdCountry = countryService.createNewCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
    }

    /**
     * This method is used to return List of countries
     *
     * @param range
     * @return country
     */

    @GetMapping
    public ResponseEntity<List<Country>> listCountries(@PathParam(value = "range") String range) {
        List<Country> countries = countryService.listOfCountries(null == range ? "0-5" : range);
        return ResponseEntity.ok(countries);
    }

    /**
     * This method is used  to retrieve a country by its unique ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable("id") Integer countryId) {
        Country country = countryService.getCountryById(countryId);
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * This method is used to update a country's name.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable("id") Integer countryId, @RequestBody Country updatedCountry) {
        Country country = countryService.updateCountry(countryId, updatedCountry);
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method is used to delete a country.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") Integer countryId) {
        Boolean deleted = countryService.deleteCountry(countryId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}