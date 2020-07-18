import React from 'react';
import Axios from 'axios';

class Forget extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            lsname: "",
            string: "",
        };
    }

    componentDidMount = () => {
        console.log('componentDidMount called');

    }
    formSubmitHandler(event) {
        event.preventDefault();
        console.log("email:", this.state.email);
        console.log("lsname:", this.state.lsname);

        let lastName = this.state.lsname;
        let emailid = this.state.email;
        Axios.get(`http://localhost:8060/authentication-service/forget/` + lastName + "/" + emailid)
            .then(res => {
                localStorage.setItem("forgetPasswordVerification", res.data);
                console.log(res.data);
                if (res.data) {
                    window.location.href = '/reset'
                }
                else {
                    this.setState({ string: "No User Found!" })
                }

            })

    }
    changeEmailHandler = (event) => {
        this.setState({ email: event.target.value });
    }

    changelsnameHandler = (event) => {
        this.setState({ lsname: event.target.value });
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
                                <p className="text-dark">Enter your E-mail and Last Name</p>
                                <div>
                                    <h3 style={{ color: '#FF0000' }}>{this.state.string}</h3>
                                </div>

                                <div className="md-form">
                                    <label for="materialLoginFormEmail">E-mail</label>
                                    <input type="text" id="materialLoginFormEmail" value={this.state.email} onChange={this.changeEmailHandler.bind(this)} className="form-control" />

                                </div>
                                <div className="md-form">
                                    <label for="materialLoginFormPassword">Last Name</label>
                                    <input type="text" id="materialLoginFormPassword" value={this.state.lsname} onChange={this.changelsnameHandler.bind(this)} className="form-control" />


                                </div>
                                <button className="btn btn-outline btn-rounded btn-block my-4 waves-effect z-depth-0 text-white bg-success" type="submit" onClick={this.formSubmitHandler.bind(this)}>Confirm</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div >
        )
    }

}
export default Forget