package com.cmpe202.individual.response;

import java.io.IOException;

public interface BillingHandler {
    public void setBillingHandler(BillingHandler billingHandler );
    public void validateStock() throws IOException;
}
