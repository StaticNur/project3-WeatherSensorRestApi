package com.restApi.ProjectSensor.RestTemplateSimulateWeatherSensor;

import com.restApi.ProjectSensor.dto.MeasurementDTO;
import com.restApi.ProjectSensor.dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class SimulatedWeatherSensor {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurements/add";

        //postMethod(restTemplate,url);
        getMethod(restTemplate);
    }
    public static void postMethod(RestTemplate restTemplate,String url){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            MeasurementDTO jsonData = new MeasurementDTO();

            double minValue = -100.0;
            double maxValue = 100.0;
            double randomValue = minValue + random.nextDouble() * (maxValue - minValue);
            jsonData.setValue(randomValue);

            jsonData.setRaining(random.nextBoolean());

            String[] sensorName = {"Sensor name tast", "Sensor name tast 2", "Sensor in Saransk"};
            SensorDTO sensorDTO = new SensorDTO();
            sensorDTO.setName(sensorName[random.nextInt(sensorName.length)]);
            jsonData.setSensorDTO(sensorDTO);

            HttpEntity<MeasurementDTO> request = new HttpEntity<>(jsonData, httpHeaders);
            String response = restTemplate.postForObject(url, request, String.class);
            System.out.println(response);
        }
    }
    public static void getMethod(RestTemplate restTemplate){
        String url = "http://localhost:8080/measurements";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MeasurementDTO[] response = restTemplate.getForObject(url,MeasurementDTO[].class);
        for (MeasurementDTO measurementDTO: response){
            System.out.println(measurementDTO.toString());
        }
    }
    public static void buildingAGraph(){
        //ExampleChart<XYChart> exampleChart = new AreaChart01();
        //XYChart chart = exampleChart.getChart();
        //new SwingWrapper<XYChart>(chart).displayChart();
    }
}
