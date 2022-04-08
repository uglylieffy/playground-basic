import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import ca.uhn.fhir.context.*;
import ca.uhn.fhir.rest.client.api.*;
import ca.uhn.fhir.rest.client.interceptor.*;

import java.io.*;
import java.util.*;


import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.*;

public class SampleClient {

    public static void main(String[] theArgs) {

        // Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));
        client.registerInterceptor(new MyClientInterceptor());

        // // Search for Patient resources
        // Bundle response = client
        //         .search()
        //         .forResource("Patient")
        //         .where(Patient.FAMILY.matches().value("SMITH"))
        //         .returnBundle(Bundle.class)
        //         .execute();
        // String fileName = "./src/main/java/lastName.txt";
        // String fileName = "test.txt";
        ReadFiles lastName = new ReadFiles();
        List<String> nameList = lastName.readFile();

        double responseAvgTime = avgTime(client);
        // print 1st time out of avgTime here

        for (int i = 0; i < 3; i++) {
            // I need to do something here to enable the cache and everything
            // really don't have time, my 3 assignments are due pretty soon TAT
            // kinda need to prioritize school here
            responseAvgTime = avgTime(client);

            // print response time here when I fixed the bug
        }
        
        

    }

    public static void searchName(List<String> nameList) {
        for (String name : nameList) {
            Bundle response = client
                    .search()
                    .forResource("Patient")
                    .where(Patient.FAMILY.matches().value(name))
                    .returnBundle(Bundle.class)
                    // supposely I need to inser something kind of timer here
                    // but which one?
                    // .requestStopWatch?
                    .execute();
        }
    }

    public static void avgTime(MyClientInterceptor client) {
        // I don't think this is how you're supposed to use it
        // But I dpn't have enough time on this anymore, and the FHIR
        // documentation doesn't really clearly indicate the usage
        // of the StopWatch Class

        client.requestStopWatch();
    }

}


