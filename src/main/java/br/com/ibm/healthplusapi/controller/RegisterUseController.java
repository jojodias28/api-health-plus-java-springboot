package br.com.ibm.healthplusapi.controller;

import br.com.ibm.healthplusapi.controller.response.ResponseHandler;
import br.com.ibm.healthplusapi.domain.dto.RegisterUserDto;
import br.com.ibm.healthplusapi.entity.RegisterUserEntity;
import br.com.ibm.healthplusapi.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/register")
public class RegisterUseController {

    @Autowired
    private RegisterUserService service;

    @PostMapping
    public ResponseEntity<RegisterUserDto> insert(@RequestBody RegisterUserDto userDto){
        return new ResponseEntity<>(this.service.insertUser(userDto), CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> userLogin(@RequestBody RegisterUserDto userDto){
        RegisterUserEntity entity = service.userLogin(userDto.getEmail(), userDto.getPassword());
        if(entity != null){
            return ResponseEntity.ok().body(entity);
        }else{
            return ResponseHandler.generateResponse("User not authenticated", HttpStatus.NOT_FOUND, "NOT OK");
        }

    }

    @GetMapping
    public ResponseEntity<List<RegisterUserDto>> findAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegisterUserDto> findById(@PathVariable Long id){
        return  ResponseEntity.ok(this.service.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RegisterUserDto> update(@PathVariable Long id, @RequestBody RegisterUserDto userDto){
        userDto = service.update(id, userDto);
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RegisterUserDto> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }




}
