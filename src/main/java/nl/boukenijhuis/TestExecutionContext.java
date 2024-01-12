package nl.boukenijhuis;

import com.microsoft.azure.functions.ExecutionContext;

import java.util.logging.Logger;

public class TestExecutionContext implements ExecutionContext {

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public String getInvocationId() {
        return null;
    }

    @Override
    public String getFunctionName() {
        return null;
    }

}
