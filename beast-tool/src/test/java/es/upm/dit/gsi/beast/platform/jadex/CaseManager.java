package es.upm.dit.gsi.beast.platform.jadex;

import org.junit.Test;
import es.upm.dit.gsi.beast.story.BeastTestCaseRunner;

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
  
	  BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.SetBelievesInAgent.SetBelievesInAgent");
  }

  /**
   * This is the scenario: GetBelievesFromAgent,
   * where the GIVEN is described as: that one agent is started in Jadex Platform,
   * the WHEN is described as: tester wants to get a belief from a Jadex agent
   * and the THEN is described as: the Jadex belief is retrieved
   */
  @Test
  public void ScenarioGetBelievesFromAgent() {
  
	  BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.GetBelievesFromAgent.GetBelievesFromAgent");
  }

  /**
   * This is the scenario: TestingMockBridgeCaseOne,
   * where the GIVEN is described as: one bridge mock and one listener mock in Jadex Platform,
   * the WHEN is described as: bridge mocks has to send a message to listener
   * and the THEN is described as: listener receives the message
   */
  @Test
  public void ScenarioTestingMockBridgeCaseOne() {
  
	  BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.TestingMockBridgeCaseOne.TestingMockBridgeCaseOne");
  }

  /**
   * This is the scenario: TestingMockBridgeCaseTwo,
   * where the GIVEN is described as: one repository mock and one bridge mock in Jadex Platform,
   * the WHEN is described as: bridge mocks has to send a message to repository
   * and the THEN is described as: bridge receives the answer from repository
   */
  @Test
  public void ScenarioTestingMockBridgeCaseTwo() {
  
	  BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.TestingMockBridgeCaseTwo.TestingMockBridgeCaseTwo");
  }

  /**
   * This is the scenario: TestingMockListener,
   * where the GIVEN is described as: one listener mock in Jadex Platform,
   * the WHEN is described as: 10 messages are sent to listener
   * and the THEN is described as: listener receives all messages
   */
  @Test
  public void ScenarioTestingMockListener() {
  
	  BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.TestingMockListener.TestingMockListener");
  }

  /**
   * This is the scenario: TestingMockRepository,
   * where the GIVEN is described as: one bridge mock and one repository mock in Jadex Platform,
   * the WHEN is described as: bridge sends a message to repository
   * and the THEN is described as: repository answers with the correct message
   */
  @Test
  public void ScenarioTestingMockRepository() {
  
	  BeastTestCaseRunner.executeBeastTestCase("es.upm.dit.gsi.beast.platform.jadex.TestingMockRepository.TestingMockRepository");
  }

}
