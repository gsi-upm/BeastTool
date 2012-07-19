package es.upm.dit.gsi.beast.reader.mas.test;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import es.upm.dit.gsi.beast.story.BeastTestCaseRunner;

/**
 * Main class to launch all tests in a single run
 *
 * @author es.upm.dit.gsi.beast
 */
public class CaseManager {

  /**
   * This is the story: Test Story,
   * requested by: user,
   * providing the feature: do something
   * so the user gets the benefit: I have a benefit
   */
  @Test
  public void testStory() {
      JUnitCore.main("es.upm.dit.gsi.beast.reader.mas.test.TestStory");
  }

}
