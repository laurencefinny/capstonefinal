import React from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table-plus';
import Axios from 'axios';
class feedBackQues extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],

        }
    }

    buttonFormatter = (cell, row) => {

        console.log("roww", row);
        return <div>
            <button className="py-1 w-25 small font-weight-bold text-white" style={{ backgroundColor: '#0000b3' }} onClick={() => this.editQuestionHandler(row.id)}> <i className="fa fa-edit"></i>&nbsp;EDIT</button>
        </div>
    }
    addQuestionHandler = () => {
        this.props.history.push({ pathname: '/add-question/' })
    }
    editQuestionHandler = (id) => {
        this.props.history.push({ pathname: '/edit-question/' + id })
    }

    componentDidMount = () => {



        Axios.get('http://localhost:8060/feedback-service/FeedbackQuestions', { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {
            console.log("res val:", res);
            console.log("res:", res.data);
            if (res.status == '200') {
                this.ansTableHandler(res.data)

            }
        }).catch(function (error) {
            console.log("error:", error);
        });

    }
    ansTableHandler = (quesdata) => {
        quesdata.filter((ques) => {
            console.log("val:", ques.body);
            console.log("question:", ques.body.question);
            this.state.data.push(ques.body)

            return (
                ques

            )
        })
        this.setState({ data: this.state.data })
        console.log("data", this.state.data);

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
            <div className="container-fluid" style={{paddingTop:"25px"}}>
                <div style={{paddingBottom:"25px"}}>
                    <div className="py-2 text-white" style={{ backgroundColor: '#0000b3',paddingTop:"25px" }}> <h6 className="font-weight-bolder ml-3">FEEDBACK QUESTIONS</h6></div>
                    <div className="text-right"><button className="bg-success text-white  mt-1 mb-1 py-1 text-right " onClick={this.addQuestionHandler.bind(this)}>+ Add Question</button></div>
                    <div className="add-pmo">
                        <BootstrapTable data={this.state.data} striped hover options={options} pagination>
                            <TableHeaderColumn width='550' dataField='question' className="event-table-th">Question</TableHeaderColumn>
                            <TableHeaderColumn width='150'dataField='tot_ans' isKey className="event-table-th">Total Answer</TableHeaderColumn>
                            <TableHeaderColumn width='150' dataField='fb_type' className="event-table-th">Feedback Type</TableHeaderColumn>
                            <TableHeaderColumn dataField='button' width='220' dataFormat={this.buttonFormatter.bind(this)} className="event-table-th">Action</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        )
    }else {
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

export default feedBackQues;