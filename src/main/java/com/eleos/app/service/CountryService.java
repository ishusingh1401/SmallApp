package com.eleos.app.service;

import com.eleos.app.db.Country;
import com.eleos.app.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country createNewCountry(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> listOfCountries(String range) {
        int startRange = 0;
        int endRange = 5;
        Sort sort = Sort.by(Sort.Order.asc("countryName"));
        Pageable pageable = null;
        String[] rangeArray = range.split("-");
        if (!range.isEmpty()) {
            startRange = Integer.parseInt(rangeArray[0]);
            endRange = Integer.parseInt(rangeArray[1]);
        }
        if (startRange < endRange) {
            pageable = PageRequest.of((endRange / (endRange - startRange)) - 1, endRange - startRange, sort);
        }
        assert pageable != null;
        Page<Country> countryPageList = countryRepository.findAll(pageable);
        return countryPageList.getContent();
    }

    public Country getCountryById(Integer countryID) {
        Optional<Country> optionalCountry = countryRepository.findById(countryID);
        return optionalCountry.orElse(null);
    }

    public Country updateCountry(Integer countryID, Country updatedCountry) {
        Optional<Country> existingCountry = countryRepository.findById(countryID);
        if (existingCountry.isPresent()) {
            Country country = existingCountry.get();
            country.setCountryName(updatedCountry.getCountryName());
            return countryRepository.save(country);
        } else {
            return null;
        }
    }

    public Boolean deleteCountry(Integer countryID) {
        Optional<Country> optionalCountry = countryRepository.findById(countryID);
        if (optionalCountry.isPresent()) {
            countryRepository.delete(optionalCountry.get());
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
