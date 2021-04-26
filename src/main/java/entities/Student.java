package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Student {

    private int code;

    public String getName() {
        return name;
    }

    private String name;

    public String getLastName() {
        return lastName;
    }

    private String lastName;
    private String email;

    private List<Activity> assignedActivities;

    public Student(String name, String lastName, int code, String email, List<Activity> assignedActivities) {
        this.name = name;
        this.lastName = lastName;
        this.code = code;
        this.email = email;
        this.assignedActivities = assignedActivities;
    }

    public Duration getActivitiesDuration() throws SabanaResearchException {
        if(assignedActivities == null){
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_STUDENT);
        }
        Duration activitiesDuration = Duration.ZERO;
        for(Activity a: this.assignedActivities){
            activitiesDuration = activitiesDuration.plus(a.getDuration());
        }

        return activitiesDuration;
    }


}
