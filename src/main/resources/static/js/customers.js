//customers details
function showCreateForm() {
    document.getElementById('create-form').style.display = 'block';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('read-section').style.display = 'none';
}

function showReadSection() {
    document.getElementById('create-form').style.display = 'none';
    document.getElementById('table-container').style.display = 'block';
    document.getElementById('read-section').style.display = 'block';
}

function submitForm(event) {
    event.preventDefault(); // Prevent form from submitting the traditional way

    // Get form data
    const formData = new FormData(document.getElementById('customerForm'));

    // Convert FormData to a plain object
    const customerData = {};
    formData.forEach((value, key) => {
        customerData[key] = value;
    });

    // For now, just log the data to the console
    console.log('Customer Data:', customerData);

    // Hide the form after submission
    document.getElementById('create-form').style.display = 'none';

    // Optionally, you could clear the form fields here
    document.getElementById('customerForm').reset();
}

function fetchCustomers() {
    const customerId = document.getElementById('customerId').value.trim();

    

    // Mock data for demonstration purposes
    const mockData = [
        { id: 1, fname: 'John', lname: 'Doe', email: 'john.doe@example.com', phone: '123-456-7890', address: '123 Elm Street', plan: 'basic' },
        { id: 2, fname: 'Jane', lname: 'Smith', email: 'jane.smith@example.com', phone: '098-765-4321', address: '456 Oak Avenue', plan: 'premium' }
    ];

    let filteredData = mockData;
    if (customerId) {
        filteredData = mockData.filter(customer => customer.id == customerId);
    }

    const tableBody = document.getElementById('customerTableBody');
    tableBody.innerHTML = '';

    if (filteredData.length > 0) {
        filteredData.forEach(customer => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${customer.id}</td>
                <td>${customer.fname}</td>
                <td>${customer.lname}</td>
                <td>${customer.email}</td>
                <td>${customer.phone}</td>
                <td>${customer.address}</td>
                <td>${customer.plan}</td>
            `;
            tableBody.appendChild(row);
        });
    } else {
        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="7">No customers found</td>';
        tableBody.appendChild(row);
    }
}

function updateCustomer() {
    alert('Update Customer button clicked');
}

function deleteCustomer() {
    alert('Delete Customer button clicked');
}
