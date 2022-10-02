import {Link, useHistory } from "react-router-dom";
import {useState} from 'react';
import FormInput from '../FormInput/FormInput';

  const baseUrl = "http://localhost:8080/api/agent";

function AddAgent(){

    const DEFAULT_AGENT = {
        firstName: "",
        middleName: "",
        lastName: "",
        dob: "",
        heightInInches: ""
      };

    const[agent, setAgent] = useState(DEFAULT_AGENT);//state that we track about the page, that way when it does update it will refresh the component

    const history = useHistory();


    function handleSubmit(event){//take in an event to prevent it from posting
        event.preventDefault();

        fetch(baseUrl, {
            method: "POST",
            body: JSON.stringify(agent),
            headers: {
                "Content-Type": "application/json"
            }
        })
        //fetch returns a response
        .then(async response => {
            if(response.status === 201){
                //if successful...
                history.push("/agents");
            }
            return Promise.reject(await response.json());
            
        })
        //when response.json happens...
        //returns hydrated agents
        .then(addedAgent =>  history.push("/agents"))
        .catch(error => {
            if(error instanceof TypeError){
                showErrors(["Could not connect to the api."]);//put string into an array because it's handeling multiple error messages
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


    return (
        // <h2>
        //     {agent ? agent.firstName : "Agent not loaded"}
        // </h2>

        <div className='container'>
            
            <form onSubmit={handleSubmit}>
                <FormInput 
                    inputType={"text"} 
                    identifier={"firstName"} 
                    labelText={"First Name"}/>
                <FormInput 
                    inputType={"text"} 
                    identifier={"middleName"} 
                    labelText={"Middle Name"}/>
                <FormInput 
                    inputType={"text"} 
                    identifier={"lastName"} 
                    labelText={"Last Name"}/>
                <FormInput 
                    inputType={"date"} 
                    identifier={"dob"} 
                    labelText={"Date of Birth"}/>

                <button className='btn btn-primary'>Add</button>
                <Link to="/agents" className="btn btn-danger">Cancel</Link>

                <h5>Messages</h5>
                <div id="messages" class="alert alert-info" role="alert"></div>
            </form> 
        </div>
    );
}
export default AddAgent;