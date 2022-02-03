package com.viewstatement.repo;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.viewstatement.model.Statement;
public interface ViewstatementRepository extends CrudRepository<Statement ,Long>{
List<Statement> findByAccountId(Long accountId);
}
