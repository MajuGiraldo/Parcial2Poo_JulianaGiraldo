package entities;

import java.time.Duration;

public interface ISynthetizer {

    /**
     * Summarizes the information of the students or the iterations within a project.
     *
     *
     * @return The duration and name of students or duration and objective of iterations in a project.
     */

    String synthetize() throws SabanaResearchException;
}
