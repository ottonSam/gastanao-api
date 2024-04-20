package dev.ottonsam.gastanaoapi.controller;

import dev.ottonsam.gastanaoapi.domain.dto.ExpendRequestDTO;
import dev.ottonsam.gastanaoapi.domain.dto.ExpendResponseDTO;
import dev.ottonsam.gastanaoapi.service.ExpendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/expends")
public class ExpendController {
    private final ExpendService expendService;
    public ExpendController(ExpendService expendService) {
        this.expendService = expendService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpendResponseDTO> getById(@PathVariable Long id) {
        var expend = expendService.findById(id);
        return ResponseEntity.ok(expend);
    }

    @PostMapping
    public ResponseEntity<ExpendResponseDTO> create(@RequestBody ExpendRequestDTO expendRequestDTO) {
        var expendCreated = expendService.create(expendRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(expendCreated.id())
                .toUri();
        return ResponseEntity.created(location).body(expendCreated);
    }
}
