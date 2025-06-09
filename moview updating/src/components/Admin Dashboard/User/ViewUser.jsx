import React, {useState , useEffect } from 'react'
import axios from 'axios'
import UserService from '../../../services/UserService'

export function ViewUser(props)  {
    
    const id = props.match.params.id
    const [user, setUser] = useState([])


    const viewUserData = async ()=>{
        
        const res = await axios.get("http://localhost:8202/user/list",{clientId : "1211",  
        clientSecret : "123"});
        setUser(res.data);
    }

    useEffect(()=>{
      viewUserData(); 
    },[])

    
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View User Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> User Id: </label>
                            <div> { user.id}</div>
                        </div>
                        <div className = "row">
                            <label> Name: </label>
                            <div> { user.name}</div>
                        </div>
                        <div className = "row">
                            <label> Username: </label>
                            <div> { user.username}</div>
                        </div>
                        <div className = "row">
                            <label> Email: </label>
                            <div> { user.email}</div>
                        </div>
                     </div>
                </div>
            </div>
        )
    }
