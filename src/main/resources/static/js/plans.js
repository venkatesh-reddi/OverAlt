// Global variables to track the current action
let cp = 0; // Create Plan
let up = 0; // Update Plan
let dp = 0; // Delete Plan

// Function to show all plans
function showAllPlans() {
    console.log('Executing showAllPlans function.');
    document.getElementById('filter-section').style.display = 'none';
    document.getElementById('table-container').style.display = 'block';
    console.log('Fetching all plans.');
    fetchPlans(); // Fetch all plans
}

// Function to show plan by ID
function showPlanById() {
    console.log('Executing showPlanById function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'block';
    document.getElementById('getbyid').style.display = 'block';
    document.getElementById('submit').style.display = 'none';

    // Show only Plan ID filter
    console.log('Showing Plan ID filter.');
    document.getElementById('pId').style.display = 'block';
    document.getElementById('planId').style.display = 'block';
    document.getElementById('planName').style.display = 'none';
    document.getElementById('monthlyCost').style.display = 'none';
    document.getElementById('dataLimit').style.display = 'none';
    document.getElementById('callMinutes').style.display = 'none';
    document.getElementById('maxFamilyMembers').style.display = 'none';
    document.getElementById('maxFriends').style.display = 'none';
    document.getElementById('planId').focus();

    // Fetch the plan details by ID
    console.log('Fetching plan by ID.');
    fetchPlanById();
}

// Function to create a new plan
function createPlan() {
    location.reload;
    cp = 1;
    console.log('Executing createPlan function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('submit').style.display = 'block';
    document.getElementById('getbyid').style.display = 'none';

    // Show form fields for creating a new plan
    console.log('Displaying form fields for new plan creation.');
    document.getElementById('planId').style.display = 'block';
    document.getElementById('planName').style.display = 'block';
    document.getElementById('monthlyCost').style.display = 'block';
    document.getElementById('dataLimit').style.display = 'block';
    document.getElementById('callMinutes').style.display = 'block';
    document.getElementById('maxFamilyMembers').style.display = 'block';
    document.getElementById('maxFriends').style.display = 'block';
}

// Function to update a plan
function updatePlan() {
    location.reload;
    up = 1;
    console.log('Executing updatePlan function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('submit').style.display = 'block';
    document.getElementById('getbyid').style.display = 'none';

    // Show form fields for updating an existing plan
    console.log('Displaying form fields for updating plan.');
    document.getElementById('planId').style.display = 'block';
    document.getElementById('planName').style.display = 'block';
    document.getElementById('monthlyCost').style.display = 'block';
    document.getElementById('dataLimit').style.display = 'block';
    document.getElementById('callMinutes').style.display = 'block';
    document.getElementById('maxFamilyMembers').style.display = 'block';
    document.getElementById('maxFriends').style.display = 'block';
}

// Function to delete a plan
function deletePlan() {
    location.reload;
    dp = 1;
    console.log('Executing deletePlan function.');
    document.getElementById('filter-section').style.display = 'block';
    document.getElementById('table-container').style.display = 'none';
    document.getElementById('submit').style.display = 'block';
    document.getElementById('getbyid').style.display = 'none';

    // Show only Plan ID filter for deletion
    console.log('Displaying Plan ID filter for deletion.');
    document.getElementById('planId').style.display = 'block';
    document.getElementById('planName').style.display = 'none';
    document.getElementById('monthlyCost').style.display = 'none';
    document.getElementById('dataLimit').style.display = 'none';
    document.getElementById('callMinutes').style.display = 'none';
    document.getElementById('maxFamilyMembers').style.display = 'none';
    document.getElementById('maxFriends').style.display = 'none';
    document.getElementById('planId').focus();
}

// Function to fetch plan by ID
function fetchPlanById() {
    const planId = document.getElementById('planId').value.trim();
    console.log(`Executing fetchPlanById function with planId: ${planId}`);

    if (planId) {
        console.log(`Fetching plan with ID: ${planId}`);
        fetch(`http://localhost:1000/plans/${planId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                console.log('Plan fetched:', data);

                const tableBody = document.getElementById('planTableBody');
                tableBody.innerHTML = '';

                if (data) {
                    console.log('Inserting fetched plan into table.');
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${data.planId}</td>
                        <td>${data.planName}</td>
                        <td>${data.monthlyCost.toFixed(2)}</td>
                        <td>${data.dataLimit}</td>
                        <td>${data.callMinutes}</td>
                        <td>${data.maxFamilyMembers}</td>
                        <td>${data.maxFriends}</td>
                    `;
                    tableBody.appendChild(row);
                } else {
                    console.log('No data found to display.');

                    const row = document.createElement('tr');
                    row.innerHTML = '<td colspan="7">No plan details found</td>';
                    tableBody.appendChild(row);
                }
            })
            .catch(error => console.error('Error fetching plan details:', error));
    } else {
        console.log('No Plan ID provided.');
    }
}

// Function to perform actions based on the current form state
// Function to perform actions based on the current form state
function performAction() {
    console.log('Executing performAction function.');

    const planId = document.getElementById('planId').value.trim();
    const planName = document.getElementById('planName').value.trim();
    const monthlyCost = parseFloat(document.getElementById('monthlyCost').value.trim());
    const dataLimit = parseInt(document.getElementById('dataLimit').value.trim(), 10);
    const callMinutes = parseInt(document.getElementById('callMinutes').value.trim(), 10);
    const maxFamilyMembers = parseInt(document.getElementById('maxFamilyMembers').value.trim(), 10);
    const maxFriends = parseInt(document.getElementById('maxFriends').value.trim(), 10);

    console.log('Form values:', {
        planId,
        planName,
        monthlyCost,
        dataLimit,
        callMinutes,
        maxFamilyMembers,
        maxFriends
    });

    if (cp === 1 && document.getElementById('planName').style.display === 'block' && planId !== '') {
        // Create Plan
        console.log('Creating new plan.');
        fetch('http://localhost:1000/plans', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ planId, planName, monthlyCost, dataLimit, callMinutes, maxFamilyMembers, maxFriends })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Plan created:', data);
            location.reload;
            document.getElementById('table-container').style.display = 'block';
            fetchPlans(); // Refresh the list of plans
        })
        .catch(error => console.error('Error creating plan:', error));
    } else if (up === 1 && document.getElementById('planName').style.display === 'block' && planName !== '' && planId !== '') {
        // Update Plan
        console.log('Updating plan with ID:', planId);
        fetch(`http://localhost:1000/plans/${planId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ planName, monthlyCost, dataLimit, callMinutes, maxFamilyMembers, maxFriends })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Plan updated:', data);
            location.reload;
            document.getElementById('table-container').style.display = 'block';
            fetchPlans(); // Refresh the list of plans
        })
        .catch(error => console.error('Error updating plan:', error));
    } else if (dp === 1 && planId !== '' && document.getElementById('planName').style.display === 'none') {
        // Delete Plan
        console.log('Deleting plan with ID:', planId);
        fetch(`http://localhost:1000/plans/${planId}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            console.log('Plan deleted.');
            location.reload;
            document.getElementById('table-container').style.display = 'block';
            fetchPlans(); // Refresh the list of plans
        })
        .catch(error => console.error('Error deleting plan:', error));
    } else {
        console.log('No action performed. Invalid form state.');
    }

    // Reset global variables after performing the action
    cp = 0;
    up = 0;
    dp = 0;
}


// Fetch all plans
function fetchPlans() {
    console.log('Executing fetchPlans function.');
    fetch('http://localhost:1000/plans')
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Plans fetched:', data);

            const tableBody = document.getElementById('planTableBody');
            tableBody.innerHTML = '';

            const plans = Array.isArray(data) ? data : [data];

            if (plans.length > 0) {
                plans.forEach(plan => {
                    console.log('Inserting plan into table:', plan);

                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${plan.planId}</td>
                        <td>${plan.planName}</td>
                        <td>${plan.monthlyCost.toFixed(2)}</td>
                        <td>${plan.dataLimit}</td>
                        <td>${plan.callMinutes}</td>
                        <td>${plan.maxFamilyMembers}</td>
                        <td>${plan.maxFriends}</td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                console.log('No data found to display.');

                const row = document.createElement('tr');
                row.innerHTML = '<td colspan="7">No plan details found</td>';
                tableBody.appendChild(row);
            }
        })
        .catch(error => console.error('Error fetching plan details:', error));
}
