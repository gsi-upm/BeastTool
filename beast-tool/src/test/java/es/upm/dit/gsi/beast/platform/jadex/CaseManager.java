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

  /**
   * This is the scenario: TestingMockBridge1,
   * where the GIVEN is described as: one listener mock and one repository mock in Jadex Platform,
   * the WHEN is described as: bridge mocks has to send a message to listener
   * and the THEN is described as: listener receives the message
   */
  @Test
  public void ScenarioTestingMockBridge1() {
  
	  StoryRunner.executeStory("es.upm.dit.gsi.beast.platform.jadex.TestingMockBridge1.TestingMockBridge1");
  }

  /**
   * This is the scenario: TestingMockBridge2,
   * where the GIVEN is described as: one repository mock and one bridge mock in Jadex Platform,
   * the WHEN is described as: bridge mocks has to send a message to repository
   * and the THEN is described as: bridge receives the answer from repository
   */
  @Test
  public void ScenarioTestingMockBridge2() {
  
	  StoryRunner.executeStory("es.upm.dit.gsi.beast.platform.jadex.TestingMockBridge2.TestingMockBridge2");
  }

  /**
   * This is the scenario: TestingMockListener,
   * where the GIVEN is described as: one listener mock in Jadex Platform,
   * the WHEN is described as: 10 messages are sended to listener
   * and the THEN is described as: listener receives all messages
   */
  @Test
  public void ScenarioTestingMockListener() {
  
	  StoryRunner.executeStory("es.upm.dit.gsi.beast.platform.jadex.TestingMockListener.TestingMockListener");
  }

  /**
   * This is the scenario: TestingMockRepository,
   * where the GIVEN is described as: one bridge mock and one repository mock in Jadex Platform,
   * the WHEN is described as: bridge sends a message to repository
   * and the THEN is described as: repository answers with the correct message
   */
  @Test
  public void ScenarioTestingMockRepository() {
  
	  StoryRunner.executeStory("es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository.TestingMockRepository");
  }

}
