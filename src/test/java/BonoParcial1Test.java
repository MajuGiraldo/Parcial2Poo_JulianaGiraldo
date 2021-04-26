import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BonoParcial1Test {

    private Group group;
    private List<Project> projects;
    private List<Iteration> iterations;
    private NormalActivity normalActivity;
    private NormalActivity normalActivity1;
    private NormalActivity normalActivity2;
    private NormalActivity normalActivity3;
    private NormalActivity normalActivity4;
    private NormalActivity normalActivity5;
    private NormalActivity normalActivity6;

    public BonoParcial1Test() {
        this.projects = new ArrayList<>();
        this.iterations = new ArrayList<>();
    }

    @BeforeEach
    public void setup() {

        // Create groups
        group = new Group("Medical Science Group");

        // Create projects
        projects.add(new Project("COVID 19 Vaccine", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), group)); // OPEN date but with all the activities CLOSED
        projects.add(new Project("Amazing Masks", LocalDate.now().plusDays(1), LocalDate.now().plusDays(10), group)); // OPEN date but with all the activities OPENED
        projects.add(new Project("New Economic Model", LocalDate.now().minusDays(10), LocalDate.now().minusDays(1), group)); // CLOSED date

        // Create iterations
        iterations.add(new Iteration("1. Understand Virus", projects.get(0)));
        iterations.add(new Iteration("2. Create Vaccine", projects.get(0)));

        iterations.add(new Iteration("1. Investigate materials", projects.get(1)));

        iterations.add(new Iteration("1. Design new model", projects.get(2)));

        // Create activities
        normalActivity = new NormalActivity("Investigate ARN", Activity.CLOSED_STATE, iterations.get(0));
        normalActivity1 = new NormalActivity("Investigate infected people", Activity.CANCELED_STATE, iterations.get(0));
        normalActivity2 = new NormalActivity("Test in animals", Activity.CANCELED_STATE, iterations.get(1));
        normalActivity3 = new NormalActivity("Test in humans", Activity.CLOSED_STATE, iterations.get(1));

        normalActivity4 = new NormalActivity("Verify color", Activity.CLOSED_STATE, iterations.get(2));
        normalActivity5 = new NormalActivity("Buy massive", Activity.PENDING_STATE, iterations.get(2));

        normalActivity6 = new NormalActivity("Study previous models", Activity.PENDING_STATE, iterations.get(3));

    }

    @Test
    @DisplayName("GIVEN a group WHEN calculate open activities THEN get right open activities")
    public void shouldCalculateOpenActivities() {

        assertEquals(2, group.countOpenActivities());
    }

    @Test
    @DisplayName("GIVEN a group WHEN calculate closed activities THEN get right closed activities")
    public void shouldCalculateClosedActivities() {

        assertEquals(5, group.countClosedActivities());
    }

    @Test
    @DisplayName("GIVEN a group WHEN calculate open activities THEN get different open activities")
    public void shouldNotCalculateOpenActivities() {

        assertNotEquals(4, group.countOpenActivities());
    }

    @Test
    @DisplayName("GIVEN a group WHEN calculate closed activities THEN get different closed activities")
    public void shouldNotCalculateClosedActivities() {

        assertNotEquals(3, group.countClosedActivities());
    }

}
