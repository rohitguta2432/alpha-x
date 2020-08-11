package io.rammila.api.service;

import io.rammila.api.model.Address;
import io.rammila.api.model.Role;
import io.rammila.api.model.User;
import io.rammila.api.repository.AddressRepository;
import io.rammila.api.repository.UserRepository;
import io.rammila.api.utility.RoleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public Address createAddress(Address address){
        log.info("creating user address : {} ", address);
        Optional<User> users = userRepository.findById(RoleUtil.getCurrentUseInfo().getId());
        if(users.isPresent()){
            List<Address> addressList = users.get().getAddress();
            addressList.add(address);
            users.get().setAddress(addressList);
            userRepository.save(users.get());
        }
        return address;
    }
    public List<Address> getAddress(){
        log.info("fetching user all address ");
        return userRepository.findById(RoleUtil.getCurrentUseInfo().getId()).get().getAddress();
    }

}
