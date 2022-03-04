package br.com.lazaro.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lazaro.api.models.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

}
