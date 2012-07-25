package es.upm.dit.gsi.beast.platform.jade;

import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 * Main class to launch all tests in a single run
 *
 * @author es.upm.dit.gsi.beast
 */
public class CaseManager {

  /**
   * This is the story: jade platform test,
   * requested by: developer,
   * providing the feature: generate tests for the agent
   * so the user gets the benefit: null
   */
  @Test
  public void jadePlatformTest() {
      JUnitCore.main("es.upm.dit.gsi.beast.platform.jade.JadePlatformTest");
  }

}
