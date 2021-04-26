package entities;

import java.time.Duration;
import java.util.List;

public class StudentSynthetizer implements ISynthetizer{

    private List<Student> students;

    public StudentSynthetizer(List<Student> students) {
        this.students = students;
    }

    @Override
    public String synthetize() throws SabanaResearchException {
        if (students == null){
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_STUDENT_SYNTHETIZER);
        }
        String summary = "";
        for(Student s: students){
            summary = s.getName() + s.getLastName() + s.getActivitiesDuration().toString();
        }
        return summary;
    }
}
