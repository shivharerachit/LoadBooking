package com.bs.services;

import com.bs.model.Load;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
import java.util.Optional;

public interface LoadService {
    Load createLoad(Load load);
    Optional<Load> getLoadById(UUID id);
    Page<Load> getAllLoads(Pageable pageable);
    Load updateLoad(UUID id, Load load);
    void deleteLoad(UUID id);
}