package app;

import app.db.AssignedCostume;
import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepository extends CrudRepository<AssignedCostume, Integer> {
}
