import React from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table-plus';
import Axios from 'axios';

class Role extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            user: [],
            newUser: [],
            email: ''
        }
    }

    componentDidMount = () => {
        Axios.get('http://localhost:8060/authentication-service/users', { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

            if (res.status == '200') {
                this.userTableHandler(res.data)

            }
        }).catch(function (error) {
            console.log("error:", error);
        });
    }
    userTableHandler = (userdata) => {
        userdata.filter((user) => {
            if (user.body.role == "Pmo") {
                this.state.data.push(user.body)
            }
            else{
                let user = {

                    us_fs_name: 'Pmo',
                    us_ls_name: 'Pmo',
                    us_emp_id: '',
    
                }
                this.state.user.push(user)
            }
        })
        this.setState({ user: this.state.user })
        this.setState({ data: this.state.data })

    }


    formSubmitHandler(event) {
        event.preventDefault();
        console.log(this.state.email);
        console.log(this.state.user[0].us_fs_name)
        if (window.confirm('Are you sure you want to Add this User ' + this.state.email + ' ?')) {
            let add = this.state.email + " " + this.state.user[0].us_fs_name + " " + this.state.user[0].us_ls_name;
                Axios.post('http://localhost:8060/authentication-service/users/', [add], { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } })
                    .then((response) => {
                        console.log(response);
                        window.location.href = '/role'
                    }, (error) => {
                        alert("User Already Registered")
                        console.log(error);
                    });
                }

    }
    formSubmitHandlerRemove(event) {
        event.preventDefault();
        this.state.data.map((name,index)=>{
            console.log(this.state.data[index].us_emp_id)
            if(this.state.data[index].us_emp_id == this.state.email){
                if (window.confirm('Are you sure you want to Delete this User ' + this.state.email + ' ?')) {
                    Axios.delete('http://localhost:8060/authentication-service/users/' + this.state.email, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } })
                        .then((response) => {
                            console.log(response);
                            alert("SuccessFully Removed");
                            window.location.href = '/role'
                        }, (error) => {
                            console.log(error);
                        });
        
                    // history.push('/questions/')
                }
            }
            else if(this.state.data.length<=index){
                alert("Not a Pmo!")
            }
            
        })
    }

    changeEmailHandler = (event) => {
        this.setState({ email: event.target.value });
    }

    buttonFormatter = (cell, row) => {

        console.log("roww", row);
        return <div>
          <button className="py-1 w-120 small font-weight-bold text-white" style={{ backgroundColor: '#0000b3' }} onClick={() => this.eventDetailsHandler(row.us_emp_id)}> <i class="material-icons md-18 inline-icon">card_membership</i>&nbsp;Promote</button>
        </div>
      }

      eventDetailsHandler = (employeeId) => {

        console.log(employeeId)
    
        Axios.get(`http://localhost:8060/authentication-service/promote/` + employeeId)
        .then(res => {
            console.log(res.data);
            if (res.data) {
                alert(employeeId+" Promoted Successfully")
                sessionStorage.setItem("promoted",true);
                localStorage.setItem("promoted",true);
                window.location.href = '/role'
            }
            else {
                this.setState({ string: "No User Found!" })
            }

        })
      }

    render() {
        const options = {
            prePage: 'Prev',
            nextPage: 'Next',
            firstPage: 'First',
            lastPage: 'Last',
            hideSizePerPage: true,

        };
        if (localStorage.getItem("role") == "Admin") {
            return (

                <div className="container-fluid">

                    <div >
                        <div className="py-2 text-white" style={{ backgroundColor: '#0000b3' }}> <h6 className="font-weight-bolder ml-3">Assign Pmo</h6></div>
                        <div className="row py-2 justify-content-lg-center">

                            <div className="col-9 col-sm-4 ml-2"><h5 className="mb-2" >Employee ID <label className="ml-1 mt-1"> <input type="text" id="materialLoginFormEmail" value={this.state.email} onChange={this.changeEmailHandler.bind(this)} className="form-control" /></label></h5></div>
                            {/* <label for="materialLoginFormEmail">E-mail</label> */}
                            <div className="col-sm-4">
                                <button type="submit" className=" ml-2 btn-md btn btn-success font-weight-bolder" onClick={this.formSubmitHandler.bind(this)}>Add Pmo</button>
                                <button type="button" className=" ml-2 btn-md btn btn-danger font-weight-bolder" onClick={this.formSubmitHandlerRemove.bind(this)}>Remove Pmo</button>
                            </div>
                        </div>
                    </div>

                    <div>

                        <div className="py-2 mb-2 text-white" style={{ backgroundColor: '#0000b3' }}> <h6 className="font-weight-bolder ml-3">Pmo Users</h6></div>
                        <div >
                            <BootstrapTable data={this.state.data} options={options} pagination >
                                <TableHeaderColumn dataField='us_emp_id' isKey className="event-table-th">Employee ID</TableHeaderColumn>
                                <TableHeaderColumn dataField='us_fs_name' className="event-table-th">First Name</TableHeaderColumn>
                                <TableHeaderColumn dataField='us_ls_name' className="event-table-th">Last Name</TableHeaderColumn>
                                <TableHeaderColumn width='120' export={false} dataField='button' dataFormat={this.buttonFormatter.bind(this)} >Action</TableHeaderColumn>
                            </BootstrapTable>
                        </div>
                    </div>

                </div>
            )
        } else {
            return (
                <div className="container">
                    <div className="alert alert-danger  alert-dismissible  col-lg-9 col-md-9 col-sm-12 ">
                        <button type="button" className="close" data-dismiss="alert">&times;</button>
                        <strong><i className="fa fa-exclamation-triangle mr-1"></i>Warning!</strong> <text> Only Admin have the privileges to access this site</text>
                    </div>
                </div>
            )
        }

    }
}
export default Role;