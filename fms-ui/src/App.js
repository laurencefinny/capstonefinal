import React from 'react';
import './App.css';
import { Router } from 'react-router-dom';
import { Route, Switch } from 'react-router';
import Login from './container/Login';
import DashBoard from './container/DashBoard';
import Event from './container/Event';
import Report from './container/Report';
import EventDetails from './component/EventDetails';
import Role from './container/Role';
import feedBackQues from './container/FeedBackQues';
import AddQues from './container/AddQues';
import EditQues from './container/EditQues';
import Header from './component/Header';
import Footer from './component/Footer';
import history from './redux/history';
import Attempted from './container/Attempted';
import NotAttempted from './container/NotAttempted';
import UnRegistered from './container/UnRegistered';
import Thanks from './container/thanks';
import Forget from './container/ForgetPassword';
import Reset from './container/ResetPassword';
import UserDashBoard from './container/userDashboard';
function App() {

  return (

    <Router history={history} >

      {
        window.location.pathname === '/' ? '' : <Header />
      }
      <Switch>
        <Route path="/userDashboard" component={UserDashBoard} />
        <Route path="/reset" component={Reset} />
        <Route path="/forget" component={Forget} />
        <Route path="/dashboard" component={DashBoard} />
        <Route path="/event" component={Event} />
        <Route path="/report" component={Report} />
        <Route path="/details/:id" component={EventDetails} />
        <Route path="/role" component={Role} />
        <Route path="/questions" component={feedBackQues} />
        <Route path="/unreg/:id" component={UnRegistered} />
        <Route path="/thanks" component={Thanks} />
        <Route path="/add-question/" component={AddQues} />
        <Route path="/edit-question/:id" component={EditQues} />
        <Route path="/attempted/:id" component={Attempted} />
        <Route path="/not-attempted/:id" component={NotAttempted} />
      </Switch>
      {
        window.location.pathname === '/' ? '' : <Footer />
      }
      <Route exact path="/" component={Login} />
    </Router>
  );
}

export default App;
