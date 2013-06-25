package beast.tutorial.jadex.manager.mas;

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
   * This is the story: Processing message,
   * requested by: ReporterAgent,
   * providing the feature: process any FIPA-INFORM message from a RecorderAgent which contains a recorded message,
   * so the user gets the benefit: I am able to generate an issue report based on that message content.
   */
  @Test
  public void processingMessage() {
      Result result = JUnitCore.runClasses(beast.tutorial.jadex.stories.mas.ProcessingMessage.class);
      Assert.assertTrue(result.wasSuccessful());
  }

  /**
   * This is the story: Record a message,
   * requested by: RecorderAgent,
   * providing the feature: record incoming calls,
   * so the user gets the benefit: I can pass the message to a ReporterAgent.
   */
  @Test
  public void recordAMessage() {
      Result result = JUnitCore.runClasses(beast.tutorial.jadex.stories.mas.RecordAMessage.class);
      Assert.assertTrue(result.wasSuccessful());
  }

  /**
   * This is the story: Passing an incoming call,
   * requested by: HelpDeskAgent,
   * providing the feature: process a FIPA-REQUEST message from a RecorderAgent,
   * so the user gets the benefit: I am able to pass an incoming call to a human operator.
   */
  @Test
  public void passingAnIncomingCall() {
      Result result = JUnitCore.runClasses(beast.tutorial.jadex.stories.mas.PassingAnIncomingCall.class);
      Assert.assertTrue(result.wasSuccessful());
  }

}
