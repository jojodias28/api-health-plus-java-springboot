package br.com.ibm.healthplusapi.domain.converter;

import br.com.ibm.healthplusapi.domain.dto.RegisterUserDto;
import br.com.ibm.healthplusapi.entity.RegisterUserEntity;

import java.util.Optional;

public class RegisterUserConverter {

    public RegisterUserConverter() {
    }

    public static RegisterUserEntity toEntity(Optional<RegisterUserDto> dto){
        if(dto.isPresent()){
            return new RegisterUserEntity(dto.get().getId(), dto.get().getName(), dto.get().getEmail(), dto.get().getPassword());
        }else {
            throw new IllegalArgumentException("User can not be null");
        }

    }
    public static RegisterUserDto toDto(Optional<RegisterUserEntity> entity){
        if(entity.isPresent()){
            return new RegisterUserDto(entity.get().getId(), entity.get().getName(), entity.get().getEmail(), entity.get().getPassword());
        }else {
            throw new IllegalArgumentException("User can not be null");
        }
    }

}
