const baseUrl = "http://localhost:8080/api/agent";

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
    .then( listOfAgents => renderAgents(listOfAgents)) //if it succeeds... should have list of agents. 
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
    <td><button onClick="editAgent(${a.agentId})">EditAgent</button><button onClick="deleteAgent(${a.agentId})">DeleteAgent</button></td>
</tr>`);

//combine all of them
const html = agentRows.reduce((prev,curr) =>prev+curr );

//set inner html of tbody
//#agents = table id
//tbody gets child tbody element underneath the single agent element
document.querySelector("#agents tbody").innerHTML = html;

}

function editAgent(id){
    console.log("editing-agent " + id)
}

function deleteAgent(id){
    console.log("deleting-agent " + id)
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
            showErrors(["Could not connect to the api."]);
        } else{
            showErrors(error);
        }
    });
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

getAllAgents();