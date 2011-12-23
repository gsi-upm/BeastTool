package es.upm.dit.gsi.beast.platform.jade;

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
   * where the GIVEN is described as: that TesterAgent is started in Jade Platform in Main-Container with Configuration 4,
   * the WHEN is described as: tester wants to set a belief inside a Jade agent
   * and the THEN is described as: the Jade belief is set
   */
  @Test
  public void ScenarioSetBelievesInAgent() {
  
	  StoryRunner.executeStory("es.upm.dit.gsi.beast.platform.jade.SetBelievesInAgent.SetBelievesInAgent");
  }

  /**
   * This is the scenario: GetBelievesFromAgent,
   * where the GIVEN is described as: that TesterAgent is started in Jade Platform in Main-Container with Configuration 1,
   * the WHEN is described as: tester wants to get a belief from a Jade agent
   * and the THEN is described as: the Jade belief is retrieved
   */
  @Test
  public void ScenarioGetBelievesFromAgent() {
  
	  StoryRunner.executeStory("es.upm.dit.gsi.beast.platform.jade.GetBelievesFromAgent.GetBelievesFromAgent");
  }

}
