Feature: Creating and submitting a new request

  Scenario: Step-1 User creates and submits a new request
    Given the user is on the login page
    When the user logs in with valid credentials
    And the user clicks on "New Document Request"
    And the user fills in the document request details
    And the user uploads medical, billing, and X-ray documents
    And the user accepts the terms and conditions
#    Then the new request should be submitted successfully

  
  Scenario: Step-2 Login to the application
    Given The user launches the browser and navigates to the Yellow URL
    When The user enters valid credentials "roverbo99@yopmail.com" and "Test@123"
    Then The user should be logged in successfully
 # Scenario: Search and update a record
 #   Given The user is logged in
    When The user searches for the record "LZI-645"
    And Selects the record
#   And Updates the internal status to "Approved"
#   Then The order details should be updated successfully

#  Scenario: Step-2 Upload a file
    Given The user navigates to the "Files" tab
    When The user uploads the file "C:\\Users\\admin\\Desktop\\TestFileupload.pdf"
    Then The file should be uploaded successfully

#  Scenario: Step-3 Generate and verify the PDF
#    Given The file is uploaded
    When The user clicks on "Generate PDF"
    And Refreshes the page
    Then The final record should be displayed

#  Scenario: Send the invoice via email
    Given The user navigates to the "Invoice" tab
    When Sends the email
    And confirms the email action
    Then The email should be sent successfully
    And The status "Pending Payment" should be displayed