package com.example.sick.service;

import com.example.sick.api.model.exception.StatusNotFoundException;
import com.example.sick.api.model.request.StatusRequest;
import com.example.sick.api.model.response.StatusResponse;
import com.example.sick.domain.StatusDAORequest;
import com.example.sick.domain.StatusDAOResponse;
import com.example.sick.repository.StatusRepository;
import com.example.sick.utils.ApplicationStatus;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }
    static final boolean APPLICATIONISNOTOPENED = false;
    static final boolean APPLICATIONISOPENED = true;

    @Override
    public void updateStatusById(StatusRequest statusRequest) {
        statusRepository.updateStatusById(convertToStatusDAORequest(statusRequest));
        System.out.println(statusRequest.APPLICATIONSTATUS());
    }

    @Override
    public StatusResponse getStatusById(long id) throws StatusNotFoundException {
        StatusDAOResponse statusDAOResponse = statusRepository.getStatusById(id).orElseThrow(() -> new StatusNotFoundException(id));
        return convertStatusDAOResponseToStatusResponse(statusDAOResponse);
    }

    @Override
    public void updateStatusIsRead(long id) {
        statusRepository.updateStatusRead(id, APPLICATIONISOPENED);
    }

    private StatusDAORequest convertToStatusDAORequest(StatusRequest statusRequest) {
        return new StatusDAORequest(statusRequest.id(), ApplicationStatus.valueOf(statusRequest.APPLICATIONSTATUS()));
    }

    private StatusResponse convertStatusDAOResponseToStatusResponse(StatusDAOResponse statusDAOResponse) {
        return new StatusResponse(
                statusDAOResponse.id(),
                statusDAOResponse.APPLICATIONSTATUS(),
                statusDAOResponse.isOpened(),
                statusDAOResponse.updatedAt(),
                statusDAOResponse.createdAt(),
                statusDAOResponse.isHighRisk()
        );
    }
}
