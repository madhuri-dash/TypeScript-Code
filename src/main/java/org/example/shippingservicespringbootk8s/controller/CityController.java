package org.example.shippingservicespringbootk8s.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.shippingservicespringbootk8s.customException.CityAlreadyExistsException;
import org.example.shippingservicespringbootk8s.customException.ErrorResponse;
import org.example.shippingservicespringbootk8s.model.City;
import org.example.shippingservicespringbootk8s.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Shipping/India")
@Tag(name = "SHIPPING SERVICES IN CITIES OF INDIA", description = "SHIPPING SERVICES APIs")
public class CityController {

    @Autowired
    CityService cityService;

    @Operation(summary = "Get All City Details of India",
            description = "GET method of CITY details from India")

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = City.class)) }),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content) })
    @GetMapping("/CityDetails")
    private List<City> getAllCity() {
        return cityService.getAllCity();
    }

    //creating a get mapping that retrieves the detail of a specific city
    @Operation(summary = "Get City By CityCode of India",
            description = "GET method of CityCode from all over India")

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = City.class)) }),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content) })
   @GetMapping("/city/{cityCode}")
    private City getCityById(@Parameter(
           description = "CityCode of City of India to be retrieved",
           required = true)
           @PathVariable("cityCode") int cityCode) {
        return cityService.getCityById(cityCode);
    }

    //creating a delete mapping that retrieves the detail of a specific city
    @Operation(summary = "Delete City By CityCode of India",
            description = "DELETE method of CityCode from India")

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content) })
    //creating a delete mapping that deletes a city in the database
    @DeleteMapping("/RemoveCity/{cityCode}")
    private String deleteCity(@PathVariable("cityCode") int cityCode)   {
        return cityService.deleteCity(cityCode);
    }

    //creating post mapping that insert the city detail in the database
        @Operation(summary = "Add City of India",
            description = "Add methods of CITIES")

        @ApiResponses({
                @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }),
                @ApiResponse(responseCode = "404", description = "City not found",
                        content = @Content) })
    @PostMapping("/AddCity")
    private String saveCity(@RequestBody City city)   {
         return cityService.createCity(city);
    }

    //creating put mapping that put that update city detail in the database
    @Operation(summary = "Update City of India",
            description = "PUT method of CITIES")

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content) })
    @PutMapping("/UpdateCity")
    private String updateCity(@RequestBody City city)   {
        return cityService.updateCity(city);
    }

    @ExceptionHandler(value = CityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCustomerAlreadyExistsException(CityAlreadyExistsException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    //Displaying Cities using pagination and sorting been done by cityName
    @Operation(summary = "Get City of India showing in pagination and sorting by cityName",
            description = "GET method of CITIES from all over India using pagination")

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content) })
    @GetMapping("/cities")
    public List<City> getCityWithPaging(@RequestParam(defaultValue = "0") int pageNum,
                                   @RequestParam(defaultValue = "2") int pageSize,
                                        @RequestParam(defaultValue = "cityName") String sortBy) {
        return cityService.getCityWithPaging(pageNum,pageSize,sortBy);
    }

    //Displaying Cities using pagination & filtered by CityName
    @Operation(summary = "Get City of India showing by pagination and filtered by cityName starting with Ba",
            description = "GET method of CITIES from India using pagination and filtered by cityName")

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content) })
    @GetMapping("/citiesName")
    public List<City> getCityByNamePageSize(@RequestParam(defaultValue = "0") int pageNum,
                                        @RequestParam(defaultValue = "2") int pageSize) {
        return cityService.getCityByNamePageSize(pageNum,pageSize);
    }

    //Deployment check in local using Docker in Local powerShell
    @Operation(summary = "Deployment using Docker",
            description = "GET method Testing check")

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Testing Unsuccessful",
                    content = @Content) })
    @GetMapping("/message")
    public String getCityByNamePageSize(){
    return "Shipping Service deployed successfully using Docker!!!";
    }

    
}
