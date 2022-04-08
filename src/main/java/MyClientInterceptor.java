import java.io.IOException;

import org.hl7.fhir.r4.*;
import ca.uhn.fhir.context.*;
import ca.uhn.fhir.rest.client.api.*;
import ca.uhn.fhir.rest.client.interceptor.*;


public class MyClientInterceptor implements IClientInterceptor{

    @Override
    public void interceptRequest(IHttpRequest theRequest) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void interceptResponse(IHttpResponse theResponse) throws IOException {
        // TODO Auto-generated method stub
        
    }

}