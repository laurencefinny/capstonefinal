import React, { useEffect, useState } from 'react';
import Axios from 'axios';
import moment from 'moment'
import history from '../redux/history';

class Attempted extends React.Component {
    constructor(props) {
        super(props);

        this.state = {

            data:[],
            rating: 0,
            likes: '',
            dislike: ''
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
    }
 
    submitFeedback = () => {
       let a = this.state
       let fb = {id:'',rating: a.rating,likes: a.likes,dislike: a.dislike,event_event_id:this.props.match.params.id}
       console.log(fb)
        Axios.post('http://localhost:9041/email/participated',fb).then(res => {

            if (res.status == '200') {
                console.log(res.data)
                this.setState({ data: res.data })


            }
        }).catch(function (error) {
            console.log("error:", error);
        });
        history.push('/thanks')
    }
   
        
    
    rating = (e) => {
        this.setState({ rating: e.target.id })
    }
    likes = (e) => {
        this.setState({ likes: e.target.value })
    }
    dislikes = (e) => {
        this.setState({ dislike: e.target.value })
    }

    render() {
        return (
            <div class="container-fluid mt-4">
                <div class=" card-title py-3" > <h5 class="font-weight-bold text-success text-center ml-3">Feedback request for {this.state.data.event_name} on {moment(this.state.data.event_date).format('MMM D, YYYY')}</h5></div>
                <div className="row mt-2">
                    <div className="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div className="card">
                            <div className="card-header text-white" style={{ backgroundColor: '#0000b3' }}>
                                <span style={{ float: "left" }}>
                                    How do you rate the overall event?
                            </span>
                            </div>
                            <div className="card-body" onClick={this.rating} >
                                <h5 className="card-title mb-5" style={{ display: 'inline', padding: '3.25em' }}><i className='fas fa-angry ' id='1' style={{ fontSize: 75, color: 'red', cursor: 'pointer' }}></i></h5>
                                <h5 className="card-title mb-5" style={{ display: 'inline', padding: '3.25em' }}><i className='far fa-frown' id='2' style={{ fontSize: 75, color: 'orange', cursor: 'pointer' }}></i></h5>
                                <h5 className="card-title" style={{ display: 'inline', padding: '3.25em' }}><i className='far fa-meh' id='3' style={{ fontSize: 75, color: 'red', cursor: 'pointer' }}></i></h5>
                                <h5 className="card-title" style={{ display: 'inline', padding: '3.25em' }}><i className='far fa-grin' id='4' style={{ fontSize: 75, color: '#05b705', cursor: 'pointer' }}></i></h5>
                                <h5 className="card-title" style={{ display: 'inline', padding: '3.25em' }}><i className='far fa-grin-hearts' id='5' style={{ fontSize: 75, color: 'green', cursor: 'pointer' }}></i></h5>
                            </div>
                        </div>
                        <div className="card">
                            <div className="card-header text-white" style={{ backgroundColor: '#0000b3' }}>
                                <span style={{ float: "left" }}>
                                    What did you like about this volunteering activity?
                            </span>
                            </div>
                            <div className="card-body ">
                                <textarea onChange={this.likes} className="form-control " id="validationTextarea" placeholder="you're thoughts here" required></textarea>
                            </div>
                        </div>
                        <div className="card mb-3">
                            <div className="card-header text-white" style={{ backgroundColor: '#0000b3' }}>
                                <span style={{ float: "left" }}>
                                    What can be improved in this volunteering activity?
                            </span>
                            </div>
                            <div className="card-body mb-3"  >
                                <textarea onChange={this.dislikes} className="form-control" id="validationTextarea" placeholder="you're thoughts here" required></textarea>
                            </div>
                        </div>
                        <div className="modal-footer" style={{ borderTop: 0 }}>
                            <button type="button" className="btn btn-danger" style={{ float: 'right' }}>Reset</button>
                            <button type="button" className="btn btn-primary" style={{ float: 'right' }} onClick={this.submitFeedback} >Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

}

export default Attempted;
