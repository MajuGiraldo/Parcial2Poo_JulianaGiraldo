package entities;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private List<Project> projects;

    public Group(String name) {
        this.name = name;
        this.projects = new ArrayList<>();
    }

    public void addProject(Project plan) {
        this.projects.add(plan);
    }

    public int countActiveProjects(){
        int count = 0;
        for (Project p: projects){
            if (p.isActive()){
                count++;
            }
        }
        return count;
    }

    public int countOpenActivities(){
        int count = 0;
        for (Project p: projects){
            count += p.countOpenActivities();
        }
        return count;
    }

    public int countClosedActivities(){
        int count = 0;
        for (Project p: projects){
            count += p.countClosedActivities();
        }
        return count;
    }

}
