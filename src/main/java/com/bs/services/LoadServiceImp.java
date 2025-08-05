package com.bs.services;

import com.bs.model.Load;
import com.bs.repo.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoadServiceImp implements LoadService {

    @Autowired
    private LoadRepository loadRepository;

    @Override
    public Load createLoad(Load load) {
        if (load.getStatus() == null) {
            load.setStatus(Load.Status.POSTED);
        }
        load.setDatePosted(LocalDateTime.now());
        return loadRepository.save(load);
    }

    @Override
    public Optional<Load> getLoadById(UUID id) {
        return loadRepository.findById(id);
    }

    @Override
    public Page<Load> getAllLoads(Pageable pageable) {
        return loadRepository.findAll(pageable);
    }

    @Override
    public Load updateLoad(UUID id, Load updatedLoad) {
        return loadRepository.findById(id)
            .map(existingLoad -> {
                existingLoad.setShipperId(updatedLoad.getShipperId());
                existingLoad.setLoadingPoint(updatedLoad.getLoadingPoint());
                existingLoad.setUnloadingPoint(updatedLoad.getUnloadingPoint());
                existingLoad.setLoadingDate(updatedLoad.getLoadingDate());
                existingLoad.setUnloadingDate(updatedLoad.getUnloadingDate());
                existingLoad.setProductType(updatedLoad.getProductType());
                existingLoad.setTruckType(updatedLoad.getTruckType());
                existingLoad.setNoOfTrucks(updatedLoad.getNoOfTrucks());
                existingLoad.setWeight(updatedLoad.getWeight());
                existingLoad.setComment(updatedLoad.getComment());
                // Status update should be handled with care, so not overwritten here
                return loadRepository.save(existingLoad);
            })
            .orElseThrow(() -> new RuntimeException("Load not found with id " + id));
    }

    
    @Override
    public void deleteLoad(UUID id) {
        loadRepository.deleteById(id);
    }
}
