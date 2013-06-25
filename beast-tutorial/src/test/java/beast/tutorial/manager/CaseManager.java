package beast.tutorial.manager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Main class to launch all tests in a single run
 *
 * @author es.upm.dit.gsi.beast
 */
public class CaseManager {

  /**
   * This is the story: Inbox service,
   * requested by: Helpdesk operator,
   * providing the feature: have a system that receive any report generated by customers,
   * so the user gets the benefit: I save effort and time since less phone calls are received.
   */
  @Test
  public void InboxService() {
     Result result = JUnitCore.runClasses(beast.tutorial.stories.system.InboxService.class);
     Assert.assertTrue(result.wasSuccessful());
  }

}
