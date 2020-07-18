import React from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table-plus';
import Axios from 'axios';

class Reset extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            confirmPassword: "",
            string: ""
        };
    }

    componentDidMount = () => {
        console.log('componentDidMount called');

    }
    formSubmitHandler(event) {
        event.preventDefault();
        console.log("email:", this.state.email);
        console.log("password:", this.state.password);
        console.log("confirmPassword:", this.state.confirmPassword);

        let finalPassword = this.state.password;
        let emailid = this.state.email;
        Axios.get(`http://localhost:8060/authentication-service/reset/` + finalPassword + "/" + emailid)
            .then(res => {
                console.log("password:", this.state.password);
                console.log("confirmPassword:", this.state.confirmPassword);
                if (this.state.password == this.state.confirmPassword) {
                    console.log(res.data);
                    console.log("password reset successfull");
                    alert("Password reset done");
                    window.location.href = '/'
                }
                else {
                    this.setState({ string: "password fields must match" })
                }

            })

        // else {
        //     this.state.string = "password and confirm password did not match"
        // }


    }
    changeEmailHandler = (event) => {
        this.setState({ email: event.target.value });
    }

    changepasswordHandler = (event) => {
        this.setState({ password: event.target.value });
    }
    changeconfirmPasswordHandler = (event) => {
        this.setState({ confirmPassword: event.target.value });
    }



    render() {
        return (
            <div className="container mt-5" >
                <div className="row">
                    <div className="col-lg-7 col-sm-12">
                        <img src={require('../login_logo.jpg')} width="100%" height="85%" alt="Img not Supported" />
                    </div>
                    <div className="col-lg-5 col-sm-12">
                        <div className="card">
                            <div className="card-body px-lg-5 pt-0">
                                <h4 className="white-text  mt-4">
                                    <strong><i className="fa fa-handshake fa-rotate-45" /> <label className=" font-weight-bold  mr-2" style={{ color: '#0000b3' }}>Cognizant</label><label style={{ color: '#6DE818' }}>Outreach</label> </strong>
                                </h4>
                                <p className="text-dark">Feedback Management System</p>

                                <div>
                                    <h5 style={{ color: '#FF0000' }}>{this.state.string}</h5>
                                </div>

                                <div className="md-form">
                                    <label for="materialLoginFormEmail">E-mail</label>
                                    <input type="text" id="materialLoginFormEmail" value={this.state.email} onChange={this.changeEmailHandler.bind(this)} className="form-control" />

                                </div>
                                <div className="md-form">
                                    <label for="materialLoginFormPassword">Password</label>
                                    <input type="password" id="materialLoginFormPassword" value={this.state.password} onChange={this.changepasswordHandler.bind(this)} className="form-control" />


                                </div>
                                <div className="md-form">
                                    <label for="materialLoginFormPassword">Confirm Password</label>
                                    <input type="password" id="materialLoginFormPassword" value={this.state.confirmPassword} onChange={this.changeconfirmPasswordHandler.bind(this)} className="form-control" />


                                </div>
                                <button className="btn btn-outline btn-rounded btn-block my-4 waves-effect z-depth-0 text-white bg-success" type="submit" onClick={this.formSubmitHandler.bind(this)}>Reset</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div >
        )
    }

}
export default Reset