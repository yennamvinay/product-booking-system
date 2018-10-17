This service is used to do product booking by sellers from a company. The operations included are:
1. Manage Sellers: This would Add Seller Information, Update Seller Information , Delete Seller Information, Get All Seller Information and Get Seller Information.
2. Manage Products
3. Manage Stock Information.
4. Manage Bookings.
5. Manage payments.
6. Get stats 

Sellers API:
Add a new Seller
URL: http://localhost:8080/seller/create
HTTP Method: POST
Body:
{
  "name":"Seller 5",
  "address":"Address 5"
}

Update a Seller
URL: http://localhost:8080/seller/update/5
HTTP Method: POST
Body:
{
  "name":"Seller 5",
  "address":"Update Address 5"
}

Delete a Seller
URL: http://localhost:8080/seller/5
HTTP Method: DELETE

Get Seller Information by ID:
URL: http://localhost:8080/seller/4
HTTP Method: GET
Response Body:
{
    "id": 4,
    "name": "Seller 4",
    "address": "Address 4"
}

Get All Seller Information by ID:
URL: http://localhost:8080/seller/all
HTTP Method: GET
Response Body:
[
    {
        "id": 1,
        "name": "Seller 1",
        "address": "Address1"
    },
    {
        "id": 2,
        "name": "Seller 2",
        "address": "Address 2"
    },
    {
        "id": 3,
        "name": "Seller 3",
        "address": "Address 3"
    },
    {
        "id": 4,
        "name": "Seller 4",
        "address": "Address 4"
    }
]
