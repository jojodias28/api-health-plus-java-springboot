package br.com.ibm.healthplusapi.service;

import br.com.ibm.healthplusapi.domain.converter.RegisterUserConverter;
import br.com.ibm.healthplusapi.domain.dto.RegisterUserDto;
import br.com.ibm.healthplusapi.entity.RegisterUserEntity;
import br.com.ibm.healthplusapi.repository.RegisterUserRepository;
import br.com.ibm.healthplusapi.service.exceptions.ExistingEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterUserService {

    @Autowired
    private RegisterUserRepository repository;

    public RegisterUserEntity userLogin(String email, String password){
        Optional<RegisterUserEntity> user = repository.findByEmailAndPassword(email, password);
        return user.orElseGet(() -> {return null;});
    }

    public RegisterUserDto insertUser(RegisterUserDto userDto) {
        Optional<RegisterUserEntity> userEntityCheck = repository.findByEmail(userDto.getEmail());
        if(userEntityCheck.isEmpty()){

            RegisterUserEntity userEntity = RegisterUserConverter.toEntity(Optional.of(userDto));
            return RegisterUserConverter.toDto(Optional.ofNullable(repository.save(userEntity)));

        }else {
            throw new ExistingEmailException();
        }
    }

    public List<RegisterUserDto> findAll() {
        return repository.findAll().stream().map(e -> RegisterUserConverter.toDto(Optional.ofNullable(e))).collect(Collectors.toList());
    }

    public RegisterUserDto findById(Long id) {
        return RegisterUserConverter.toDto(repository.findById(id));
    }

    public RegisterUserDto update(Long id, RegisterUserDto userDto) {
       RegisterUserEntity userEntity = RegisterUserConverter.toEntity(Optional.of(userDto));
        RegisterUserEntity entity = repository.getReferenceById(id);
        updateData(entity, userEntity);
        return RegisterUserConverter.toDto(Optional.ofNullable(repository.save(entity)));
    }

    private void updateData(RegisterUserEntity entity, RegisterUserEntity user){
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
