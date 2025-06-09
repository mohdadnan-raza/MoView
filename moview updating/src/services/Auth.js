
export function adminAuth () {
    const item = localStorage.getItem('user')
    if(item && item.token){
        console.log(item,item.token)
        return true;
    }
    else{
        return false
    }
}