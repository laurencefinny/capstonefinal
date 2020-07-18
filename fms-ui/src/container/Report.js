import React from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table-plus';
import Axios from 'axios';
import '../index.css';

class Report extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: [],
            email: ''
        }

    }

    componentDidMount = () => {
        if (localStorage.getItem("role") == 'Admin' || localStorage.getItem("role") == 'Pmo') {
            Axios.get('http://localhost:8060/event-report-service/eventReport', { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

                if (res.status == '200') {
                    this.eventTableHandler(res.data)

                }
            }).catch(function (error) {
                console.log("error:", error);
            });
        }
        else if (localStorage.getItem("role") == 'Poc') {
            Axios.get('http://localhost:8060/event-report-service/eventReport-empId/' + localStorage.getItem("userEmpId"), { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

                if (res.status == '200') {
                    console.log(res.data);
                    this.eventTableHandler(res.data)

                }
            }).catch(function (error) {
                console.log("error:", error);
            });
        }
        else {

        }

    }

    eventTableHandler = (eventdata) => {
        eventdata.filter((event) => {

            this.state.data.push(event.body)

        })
        this.setState({ data: this.state.data })

    }


    handlerClickCleanFiltered() {

        this.refs.clear2.cleanFiltered();
        this.refs.clear3.cleanFiltered();
        this.refs.clear4.cleanFiltered();
        this.refs.clear5.cleanFiltered();
        this.refs.clear6.cleanFiltered();
    }

    formSubmitHandler(event) {
        event.preventDefault();
        console.log(this.state.email);

        Axios.get('http://localhost:8060/email-service/email/report/'+this.state.email, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

            if (res.status == '200') {
      
              console.log("email send")
              alert("E-mail Sent")
      
            }
          }).catch(function (error) {
            console.log("error:", error);
          });
      
      

    }

    changeEmailHandler = (event) => {
        this.setState({ email: event.target.value });
    }

    render() {


        const options = {
            prePage: 'Prev',
            nextPage: 'Next',
            firstPage: 'First',
            lastPage: 'Last',
            hideSizePerPage: true,

        };
        console.log(this.props)

        return (


            <div className="container-fluid" >

                <div >
                    <div className="py-2 text-white" style={{ backgroundColor: '#0000b3' }}> <h5 className="font-weight-bolder ml-3">Actions</h5></div>

                    <div className="row mb-3 mt-2">

                        <div className="col-xl-6 col-sm-6 py-2">
                            <div className="card text-white bg-info h-100">
                                <div className="card-body bg-info">
                                    <div className="rotate">
                                        <i className="fas fa-file-excel fa-4x"></i>
                                    </div>
                                    <h6 className="text"><b>Email Report!</b></h6>
                                    <p className="font-weight-bold text-md" >Employee ID <label className="ml-1 mt-1"><input  placeholder="Enter Email Address" type="text" id="materialLoginFormEmail" value={this.state.email} onChange={this.changeEmailHandler.bind(this)} className="form-control" /></label>
                                    
                                        <button type="submit" className=" ml-2 btn-sm btn-dark font-weight-bolder" onClick={this.formSubmitHandler.bind(this)}>Send Email</button></p>
                                    {/* <p className="font-weight-bold text-md"> Employee ID &nbsp; <input type="text" className="form-control-sm" placeholder="Enter Email Address" /> */}
                                    {/* <a href="#" className="btn btn-dark py-2 text-capitalize ">Send Email</a></p> */}
                                </div>
                            </div>
                        </div>

                        <div className="col-xl-6 col-sm-6 py-2">
                            <div className="card text-black bg-warning h-100">
                                <div className="card-body ">
                                    <div className="rotate">
                                        <i className="fa fa-lightbulb fa-4x"></i>
                                    </div>
                                    <h6 className="text"><b>Future Implementation!</b></h6>
                                    <p className="font-weight-bold small text-muted">This Placeholder can be use for adding any other actions in  future.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div className=" py-2 text-white mb-2" style={{ backgroundColor: '#0000b3' }}> <h5 className="font-weight-bolder ml-3" >Reports</h5></div>
                    <div className="btn btn-danger text-white float-right py-1" onClick={this.handlerClickCleanFiltered.bind(this)} > <i className="fa fa-times"></i>&nbsp;clear filters</div>
                    <BootstrapTable data={this.state.data} striped hover height='500px' options={options} pagination exportCSV csvFileName='eventReport.csv'>
                        <TableHeaderColumn width='150' ref='clear2' dataField='event_id' isKey filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>Event ID</TableHeaderColumn>
                        <TableHeaderColumn width='120' ref='clear3' dataField='event_date' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>Month</TableHeaderColumn>
                        <TableHeaderColumn width='200' ref='clear4' dataField='base_location' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>BaseLocation</TableHeaderColumn>
                        <TableHeaderColumn width='200' ref='clear5' dataField='council_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>CouncilName</TableHeaderColumn>
                        <TableHeaderColumn ref='clear6' dataField='beneficiary_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>BeneficiaryName</TableHeaderColumn>

                    </BootstrapTable>

                </div>

            </div >
        )

    }
}
export default Report