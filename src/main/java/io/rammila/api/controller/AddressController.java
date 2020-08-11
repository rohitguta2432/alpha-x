package io.rammila.api.controller;

import io.rammila.api.model.Address;
import io.rammila.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('CREATE_USER')")
    public ResponseEntity<?> create(@RequestBody Address address) {
        return new ResponseEntity<>(addressService.createAddress(address), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CREATE_USER')")
    public ResponseEntity<?> getAdddress() {
        return new ResponseEntity<>(addressService.getAddress(), HttpStatus.OK);
    }
}
