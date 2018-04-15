package org.yajac.reddit;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseTestClass {

    public Context getContext() {
        LambdaLogger logger = mock(LambdaLogger.class);
        Context contxt = mock(Context.class);
        when(contxt.getLogger()).thenReturn(logger);
        return contxt;
    }
}
