package br.com.ottonsam.gastanaoapi.controller;

import br.com.ottonsam.gastanaoapi.entity.dtos.CreateTransactionDto;
import br.com.ottonsam.gastanaoapi.entity.dtos.ResponseTransactionDto;
import br.com.ottonsam.gastanaoapi.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    ResponseEntity<List<ResponseTransactionDto>> getAllTransactions(JwtAuthenticationToken token) {
        List<ResponseTransactionDto> transactions = transactionService.listAllTransactions(token);

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{categoryId}")
    ResponseEntity<List<ResponseTransactionDto>> getTransactionsByCategory(JwtAuthenticationToken token, @PathVariable("categoryId") UUID categoryId) {
        List<ResponseTransactionDto> transactions = transactionService.listAllTransactionsByCategory(token, categoryId);

        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    ResponseEntity<ResponseTransactionDto> createTransaction(JwtAuthenticationToken token, @RequestBody CreateTransactionDto transactionDto) {
        Optional<ResponseTransactionDto> transaction = Optional.ofNullable(transactionService.createTransaction(transactionDto, token));

        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseTransactionDto> updateTransaction(@PathVariable("id") UUID id, JwtAuthenticationToken token, @RequestBody CreateTransactionDto transactionDto) {
        Optional<ResponseTransactionDto> transaction = Optional.ofNullable(transactionService.updateTransaction(transactionDto, id, token));

        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTransaction(@PathVariable("id") UUID id, JwtAuthenticationToken token) {
        if (transactionService.deleteTransaction(id, token)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
