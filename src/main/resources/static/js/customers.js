function showAllCustomers() {
    console.log('Showing all customers.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('form-container').style.display = 'none';
    document.getElementById('table-container').style.display = 'block';
    console.log('Fetching all customers from server...');
    fetchCustomers();
}

function showCustomerById() {
    console.log('Showing customer by ID.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('form-container').style.display = 'none';
    document.getElementById('table-container').style.display = 'block';
    document.getElementById('customerId').focus();
    console.log('Customer ID input is now visible and focused.');
}

function showCreateForm() {
    console.log('Displaying form to create a customer.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('form-container').style.display = 'block';
    document.getElementById('customerForm').reset();
    document.getElementById('formType').value = 'create';
    document.getElementById('create-update-fields').style.display = 'block';
    document.getElementById('delete-field').style.display = 'none'; // Hide delete field
    console.log('Form type set to "create" and all fields visible.');
}

function showUpdateForm() {
    console.log('Displaying form to update a customer.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('form-container').style.display = 'block';
    document.getElementById('customerForm').reset();
    document.getElementById('formType').value = 'update';
    document.getElementById('create-update-fields').style.display = 'block';
    document.getElementById('delete-field').style.display = 'none'; // Hide delete field
    console.log('Form type set to "update" and all fields visible.');
}

function showDeleteForm() {
    console.log('Displaying form to delete a customer.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('form-container').style.display = 'block';
    document.getElementById('customerForm').reset();
    document.getElementById('formType').value = 'delete';
    document.getElementById('create-update-fields').style.display = 'none'; // Hide create/update fields
    document.getElementById('delete-field').style.display = 'block'; // Show delete field
    console.log('Form type set to "delete" and delete field visible.');
}

function fetchCustomers() {
    const customerId = document.getElementById('customerId').value.trim();
    let url = 'http://localhost:1000/customers';

    if (customerId) {
        url += `/${customerId}`;
        console.log(`Fetching customer with ID ${customerId}`);
    } else {
        console.log('Fetching all customers.');
    }

    fetch(url)
        .then(response => {
            if (!response.ok) {
                console.error('Network response was not ok:', response.statusText);
                throw new Error('Network response was not ok.');
            }
            console.log('Network response was ok.');
            return response.json();
        })
        .then(data => {
            console.log('Data received:', data);
            const tableBody = document.getElementById('customerTableBody');
            tableBody.innerHTML = '';

            const customers = Array.isArray(data) ? data : [data];

            if (customers.length > 0) {
                customers.forEach(customer => {
                    const row = document.createElement('tr');
                    row.innerHTML = 
                        `<td>${customer.customerId}</td>
                         <td>${customer.firstName}</td>
                         <td>${customer.lastName}</td>
                         <td>${customer.phoneNumber}</td>
                         <td>${customer.email}</td>
                         <td>${customer.address}</td>`;
                    tableBody.appendChild(row);
                });
            } else {
                const row = document.createElement('tr');
                row.innerHTML = '<td colspan="6">No customers found</td>';
                tableBody.appendChild(row);
            }
        })
        .catch(error => console.error('Error fetching customer details:', error));
}

document.getElementById('customerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formType = document.getElementById('formType').value;
    const cid = document.getElementById('cid').value.trim();
    const deleteCid = document.getElementById('deleteCid').value.trim();
    const customerData = {
        firstName: document.getElementById('firstName').value.trim(),
        lastName: document.getElementById('lastName').value.trim(),
        phoneNumber: document.getElementById('phoneNumber').value.trim(),
        email: document.getElementById('email').value.trim(),
        address: document.getElementById('address').value.trim()
    };

    console.log(`Form Type: ${formType}`);
    console.log(`Customer ID: ${cid}`);
    console.log(`Delete Customer ID: ${deleteCid}`);
    console.log('Customer Data:', customerData);

    switch (formType) {
        case 'create':
            console.log('Sending POST request to create customer...');
            fetch('http://localhost:1000/customers', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(customerData)
            })
            .then(response => {
                if (!response.ok) {
                    console.error('Network response was not ok:', response.statusText);
                    throw new Error('Network response was not ok.');
                }
                console.log('Customer created successfully.');
                alert('Customer created successfully!');
                showAllCustomers(); // Refresh the customer list
            })
            .catch(error => console.error('Error creating customer:', error));
            break;

        case 'update':
            if (!cid) {
                alert('Please enter the customer ID.');
                return;
            }
            console.log('Sending PUT request to update customer...');
            fetch(`http://localhost:1000/customers/${cid}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(customerData)
            })
            .then(response => {
                if (!response.ok) {
                    console.error('Network response was not ok:', response.statusText);
                    throw new Error('Network response was not ok.');
                }
                console.log('Customer updated successfully.');
                alert('Customer updated successfully!');
                showAllCustomers(); // Refresh the customer list
            })
            .catch(error => console.error('Error updating customer:', error));
            break;

        case 'delete':
            if (!deleteCid) {
                alert('Please enter the customer ID.');
                return;
            }
            console.log('Sending DELETE request to delete customer...');
            fetch(`http://localhost:1000/customers/${deleteCid}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (!response.ok) {
                    console.error('Network response was not ok:', response.statusText);
                    throw new Error('Network response was not ok.');
                }
                console.log('Customer deleted successfully.');
                alert('Customer deleted successfully!');
                showAllCustomers(); // Refresh the customer list
            })
            .catch(error => console.error('Error deleting customer:', error));
            break;
    }
});
