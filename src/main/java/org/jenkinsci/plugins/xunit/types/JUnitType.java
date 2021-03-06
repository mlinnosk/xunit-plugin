package org.jenkinsci.plugins.xunit.types;

import com.thalesgroup.dtkit.metrics.hudson.api.descriptor.TestTypeDescriptor;
import com.thalesgroup.dtkit.metrics.hudson.api.type.TestType;
import com.thalesgroup.dtkit.metrics.model.InputMetric;
import com.thalesgroup.dtkit.metrics.model.InputMetricException;
import com.thalesgroup.dtkit.metrics.model.InputMetricFactory;
import hudson.Extension;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * @author Gregory Boissinot
 */
public class JUnitType extends TestType {

    @DataBoundConstructor
    public JUnitType(String pattern, boolean failureIfNotNew, boolean deleteOutputFiles, boolean stopProcessingIfError) {
        super(pattern, failureIfNotNew, deleteOutputFiles, stopProcessingIfError);
    }

    @Extension
    public static class JUnitInputMetricDescriptor extends TestTypeDescriptor<JUnitType> {

        public JUnitInputMetricDescriptor() {
            super(JUnitType.class, null);
        }

        @Override
        public String getId() {
            return this.getClass().getName();
        }

        @Override
        public InputMetric getInputMetric() {
            try {
                return InputMetricFactory.getInstance(JUnitInputMetric.class);
            } catch (InputMetricException e) {
                throw new RuntimeException("Can't create the inputMetric object for the class " + JUnitInputMetric.class);
            }
        }
    }
}
