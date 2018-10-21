This service is used to do product booking by sellers from a company and do payments for the products received.

Below are the steps to follow:
1. Check out the project, import it into eclipse and run the project from eclipse.
2. Use the URL http://localhost:8080/swagger-ui.html to browse all the api supported for this project.
3. We need to upload metadata to proceed. First we need to add below details in sequence:</br>
    a. Add Seller Information</br>
    b. Add Product Information</br>
    c. Add Stock Information</br>
    d. Book an order for the product.</br>
    e. Do payment for the orders recieved.</br>
    f. Get products to be repenished.</br>
    g. Get no of products booked by a seller in a year.</br>
    f. Send notifications to company manager after doing payments, pending payments and products to be repenished.</br>
    
After adding seller information, product information and stock information a booking can be done for a product of some stock by a seller. While booking an order, we have a parameter as booking type where 1 indicates book only if stock is available for the product and 2 indicates book even if stock is not available for the product and deliver it once available.

After booking is done and is delivered, payment is done for each booking.
