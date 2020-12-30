package app;

import app.db.AssignedCostume;
import app.input.CostumeInput;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Locale;

@Component
public class CostumeAssigner {

    public AssignedCostume assignedCostume(CostumeInput costumeInput) {
        var assignedCostume = new AssignedCostume();
        String channel = "";
        if (costumeInput.getCondition().toLowerCase(Locale.ROOT).equals("new"))
            channel = "Amazon";
        else
            channel = "eBay";

        assignedCostume.setCostumeId(costumeInput.getId());
        assignedCostume.setAssignmentChannel(channel);
        assignedCostume.setAssignmentDate(Calendar.getInstance().getTime());
        return assignedCostume;
    }
}
