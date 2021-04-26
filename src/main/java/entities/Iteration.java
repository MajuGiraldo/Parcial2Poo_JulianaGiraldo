package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Iteration {

    public String getGoal() {
        return goal;
    }

    private String goal;
    private Project project;
    private List<Activity> activities;

    public Iteration(String goal, Project project) {
        this.goal = goal;
        this.project = project;
        this.activities = new ArrayList<>();

        if(project != null){
            project.addIteration(this);
        }
    }

    public Iteration(String goal, Project project, List<Activity> activities) {
        this.goal = goal;
        this.project = project;
        this.activities = activities;

        if(project != null){
            project.addIteration(this);
        }
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public int countOpenActivities(){
        int count = 0;
        for (Activity a: this.activities) {
            if (a.isActive()){
                count++;
            }
        }
        return count;
    }

    public int countClosedActivities(){
        return (int) this.activities.stream().map(a -> a.isActive()).filter(b -> !b).count();
    }

    public Duration getDuration() throws SabanaResearchException{

        if (this.activities.isEmpty()){
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_ITERATION);
        }

        Duration iterationDuration = Duration.ZERO;
        for(Activity a: this.activities){
            iterationDuration = iterationDuration.plus(a.getDuration());
        }

        return iterationDuration;
    }

    public Duration getActivitiesDuration() throws SabanaResearchException {
        if(activities == null){
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_ITERATION);
        }
        Duration activitiesDuration = Duration.ZERO;
        for(Activity a: this.activities){
            activitiesDuration = activitiesDuration.plus(a.getDuration());
        }
        return activitiesDuration;
    }
}
