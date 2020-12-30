package app;

import app.db.AssignedCostume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class PostgreSalesRepository implements SalesRepository {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Override
    public void markAsSold(int costumeId) {

        AtomicReference<AssignedCostume> found = new AtomicReference<>();
        assignmentRepository.findAll().forEach(assignedCostume -> {
            if (assignedCostume.getCostumeId() == costumeId)
                found.set(assignedCostume);
        });
        AssignedCostume assigned = found.get();
        assigned.setSold(true);

        assignmentRepository.save(assigned);
    }
}
