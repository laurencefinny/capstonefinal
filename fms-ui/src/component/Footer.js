import React from 'react';
import { MDBContainer, MDBFooter } from "mdbreact";

const Footer = () => {

    return (


        <MDBFooter style={{
            backgroundColor: '#0000b3', position: "fixed", left: "0",
            bottom: "0",
            height: "50px",
            width: "100%"
        }} className="font-small footer-copyright text-center py-3 mt-3">

            <MDBContainer fluid  >
                ©2020 Outreach Feedback Management System -Cognizant All Rights Reserved
            </MDBContainer>

        </MDBFooter>
    );

}

export default Footer