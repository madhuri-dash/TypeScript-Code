package org.example.shippingservicespringbootk8s.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.example.shippingservicespringbootk8s.customException.CityAlreadyExistsException;
import org.example.shippingservicespringbootk8s.customException.NoCityFoundException;
import org.example.shippingservicespringbootk8s.model.City;
import org.example.shippingservicespringbootk8s.repository.CityRepository;
import org.example.shippingservicespringbootk8s.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Value("${cityNamePattern}")
    private String cityNameLike;

    //getting all city details
    public List<City> getAllCity() {
        List<City> cities = new ArrayList<City>();
        cityRepository.findAll().forEach(city -> cities.add(city));
        return cities;
    }

    //getting a specific city
    public City getCityById(int cityCode){
        return cityRepository.findById(cityCode).orElseThrow(
                () -> new NoCityFoundException("No city found with code " + cityCode)
        );
    }

    //adding a specific city
    public String createCity(City city) {
        City existingCity
                = cityRepository.findById(city.getCityCode())
                .orElse(null);
        if (existingCity == null) {
            cityRepository.save(city);
            return "City added successfully";
        }
        else
            throw new CityAlreadyExistsException(
                    "City already exists!!");
    }

    //updating a specific city
    public String updateCity(City city) {
        City existingCity
                = cityRepository.findById(city.getCityCode())
                .orElse(null);

        if(existingCity!= null) {
            existingCity.setCityCode(city.getCityCode());
            existingCity.setCityName(city.getCityName());
            existingCity.setCityPinCode(city.getCityPinCode());
            cityRepository.save(existingCity);
            return "City updated successfully";
        }
        else{
            throw new NoCityFoundException(
                    "No City Found!!");
        }
    }

    //deleting a specific city
    public String deleteCity(int cityCode) {
        City existingCity
                = cityRepository.findById(cityCode)
                .orElse(null);

        if(existingCity!= null) {
            cityRepository.deleteById(cityCode);
            return "City deleted successfully";
        }
        else{
            throw new NoCityFoundException(
                    "No City Found!!");
        }
    }

    //showing cities using pagination and sorting by cityName
    public List<City> getCityWithPaging(int pageNum, int pageSize, String sortBy) {
        Pageable pg = PageRequest.of(pageNum,pageSize,Sort.by(sortBy).ascending());
        Page<City> pagedResult = cityRepository.findAll(pg);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<City>();
        }
    }

    //showing cities using pagination and filteredBy cityName
    public List<City> getCityByNamePageSize(int pageNum, int pageSize) {
        Pageable pg1 = PageRequest.of(pageNum,pageSize);

        List<City> pageDetails = cityRepository.findByCityNameLike(cityNameLike,pg1);
            return pageDetails;

    }
}
