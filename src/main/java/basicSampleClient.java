import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;


import java.util.stream.Collectors;
import java.util.*;


public class basicSampleClient {

    public static void main(String[] theArgs) {

        // Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));

        // Search for Patient resources
        Bundle response = client
                .search()
                .forResource("Patient")
                .where(Patient.FAMILY.matches().value("SMITH"))
                .returnBundle(Bundle.class)
                .execute();

        // getData() gather the data from the url        
        List<Patient> patients = getData(response);
        printer(patients);
        
    }

    // getData() gather the data from the url  
    // uses function mapper here to turn bundle object to list of data
    public static List<Patient> getData(Bundle response) {
        List<Patient> patients = new ArrayList<>();
        patients = response.getEntry().stream().map(
                e -> ((Patient) e.getResource())).collect(Collectors.toList());
        return patients;
    }

    public static void printer(List<Patient> patients) {
        // pToSort is the patient data need to be sorted
        // I dup it later on as sortP, so the original data will not be modified
        List<ArrayList<String>> pToSort = new ArrayList<>();

        System.out.println("--------------------Before Sorting Start--------------------");
        System.out.printf("%-15s %-15s %-15s \n", "First Name", "Family Name", "Date Of Birth");
        for (Patient p : patients) {
            // get the full name list fist 
            HumanName pName = p.getName().get(0);
            // then divide into first name and last name
            String firstName = pName.getGiven().get(0).toString();
            String famName = pName.getFamily().toString();
            java.util.Date pDOB = p.getBirthDate();

            // turn data pDOB into string, makes sorting easier
            String pDOBs = String.valueOf(pDOB);

            // the array list temp stores the sets of patient's info
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(firstName);
            temp.add(famName);
            temp.add(pDOBs);

            // then the temp info is store as a list into the list pToSort
            // which is a nested list
            pToSort.add(temp);

            // Before sorting
            System.out.printf("%-15s %-15s %s \n", firstName, famName, pDOB);
        }
        System.out.println("--------------------Before Sorting Finish--------------------");

        // copy pToSort as sortP, then perform modification on here
        List<ArrayList<String>> sortP = new ArrayList<>(pToSort);
        // take advantage of custom Comparator
        sortP.sort((l1, l2) -> l1.get(0).compareTo(l2.get(0)));

        // After sorting
        System.out.println("--------------------After Sorting Start--------------------");
        System.out.printf("%-15s %-15s %-15s \n", "First Name", "Family Name", "Date Of Birth");
        for (ArrayList<String> s : sortP) {
            System.out.printf("%-15s %-15s %s \n", s.get(0), s.get(1), s.get(2));
        }
        System.out.println("--------------------After Sorting Finish--------------------");
    }
    
}