import axios from 'axios';

const user_url = "https://localhost:8202";

class UserService {

    getUser(){
        return axios.get(user_url +'/user/list');
    }

    getUserById(id){
        return axios.get(user_url + '/user/id/' + id);
    }

    updateUser(user, id){
        return axios.put(user_url + '/update/user/id/' + id, user);
    }

    deleteUser(id){
        return axios.delete(user_url + '/delete/user/id/' +id);
    }
}

export default new UserService()