function showAllCalls() {
    console.log('Showing all calls.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('table-container').style.display = 'block';
    // Clear all filter inputs
    document.getElementById('callId').value = '';
    document.getElementById('callerId').value = '';
    document.getElementById('receiverId').value = '';
    // Fetch all calls
    fetchCalls();
}

function showByCallId() {
    console.log('Showing calls by Call ID.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'block';
    document.getElementById('cId').style.display = 'block';
    document.getElementById('crId').style.display = 'none';
    document.getElementById('rsId').style.display = 'none';
    // Set filters for Call ID
    document.getElementById('callId').style.display = 'block';
    document.getElementById('callerId').style.display = 'none';
    document.getElementById('receiverId').style.display = 'none';
    document.getElementById('callId').focus();
    // Clear other filters
    document.getElementById('callerId').value = '';
    document.getElementById('receiverId').value = '';
    // Fetch calls based on Call ID
    fetchCalls();
}

function showByCallerId() {
    console.log('Showing calls by Caller ID.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'block';
    document.getElementById('crId').style.display = 'block';
    document.getElementById('cId').style.display = 'none';
    document.getElementById('rsId').style.display = 'none';
    // Set filters for Caller ID
    document.getElementById('callId').style.display = 'none';
    document.getElementById('callerId').style.display = 'block';
    document.getElementById('receiverId').style.display = 'none';
    document.getElementById('callerId').focus();
    // Clear other filters
    document.getElementById('callId').value = '';
    document.getElementById('receiverId').value = '';
    // Fetch calls based on Caller ID
    fetchCalls();
}

function showByReceiverId() {
    console.log('Showing calls by Receiver ID.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'block';
    document.getElementById('rsId').style.display = 'block';
    document.getElementById('crId').style.display = 'none';
    document.getElementById('cId').style.display = 'none';
    // Set filters for Receiver ID
    document.getElementById('callId').style.display = 'none';
    document.getElementById('callerId').style.display = 'none';
    document.getElementById('receiverId').style.display = 'block';
    document.getElementById('receiverId').focus();
    // Clear other filters
    document.getElementById('callId').value = '';
    document.getElementById('callerId').value = '';
    // Fetch calls based on Receiver ID
    fetchCalls();
}

function fetchCalls() {
    const callId = document.getElementById('callId').value.trim();
    const callerId = document.getElementById('callerId').value.trim();
    const receiverId = document.getElementById('receiverId').value.trim();

    console.log('Fetching calls with parameters:', { callId, callerId, receiverId });

    let url = 'http://localhost:1000/calldetails';

    if (callId) {
        url += `/${callId}`;
        console.log('Fetching by Call ID:', url);
    } else if (callerId) {
        url = `http://localhost:1000/calldetails/caller/${callerId}`;
        console.log('Fetching by Caller ID:', url);
    } else if (receiverId) {
        url = `http://localhost:1000/calldetails/receiver/${receiverId}`;
        console.log('Fetching by Receiver ID:', url);
    } else {
        console.log('Fetching all calls.');
    }

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Data fetched:', data);

            const tableBody = document.getElementById('callTableBody');
            tableBody.innerHTML = '';

            // Handle cases for both arrays and single objects
            const calls = Array.isArray(data) ? data : [data];

            if (calls.length > 0) {
                calls.forEach(call => {
                    console.log('Inserting call into table:', call);

                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${call.callId}</td>
                        <td>${call.callerId}</td>
                        <td>${call.receiverId}</td>
                        <td>${new Date(call.callStartTime).toLocaleString()}</td>
                        <td>${new Date(call.callEndTime).toLocaleString()}</td>
                        <td>${call.callDuration} mins</td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                console.log('No data found to display.');

                const row = document.createElement('tr');
                row.innerHTML = '<td colspan="6">No call details found</td>';
                tableBody.appendChild(row);
            }
        })
        .catch(error => console.error('Error fetching call details:', error));
}
