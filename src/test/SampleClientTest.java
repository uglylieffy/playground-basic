import org.hl7.fhir.r4.*;
import ca.uhn.fhir.context.*;
import ca.uhn.fhir.rest.client.api.*;
import ca.uhn.fhir.rest.client.interceptor.*;
import ch.qos.logback.core.net.server.Client;

// not so perfect test sample as I can't really test the intermediate task
// right at this moment
public class SampleClientTest {
    public void printTest() {
        try {
			basicSampleClient.getData(response).printer();
		} catch (IOException e) {
			System.out.println("An error occurred.");
            e.printStackTrace();
		}
    }

    public void getAveTime() {
        SampleClient.avgTime(client);
        try {
			SampleClient.avgTime(client);
		} catch (IOException e) {
			System.out.println("An error occurred.");
            e.printStackTrace();
		}
    }

}
