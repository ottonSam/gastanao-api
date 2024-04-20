package dev.ottonsam.gastanaoapi.service;

import dev.ottonsam.gastanaoapi.domain.dto.ExpendRequestDTO;
import dev.ottonsam.gastanaoapi.domain.dto.ExpendResponseDTO;

public interface ExpendService {
    ExpendResponseDTO findById(Long id);
    ExpendResponseDTO create(ExpendRequestDTO expendRequestDTO);
}
