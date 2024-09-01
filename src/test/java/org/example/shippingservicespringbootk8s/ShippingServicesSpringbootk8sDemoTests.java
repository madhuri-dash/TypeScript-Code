package org.example.shippingservicespringbootk8s;

import org.example.shippingservicespringbootk8s.model.City;
import org.example.shippingservicespringbootk8s.repository.CityRepository;
import org.example.shippingservicespringbootk8s.serviceImpl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ShippingServicesSpringbootk8sDemoTests {

    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    private City city;

    @BeforeEach
    public void setup() {
        city = City.builder()
                .cityCode(1)
                .cityName("OLD-BERHAMPUR")
                .cityPinCode(760009)
                .build();
    }

    // JUnit test for AddCity method
    @DisplayName("JUnit test for CreateCity method")
    @Test
    public void givenCityObject_whenCreateCity_thenReturnCityObject() {
        lenient().when(cityRepository.findById(1)).thenReturn(Optional.empty());
        lenient().when(cityRepository.save(city)).thenReturn(city);
        String savedCity = cityService.createCity(city);
        System.out.println(savedCity);
        assertEquals(savedCity, "City added successfully");
    }

    //JUnit test for getAllCity method
    @DisplayName("JUnit test for getAllCities method")
    @Test
    public void givenCitysList_whenGetAllCity_thenReturnCityList() {
        // given - precondition or setup
        City city1 = City.builder()
                .cityCode(2)
                .cityName("Patia")
                .cityPinCode(751024)
                .build();

        when(cityRepository.findAll()).thenReturn(List.of(city, city1));
        // when -  action or the behaviour that we are going test
        List<City> cityList = cityService.getAllCity();
        // then - verify the output
        assertThat(cityList).isNotNull();
        assertThat(cityList.size()).isEqualTo(2);
    }

    //JUnit test for getCityById method
    @DisplayName("JUnit test for getCityById method")
    @Test
    public void givenCityId_whenGetCityById_thenReturnCityObject() {
        when(cityRepository.findById(1)).thenReturn(Optional.of(city));
        City savedCity = cityService.getCityById(city.getCityCode());
        assertThat(savedCity).isNotNull();

    }

    //JUnit test for updateCity method
    @DisplayName("JUnit test for updateCity method")
    @Test
    public void givenCityObject_whenUpdateCity_thenReturnUpdatedCity() {
        lenient().when(cityRepository.findById(1)).thenReturn(Optional.ofNullable(city));
        lenient().when(cityRepository.save(city)).thenReturn(city);
        city.setCityName("BankColny");
        city.setCityPinCode(760001);
        String updatedCity = cityService.updateCity(city);
        assertThat(updatedCity).isEqualTo("City updated successfully");
    }

    //JUnit test for deleteCity method
    @DisplayName("JUnit test for deleteCity method")
    @Test
    public void givenCityId_whenDeleteCity_thenNothing() {
        int cityId = 1;
        lenient().when(cityRepository.findById(cityId)).thenReturn(Optional.ofNullable(city));
        lenient().when(cityRepository.save(city)).thenReturn(city);
        String deletedCity = cityService.deleteCity(cityId);
        assertThat(deletedCity).isEqualTo("City deleted successfully");
    }
}
