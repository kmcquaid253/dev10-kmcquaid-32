import './Agent.css';
import {Link} from 'react-router-dom';

function Agent(props){
    return(

        <div className="card">
            <div className="card-header hed">
                <h5 className='d-inline txt-font'>Agent ID: {props.agentData.agentId}</h5>
                <button className='float-end btn btn-sm btn-danger age'><Link to={"/agent/delete/" + props.agentData.agentId}>Delete</Link></button>
                <button className='float-end btn btn-sm btn-warning edit-btn'><Link to={"/agent/edit/" + props.agentData.agentId}>Edit</Link></button>
            </div>

                <div className="card-body">
                    <p className="card-text">First Name: {props.agentData.firstName}</p>
                    <p className="card-text">Middle Name: {props.agentData.middleName}</p>
                    <p className="card-text">Last Name: {props.agentData.lastName}</p>
                    <p className="card-text">Date of Birth: {props.agentData.dob}</p>
                    <p className="card-text">Height In Inches: {props.agentData.heightInInches}</p>
                </div>
        </div>
        
    );
}

export default Agent;