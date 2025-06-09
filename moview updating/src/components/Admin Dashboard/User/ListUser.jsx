import React, { useState , useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import UserService from '../../../services/UserService';

export function ListUser () {
    const navigate = useNavigate();

    const { id } = useParams()

    const [users, setUsers] = useState([])

    function deleteUser(id){
        UserService.deleteUser(id).then( () => {
            setUsers(users => users.filter(user => user.id !== id));
        });
    }

    const listUserData = async () => {
        let x = JSON.parse(localStorage.getItem('user'))
        console.log(x.jwtToken)
        const res = axios.get('http://localhost:8202/user/list')
        console.log(res)
        setUsers(res.data);
      };
    
      useEffect(() => {
        listUserData();
      }, [])


    
    return (
        <div>
             <h2 className="text-center m-100">User List</h2>
             <br></br>
             {/* <div className = "row">
                    <table className = "table table-bordered text-center" style={{color:'#fff'}}>
                        <thead>
                            <tr>
                                <th> User Id</th>
                                <th> Name</th>
                                <th> Username</th>
                                <th> Email</th>
                                <th> Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                users.map(
                                    user => 
                                    <tr key = {user.id}>
                                         <td> { user.name} </td>  
                                         <td> { user.username} </td>  
                                         <td> { user.email} </td> 
                                         <td>
                                             <button onClick={ () => navigate(`/update/user/id/${id}`)} className="btn btn-info">Update </button>
                                             <button style={{marginLeft: "10px"}} onClick={ () => deleteUser(user.id)} className="btn btn-danger">Delete </button>
                                             <button style={{marginLeft: "10px"}} onClick={ () => navigate(`user/id/${id}`)} className="btn btn-info">View </button>
                                         </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
             </div> */}
        </div>
    )
}


