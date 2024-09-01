package org.example.shippingservicespringbootk8s.service;

import org.example.shippingservicespringbootk8s.model.City;

import java.util.List;

public interface CityService {
    List<City> getAllCity();
    City getCityById(int id);
    String createCity(City city);
    String updateCity(City city);
    String deleteCity(int id);
    List<City> getCityWithPaging(int pageNum,int pageSize,String sortBy);
    List<City> getCityByNamePageSize(int pageNum,int pageSize);

}
