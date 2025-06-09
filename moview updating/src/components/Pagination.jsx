import React from 'react'
import { Button } from 'react-bootstrap';

export const pagination = ({totalposts,postsperpage,setCurrentPage}) => {

    let pages = [];

    const numOfPages = math.ceil(totalposts/postsperpage);
    console.log(numOfPages)

    for(let i=0;i<=numOfPages;i++){
        pages.push(i);
    }
  return (
    <div className='pagination'>
        {    
        pages.map((page,index)=>{
                return(
                <Button className={num==='page-btn'} key={index} onClick={()=>setCurrentPage(page)}>{page}</Button>
                )})
        }̉̉̉̉̉̉̉̉̉̉̉̉

    </div>
  )
}
