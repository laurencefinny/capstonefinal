import React from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table-plus';
import Axios from 'axios';

class UserDashBoard extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      data: [],
      dataUnReg: [],
    }

  }

  componentDidMount = () => {
    Axios.get('http://localhost:8060/event-report-service/volunteer/reg/' + localStorage.getItem("userEmpId"), { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

      if (res.status == '200') {
        localStorage.setItem("Reg", true);
        this.eventTableHandler(res.data)

      }
    }).catch(function (error) {
      console.log("error:", error);
    });

    Axios.get('http://localhost:8060/event-report-service/volunteer/unreg/' + localStorage.getItem("userEmpId"), { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

      if (res.status == '200') {
        console.log(res.status)
        this.eventTableHandlerUnReg(res.data)
        localStorage.setItem("unReg", true);

      }
    }).catch(function (error) {
      console.log("error:", error);
    });
  }

  eventDetailsHandler = (eventId) => {

    this.props.history.push({ pathname: '/attempted/' + eventId })


  }

  eventDetailsHandlerUnReg = (eventId) => {

    this.props.history.push({ pathname: '/not-attempted/' + eventId })


  }

  eventDetailsHandlerUnReggg = (eventId) => {

    this.props.history.push({ pathname: '/unreg/' + eventId })


  }





  eventTableHandler = (eventdata) => {
    eventdata.filter((event) => {

      this.state.data.push(event.body)


    })
    this.setState({ data: this.state.data })

  }

  eventTableHandlerUnReg = (eventdata) => {
    eventdata.filter((event) => {

      this.state.dataUnReg.push(event.body)


    })
    this.setState({ dataUnReg: this.state.dataUnReg })
    // localStorage.setItem("status",this.state.dataUnReg[0].status)
    // sessionStorage.setItem({ dataUnReg: this.state.dataUnReg.status })
  }


  buttonFormatter = (cell, row) => {

    console.log("roww", row);
    return <div>
      <button className="py-1 w-90 small font-weight-bold text-white" style={{ backgroundColor: '#0000b3' }} onClick={() => this.eventDetailsHandler(row.event_id)}> <i className="fa fa-handshake"></i>&nbsp;FeedBack</button>
    </div>
  }

  buttonFormatterUnReg = (cell, row) => {

    console.log("roww", row);
    return <div>
      <button className="py-1 w-90 small font-weight-bold text-white" style={{ backgroundColor: '#0000b3' }} onClick={() => this.eventDetailsHandlerUnReg(row.event_id)}> <i className="fa fa-handshake"></i>&nbsp;FeedBack</button>
    </div>
  }

  buttonFormatterUnReggg = (cell, row) => {

    console.log("roww", row);
    return <div>
      <button className="py-1 w-90 small font-weight-bold text-white" style={{ backgroundColor: '#0000b3' }} onClick={() => this.eventDetailsHandlerUnReggg(row.event_id)}> <i className="fa fa-handshake"></i>&nbsp;FeedBack</button>
    </div>
  }


  handlerClickCleanFiltered() {
    this.refs.clear1.cleanFiltered();
    this.refs.clear2.cleanFiltered();
    this.refs.clear3.cleanFiltered();
    this.refs.clear4.cleanFiltered();
    this.refs.clear5.cleanFiltered();
    this.refs.clear6.cleanFiltered();
  }

  render() {
    console.log("event propss", this.props);
    console.log(localStorage.getItem("status"));
    const options = {
      prePage: 'Prev',
      nextPage: 'Next',
      firstPage: 'First',
      lastPage: 'Last',
      hideSizePerPage: true,

    };
    if (localStorage.getItem("status") == null) {
      return (

        <div className="container-fluid" >

          <div >
            <div className="py-2 text-white" style={{ backgroundColor: '#0000b3' }}> <h5 className="font-weight-bold ml-3">Actions</h5></div>

            <div className="row mb-3 mt-2">

              {/* <div className="col-xl-6 col-sm-6 py-2">
                <div className="card text-white bg-info h-100">
                  <div className="card-body bg-info">
                    <div className="rotate">
                      <i className="fa fa-envelope fa-4x"></i>
                    </div>
                    <h6 className="text"><b>Email Reminder!</b></h6>
                    <p className="font-weight-bold small">Sit back and relax while app  send emails!!
                              <a href="#" onClick={this.sendEmail} className="btn btn-dark py-2 text-capitalize ">Send Email</a></p>
                  </div>
                </div>
              </div> */}

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
            <div className="py-2 text-white mb-2" style={{ backgroundColor: '#0000b3' }}> <h5 className="font-weight-bold ml-3">Events</h5></div>
            <div className="btn btn-danger text-white float-right py-1" onClick={this.handlerClickCleanFiltered.bind(this)} > <i className="fa fa-times"></i>&nbsp;clear filters</div>
            <BootstrapTable data={this.state.data} striped hover height='500px' options={options} pagination exportCSV csvFileName='event.csv'>
              <TableHeaderColumn width='120' export={false} ref='clear1' dataField='button' dataFormat={this.buttonFormatter.bind(this)} >Action</TableHeaderColumn>
              <TableHeaderColumn width='150' ref='clear2' dataField='event_id' isKey filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>Event ID</TableHeaderColumn>
              <TableHeaderColumn width='150' ref='clear3' dataField='event_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>Event Name</TableHeaderColumn>
              <TableHeaderColumn width='200' ref='clear4' dataField='base_location' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>BaseLocation</TableHeaderColumn>
              {/* <TableHeaderColumn width='200' ref='clear5' dataField='council_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>CouncilName</TableHeaderColumn> */}
              <TableHeaderColumn width='200' ref='clear6' dataField='beneficiary_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>BeneficiaryName</TableHeaderColumn>

            </BootstrapTable>

          </div>

        </div >
      )
    }
    else if (localStorage.getItem("status")=="NotAttempt") {
      return (

        <div className="container-fluid" >

          <div >
            <div className="py-2 text-white" style={{ backgroundColor: '#0000b3' }}> <h5 className="font-weight-bold ml-3">Actions</h5></div>

            <div className="row mb-3 mt-2">

              {/* <div className="col-xl-6 col-sm-6 py-2">
                <div className="card text-white bg-info h-100">
                  <div className="card-body bg-info">
                    <div className="rotate">
                      <i className="fa fa-envelope fa-4x"></i>
                    </div>
                    <h6 className="text"><b>Email Reminder!</b></h6>
                    <p className="font-weight-bold small">Sit back and relax while app  send emails!!
                              <a href="#" onClick={this.sendEmail} className="btn btn-dark py-2 text-capitalize ">Send Email</a></p>
                  </div>
                </div>
              </div> */}

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
            <div className="py-2 text-white mb-2" style={{ backgroundColor: '#0000b3' }}> <h5 className="font-weight-bold ml-3">Events</h5></div>
            <div className="btn btn-danger text-white float-right py-1" onClick={this.handlerClickCleanFiltered.bind(this)} > <i className="fa fa-times"></i>&nbsp;clear filters</div>
            <BootstrapTable data={this.state.dataUnReg} striped hover height='500px' options={options} pagination exportCSV csvFileName='event.csv'>
              <TableHeaderColumn width='120' export={false} ref='clear1' dataField='button' dataFormat={this.buttonFormatterUnReg.bind(this)} >Action</TableHeaderColumn>
              <TableHeaderColumn width='150' ref='clear2' dataField='event_id' isKey filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>Event ID</TableHeaderColumn>
              <TableHeaderColumn width='150' ref='clear3' dataField='event_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>Event Name</TableHeaderColumn>
              <TableHeaderColumn width='200' ref='clear4' dataField='base_location' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>BaseLocation</TableHeaderColumn>
              {/* <TableHeaderColumn width='200' ref='clear5' dataField='council_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>CouncilName</TableHeaderColumn> */}
              <TableHeaderColumn width='200' ref='clear6' dataField='beneficiary_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>BeneficiaryName</TableHeaderColumn>

            </BootstrapTable>

          </div>

        </div >
      )
    }
    else if(localStorage.getItem("status")=="UnReg"){
      return (

        <div className="container-fluid" >

          <div >
            <div className="py-2 text-white" style={{ backgroundColor: '#0000b3' }}> <h5 className="font-weight-bold ml-3">Actions</h5></div>

            <div className="row mb-3 mt-2">

              {/* <div className="col-xl-6 col-sm-6 py-2">
                <div className="card text-white bg-info h-100">
                  <div className="card-body bg-info">
                    <div className="rotate">
                      <i className="fa fa-envelope fa-4x"></i>
                    </div>
                    <h6 className="text"><b>Email Reminder!</b></h6>
                    <p className="font-weight-bold small">Sit back and relax while app  send emails!!
                              <a href="#" onClick={this.sendEmail} className="btn btn-dark py-2 text-capitalize ">Send Email</a></p>
                  </div>
                </div>
              </div> */}

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
            <div className="py-2 text-white mb-2" style={{ backgroundColor: '#0000b3' }}> <h5 className="font-weight-bold ml-3">Events</h5></div>
            <div className="btn btn-danger text-white float-right py-1" onClick={this.handlerClickCleanFiltered.bind(this)} > <i className="fa fa-times"></i>&nbsp;clear filters</div>
            <BootstrapTable data={this.state.dataUnReg} striped hover height='500px' options={options} pagination exportCSV csvFileName='event.csv'>
              <TableHeaderColumn width='120' export={false} ref='clear1' dataField='button' dataFormat={this.buttonFormatterUnReggg.bind(this)} >Action</TableHeaderColumn>
              <TableHeaderColumn width='150' ref='clear2' dataField='event_id' isKey filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>Event ID</TableHeaderColumn>
              <TableHeaderColumn width='150' ref='clear3' dataField='event_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>Event Name</TableHeaderColumn>
              <TableHeaderColumn width='200' ref='clear4' dataField='base_location' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>BaseLocation</TableHeaderColumn>
              {/* <TableHeaderColumn width='200' ref='clear5' dataField='council_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>CouncilName</TableHeaderColumn> */}
              <TableHeaderColumn width='200' ref='clear6' dataField='beneficiary_name' filter={{ type: 'TextFilter', delay: 1000, placeholder: 'Filter column...' }}>BeneficiaryName</TableHeaderColumn>

            </BootstrapTable>

          </div>

        </div >
      )
    }
    else{
      return null;
    }


  }
}
export default UserDashBoard