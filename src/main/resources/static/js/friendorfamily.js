function showByPhoneNumber() {
    console.log('Showing contact by phone number.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('form-container').style.display = 'none';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('contactNumber').style.display = 'block';
    document.getElementById('contactNumberLabel').style.display = 'block';
    document.getElementById('cid').style.display = 'none';
    document.getElementById('cidLabel').style.display = 'none';
    document.getElementById('contactNumber').focus();
    console.log('Contact Number input is now visible and focused.');
     // Fetch contacts to populate the table
}


function showById() {
    console.log('Showing contact by ID.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('form-container').style.display = 'none';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('contactNumber').style.display = 'none';
    document.getElementById('contactNumberLabel').style.display = 'none';
    document.getElementById('cidLabel').style.display = 'block';
    document.getElementById('cid').style.display = 'block'; // Show Customer ID input
    document.getElementById('cid').focus();
    console.log('Customer ID input is now visible and focused.');
}

function showCreateForm() {
    console.log('Displaying form to add a contact.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('form-container').style.display = 'block';
    document.getElementById('contactForm').reset();
    document.getElementById('formType').value = 'create';
    document.getElementById('create-update-fields').style.display = 'block';
    document.getElementById('delete-field').style.display = 'none'; // Hide delete field
    console.log('Form type set to "create" and all fields visible.');
}

function showUpdateForm() {
    console.log('Displaying form to update a contact.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('form-container').style.display = 'block';
    document.getElementById('customerId').style.display = 'none';
    document.getElementById('cusIdLabel').style.display = 'none';
    document.getElementById('contactForm').reset();
    document.getElementById('formType').value = 'update';
    document.getElementById('create-update-fields').style.display = 'block';
    document.getElementById('delete-field').style.display = 'none'; // Hide delete field
    console.log('Form type set to "update" and all fields visible.');
}

function showDeleteForm() {
    console.log('Displaying form to delete a contact.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('form-container').style.display = 'block';
    document.getElementById('contactForm').reset();
    document.getElementById('formType').value = 'delete';
    document.getElementById('create-update-fields').style.display = 'none'; // Hide create/update fields
    document.getElementById('delete-field').style.display = 'block'; // Show delete field
    console.log('Form type set to "delete" and delete field visible.');
}

function fetchContacts() {
    const contactNumber = document.getElementById('contactNumber').value.trim();
    const customerId = document.getElementById('cid').value.trim();
    document.getElementById('table-container').style.display = 'block';
    let url = '';

    // Determine URL based on input
    if (contactNumber && !customerId) {
        url = `http://localhost:1000/friendorfamily/${contactNumber}`;
        console.log(`Fetching contact with Phone Number ${contactNumber}`);
    } else if (customerId) {
        url = `http://localhost:1000/friendorfamily/customer/${customerId}`;
        console.log(`Fetching contacts for Customer ID ${customerId}`);
    } else {
        console.error('No contact number or customer ID provided.');
        return;
    }

    // Fetch contacts from server
    fetch(url)
        .then(response => {
            if (!response.ok) {
                console.error('Network response was not ok:', response.statusText);
                throw new Error('Network response was not ok.');
            }
            return response.json();
        })
        .then(data => {
            console.log('Data received:', data);

            // Clear previous results
            const tableBody = document.getElementById('contactTableBody');
            tableBody.innerHTML = '';

            // Ensure data is treated as an array
            const contacts = Array.isArray(data) ? data : [data];

            if (contacts.length > 0) {
                contacts.forEach(contact => {
                    const row = document.createElement('tr');
                    row.innerHTML = 
                        `<td>${contact.contactNumber}</td>
                         <td>${contact.contactName}</td>
                         <td>${contact.relationshipType}</td>`;
                    tableBody.appendChild(row);
                });
            } else {
                const row = document.createElement('tr');
                row.innerHTML = '<td colspan="3">No contacts found</td>';
                tableBody.appendChild(row);
            }
        })
        .catch(error => console.error('Error fetching contact details:', error));
}



document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formType = document.getElementById('formType').value;
    const customerId = document.getElementById('customerId').value.trim();
    const contactNumber = document.getElementById('contactNumberInput').value.trim();
    const deleteContactNumber = document.getElementById('deleteContactNumber').value.trim();
    const contactData = {
        contactName: document.getElementById('contactName').value.trim(),
        contactNumber: document.getElementById('contactNumberInput').value.trim(),
        relationshipType: document.getElementById('relationshipType').value.trim()
    };

    console.log(`Form Type: ${formType}`);
    console.log(`Contact Number: ${contactNumber}`);
    console.log(`Delete Contact Number: ${deleteContactNumber}`);
    console.log('Contact Data:', contactData);

    let method, url;

    switch (formType) {
        case 'create':
            method = 'POST';
            url = 'http://localhost:1000/friendorfamily';
            break;
        case 'update':
            method = 'PUT';
            url = `http://localhost:1000/friendorfamily/${contactNumber}`;
            break;
        case 'delete':
            method = 'DELETE';
            url = `http://localhost:1000/friendorfamily/${deleteContactNumber}`;
            break;
        default:
            alert('Invalid form type');
            return;
    }

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: method !== 'DELETE' ? JSON.stringify(contactData) : undefined
    })
    .then(response => {
        if (!response.ok) {
            console.error('Network response was not ok:', response.statusText);
            throw new Error('Network response was not ok.');
        }
        alert(`Contact ${formType}d successfully!`);
         // Refresh the contact list
    })
    .catch(error => console.error(`Error ${formType}ing contact:`, error));
});
