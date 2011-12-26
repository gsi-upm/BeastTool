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

import es.upm.dit.gsi.beast.story.testCase.Scenario;

/**
 * Main class to create the setup of each Test. The evaluation represents the
 * GIVEN part of our BDD Test.
 * 
 * @author Jorge Solitario
 */
public class CreateScenario {

    /**
     * Method to create the scenario of each test. IT does not check if the file
     * is already created, overwriting it.
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
    public static void createScenario(String scenario_name, String path,
            String client_description, String dest_dir) {

        Logger logger = Logger.getLogger("CreateScenario.java");

        JavaSourceFactory factory = new JavaSourceFactory();

        JavaQName className = JavaQNameImpl.getInstance(path, "Scenario");
        JavaSource js = factory.newJavaSource(className, "public");
        js.addExtends(Scenario.class);
        // js.addImport(SFipa.class);

        js.newComment();
        JavaComment classComment = js.getComment();
        classComment.addLine(" ");
        classComment
                .addLine("This is the class that must create the Scenario.");
        classComment.addLine("It is related with the GIVEN part.");
        classComment.addLine(" ");
        classComment
                .addLine("In startAgents method the following method must be used");
        classComment.addLine("   super.startAgent(agent_name,agent_path)");
        classComment.addAuthor("es.upm.dit.gsi.beast");

        JavaMethod jm = js.newJavaMethod("startAgents", "void", "public");
        jm.addLine("// TODO implement this method to represent the @Given part of the test in Java code.");
        jm.addLine("logger.warning(\"Implement startAgents() method in "
                + path
                + ".Scenario"
                + scenario_name
                + ".java -> Auto-generated stub by Beast -> es.upm.dit.gsi.beast-tool\");");
        jm.addLine("\n");
        jm.addLine("   //EXAMPLE for Jadex: startAgent(\"Steve\", \"org.example.Steve.agent.xml\"); // This xml file is the jadex agent description file (ADF)");
        jm.addLine("   //EXAMPLE for Jade: startAgent(\"Steve\", \"org.example.Steve\"); // This string is the agent class Steve.java that extends Jade Agent class");
     
        jm.newComment();
        JavaComment methodComment = jm.getComment();
        methodComment.addLine(" ");
        methodComment
                .addLine("Here the description given by the client must be written,");
        methodComment.addLine("which is: ");
        methodComment.addLine(" ");
        methodComment.addLine("  " + client_description);

        // logger.fine("Scenario"+scenario_name+" has been created in "+dest_dir+Reader.createFolderPath(package_path));
        try {
            factory.write(new File(dest_dir));
        } catch (IOException e) {
            logger.severe("ERROR writing the scenario of " + scenario_name);
        }
    }
}
