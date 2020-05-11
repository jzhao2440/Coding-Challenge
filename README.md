# Task 1
   Endpoint:
   GET:
   http://localhost:8080/reportingstructure/{id}
   
# Task 2
   Endpoints:
   Create
   POST:
   http://localhost:8080/compensation
   
   The JSON format for create could be like this for example:
   
   {
    "employee": {
         "employeeId": "c0c2293d-16bd-4603-8e08-638a9d18b22c",
         "firstName": "George",
         "lastName": "Harrison",
         "position": "Developer III",
         "department": "Engineering",
         "directReports": null
    },
    "salary": 100000.00,
    "effectiveDate": "2020-05-10"
   }
   
   Note: The employeeId has to really exist in the database to make the compensation creation work.


   Read
   GET:
   http://localhost:8080/compensation/{id}