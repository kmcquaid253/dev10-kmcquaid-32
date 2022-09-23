fetch("http://localhost:8080/api/agent")    //simple GET request, gets all my agents data
.then(response =>{                          //response checks the status of the response 
    if(response.status === 200){
        return response.json();             //json promise
    } else{
        console.log(response);
    }
})
.then( listOfAgents => renderAgents(listOfAgents)) //if it succeeds... should have list of agents. Turn every agent into a row in our table

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