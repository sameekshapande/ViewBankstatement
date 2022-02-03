package com.viewstatement.repo;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.viewstatement.model.Accounts;
public interface AccountRepository extends CrudRepository<Accounts ,Long>{
Optional<Accounts> findById(Long accountId);
}
