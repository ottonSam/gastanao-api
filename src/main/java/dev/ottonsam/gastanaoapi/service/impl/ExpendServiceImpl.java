package dev.ottonsam.gastanaoapi.service.impl;

import dev.ottonsam.gastanaoapi.domain.dto.ExpendRequestDTO;
import dev.ottonsam.gastanaoapi.domain.dto.ExpendResponseDTO;
import dev.ottonsam.gastanaoapi.domain.model.Expend;
import dev.ottonsam.gastanaoapi.domain.repository.CategoryRepository;
import dev.ottonsam.gastanaoapi.domain.repository.ExpendRepository;
import dev.ottonsam.gastanaoapi.service.ExpendService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ExpendServiceImpl implements ExpendService {
    private ExpendRepository expendRepository;
    private CategoryRepository categoryRepository;

    public ExpendServiceImpl(ExpendRepository expendRepository, CategoryRepository categoryRepository) {
        this.expendRepository = expendRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ExpendResponseDTO findById(Long id) {
        var expend = expendRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new ExpendResponseDTO(expend);
    }

    @Override
    public ExpendResponseDTO create(ExpendRequestDTO expendRequestDTO) {
        var category = categoryRepository.findById(expendRequestDTO.CategoryId()).orElseThrow(NoSuchElementException::new);
        var expend = new Expend(expendRequestDTO);
        expend.setCategory(category);
        var expendResponse = expendRepository.save(expend);
        return new ExpendResponseDTO(expendResponse);
    }
}
