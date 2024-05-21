package com.makhchan.ebanking.web;

import com.makhchan.ebanking.dtos.CustomerDTO;
import com.makhchan.ebanking.exceptions.CustomerNotFoundException;
import com.makhchan.ebanking.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER') or hasAuthority('SCOPE_ADMIN')")
    public List<CustomerDTO> getCustomers(){
        log.info("Getting all customers");
        return bankAccountService.listCustomers();
    }
    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long id)throws CustomerNotFoundException {
        log.info("Getting customer with id {}", id);
        return bankAccountService.getCustomer(id);
    }
    @PostMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        log.info("Saving customer");
        return bankAccountService.saveCustomer(customerDTO);
    }
    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDTO updateCustomer(@PathVariable(name = "customerId") Long customerId,@RequestBody CustomerDTO customerDTO){
        log.info("Updating customer with id {}", customerId);
        customerDTO.setId(customerId);
        return bankAccountService.saveCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteCustomer(@PathVariable(name = "customerId") Long customerId) {
        log.info("Deleting customer with id {}", customerId);
        bankAccountService.deleteCustomer(customerId);
    }
    @GetMapping("/customers/search")
    @PreAuthorize("hasAuthority('SCOPE_USER') or hasAuthority('SCOPE_ADMIN')")
    public List<CustomerDTO> searchCustomers(@RequestParam (name = "keyword", defaultValue = "") String keyword){
        return bankAccountService.searchCustomers("%" + keyword + "%");
    }
}