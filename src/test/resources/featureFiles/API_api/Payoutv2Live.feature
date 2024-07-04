Feature:  Payout v2Live

  @API
  Scenario: Get Balance
    When Verify get balance

  @API
  Scenario: Add Bene ID
    When Verify Add beneficiary required fields
    When Verify BeneId Duplicate

  @API
  Scenario: Request IMPS
    When Verify Error Request IMPS
    When Request IMPS

  @API
  Scenario: Transfer Status
    When Verify transfer_reference id not exist
    When Verify transfer_reference id not null

  Scenario: Get Bene ID
    When Get Bene ID
    When Verify get Bene Id Error