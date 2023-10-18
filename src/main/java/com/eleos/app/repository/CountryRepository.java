package com.eleos.app.repository;

import com.eleos.app.db.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> , PagingAndSortingRepository<Country, Integer> {
}
