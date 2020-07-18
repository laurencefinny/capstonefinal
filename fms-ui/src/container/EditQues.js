import React from 'react';
import Axios from 'axios';
import history from '../redux/history';

class EditQues extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            question: [{
                id: '',
                question: '',
                fb_type: ''

            }],
            ans: [{
                id: '',
                answers: '',
                questions_id: this.props.match.params.id
            }],
            answers: [],
            delAns: [],




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


        const newAns = this.state.ans.filter((e, i) => {
            if (i > this.state.answers.length - 1)
                return e
        })

        if (window.confirm('Are you sure you want to Save?')) {
            console.log(this.state)
            Axios.put('http://localhost:8060/feedback-service/Edit-question/' + this.state.question.id, this.state.question, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } })
                .then((response) => {
                    console.log(response);
                }, (error) => {
                    console.log(error);
                });


            Axios.put('http://localhost:8060/feedback-service/Edit-answers/', this.state.answers, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } })
                .then((response) => {
                    console.log(response);
                }, (error) => {
                    console.log(error);
                });
            Axios.post('http://localhost:8060/feedback-service/Edit-addAnswers/', newAns, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } })
                .then((response) => {
                    console.log(response);
                }, (error) => {
                    console.log(error);
                });

            Axios.delete('http://localhost:8060/feedback-service/deleteAllAnswers/', { data: this.state.delAns , headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } })
                .then((response) => {
                    console.log(response);
                }, (error) => {
                    console.log(error);
                });
            // history.push('/questions/' )
        }
    }

    addAnswer = () => {

        this.state.ans.push({ 'answers': "", questions_id: this.state.question.id })
        this.setState({ ans: this.state.ans })

    }

    removeAnswer = (e) => {

        this.state.delAns.push(this.state.ans[e.target.id])
        this.setState({ delAns: this.state.delAns })
        this.state.ans.splice(e.target.id, 1)
        this.setState({ ans: this.state.ans })

    }

    cancelOrDelete = (e) => {
        console.log()
        if (e.target.id === "cancel") {
            if (window.confirm('Are you sure you want to Cancel?')) {
                history.push('/questions/')

            }
        } else {
            if (window.confirm('Are you sure you want to Delete?')) {


                Axios.delete('http://localhost:8060/feedback-service/deleteQuestion/' + this.state.question.id, {
                    data: this.state.question
                ,  headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}`}  })
                    .then((response) => {
                        console.log(response);
                    }, (error) => {
                        console.log(error);
                    });
                Axios.delete('http://localhost:8060/feedback-service/deleteAllAnswers/', { data: this.state.ans ,  headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` }} )
                    .then((response) => {
                        console.log(response);
                    }, (error) => {
                        console.log(error);
                    });
                history.push('/questions/')
            }
        }


    }

    componentDidMount = () => {

        let queId = this.props.match.params.id;
        let ansId;
        Axios.get('http://localhost:8060/feedback-service/Edit-question/' + queId, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

            if (res.status == '200') {
                this.setState({ question: res.data })
                this.ansId = this.state.question.id;


                Axios.get('http://localhost:8060/feedback-service/answers/' + this.ansId, { headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${localStorage.getItem("token")}` } }).then(res => {

                    if (res.status == '200') {
                        this.ansTableHandler(res.data)
                    }
                }).catch(function (error) {
                   
                    history.push('/questions/')
                });

            }
        }).catch(function (error) {
           
            history.push('/questions/')
        });



    }

    ansTableHandler = (ansData, i) => {
        this.state.ans.splice(0, 1)
        ansData.filter((ans) => {
            if (ans) {
                this.state.answers.push(ans.body)
                this.state.ans.push(ans.body)
            } else {
                history.push('/questions/')
            }
        })


        this.setState({ answers: this.state.answers })

        this.setState({ ans: this.state.ans })

    }
    render() {


        if (localStorage.getItem("role") == "Admin") {
            return (
                <form onSubmit={this.handleSubmit} >
                    <div class="container-fluid" style={{paddingTop:"25px",paddingBottom:"25px"}}>
                        <div class="py-3 text-white mb-3" style={{ backgroundColor: '#0000b3' }}> <h6 class="font-weight-bolder ml-3">Edit Question:&nbsp; &nbsp;{this.state.question.question}</h6></div>
                        <div class="mt-4">
                            <div class="form-group row ml-1">
                                <div class="col-xl-2 col-md-4 mb-3 mr-1" > <h5>Feedback Type</h5></div>
                                <div class="custom-control custom-radio ml-2  col-xl-2 col-md-4 mb-3">
                                    <input type="radio" id="customRadioInline1" name="fb_type" value="participated" onChange={this.handleChange} checked={this.state.question.fb_type === "participated"} class="custom-control-input" />
                                    <label class="custom-control-label" for="customRadioInline1">Participated</label>
                                </div>
                                <div class="custom-control custom-radio ml-2  col-xl-2 col-md-4 mb-3">
                                    <input type="radio" id="customRadioInline2" name="fb_type" onChange={this.handleChange} value="notparticipated" checked={this.state.question.fb_type === "notparticipated"} class="custom-control-input" />
                                    <label class="custom-control-label" for="customRadioInline2">Not participated</label>
                                </div>
                                <div class="custom-control custom-radio ml-2 col-xl-2 col-md-4 mb-3">
                                    <input type="radio" id="customRadioInline3" class="custom-control-input" onChange={this.handleChange} name="fb_type" value="unregistered" checked={this.state.question.fb_type === "unregistered"} />
                                    <label class="custom-control-label" for="customRadioInline3">Unregistered</label>
                                </div>
                            </div>
                            <div class="ml-2">
                                <div class="custom-control custom-checkbox mt-1  ml-1">
                                    <input type="checkbox" class="custom-control-input" id="customCheck1" />
                                    <label class="custom-control-label" for="customCheck1">Allow Multiple Answers</label>
                                </div>
                                <div class="custom-control custom-checkbox mt-2  ml-1">
                                    <input type="checkbox" class="custom-control-input" id="customCheck2" />
                                    <label class="custom-control-label" for="customCheck2">Free text Answers</label>
                                </div>
                                <div class="custom-control custom-checkbox mt-2 mb-3 ml-1">
                                    <input type="checkbox" class="custom-control-input" id="customCheck3" />
                                    <label class="custom-control-label" for="customCheck3">Custom Question</label>
                                </div>
                            </div>

                            <div class="form-group row ">
                                <label for="inputQue" class="col-xl-2 col-md-4 mb-2 ml-3"><h5>Question</h5></label>
                                <div class="col-xl-8 col-md-4 col-sm-6 ml-2">
                                    <input type="text" class="form-control bg-light mr-1" id="inputQue" value={this.state.question.question} onChange={this.handleChange} name="question" placeholder="Description (required)" />
                                </div>
                            </div>

                            {this.state.ans.map((ans, i) => {



                                return (<div class="form-group row ">
                                    <label for="inputQue" class="col-xl-2 col-md-4 mb-2 ml-3" ><h6>Answers {i + 1}</h6></label>
                                    <div class="col-xl-8 col-md-4 col-sm-6 ml-2">
                                        <input type="text" class="form-control bg-light mr-1 mb-2" key={i} id={i} name="answers" onChange={this.ansHandleChange} key={i} value={ans.answers} placeholder="Description  (required)" />
                                    </div>
                                    <div class=" ml-4">
                                        <button type="button" class="form-control small bg-danger text-white py-1" id={i} onClick={this.removeAnswer} ><i className="fa fa-times"></i>&nbsp;DELETE</button>
                                    </div>
                                </div>)

                            }
                            )}
                            <div class="form-group row ml-1">
                                <button type="button" class=" ml-2 py-1 btn-lg btn btn-success" id="inputQue" onClick={this.addAnswer} ><div ><h7 class="font-weight-bolder ">ADD</h7>&nbsp;<h7 class="font-weight-bolder">ANSWER</h7></div> </button>
                            </div>
                            <div class="row">
                                <label for="inputQue" class="col-xl-2 col-md-4"></label>
                                <div class="col-xl-8 col-md-4 col-sm-6 ml-2 ">
                                    <button type="submit" value="save" class="py-2 w  ml-2 btn btn-primary btn-lg font-weight-bolder">SAVE</button>
                                    <button type="button" class="py-2 ml-2 btn btn-dark font-weight-bolder btn-lg" id={"cancel"} onClick={this.cancelOrDelete}  >CANCEL</button>
                                    <button type="button" class="py-2 ml-2 btn btn-danger font-weight-bolder btn-lg" id={"delete"} onClick={this.cancelOrDelete}>DELETE</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </form>



            )
        } else {
            return (
                <div class="container">
                    <div className="alert alert-danger  alert-dismissible  col-lg-9 col-md-9 col-sm-12 ">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong><i class="fa fa-exclamation-triangle mr-1"></i>Warning!</strong> <text> Only Admin have the privileges to access this site</text>
                    </div>
                </div>
            )
        }
    }
}
export default EditQues;