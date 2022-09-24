const baseUrl = "http://localhost:8080/api/agent";

let agents = []; //track array of agents, useful when editing

function getAllAgents(){

    fetch(baseUrl)    //simple GET request, gets all my agents data
    .then(async response =>{                          //response checks the status of the response 
        if(response.status === 200){
            clearErrors();
            return response.json();             //json promise
        } else{
            Promise.reject(await response.json())
        }
    })
    .then( listOfAgents => {
        //Orders list of agents by id
        listOfAgents.sort((a,b) => a.agentId - b.agentId);
        agents = [...listOfAgents];//make a copy in case renderAgents affects it
        renderAgents(listOfAgents);
    }) //if it succeeds... should have list of agents. 
                                                       //Turn every agent into a row in our table
    .catch(error => {
        if(error instanceof TypeError){
            showErrors(["Could not connect to the api."]);
        } else{
            showErrors(error);
        }
    });
}

function renderAgents(agents){
//Converting array of agent objects to an array of row
//onclick bakes the passed object when generated
const agentRows = agents.map(a => `<tr> 
    <td>${a.agentId}</td>
    <td>${a.firstName}</td>
    <td>${a.middleName}</td>
    <td>${a.lastName}</td>
    <td>${a.dob}</td>
    <td>${a.heightInInches}</td>
    <td class="text-right"><button onClick="loadAgentForEdit(${a.agentId})" class="btn btn-primary edit-btn">EditAgent</button><button onClick="deleteAgent(${a.agentId})" class="btn btn-danger">DeleteAgent</button></td>
</tr>`);

//combine all of them
const html = agentRows.reduce((prev,curr) =>prev+curr );

//set inner html of tbody
//#agents = table id
//tbody gets child tbody element underneath the single agent element
document.querySelector("#agents tbody").innerHTML = html;

}

function loadAgentForEdit(id){
    
    //checks that matching agent matches with the agent selected
    //looks them up by id
    let matchingAgent = agents.find(a => a.agentId === id);

    //Take fields of matchingAgent and set all values of inputs (index.html -> <form onsubmit="editAgent(event)"> -> <input>)
    //Write the values
    //document.getElementById("[label for value]").value = matchingAgent.[property name]
    document.getElementById("editId").value = matchingAgent.agentId;
    document.getElementById("editFirstName").value = matchingAgent.firstName;
    document.getElementById("editMiddleName").value = matchingAgent.middleName;
    document.getElementById("editLastName").value = matchingAgent.lastName;
    document.getElementById("editDob").value = matchingAgent.dob;
    document.getElementById("editHeightInInches").value = matchingAgent.heightInInches;

    //makes the form appear when we click 'EditAgent' button
    document.getElementById("editForm").setAttribute("class", "d-block");
}

function editAgent(evt){
    evt.preventDefault();//prevents the page from trying to reload

    //build up an object to send over
    //create variables that have the names that I expect, read them from the edited elements
    //and then build an object out of them
    const agentId = document.getElementById("editId").value;
    const firstName = document.getElementById("editFirstName").value;
    const middleName = document.getElementById("editMiddleName").value;
    const lastName = document.getElementById("editLastName").value;
    const dob = document.getElementById("editDob").value;
    const heightInInches = document.getElementById("editHeightInInches").value;

    const editedAgent = {agentId, firstName, middleName, lastName, dob, heightInInches};

    //create PUT request
    fetch(baseUrl + "/" + agentId, {
        method: "PUT",
        body: JSON.stringify(editedAgent),
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(async response => {
        if(response.status === 204){
            //if successful get all agents & clear all agents
            clearEditForm();
            getAllAgents();
        }else if(response.status === 409){
            return Promise.reject(["Id mismatch between url and sent agent."]);//put string into an array because it's handeling multiple error messages
        } else if( response.status == 400 ){
            return Promise.reject( await response.json());
        }
    })
    .catch(errorList => {
        if(errorList instanceof TypeError){
            showErrors(["Could not connect to the api."]);
        } else{
            showErrors(errorList);
        }
    });
}

function deleteAgent(id){
    //finding agent
    let matchingAgent = agents.find(a => a.agentId === id);

    if(window.confirm(`Are you sure you want to delete Agent ${matchingAgent.agentId}: ${matchingAgent.firstName} ${matchingAgent.lastName}?`)){
        fetch( baseUrl + "/" + id, {
            method: "DELETE"
        })
        .then(response => {
            if(response.status === 204){
                //reload all agents
                getAllAgents();
            } else{
                showErrors(["Could not find matching agent to delete."])
            }
        })
        .catch(() => showErrors(["Could not connect to API."]));
    }
}

function addAgent(event){
    event.preventDefault();

    //make variables using the property names to buld up an object
    const firstName = document.getElementById("firstName").value;
    const middleName = document.getElementById("middleName").value;
    const lastName = document.getElementById("lastName").value;
    const dob = document.getElementById("dob").value;
    const heightInInches = document.getElementById("heightInInches").value;


    const agentToAdd = {firstName, middleName, lastName, dob, heightInInches};

    fetch(baseUrl, {
        method: "POST",
        body: JSON.stringify(agentToAdd),
        headers: {
            "Content-Type": "application/json"
        }
    })
    //fetch returns a response
    .then(async response => {
        if(response.status === 201){
            hideAddForm();
            clearErrors();
            //if successful...
            return response.json();
        }
        return Promise.reject(await response.json());
        
    })
    //when response.json happens...
    //returns hydrated agents
    .then(addedAgent => getAllAgents())
    .catch(error => {
        if(error instanceof TypeError){
            showErrors(["Could not connect to the api."]);//put string into an array because it's handeling multiple error messages
        } else{
            showErrors(error);
        }
    });
}

function showAddForm(){
    //shows the form
    document.getElementById("addForm").setAttribute("class", "d-block");
    //hide the button itself
    document.getElementById("showAddFormBtn").setAttribute("class", "d-none");
}

function hideAddForm(){
    document.getElementById("addForm").setAttribute("class", "d-none");
    document.getElementById("showAddFormBtn").setAttribute("class", "btn btn-primary");
}

function showErrors( listOfErrorMessages ){

    //looks for 'messages' div in index.html
    const messageContainer = document.getElementById("messages");

    //converts error message into a sequence of paragraphs
    //becoming the innerHTML of the <div id="messages"></div>
    //in my html file
    messageContainer.innerHTML = listOfErrorMessages.map( m => "<p>" + m + "</p>" ).reduce( (prev, curr) => prev + curr ); //reduce into one big string

}

//when successful, clears errors
function clearErrors(){
    document.getElementById("messages").innerHTML = "";
}

function clearEditForm(){
    document.getElementById("editId").value = "";
    document.getElementById("editFirstName").value = "";
    document.getElementById("editMiddleName").value = "";
    document.getElementById("editLastName").value = "";
    document.getElementById("editDob").value = "";
    document.getElementById("editHeightInInches").value = "";

    //hides the form along with clear
    document.getElementById("editForm").setAttribute("class", "d-none");
}

function clearAddForm(){
    document.getElementById("firstName").value = "";
    document.getElementById("middleName").value = "";
    document.getElementById("lastName").value = "";
    document.getElementById("dob").value = "";
    document.getElementById("heightInInches").value = "";

    //hides the form 
    hideAddForm();
}

getAllAgents();