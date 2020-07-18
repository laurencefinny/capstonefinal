import React from 'react';
import Axios from 'axios';
import './DashBoard.css';

class AddQues extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            question: [{
                id: '',
                question: '',
                fb_type: ''

            }],
            questions_id: '',
            ans: [],





        }



        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.ansHandleChange = this.ansHandleChange.bind(this);
        this.addAnswer = this.addAnswer.bind(this)
        this.removeAnswer = this.removeAnswer.bind(this)

    }


    handleChange(event) {
        const target = event.target;
        const value = target.type === "checkbox" ? target.checked : target.value;
        const name = target.name;

        this.setState(prevState => ({
            question: {
                ...prevState.question,
                [name]: value
            },


        }));


    }
    ansHandleChange(event) {
        const ans = this.state.ans;

        ans[event.target.id].answers = event.target.value;

        this.setState({ ans: ans })

    }
    handleSubmit(event) {

        event.preventDefault();


        Axios.put('http://localhost:8060/feedback-service/Edit-question/' + this.state.questions_id, this.state.question, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } })
            .then((response) => {
                console.log(response);
            }, (error) => {
                console.log(error);
            });

        let obj = [...this.state.ans]

        if (obj.length <= 0) {
            obj = [{ id: '', answers: '', questions_id: this.state.questions_id }]
        }
        console.log(obj)
        Axios.put('http://localhost:8060/feedback-service/Edit-answers/', obj, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } })
            .then((response) => {
                console.log(response);
            }, (error) => {
                console.log(error);
            });
        if (window.confirm('Are you sure you want to Save?')) {
            //this.props.history.push({ pathname: '/questions/' })


        }
    }

    addAnswer = () => {

        this.state.ans.push({ 'answers': "", questions_id: this.state.questions_id })
        this.setState({ ans: this.state.ans })

    }

    removeAnswer = (e) => {

        this.state.ans.splice(e.target.id, 1)
        this.setState({ ans: this.state.ans })

    }

    cancelOrDelete = (e) => {
        console.log()
        if (e.target.id === "cancel") {
            if (window.confirm('Are you sure you want to Cancel?')) {
                this.props.history.push({ pathname: '/questions/' })
            }
        } else {
            if (window.confirm('Are you sure you want to Delete?')) {

                this.props.history.push({ pathname: '/questions/' })
            }
        }


    }

    componentDidMount = () => {


        Axios.get('http://localhost:8060/feedback-service/all-questions/',  { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

            if (res.status == '200') {
                this.state.question = res.data
                console.log(this.state.question[this.state.question.length - 1].body.id)
                this.state.questions_id = this.state.question[this.state.question.length - 1].body.id + 1;


            }
        }).catch(function (error) {
            console.log("error:", error);
        });




    }
    render() {


        if (localStorage.getItem("role") == "Admin") {
            return (
                <form onSubmit={this.handleSubmit} >
                    <div className="container-fluid" style={{paddingTop:"25px"}}>
                        <div className="py-2 text-white mb-2" style={{ backgroundColor: '#0000b3' }}> <h6 className="font-weight-bolder ml-3">Add Question:&nbsp;<span className="font-weight-bold small">{this.state.question.question} </span> </h6></div>
                        <div className="mt-4">
                            <div className="form-group row ml-1">
                                <div className="col-xl-2 col-md-4 mb-3 mr-1" > <h5>Feedback Type</h5></div>
                                <div className="custom-control custom-radio ml-2  col-xl-2 col-md-4 mb-3">
                                    <input type="radio" id="customRadioInline1" required name="fb_type" value="participated" onChange={this.handleChange} checked={this.state.question.fb_type === "participated"} className="custom-control-input" />
                                    <label className="custom-control-label" for="customRadioInline1">Participated</label>
                                </div>
                                <div className="custom-control custom-radio ml-2  col-xl-2 col-md-4 mb-3">
                                    <input type="radio" id="customRadioInline2" required name="fb_type" onChange={this.handleChange} value="notparticipated" checked={this.state.question.fb_type === "notparticipated"} className="custom-control-input" />
                                    <label className="custom-control-label" for="customRadioInline2">Not participated</label>
                                </div>
                                <div className="custom-control custom-radio ml-2 col-xl-2 col-md-4 mb-3">
                                    <input type="radio" id="customRadioInline3" required className="custom-control-input" onChange={this.handleChange} name="fb_type" value="unregistered" checked={this.state.question.fb_type === "unregistered"} />
                                    <label className="custom-control-label" for="customRadioInline3">Unregistered</label>
                                </div>
                            </div>
                            <div className="ml-2">
                                <div className="custom-control custom-checkbox mt-1  ml-1">
                                    <input type="checkbox" className="custom-control-input" id="customCheck1" />
                                    <label className="custom-control-label" for="customCheck1">Allow Multiple Answers</label>
                                </div>
                                <div className="custom-control custom-checkbox mt-2  ml-1">
                                    <input type="checkbox" className="custom-control-input" id="customCheck2" />
                                    <label className="custom-control-label" for="customCheck2">Free text Answers</label>
                                </div>
                                <div className="custom-control custom-checkbox mt-2 mb-3 ml-1">
                                    <input type="checkbox" className="custom-control-input" id="customCheck3" />
                                    <label className="custom-control-label" for="customCheck3">Custom Question</label>
                                </div>
                            </div>

                            <div className="form-group row ">
                                <label for="inputQue" className="col-xl-2 col-md-4 mb-2 ml-3"><h5>Question</h5></label>
                                <div className="col-xl-8 col-md-4 col-sm-6 ml-2">
                                    <input type="text" className="form-control bg-light mr-1" required id="inputQue" value={this.state.question.question} onChange={this.handleChange} name="question" placeholder="Description (required)" />
                                </div>
                            </div>

                            {this.state.ans.map((ans, i) => {


                                return (<div className="form-group row ">
                                    <label for="inputQue" className="col-xl-2 col-md-4 mb-2 ml-3" ><h6>Answers {i + 1}</h6></label>
                                    <div className="col-xl-8 col-md-4 col-sm-6 ml-2">
                                        <input type="text" className="form-control bg-light mr-1 mb-2" key={i} id={i} name="answers" onChange={this.ansHandleChange} key={i} value={ans.answers} placeholder="Description  (required)" />
                                    </div>
                                    <div className=" ml-4">
                                        <button type="button" className="form-control small bg-danger text-white py-1" id={i} onClick={this.removeAnswer} ><i classNameName="fa fa-times"></i>&nbsp;DELETE</button>
                                    </div>
                                </div>)

                            }
                            )}
                            <div className="form-group row ml-1">
                                <button type="button" className=" ml-2 py-1  btn-lg btn btn-success" id="inputQue" onClick={this.addAnswer} ><div><h7 className="font-weight-bolder ">ADD</h7>&nbsp;<h7 className="font-weight-bolder">ANSWER</h7></div> </button>
                            </div>
                            <div className="row">
                                <label for="inputQue" className="col-xl-2 col-md-4"></label>
                                <div className="col-xl-8 col-md-4 col-sm-6 ml-2 ">
                                    <button type="submit" value="save" className="py-2 w  ml-2 btn-lg btn btn-primary font-weight-bolder">SAVE</button>
                                    <button type="button" className="py-2 ml-2 btn btn-dark btn-lg  font-weight-bolder" id={"cancel"} onClick={this.cancelOrDelete}  >CANCEL</button>
                                    <button type="button" className="py-2 ml-2 btn btn-danger btn-lg font-weight-bolder" id={"delete"} onClick={this.cancelOrDelete}>DELETE</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </form>

            )
        } else {
            return (
                <div className="container">
                    <div className="finny alert alert-danger  alert-dismissible  col-lg-9 col-md-9 col-sm-12 ">
                        <button type="button" className="close" data-dismiss="alert">&times;</button>
                        <strong><i className="fa fa-exclamation-triangle mr-1"></i>Warning!</strong> <text> Only Admin have the privileges to access this site</text>
                    </div>
                </div>
            )
        }
    }
}
export default AddQues;