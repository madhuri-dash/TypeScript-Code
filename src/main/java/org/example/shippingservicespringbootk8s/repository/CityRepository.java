package org.example.shippingservicespringbootk8s.repository;

import org.example.shippingservicespringbootk8s.model.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer> {
    public List<City> findByCityNameLike(String cityName, Pageable pageable);
}
