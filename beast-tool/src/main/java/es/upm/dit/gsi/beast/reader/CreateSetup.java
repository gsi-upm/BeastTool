package es.upm.dit.gsi.beast.reader;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.ws.jaxme.js.JavaComment;
import org.apache.ws.jaxme.js.JavaMethod;
import org.apache.ws.jaxme.js.JavaQName;
import org.apache.ws.jaxme.js.JavaQNameImpl;
import org.apache.ws.jaxme.js.JavaSource;
import org.apache.ws.jaxme.js.JavaSourceFactory;

import es.upm.dit.gsi.beast.story.testCase.Setup;

/**
 * Main class to create the setup of each Test. The evaluation represents the
 * WHEN part of our BDD Test.
 * 
 * @author Jorge Solitario
 */
public class CreateSetup {

    /**
     * Method to create the setup of each test. IT does not check if the file is
     * already created, overwriting it.
     * 
     * @param scenario_name
     *            The name given by the client
     * @param path
     *            The package of the test
     * @param client_description
     *            The plain text given by the client in the THEN part
     * @param dest_dir
     *            the working directory (typically src/main/java)
     */
    public static void createSetup(String scenario_name, String path,
            String client_description, String dest_dir) {

        Logger logger = Logger.getLogger("CreateSetup.java");

        JavaSourceFactory factory = new JavaSourceFactory();

        JavaQName className = JavaQNameImpl.getInstance(path, "Setup"
                + scenario_name);
        JavaSource js = factory.newJavaSource(className, "public");

        js.newComment();
        JavaComment classComment = js.getComment();
        classComment.addLine(" ");
        classComment.addLine("This is the class that must create the Setup.");
        classComment.addLine("It is related with the WHEN part.");
        classComment.addLine(" ");
        classComment
                .addLine("In setStates method the following methods must be used");
        classComment
                .addLine("   super.setBeliefValue (agent_name, belief_name, new_value )");
        classComment
                .addLine("   super.sendMessageToAgent(agent_name, msgtype, message_content)");
        classComment.addLine("   super.getAgentPlans(agent_name)");
        classComment.addLine("   super.getAgentGoals(agent_name )");

        classComment.addAuthor("es.upm.dit.gsi.beast");

        JavaMethod jm = js.newJavaMethod("setStates", "void", "public");
        jm.addLine("// TODO implement this method to represent the @When part of the test in Java code.");
        jm.addLine("logger.warning(\"Implement setStates() method in "
                + path
                + ".Setup"
                + scenario_name
                + ".java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool\");");
        jm.addLine("\n   //EXAMPLE: super.setBeliefValue(\"Steve\", \"age\", 21);");

        
        jm.newComment();
        JavaComment methodComment = jm.getComment();
        methodComment.addLine(" ");
        methodComment
                .addLine("Here the description given by the client must be written,");
        methodComment.addLine("which is: ");
        methodComment.addLine(" ");
        methodComment.addLine("  " + client_description);

        js.addExtends(Setup.class);
        // logger.info("Setup"+scenario_name+" has been created in "+dest_dir+Reader.createFolderPath(path));
        try {
            factory.write(new File(dest_dir));
        } catch (IOException e) {
            logger.severe("ERROR writing the setup of " + scenario_name);
            ;
        }
    }
}
