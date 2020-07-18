import React from 'react';
import Axios from 'axios';
import { connect } from 'react-redux';
import * as actionCreators from '../redux/Action';

class Login extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      isAdmin: false

    };
  }

  componentDidMount = () => {
    console.log('componentDidMount called');
  }


  formSubmitHandler(event) {
    event.preventDefault();
    console.log("email:", this.state.email);
    console.log("password:", this.state.password);

    let credentials = "Basic " + btoa(this.state.email + ":" + this.state.password);
    Axios.get(`http://localhost:8060/authentication-service/authenticate`, { headers: { 'Authorization': credentials } })
      .then(res => {
        console.log("res:", res.data);
        localStorage.setItem("token", res.data.token);
        localStorage.setItem("username", res.data.username);
        localStorage.setItem("userEmpId", res.data.userEmpId);

        if (res.data.role.includes("Admin")) {
          localStorage.setItem("role", "Admin");
        } else if (res.data.role.includes("Pmo")) {
          localStorage.setItem("role", "Pmo");
        } else if (res.data.role.includes("Poc")) {
          localStorage.setItem("role", "Poc");
        } else {
          localStorage.setItem("role", "User");
        }

        if (res.status == '200') {
          this.props.onLogin(res.data)
          localStorage.setItem("isLoggedIn", true);
          window.location.href = '/dashboard'

        } else if (res.status == '401') {
          console.log("unauthorised")
        } else {
          console.log("intenal Server error")
        }

      }).catch(function (error) {
        alert("E-mail or Password Incorrect")
        console.log("unauthorised:", error);
      });

      Axios.get('http://localhost:8060/event-report-service/vol/status/' + this.state.email,{ headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

        if (res.status == '200') {
          console.log(res.status)
          console.log(res.data.status);
          localStorage.setItem("status", res.data.status);
  
        }
      }).catch(function (error) {
        console.log("error:", error);
      });

  }
  changeEmailHandler = (event) => {
    this.setState({ email: event.target.value });
  }

  changePasswordHandler = (event) => {
    this.setState({ password: event.target.value });
  }



  render() {
    return (
      <div className="container mb-5 mt-5" >
        <div className="row mb-5 mt-5">
          <div className="col-lg-7 col-sm-12 ">
            <img src={require('../login_logo.jpg')} width="100%" height="100%" alt="Img not Supported" />
          </div>
          <div className="col-lg-5 col-sm-12 ">
            <div className="card h-100">
              <div className="card-body px-lg-5 pt-0 mt-3">
                <h4 className="white-text  mt-4">
                  <strong><i className="fa fa-handshake fa-rotate-45" /> <label className=" font-weight-bold  mr-2" style={{ color: '#0000b3' }}>Cognizant</label><label style={{ color: '#6DE818' }}>Outreach</label> </strong>
                </h4>
                <p className="text" style={{color:"#948e8e"}}>Feedback Management System</p>

                <div className="md-form">
                  <label for="materialLoginFormEmail">E-mail</label>
                  <input type="text" id="materialLoginFormEmail" value={this.state.email} onChange={this.changeEmailHandler.bind(this)} className="form-control" />

                </div>
                <div className="md-form">
                  <label for="materialLoginFormPassword">Password</label>
                  <input type="password" id="materialLoginFormPassword" value={this.state.password} onChange={this.changePasswordHandler.bind(this)} className="form-control" />


                </div>

                <div className="d-flex justify-content-around">
                  {/* <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="customCheck1" />
                    <label className="custom-control-label" for="customCheck1">Remember Me</label>
                  </div> */}
                  <div>
                    <a href="/forget">Forgot password?</a>
                  </div>
                </div>

                <button className="btn btn-outline btn-rounded btn-block my-4 waves-effect z-depth-0 text-white bg-success" type="submit" onClick={this.formSubmitHandler.bind(this)}>Sign Me in</button>
                {/* <p>Not a member?
             <a href="">Register</a>
                </p> */}
              </div>
            </div>
          </div>
        </div>

      </div >
    )
  }
}


const mapDispatchToProps = dispatch => {
  return {
    onLogin: (val) => { dispatch(actionCreators.login(val)) }

  };
};

export default connect(null, mapDispatchToProps)(Login);


