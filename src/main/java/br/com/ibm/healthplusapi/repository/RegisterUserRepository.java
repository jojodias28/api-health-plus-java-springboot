package br.com.ibm.healthplusapi.repository;

import br.com.ibm.healthplusapi.entity.RegisterUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUserEntity, Long> {
        Optional<RegisterUserEntity> findByEmailAndPassword(String email, String password);

    Optional<RegisterUserEntity> findByEmail(String email);
}
