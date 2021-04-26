import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class BonoTest {

    private Project wellFormedProject; // Project with correct summarize(StudentSynthesizer)
    private Project wellFormedProject1; // Project with correct summarize (ExecutiveSynthesizer)
    private Project badFormedProject; // Project where a student hasn't activities
    private Project badFormedProject1; // Project where a iteration hasn't activities
    private Project badFormedProject2; // Project where a student synthetizer hasn't students
    private Project badFormedProject3; // Project where a executive synthetizer hasn't iterations

    @BeforeEach
    public void setup() {

        setupWellFormedProject();
        setupWellFormedProject1();
        setupBadFormedProject();
        setupBadFormedProject1();
        setupBadFormedProject2();
        setupBadFormedProject3();
    }

    @Test
    @DisplayName("GIVEN a well formed project WHEN summarize THEN return the name and duration of students in a project")
    public void shouldSummarizeWhenWellFormedProject() {

        try {
            String summary = wellFormedProject.summarize();
            assertNotNull(summary);
        } catch (SabanaResearchException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("GIVEN a well formed project WHEN summarize THEN return the goal and duration of iterations in a project")
    public void shouldSummarizeWhenWellFormedProject2() {

        try {
            String summary = wellFormedProject1.summarize();
            assertNotNull(summary);
        } catch (SabanaResearchException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("GIVEN a student without activities WHEN summarize THEN get SabanaResearchException")
    public void shouldThrowsSabanaResearchExceptionWhenStudentWithoutActivities() {

        SabanaResearchException exception = assertThrows(SabanaResearchException.class, () -> badFormedProject.summarize());
        assertEquals(SabanaResearchException.BAD_FORMED_STUDENT, exception.getMessage());
    }

    @Test
    @DisplayName("GIVEN a iteration without activities WHEN summarize THEN get SabanaResearchException")
    public void shouldThrowsSabanaResearchExceptionWhenIterationWithoutActivities() {

        SabanaResearchException exception = assertThrows(SabanaResearchException.class, () -> badFormedProject1.summarize());
        assertEquals(SabanaResearchException.BAD_FORMED_ITERATION, exception.getMessage());
    }

    @Test
    @DisplayName("GIVEN a student synthetizer without students WHEN summarize THEN get SabanaResearchException")
    public void shouldThrowsSabanaResearchExceptionWhenStudentSynthetizerWithoutStudents() {

        SabanaResearchException exception = assertThrows(SabanaResearchException.class, () -> badFormedProject2.summarize());
        assertEquals(SabanaResearchException.BAD_FORMED_STUDENT_SYNTHETIZER, exception.getMessage());
    }

    @Test
    @DisplayName("GIVEN a executive synthetizer without iterations WHEN summarize THEN get SabanaResearchException")
    public void shouldThrowsSabanaResearchExceptionWhenExecutiveSynthetizerWithoutIterations() {

        SabanaResearchException exception = assertThrows(SabanaResearchException.class, () -> badFormedProject3.summarize());
        assertEquals(SabanaResearchException.BAD_FORMED_EXECUTIVE_SYNTHETIZER, exception.getMessage());
    }


    private void setupWellFormedProject() {

        Iteration iteration = new Iteration("Sembrar arboles", wellFormedProject);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity("primera siembra", Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step("seleccionar semillas", Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity("terreno", Activity.ACTIVE_STATE, null);
        activity.addStep(new Step("buscar terreno", Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity("documentacion terreno", Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, "¿en que aporta al medio ambiente?", Duration.ofDays(1)));

        List<Activity> activities = new ArrayList<>();
        activities.add(normalActivity);
        activities.add(activity);

        Group group = new Group("Medio ambiente");
        Student student1 = new Student("Maria", "Gonzalez", 12345, "maria.gonzalez@unisanana.edu.co", activities);
        Student student2 = new Student("Juan", "Perez", 67890, "juan.perez@unisanana.edu.co", activities);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        ISynthetizer synthetizer = new StudentSynthetizer(students);
        wellFormedProject = new Project("Arboles", group, synthetizer);

    }

    private void setupWellFormedProject1() {

        Iteration iteration = new Iteration("manejar microprocesadores", wellFormedProject);

        // Create a Normal Activity
        NormalActivity normalActivity = new NormalActivity("programacion", Activity.ACTIVE_STATE, iteration);
        normalActivity.addStep(new Step("crear buen código", Duration.ofDays(1)));

        // Create a Documented Activity
        NormalActivity activity = new NormalActivity("electronica", Activity.ACTIVE_STATE, null);
        activity.addStep(new Step("circuitos electricos", Duration.ofDays(1)));
        DocumentedActivity documentedActivity = new DocumentedActivity("documentacion electronica", Activity.ACTIVE_STATE, iteration, activity);
        documentedActivity.addQuestion(new Question(Question.EASY_QUESTION, "¿es una buena implementacion?", Duration.ofDays(1)));

        List<Activity> activities = new ArrayList<>();
        activities.add(normalActivity);
        activities.add(activity);

        Group group = new Group("Nuevas tecnologia");
        Iteration iteration1 = new Iteration("construir hardware", wellFormedProject1, activities);
        Iteration iteration2 = new Iteration("contruir software", wellFormedProject1, activities);

        ArrayList<Iteration> iterations = new ArrayList<>();
        iterations.add(iteration1);
        iterations.add(iteration2);
        ISynthetizer synthetizer = new ExecutiveSynthetizer(iterations);
        wellFormedProject1 = new Project("Robotica", group, synthetizer);

    }

    private void setupBadFormedProject() {

        Group group = new Group("Cocina");
        Student student1 = new Student("Mariana", "Gordillo", 12346, "mariana.gordillo@unisabana.edu.co", null);
        Student student2 = new Student("Juanes", "Peñaloza", 57890, "juanes.peñaloza@unisabana.edu.co", null);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        ISynthetizer synthetizer = new StudentSynthetizer(students);
        badFormedProject = new Project("entradas", group, synthetizer);

    }

    private void setupBadFormedProject1() {

        Group group = new Group("Nuevas tecnologias 2");
        Iteration iteration1 = new Iteration("construir hardware", badFormedProject1, null);
        Iteration iteration2 = new Iteration("contruir software", badFormedProject1, null);

        ArrayList<Iteration> iterations = new ArrayList<>();
        iterations.add(iteration1);
        iterations.add(iteration2);

        ISynthetizer synthetizer = new ExecutiveSynthetizer(iterations);
        badFormedProject1 = new Project("Robotica2", group, synthetizer);

    }

    private void setupBadFormedProject2() {

        Group group = new Group("Nuevas tecnologias 3");
        ISynthetizer synthetizer = new StudentSynthetizer(null);
        badFormedProject2 = new Project("robotica3", group, synthetizer);

    }

    private void setupBadFormedProject3() {

        Group group = new Group("Nuevas tecnologias 4");
        ISynthetizer synthetizer = new ExecutiveSynthetizer(null);
        badFormedProject3 = new Project("robotica4", group, synthetizer);

    }

}
