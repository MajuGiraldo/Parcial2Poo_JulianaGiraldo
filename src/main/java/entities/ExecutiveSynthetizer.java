package entities;

import java.time.Duration;
import java.util.List;

public class ExecutiveSynthetizer implements ISynthetizer{

    private List<Iteration> iterations;

    public ExecutiveSynthetizer(List<Iteration> iterations) {
        this.iterations = iterations;

    }

    @Override
    public String synthetize() throws SabanaResearchException {
        if(iterations == null){
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_EXECUTIVE_SYNTHETIZER);
        }
        String summary = "";
        for (Iteration i: iterations){
            summary = i.getGoal() + i.getActivitiesDuration().toString();
        }
        return summary;
    }
}
