package StepDefs.Fnl_Pg;

import StepDefs.BaseStepDef;
import io.cucumber.java.en.When;

public class Payoutv2LiveTransactionStepDef extends BaseStepDef {


    @When("Verify get balance")
    public void verifyGetBalance() {
        payoutv2live.getToken();
        payoutv2live.getBalance();
    }

    @When("Verify Add beneficiary required fields")
    public void verifyAddBeneficiaryRequiredFields() {
        payoutv2live.getToken();
        payoutv2live.addBeneficiaryVerifyRequired();
    }

    @When("Verify BeneId Duplicate")
    public void verifyBeneIdDuplicate() {
        payoutv2live.getToken();
        payoutv2live.verifyBeneIdDuplicate();
    }

    @When("Request IMPS")
    public void requestIMPS() {
        payoutv2live.getToken();
        payoutv2live.requestImps();
        payoutv2live.getTransferStatus();
    }

    @When("Get Bene ID")
    public void getBeneID() {
    }

    @When("Get Transfer status")
    public void getTransferStatus() {
    }

    @When("Verify Error Request IMPS")
    public void verifyErrorRequestIMPS() {
        payoutv2live.getToken();
        payoutv2live.verifyPayidNotNUll();
        payoutv2live.withoutToken();
        payoutv2live.verifyRequiredFileds();
    }


    @When("Verify transfer_reference id not exist")
    public void verifyTransferRefrenceIdNotExist() {
        payoutv2live.verifyTransferIdNotExist();
    }

    @When("Verify transfer_reference id not null")
    public void verifyTransferRefrenceIdNotNull() {
        payoutv2live.refrenceTransferIdcantBeNull();
    }


    @When("Verify get Bene Id Error")
    public void verifyGetBeneIdError() {
    }
}
