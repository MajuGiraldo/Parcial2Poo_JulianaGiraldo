package entities;

import java.time.LocalDate;

public class Summary {

    private int activeProjects;
    private int openActivities;
    private int closedActivities;
    private LocalDate date;

    public Summary(int activeProjects, LocalDate date) {
        this.activeProjects = activeProjects;
        this.date = date;
    }

    public Summary(int activeProjects, int openActivities, int closedActivities, LocalDate date){
        this.activeProjects = activeProjects;
        this.openActivities = openActivities;
        this.closedActivities = closedActivities;
        this.date = date;
    }

    public int getOpenActivities() { return openActivities; }

    public int getClosedActivities() { return closedActivities; }

    public int getActiveProjects() {
        return activeProjects;
    }

    public LocalDate getDate() {
        return date;
    }

}
