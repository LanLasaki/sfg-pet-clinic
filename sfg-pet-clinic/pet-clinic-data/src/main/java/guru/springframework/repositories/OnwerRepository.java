package guru.springframework.repositories;

import guru.springframework.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OnwerRepository extends CrudRepository<Owner, Long> {
}
