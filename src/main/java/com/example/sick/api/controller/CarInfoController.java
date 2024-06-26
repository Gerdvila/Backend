package com.example.sick.api.controller;

import com.example.sick.api.model.response.CarMakeResponse;
import com.example.sick.api.model.response.CarModelInfoResponse;
import com.example.sick.api.model.response.CarModelResponse;
import com.example.sick.api.model.response.CarVariantInfoResponse;
import com.example.sick.service.CarInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/carApi")
public class CarInfoController {

    private final CarInfoService carInfoService;

    @Autowired
    public CarInfoController(CarInfoService carInfoService) {
        this.carInfoService = carInfoService;
    }

    @GetMapping("/makes")
    @ResponseStatus(HttpStatus.OK)
    public CarMakeResponse getCarMakes() throws JsonProcessingException {
        return carInfoService.getCarMakes();
    }

    @GetMapping("/models")
    @ResponseStatus(HttpStatus.OK)
    public CarModelResponse getMakeModels (@RequestParam String make) throws JsonProcessingException {
        return carInfoService.getModelsForMake(make);
    }

    @GetMapping("/model_info")
    @ResponseStatus(HttpStatus.OK)
    public CarModelInfoResponse getModelInfo(@RequestParam int model_id)
            throws TypeMismatchException, JsonProcessingException {
        return carInfoService.getModelInfo(model_id);
    }

    @GetMapping("/variant_info")
    @ResponseStatus(HttpStatus.OK)
    public CarVariantInfoResponse getVariantInfo(@RequestParam int variant_id)
            throws TypeMismatchException, JsonProcessingException {
        return carInfoService.getVariantInfo(variant_id);
    }
}
