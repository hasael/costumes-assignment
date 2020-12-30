package app;

import app.db.AssignedCostume;

public interface AmazonIntegrationNotifier {

    void notifyAssignment(AssignedCostume assignedCostume);
}
