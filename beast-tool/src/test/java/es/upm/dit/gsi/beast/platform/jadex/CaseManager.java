package es.upm.dit.gsi.beast.platform.jadex;

import org.junit.Test;
import es.upm.dit.gsi.beast.story.StoryRunner;

/**
 * Main class to launch all tests in a single run
 *
 * @author es.upm.dit.gsi.beast
 */
public class CaseManager {

  /**
   * This is the scenario: SetBelievesInAgent,
   * where the GIVEN is described as: that one agent is started in Jadex Platform,
   * the WHEN is described as: tester wants to set a belief inside a Jadex agent
   * and the THEN is described as: the Jadex belief is set
   */
  @Test
  public void ScenarioSetBelievesInAgent() {
  
	  StoryRunner.executeStory("es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.SetBelievesInAgent");
  }

  /**
   * This is the scenario: GetBelievesFromAgent,
   * where the GIVEN is described as: that one agent is started in Jadex Platform,
   * the WHEN is described as: tester wants to get a belief from a Jadex agent
   * and the THEN is described as: the Jadex belief is retrieved
   */
  @Test
  public void ScenarioGetBelievesFromAgent() {
  
	  StoryRunner.executeStory("es.upm.dit.gsi.beast.platform.jadex.GetBelievesFromAgent.GetBelievesFromAgent");
  }

}
