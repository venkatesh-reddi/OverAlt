// Function to show contact details by contact number
let showByC =0;
let showById =0;
let add=0;
let up=0;
let del=0;


function showContactByNumber() {
    showByC=1;
    console.log('Executing showContactByNumber function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'block';
    document.getElementById('getbycontact').style.display = 'block';
    document.getElementById('getbyid').style.display = 'none';
    document.getElementById('submit').style.display = 'none';

    // Show only Contact Number filter
    console.log('Showing Contact Number filter.');
    document.getElementById('cNumber').style.display = 'block';
    document.getElementById('contactNumber').style.display = 'block';
    document.getElementById('contactId').style.display = 'none';
    document.getElementById('contactName').style.display = 'none';
    document.getElementById('relationship').style.display = 'none';
    document.getElementById('contactNumber').focus();
}

// Function to show contact details by contact ID
function showContactById() {
    showById=1;
    console.log('Executing showContactById function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'block';
    document.getElementById('getbyid').style.display = 'block';
    document.getElementById('getbycontact').style.display = 'none';
    document.getElementById('submit').style.display = 'none';

    // Show only Contact ID filter
    console.log('Showing Contact ID filter.');
    document.getElementById('cId').style.display = 'block';
    document.getElementById('contactId').style.display = 'block';
    document.getElementById('contactNumber').style.display = 'none';
    document.getElementById('contactName').style.display = 'none';
    document.getElementById('relationship').style.display = 'none';
    document.getElementById('contactId').focus();
}

// Function to add a new contact
function addContact() {
    add=1;
    console.log('Executing addContact function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('submit').style.display = 'block';
    document.getElementById('getbycontact').style.display = 'none';
    document.getElementById('getbyid').style.display = 'none';

    // Show form fields for adding a new contact
    console.log('Displaying form fields for adding a new contact.');
    document.getElementById('contactNumber').style.display = 'block';
    document.getElementById('contactId').style.display = 'none';
    document.getElementById('contactName').style.display = 'block';
    document.getElementById('relationship').style.display = 'block';
}

// Function to update an existing contact
function updateContact() {
    up=1;
    console.log('Executing updateContact function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('submit').style.display = 'block';
    document.getElementById('getbycontact').style.display = 'none';
    document.getElementById('getbyid').style.display = 'none';

    // Show form fields for updating an existing contact
    console.log('Displaying form fields for updating contact.');
    document.getElementById('contactNumber').style.display = 'block';
    document.getElementById('contactId').style.display = 'block';
    document.getElementById('contactName').style.display = 'block';
    document.getElementById('relationship').style.display = 'block';
}

// Function to delete a contact
function deleteContact() {
    del=1;
    console.log('Executing deleteContact function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('submit').style.display = 'block';
    document.getElementById('getbycontact').style.display = 'none';
    document.getElementById('getbyid').style.display = 'none';

    // Show only Contact Number or ID filter for deletion
    console.log('Displaying Contact Number or ID filter for deletion.');
    document.getElementById('contactNumber').style.display = 'block';
    document.getElementById('contactId').style.display = 'block';
    document.getElementById('contactName').style.display = 'none';
    document.getElementById('relationship').style.display = 'none';
}

// Function to perform actions based on the current form state
function performAction() {
    console.log('Executing performAction function.');

    const contactNumber = document.getElementById('contactNumber').value.trim();
    const contactId = document.getElementById('contactId').value.trim();
    const contactName = document.getElementById('contactName').value.trim();
    const relationship = document.getElementById('relationship').value.trim();

    console.log('Form values:', {
        contactNumber,
        contactId,
        contactName,
        relationship
    });

    if (add==1 && contactNumber && document.getElementById('contactName').style.display === 'block') {
        // Add Contact
        console.log('Adding new contact.');
        fetch('http://localhost:1000/friendorfamily', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ contactNumber, contactName, relationship })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Contact added:', data);
            document.getElementById('table-container').style.display = 'block';
            fetchContacts(); // Refresh the list of contacts
        })
        .catch(error => console.error('Error adding contact:', error));
    } else if (up==1 && contactId && document.getElementById('contactName').style.display === 'block') {
        // Update Contact
        console.log('Updating contact.');
        fetch(`http://localhost:1000/friendorfamily/${contactId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ contactNumber, contactName, relationship })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Contact updated:', data);
            document.getElementById('table-container').style.display = 'block';
            fetchContacts(); // Refresh the list of contacts
        })
        .catch(error => console.error('Error updating contact:', error));
    } else if (del==1 && contactNumber && !contactId) {
        // Delete Contact
        console.log('Deleting contact.');
        fetch(`http://localhost:1000/friendorfamily/${contactNumber}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            console.log('Contact deleted.');
            document.getElementById('table-container').style.display = 'block';
            fetchContacts(); // Refresh the list of contacts
        })
        .catch(error => console.error('Error deleting contact:', error));
    } else if (showByC==1 && contactNumber) {
        // Show Contact by Number
        console.log('Showing contact by number.');
        fetch(`http://localhost:1000/friendorfamily/${contactNumber}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            displayContacts(data);
        })
        .catch(error => console.error('Error fetching contact by number:', error));
    } else if (showById==1 && contactId) {
        // Show Contact by ID
        console.log('Showing contact by ID.');
        fetch(`http://localhost:1000/friendorfamily/customer/${contactId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            displayContacts(data);
        })
        .catch(error => console.error('Error fetching contact by ID:', error));
    } else {
        console.log('Invalid form state.');
    }
}

// Function to display contacts in the table
function displayContacts(contact) {
    console.log('Executing displayContact function.');

    const tableBody = document.getElementById('contactTableBody');
    tableBody.innerHTML = '';

    // Ensure contact is an object
    if (contact && typeof contact === 'object') {
        console.log('Inserting contact into table:', contact);

        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${contact.customer}</td>
            <td>${contact.contactNumber}</td>
            <td>${contact.contactName}</td>
            <td>${contact.relationshipType}</td>
        `;
        tableBody.appendChild(row);
    } else {
        console.log('No data found to display.');

        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="4">No contact details found</td>';
        tableBody.appendChild(row);
    }
}



// Function to fetch and display all contacts (used to refresh table)
function fetchContacts() {
    console.log('Fetching all contacts.');
    fetch('http://localhost:1000/friendorfamily')
    .then(response => {
        if (!response.ok) {
            throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        displayContacts(data);
    })
    .catch(error => console.error('Error fetching contacts:', error));
}


