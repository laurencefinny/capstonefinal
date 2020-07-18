import React, { useEffect, useState } from 'react';
import Axios from 'axios';
import moment from 'moment'
import history from '../redux/history';
import Button from 'react-bootstrap/Button'

class NotAttempted extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            questions: [],
            answers: [],
            data: [],
            com: '',
            status: "null"
        }

    }
    componentDidMount = () => {
        let eventId = this.props.match.params.id;
        Axios.get('http://localhost:9042/event/' + eventId).then(res => {

            if (res.status == '200') {
                console.log(res.data)
                this.setState({ data: res.data })

            }
        }).catch(function (error) {
            console.log("error:", error);
        });


        Axios.get('http://localhost:9041/email/que/notparticipated').then(res => {

            if (res.status == '200') {
                this.setState({ questions: res.data })
                console.log(res.data.id)
                Axios.get('http://localhost:9041/email/ans/' + res.data.id).then(res => {
                    console.log(this.ansId)
                    if (res.status == '200') {
                        this.ansTableHandler(res.data)
                    }
                }).catch(function (error) {

                });
            }

        }).catch(function (error) {
        });



    }
    comment = (e) => {
        this.setState({ com: e.target.id })
        console.log(e.target.id)
        this.state.answers.splice(e.target.id)
    }
    ansTableHandler = (ansData, i) => {

        ansData.filter((ans) => {
            if (ans) {

                this.state.answers.push(ans.body)
            }
        })

        this.setState({ answers: this.state.answers })


    }
    resetFeedback = () =>{
        this.componentDidMount();
    }

    submitFeedback = () => {
        let fb = { id: '', comment: this.state.com, event_event_id: this.state.data.event_id }
        console.log(fb)
        Axios.post('http://localhost:9041/email/notparticipated', fb).then(res => {

            if (res.status == '200') {
                console.log(res.data)
                this.setState({ data: res.data })

            }
        }).catch(function (error) {
            console.log("error:", error);
        });
        history.push('/thanks')
    }

    render() {
        return (
            <div className="container-fluid">
                <div className="container-fluid">
                    <div class=" card-title py-3" > <h5 class="font-weight-bold text-success text-center ml-2">Feedback request for {this.state.data.event_name} on {moment(this.state.data.event_date).format('MMM D, YYYY')}</h5></div>
                    {/* <div class=" card-title py-2" > <h5 class="font-weight-bold text-info text-center ml-2">You are not Participated in this event</h5></div> */}
                    <div class=" py-3 text-white" > <h6 class="font-weight-bold  text-center py-3" style={{ backgroundColor: '#0000b3' }}>{this.state.questions.question}</h6></div>
                    <div className="row">

                        <div className="card">

                            <div className="card-body">
                                <div className="row">
                                    {
                                        this.state.answers.map((feedbackAnswerIns, index) => (
                                            <div className="col-lg-3 col-sm-4 py-2" key={"feedbackAnswer_" + feedbackAnswerIns.id} >
                                                {/* <Button variant="outline-primary" onClick={this.comment} id={feedbackAnswerIns.answers} name={feedbackAnswerIns.id}>{feedbackAnswerIns.answers}</Button>{' '} */}
                                                <h3 class="btn bg-dark text-white py-3" onClick={this.comment} id={feedbackAnswerIns.answers}>{feedbackAnswerIns.answers}</h3>

                                            </div>
                                        ))
                                    }
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div style={{ paddingBottom:60}} >
                    <button type="button" className="btn btn-danger" style={{ float: 'right', width: 300, }} onClick={this.resetFeedback}>Reset</button>
                    <button type="button" className="btn btn-primary" style={{ float: 'right', width: 300, }} onClick={this.submitFeedback}>Submit</button>
                </div>
            </div>

        )
    }

}

export default NotAttempted;
