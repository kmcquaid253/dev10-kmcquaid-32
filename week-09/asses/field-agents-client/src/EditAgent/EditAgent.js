import {useEffect, useState} from 'react';
import { useParams, Link, useHistory } from "react-router-dom";
import FormInput from '../FormInput/FormInput';
import "./EditAgent.css";

function EditAgent(){

    const {id} = useParams();

    const[agent, setAgent] = useState(null);//state that we track about the page, that way when it does update it will refresh the component

    const history = useHistory();

    useEffect( () => { //useEffect will only make the fetch happen once when the page first load up
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

        //create PUT request
        fetch("http://localhost:8080/api/agent/" + id, {
            method: "PUT",
            body: JSON.stringify(agent),
            headers: {
                "Content-Type": "application/json",
            }
        })
        .then(async response => {
            if(response.status === 204){
                //if successful get all agents & clear all agents
                history.push("/agents")
            }else if(response.status === 409){
                return Promise.reject(["Id mismatch between url and sent agent."]);//put string into an array because it's handeling multiple error messages
            } else if( response.status === 400 ){
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

    function inputChangeHandler(inputChangedEvent){
        const propertyName = inputChangedEvent.target.name;
        const newValue = inputChangedEvent.target.value;

        //Made a copy of the original value
        const agentCopy = {...agent};

        //update property of the copy
        agentCopy[propertyName] = newValue;

        //overwrite copy with setAgent()
        setAgent(agentCopy);
    }

    function showErrors( listOfErrorMessages ){

        //looks for 'messages' div in index.html
        const messageContainer = document.getElementById("messages");
    
        //converts error message into a sequence of paragraphs
        //becoming the innerHTML of the <div id="messages"></div>
        //in my html file
        messageContainer.innerHTML = listOfErrorMessages.map( m => "<p>" + m + "</p>" ).reduce( (prev, curr) => prev + curr ); //reduce into one big string
    
    }

    return (

        <div className='container'>
            <h4>Edit Agent:</h4>

            {agent ? 
            <form onSubmit={handleSubmit}>
                <FormInput 
                    inputType={"text"} 
                    identifier={"firstName"} 
                    labelText={"First Name"} 
                    currVal={agent.firstName} 
                    onChangeHandler={inputChangeHandler}/>
                <FormInput 
                    inputType={"text"} 
                    identifier={"middleName"} 
                    labelText={"Middle Name"} 
                    currVal={agent.middleName} 
                    onChangeHandler={inputChangeHandler}/>
                <FormInput 
                    inputType={"text"} 
                    identifier={"lastName"} 
                    labelText={"Last Name"} 
                    currVal={agent.lastName} 
                    onChangeHandler={inputChangeHandler}/>
                <FormInput 
                    inputType={"date"} 
                    identifier={"dob"} 
                    labelText={"Date of Birth"} 
                    currVal={agent.dob} 
                    onChangeHandler={inputChangeHandler}/>
                <FormInput 
                    inputType={"number"} 
                    identifier={"heightInInches"} 
                    labelText={"Height in inches"} 
                    currVal={agent.heightInInches} 
                    onChangeHandler={inputChangeHandler}/>

                <button className='btn btn-primary ed-btn'>Edit</button>
                <Link to="/agents" className="btn btn-danger can-btn">Cancel</Link>

                <h5>Messages</h5>
                <div id="messages" className="alert alert-info" role="alert"></div>
            </form> :
            null}
        </div>
    );
}

export default EditAgent;