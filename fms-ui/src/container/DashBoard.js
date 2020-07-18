import React from 'react';
import Axios from 'axios';
import { connect } from 'react-redux';
import './DashBoard.css';
import { Link } from "mdbreact";
import Loading from '../component/Loading';

class DashBoard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            totalEvents: 0,
            livesImpacted: 0,
            totalVolunteers: 0,
            totalParticipants: 0,
            dataFetch: false,
            string: ''
        }
        this.addEvent = this.addEvent.bind(this)
    }
    addEvent() {

        Axios.get('http://localhost:8060/event-report-service/importEvent', { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

            if (res.status == '200') {
                console.log("event file processed sucessfully")

            }
        }).catch(function (error) {
            console.log("error:", error);
        });
        Axios.get('http://localhost:8060/event-report-service/importEventSum', { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

            if (res.status == '200') {
                console.log("event file processed sucessfully")

            }
        }).catch(function (error) {
            console.log("error:", error);
        });
        sessionStorage.clear();
    }
    componentDidMount = () => {

        if (localStorage.getItem("role") == 'Admin' || localStorage.getItem("role") == 'Pmo') {
            Axios.get('http://localhost:8060/event-report-service/dashboard/', { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

                if (res.status == '200') {
                    this.setState({ totalEvents: res.data.totalEvents })
                    this.setState({ livesImpacted: res.data.livesImpacted })
                    this.setState({ totalVolunteers: res.data.totalVolunteers })
                    this.setState({ totalParticipants: res.data.totalParticipants })
                }
            }).catch(function (error) {
                console.log("error:", error);
            });
        } else {
            Axios.get('http://localhost:8060/event-report-service/dashboard/' + localStorage.getItem("userEmpId"), { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

                if (res.status == '200') {
                    this.setState({ totalEvents: res.data.totalEvents })
                    this.setState({ livesImpacted: res.data.livesImpacted })
                    this.setState({ totalVolunteers: res.data.totalVolunteers })
                    this.setState({ totalParticipants: res.data.totalParticipants })
                }
            }).catch(function (error) {
                console.log("error:", error);
            });

            // Axios.get('http://localhost:8060/event-report-service/volunteer/reg/' + localStorage.getItem("userEmpId"), { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

            //     if (res.status == '200') {
            //         console.log(res.data)
            //         console.log(res.data.employee_id);

            //     }
            // }).catch(function (error) {
            //     console.log("error:", error);
            // });
        }
        if (sessionStorage.getItem("promoted")) {
            this.setState({ string: "Congratulations You are Promoted as an Admin!!" })

        }
    }
    render() {
        console.log(localStorage.getItem("status"))
        console.log(localStorage.getItem("role"))
        console.log(sessionStorage.getItem("promoted"))
        if (localStorage.getItem("role") == 'User') {
            window.location.href = '/userDashboard'
        }
        else {
            return (
                <div>
                    <div className="container-fluid">
                        {localStorage.getItem("role") == "Admin" ?
                            < div className="finny alert alert-success  alert-dismissible  col-lg-9 col-md-9 col-sm-12 py-1">
                                <button type="button" className="close" data-dismiss="alert">&times;</button>
                                <label className="btn btn py-1" onClick={this.addEvent}><strong><i className="fa fa-upload mr-2"></i>Add/Upload</strong></label> <text> Click here to Process the Event Files</text>
                            </div>
                            : ''}
                        {localStorage.getItem("role") == "Admin" && sessionStorage.getItem("promoted") ?
                            < div className="finny alert alert-success  text-center alert-dismissible  col-lg-9 col-md-9 col-sm-12 py-1">
                                <button type="button" className="close" data-dismiss="alert">&times;</button>
                                <div>{this.state.string}</div>
                            </div>
                            : ''}
                        <div className="row mb-3 mt-2">
                            <div className="col-xl-3 col-sm-6 py-2">
                                <div className=" card bg-danger text-white h-100">
                                    <div className=" card-body bg-danger">
                                        <div className="rotate">
                                            <i className="fa fa-desktop fa-4x"></i>
                                        </div>
                                        <h6 className="text-uppercase">Total Events</h6>
                                        <h1 className="display-4">{this.state.totalEvents}</h1>
                                    </div>
                                    <div style={{ backgroundColor: '#632e2b' }} className="card  text-right ">
                                        <Link className="h-5" to="/event">  <small className="text-muted text-light mr-3"> View Detail &nbsp; <i className="fa fa-arrow-circle-right"></i></small>
                                        </Link>
                                    </div>
                                </div>
                            </div>

                            <div className="col-xl-3 col-sm-6 py-2">
                                <div className="card text-white bg-warning h-100">
                                    <div className="card-body bg-warning">
                                        <div className="rotate">
                                            <i className="fa fa-link fa-4x"></i>
                                        </div>
                                        <h6 className="text-uppercase">Lives impacted</h6>
                                        <h1 className="display-4">{this.state.livesImpacted}</h1>
                                    </div>
                                    <div style={{ backgroundColor: '#997e2c' }} className="card text-right">
                                        <Link className="h-5" to="/event">  <small className="text-muted text-dark mr-3"> View Detail &nbsp; <i className="fa fa-arrow-circle-right"></i></small>
                                        </Link>
                                    </div>
                                </div>
                            </div>

                            <div className="col-xl-3 col-sm-6 py-2">
                                <div className="card text-white bg-info h-100">
                                    <div className="card-body bg-info">
                                        <div className="rotate">
                                            <i className="fa fa-users fa-4x"></i>
                                        </div>
                                        <h6 className="text-uppercase">Total Volunteers</h6>
                                        <h1 className="display-4">{this.state.totalVolunteers}</h1>
                                    </div>
                                    <div style={{ backgroundColor: '#266373' }} className="card text-right">
                                        <Link className="h-5" to="/event">  <small className="text-muted text-dark mr-3"> View Detail &nbsp; <i className="fa fa-arrow-circle-right"></i></small>
                                        </Link>
                                    </div>
                                </div>
                            </div>


                            <div className="col-xl-3 col-sm-6 py-2 ">
                                <div style={{ backgroundColor: '#8c8c8c' }} className="card text-white  h-100">
                                    <div className="card-body">
                                        <div className="rotate">
                                            <i className="fa fa-clock fa-4x"></i>
                                        </div>
                                        <h6 className="text-uppercase">Total Participants</h6>
                                        <h1 className="display-4">{this.state.totalParticipants}</h1>
                                    </div>
                                    <div style={{ backgroundColor: '#4d4d4d ' }} className="card text-right">
                                        <Link className="h-5" to="/event">  <small className="text-muted text-light mr-3"> View Detail &nbsp; <i className="fa fa-arrow-circle-right"></i></small>
                                        </Link>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div >
            )
        }

    }
}
const mapStateToProps = state => {
    return {
        Users: state.userData

    };
};
export default connect(mapStateToProps, null)(DashBoard);
