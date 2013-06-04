package org.fcrepo.responses;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;

/**
 * Resolves the view to be used
 * @author Vincent Nguyen
 *
 */
public class VelocityViewer {

    private VelocityEngine velocityEngine;

    private static final Logger logger = getLogger(VelocityViewer.class);

    public VelocityViewer() {
        try {
            // Load the velocity properties from the class path
            final Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));

            // Create and initialize the template engine
            velocityEngine = new VelocityEngine(properties);
        } catch (final Exception e) {
            logger.warn("Exception rendering Velocity template: {}", e);
        }
    }

    public String getSearchForm() {

        try {
            // Build a context to hold the model
            final VelocityContext velocityContext = new VelocityContext();

            // Execute the template
            final StringWriter writer = new StringWriter();
            velocityEngine.mergeTemplate("views/search.vsl",
                                                "utf-8", velocityContext, writer);

            // Return the result
            return writer.toString();
        } catch (final Exception e) {
            logger.warn("Exception rendering Velocity template: {}", e);
        }
        return null;
    }


}