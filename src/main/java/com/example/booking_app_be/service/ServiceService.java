package com.example.booking_app_be.service;


import com.example.booking_app_be.dto.request.ServiceRequest;
import com.example.booking_app_be.dto.response.ServiceResponse;
import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.entity.Service;
import com.example.booking_app_be.exception.AppException;
import com.example.booking_app_be.exception.ErrorCode;
import com.example.booking_app_be.mapper.ServiceMapper;
import com.example.booking_app_be.repository.HotelRepository;
import com.example.booking_app_be.repository.ServiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceService {

    ServiceRepository serviceRepository;
    HotelRepository hotelRepository;
    ServiceMapper serviceMapper;

    public List<ServiceResponse> getAllByHotel(Long id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> {
            throw new AppException(ErrorCode.HOTEL_NOT_EXISTED);
        });
        List<ServiceResponse> serviceResponses = serviceRepository.findAllByHotel(hotel).stream().map(service ->
                serviceMapper.toServiceResponse(service)).toList();

        return serviceResponses;
    }

    public ServiceResponse createService(ServiceRequest request){
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(() -> {
            throw new AppException(ErrorCode.HOTEL_NOT_EXISTED);
        });

        Service service = serviceMapper.toService(request);
        service.setHotel(hotel);

        serviceRepository.save(service);

        ServiceResponse serviceResponse = serviceMapper.toServiceResponse(service);

        return serviceResponse;
    }

    public ServiceResponse updateService(Long id, ServiceRequest request){

        Service service = serviceRepository.findById(id).orElseThrow();

        serviceMapper.updateService(service, request);

        ServiceResponse serviceResponse = serviceMapper.toServiceResponse(serviceRepository.save(service));

        return serviceResponse;
    }

    public void deleteService(Long id){
        serviceRepository.deleteById(id);
    }
}