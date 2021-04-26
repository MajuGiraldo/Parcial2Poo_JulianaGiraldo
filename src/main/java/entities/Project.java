package entities;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private Group group;
    private List<Iteration> iterations;
    private ISynthetizer synthetizer;

    public Project(String name, LocalDate dateInit, LocalDate dateEnd, Group group) {
        this.name = name;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.group = group;
        this.iterations = new ArrayList<>();

        group.addProject(this);
    }

    public Project(String name, Group group, ISynthetizer synthetizer){
        this.name = name;
        this.group = group;
        this.iterations = new ArrayList<>();
        this.synthetizer = synthetizer;
    }

    public void addIteration(Iteration iteration) {
        this.iterations.add(iteration);
    }

    public void setDateInit(LocalDate dateInit) {
        this.dateInit = dateInit;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Evaluate if a project is active.
     *
     * @return false if the project has open activities or the dateEnd is before than the system date.
     */
    public boolean isActive() {
        boolean isActive = true;

        if (LocalDate.now().isAfter(this.dateEnd)){
            isActive = false;
        }else {
            int openActivities = this.countOpenActivities();
            isActive = openActivities > 0;
        }

        return isActive;
    }

    public int countOpenActivities(){
        int count = 0;
        for (Iteration i: this.iterations){
            count += i.countOpenActivities();
        }
        return count;
    }

    public int countClosedActivities(){
        int count = 0;
        for (Iteration i: this.iterations){
            count += i.countClosedActivities();
        }
        return count;
    }


    public Duration getDuration() throws SabanaResearchException {

        if (iterations.isEmpty()){
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_PROJECT);
        }

        Duration projectDuration = Duration.ZERO;
        for (Iteration i: this.iterations){
            projectDuration = projectDuration.plus(i.getDuration());
        }

        return projectDuration;
    }

    /**
     * Summarizes the information of the students or the iterations within a project.
     *
     *
     * @return The duration and name of students or the duration and objective of iterations in a project.
     */

    public String summarize() throws SabanaResearchException {
        String summary = "";
        summary = this.synthetizer.synthetize();

        return summary;
    }

}
