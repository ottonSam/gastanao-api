package br.com.ottonsam.gastanaoapi.service;

import br.com.ottonsam.gastanaoapi.entity.Category;
import br.com.ottonsam.gastanaoapi.entity.Transaction;
import br.com.ottonsam.gastanaoapi.entity.User;
import br.com.ottonsam.gastanaoapi.entity.dtos.CreateTransactionDto;
import br.com.ottonsam.gastanaoapi.entity.dtos.ResponseTransactionDto;
import br.com.ottonsam.gastanaoapi.repository.TransactionRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    CategoryService categoryService;
    UserService userService;
    TransactionRepository transactionRepository;

    public TransactionService(CategoryService categoryService, UserService userService, TransactionRepository transactionRepository) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }

    public ResponseTransactionDto createTransaction(CreateTransactionDto transactionDto, JwtAuthenticationToken token) {
        Category category = categoryService.getCategoryData(token, transactionDto.categoryId());
        User user = userService.findByUsername(token.getName());
        Transaction transaction = new Transaction(transactionDto, user, category);
        transactionRepository.save(transaction);
        return new ResponseTransactionDto(transaction);
    }

    public List<ResponseTransactionDto> listAllTransactions(JwtAuthenticationToken token) {
        User user = userService.findByUsername(token.getName());
        List<Transaction> transactions = transactionRepository.findByUser(user);
        return transactions.stream().map(ResponseTransactionDto::new).toList();
    }

    public List<ResponseTransactionDto> listAllTransactionsByCategory(JwtAuthenticationToken token, UUID categoryId) {
        Category category = categoryService.getCategoryData(token, categoryId);
        List<Transaction> transactions = transactionRepository.findByCategory(category);
        return transactions.stream().map(ResponseTransactionDto::new).toList();
    }

    public ResponseTransactionDto updateTransaction(CreateTransactionDto transactionDto, UUID id, JwtAuthenticationToken token) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            Transaction transactionToUpdate = transaction.get();
            User user = userService.findByUsername(token.getName());
            if (transactionToUpdate.getUser().equals(user)) {
                Category category = categoryService.getCategoryData(token, transactionDto.categoryId());
                transactionToUpdate.setDescription(transactionDto.description());
                transactionToUpdate.setAmount(transactionDto.amount());
                transactionToUpdate.setCategory(category);
                transactionRepository.save(transactionToUpdate);
                return new ResponseTransactionDto(transactionToUpdate);
            }
            throw new IllegalArgumentException("This transaction does not belong to the user");
        }
        return null;
    }

    public Boolean deleteTransaction(UUID id, JwtAuthenticationToken token) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            User user = userService.findByUsername(token.getName());
            if (transaction.get().getUser().equals(user)) {
                transactionRepository.delete(transaction.get());
                return true;
            }
            throw new IllegalArgumentException("This transaction does not belong to the user");
        }
        return false;
    }

}
