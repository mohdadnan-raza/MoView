import React from 'react'
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate,Link } from "react-router-dom";
import Container from 'react-bootstrap/Container';
import { BsFacebook,BsDiscord,BsTwitter} from "react-icons/bs";


export const Footer = () => {



  return (
    <>
          <>
          <div style={{marginTop:'2rem'}}>
      <Navbar expand="lg" className='footer fixed-bottom'>
      <Container fluid>
        <div className='footer-icon'><span>Contact</span>Us : admin<span>@</span>moview.<span>com</span></div>
        <div>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <div className="footer-icons">
            <Link as={Link} to ="/home" className='lin'><BsFacebook/></Link>
            <Link as={Link} to ="/home" className='lin'><BsDiscord/></Link>
            <Link as={Link} to ="/categories" className='lin'><BsTwitter/></Link> 
          </div>
          
        </Navbar.Collapse>
        </div>
      </Container>
    </Navbar>
    </div>
    </>
      

    </>

  )
}
