import {useEffect, useState} from 'react';
import { useParams, Link, useHistory } from "react-router-dom";
import DeleteFormInput from '../DeleteFormInput/DeleteFormInput';
import "./DeleteAgent.css";

function DeleteAgent(){

    const {id} = useParams();

    const[agent, setAgent] = useState(null);

    const history = useHistory();

    function showErrors( listOfErrorMessages ){

        //looks for 'messages' div in index.html
        const messageContainer = document.getElementById("messages");
    
        //converts error message into a sequence of paragraphs
        //becoming the innerHTML of the <div id="messages"></div>
        //in my html file
        messageContainer.innerHTML = listOfErrorMessages.map( m => "<p>" + m + "</p>" ).reduce( (prev, curr) => prev + curr ); //reduce into one big string
    
    }

    useEffect( () => {
        fetch("http://localhost:8080/api/agent/" + id)
        .then(
            response => {
                if(response.status === 200){//getting the single course object
                    return response.json(); //produces new 2nd promise
                } else{
                    //TODO: proper error handling
                    console.log(response);
                }
            }
        )
        .then(selectedAgent => {//variable comes from the api
            setAgent(selectedAgent);
        });
    }, []); //sending an empty array so it doesnt re-run


    function handleSubmit(event){//take in an event to prevent it from posting
        event.preventDefault();

        //create  request
        fetch("http://localhost:8080/api/agent/" + id, {
            method: "DELETE",
            body: JSON.stringify(agent),
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            if(response.status === 204){
                //if successful delete agent, bring them back to agents
                //redirect the user to the updated list of agents

                history.push("/agents");
            
            } else{
                showErrors(["Could not find matching agent to delete."])
            }
        })
        .catch(() => showErrors(["Could not connect to API."]));
    }

    return (

        <div className='container'>
            
            <div id="messages" className="alert alert-danger" role="alert">
                <h3>WARNING</h3>
                <h6 id='del-war'>Are you sure you want to delete the following Agent?</h6>
                <h6 id='del-war'>CAUTION: Deletion is permanent</h6>
            </div>
            <p></p>
            {agent ? 
            <form onSubmit={handleSubmit}>
                <DeleteFormInput 
                    inputType={"text"} 
                    identifier={"firstName"} 
                    labelText={"First Name"} 
                    currVal={agent.firstName}/>
                <DeleteFormInput 
                    inputType={"text"} 
                    identifier={"middleName"} 
                    labelText={"Middle Name"} 
                    currVal={agent.middleName}/>
                <DeleteFormInput 
                    inputType={"text"} 
                    identifier={"lastName"} 
                    labelText={"Last Name"} 
                    currVal={agent.lastName}/>
                <DeleteFormInput 
                    inputType={"date"} 
                    identifier={"dob"} 
                    labelText={"Date of Birth"} 
                    currVal={agent.dob}/>
                <DeleteFormInput 
                    inputType={"number"} 
                    identifier={"heightInInches"} 
                    labelText={"Height in inches"} 
                    currVal={agent.heightInInches}/>

                <button className='btn btn-danger del-btn'>Delete</button>
                <Link to="/agents" className="btn btn-warning can-btn">Cancel</Link>
            </form> :
            null}
        </div>
    );
}



export default DeleteAgent;