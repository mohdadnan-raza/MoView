import React from 'react'
import Carousel from 'react-bootstrap/Carousel';

export const Crousel = () => {
  return (
    <>
    <div style={{marginTop:70,marginBottom:10}}>
        <Carousel className='border border-2 border-white'>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="/images/m11.jpeg"
          alt="First slide" style={{height:300}}
        />
        <Carousel.Caption>
          <h3>First slide label</h3>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="/images/m33.png"
          alt="Second slide" style={{height:300}}
        />

        <Carousel.Caption>
          <h3>Second slide label</h3>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="/images/m22.jpeg"
          alt="Third slide" style={{height:300}}
        />

        <Carousel.Caption>
          <h3>Third slide label</h3>
          <p>
            Praesent commodo cursus magna, vel scelerisque nisl consectetur.
          </p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
    </div>
    </>
  )
}
