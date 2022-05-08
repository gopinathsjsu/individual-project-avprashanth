package com.cmpe202.individual.response;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PrintError implements  BillingHandler{

    private BillingHandler next;

    private static String ERROR_FILE = "Error.txt";

    @Override
    public void setBillingHandler(BillingHandler nextBillingHandler) {
        this.next = nextBillingHandler;
    }

    @Override
    public void validateStock() throws IOException {
        List<String> errorMessages = ValidateOrder.errorMessages;
        FileWriter writeError = new FileWriter(ERROR_FILE);
        for(String errorMessage : errorMessages) {
            writeToFile(errorMessage, writeError);
        }
        writeError.close();
        System.out.println("Cannot proceed to the payment. Please check Error.txt for more details");
    }

    private void writeToFile(String errorMessage, FileWriter writeError) throws IOException {
        writeError.write(errorMessage);
        writeError.write("\n");
    }

}
